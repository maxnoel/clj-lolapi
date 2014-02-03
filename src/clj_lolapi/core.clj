(ns clj-lolapi.core
  (:require [environ.core :as env]
            [clj-http.client :as http]
            [clojure.string :as string]))

;; Define this in the RIOT_API_KEY environment variable
(def api-key (env/env :riot-api-key))

;; TODO Add PBE/prod servers
;; (function? env. variables?)
(def server "http://prod.api.pvp.net")

(def live-root (str server "/api/lol"))

;; All static data calls use sub-paths of /static-data
(def static-root (str server "/api/lol/static-data"))

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

(defn- live-endpoint
  "Return the full endpoint URL for the requested region and method,
  for a live query (static data should use static-endpoint instead).
  If the method requires extra parameters, just add them at the end,
  e.g. (live-endpoint \"na\" \"game/by-summoner\" [summoner-id \"recent\"])"
  [region method params]
  (let [version (get method-versions method "UNKNOWN")]
    (string/join "/" (into [live-root region version method] params))))

(defn- static-endpoint
  "Return the full endpoint URL for the requested region and method,
  for static data (live queries should use live-endpoint instead)
  If the method requires extra parameters, just add them at the end,
  e.g. (static-endpoint \"na\" \"champion\" [champion-id])"
  [region method params]
  ;; All static-data methods use v1.s
  (string/join "/" (into [static-root region "v1" method] params)))

(defn- query
  "Make a HTTP request to the Riot API."
  [endpoint-fn region method & params]
  (let [url (endpoint-fn region method params)
        ;; TODO Change to :as :json-strict once clj-http supports it.
        response (http/get url {:as :json
                                :query-params {"api_key" api-key}})]
   (:body response)))

(def live-query (partial query live-endpoint))

(def static-query (partial query static-endpoint))
