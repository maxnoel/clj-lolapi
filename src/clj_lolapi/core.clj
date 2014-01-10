(ns clj-lolapi.core
  (:require [environ.core :as env]
            [clj-http.client :as http]
            [clojure.string :as string]))

;; Define this in the RIOT_API_KEY environment variable
(def api-key (env/env :riot-api-key))

;; TODO Add PBE/prod servers
;; (function? env. variables?)
(def server "http://prod.api.pvp.net")

;; Only support the latest version (at the time of this writing) of each method.
;; TODO Throw an error here on unknown method?
(def method-versions
  {"champion" "v1.1"
   "game" "v1.2"
   "league" "v2.2"
   "stats" "v1.2"
   "summoner" "v1.2"
   "team" "v2.2"})

;; All methods are not supported on all regions.
;; TODO Warn/error when attempting to access an unsupported method?
(def regions #{"na" "euw" "eune" "br" "tr"})

(defn- endpoint
  "Return the full endpoint URL for the requested region and method.
  If the method requires extra parameters, just add them at the end,
  e.g. (endpoint \"na\" \"game/by-summoner\" [summoner-id \"recent\"])"
  [region method params]
  (let [version (get method-versions method "UNKNOWN")]
    (string/join "/" (into [server "api/lol" region version method] params))))

(defn- query
  "Make a HTTP request to the Riot API."
  [region method & params]
  (let [url (endpoint region method params)
        ;; TODO Change to :as :jsons-strict once clj-http supports it.
        response (http/get url {:as :json
                                :query-params {"api_key" api-key}})]
   (:body response)))

(defn champions
  "Retrieve the data for all League of Legends champions."
  [region]
  (:champions (query region "champion")))

(defn games
  "Retrieve recent games for the target summoner."
  [region summoner-id]
  (:games (query region "game" "by-summoner" summoner-id "recent")))

(defn leagues
  "Retrieve league data for the target summoner.
  Returns an id -> league map with one entry for solo queue (key = summoner ID)
  and one for each ranked team queue that the summoner has a team in
  (key = team ID)."
  [region summoner-id]
  (query region "league" "by-summoner" summoner-id))

(defn stats
  "Retrieves player stats summaries by summoner ID.

  TODO Implement season filtering."
  [region summoner-id]
  (:playerStatSummaries (query region "stats" "by-summoner" summoner-id "summary")))

(defn ranked-stats
  "Retrieve per-champion ranked stats by summoner ID.

  TODO Implement season filtering."
  [region summoner-id]
  (:champions (query region "stats" "by-summoner" summoner-id "ranked")))

(defn summoner
  "Retrieve the target summoner (by ID)."
  [region summoner-id]
  (query region "summoner" summoner-id))

(defn summoner-by-name
  "Retrieve the target summoner (by name)."
  [region summoner-name]
  (query region "summoner" "by-name" summoner-name))

(defn summoner-masteries
  "Retrieve the mastery pages for the target summoner."
  [region summoner-id]
  (:pages (query region "summoner" summoner-id "masteries")))

(defn summoner-runes
  "Retrieve the rune pages for the target summoner."
  [region summoner-id]
  (:pages (query region "summoner" summoner-id "runes")))

(defn summoner-names
  "Retrieve the names used by the target summoners (yes, summoner-ids is a list)."
  [region summoner-ids]
  (let [joined-ids (clojure.string/join "," summoner-ids)]
    (:summoners (query region "summoner" joined-ids "name"))))

(defn teams
  "Retrieve the teams the target summoner is part of."
  [region summoner-id]
  ;; clj-http (Cheshire) deserializes that as a list instead of a vector.
  ;; TODO Remove once :as :json-strict is supported.
  (vec (query region "team" "by-summoner" summoner-id)))
