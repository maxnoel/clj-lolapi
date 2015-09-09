(ns clj-lolapi.core
  (:require [clj-lolapi.query :as query]))

;; All methods are not supported on all regions.
;; TODO Warn/error when attempting to access an unsupported method?
(def regions #{"br" "eune" "euw" "kr" "lan" "las" "na" "oce" "ru" "tr"})

(def observer-platform-ids #{"NA1" "BR1" "LA1" "LA2" "OC1" "EUN1" "TR1" "RU" "EUW1" "KR"})

;;
(defn if-region-valid
  [region-set region sexp]
  (if (contains? region-set region)
    sexp
  (throw (Exception. (str "Invalid region: " region ". Valid regions are: " region-set)))))

(defn champion-status
  "Retrieve the live data for all League of Legends champions.

  This will only give you the basic 4 ratings, whether or not the champion is
  enabled, and whether the champion is currently free to play.

  For more detailed information (e.g. hard numbers),
  use clj-lolapi.static-data/champion"
  [region]
  (if-region-valid regions region
    (:champions (query/live region ["champion"]))))


(defn games
  "Retrieve recent games for the target summoner."
  [region summoner-id]
  (if-region-valid regions region
    (:games (query/live region ["game" "by-summoner" summoner-id "recent"]))))


(defn leagues
  "Retrieve league data for the target summoner.
  Returns an id -> league map with one entry for solo queue (key = summoner ID)
  and one for each ranked team queue that the summoner has a team in
  (key = team ID)."
  [region summoner-id]
  (if-region-valid regions region
    (query/live region ["league" "by-summoner" summoner-id])))

(defn stats
  "Retrieves player stats summaries by summoner ID.

  TODO Implement season filtering."
  [region summoner-id]

  (if-region-valid regions region
    (:playerStatSummaries (query/live region ["stats" "by-summoner" summoner-id "summary"]))))

(defn ranked-stats
  "Retrieve per-champion ranked stats by summoner ID.

  TODO Implement season filtering."
  [region summoner-id]
  (if-region-valid regions region
    (:champions (query/live region ["stats" "by-summoner" summoner-id "ranked"]))))

(defn match-history
  "Retrieve match history for a summoner"
  [region summoner-id]
  (if-region-valid regions region
                   (query/live region ["matchhistory" summoner-id])))

(defn match-list
  [region summoner-id]
  (if-region-valid regions region
                   (query/live region ["matchlist" "by-summoner" summoner-id])))

(defn match
  "Retrieve match history for a summoner"
  ([region match-id] (match region match-id {}))
  ([region match-id {:keys [timeline] :or {timeline false}}]
   (if-region-valid regions region
    (query/live region ["match" match-id] {"includeTimeline" timeline}))))


(defn summoner
  "Retrieve the target summoner (by ID)."
  [region summoner-id]
  (if-region-valid regions region
    (query/live region ["summoner" summoner-id])))

(defn summoner-by-name
  "Retrieve the target summoner (by name)."
  [region & summoner-names]
  (if-region-valid regions region
    (let [joined-names (clojure.string/join "," summoner-names)]
      (query/live region ["summoner" "by-name" joined-names]))))

(defn summoner-masteries
  "Retrieve the mastery pages for the target summoner."
  [region & summoner-ids]
  (if-region-valid regions region
    (let [joined-ids (clojure.string/join "," summoner-ids)]
      (query/live region ["summoner" joined-ids "masteries"]))))

(defn summoner-runes
  "Retrieve the rune pages for the target summoner."
  [region & summoner-ids]
  (if-region-valid regions region
    (let [joined-ids (clojure.string/join "," summoner-ids)]
      (query/live region ["summoner" joined-ids "runes"]))))

(defn summoner-names
  "Retrieve the names used by the target summoners (yes, summoner-ids is a list)."
  [region & summoner-ids]
  (if-region-valid regions region
    (let [joined-ids (clojure.string/join "," summoner-ids)]
      (query/live region ["summoner" joined-ids "name"]))))

(defn teams
  "Retrieve the teams the target summoner is part of."
  [region summoner-id]
  (if-region-valid regions region
    (query/live region ["team" "by-summoner" summoner-id])))

(defn champions
  "Retrieve static data for all the League of Legends champions.

  Return a map of {id -> champion data}, where id is the champion text ID
  (their name without punctuation signs or spaces, e.g. JarvanIV, ChoGath,
  DrMundo or Fiora) as a keyword.

  Champion data is a map matching the format documented at
  https://developer.riotgames.com/api/methods#!/378/1344 , with the minor
  adjustment that its keys are keywords, not strings.

  This method should return far more detailed information, including all spells
  and attributes, for the champions.
  "
  [region]
  (if-region-valid regions region
    (:data (query/static region ["champion"] {"champData" "all"}))))

(defn champion
  "Retrieve static data for one League of Legends champion.

  Return one champion data map, same as documented in the champions function.

  The champion-id argument is the :key entry in the map (e.g. 36, not DrMundo)"
  [region champion-id]
  (if-region-valid regions region
    (query/static region ["champion" champion-id] {"champData" "all"})))

(defn items
  [region]
  (if-region-valid regions region
    (:data (query/static region ["item"]))))

(defn item
  [region item-id]
  (if-region-valid regions region
    (query/static region ["item" item-id])))

(defn masteries
  [region]
  (if-region-valid regions region
    (:data (query/static region ["mastery"]))))

(defn mastery
  [region mastery-id]
  (if-region-valid regions region
    (query/static region ["mastery" mastery-id])))

(defn runes
  [region]
  (if-region-valid regions region
    (:data (query/static region ["rune"]))))

(defn rune
  [region rune-id]
  (if-region-valid regions region
    (query/static region ["rune" rune-id])))

(defn summoner-spells
  [region]
  (if-region-valid regions region
    (:data (query/static region ["summoner-spell"]))))

(defn summoner-spell
  [region summoner-spell-id]
  (if-region-valid regions region
    (query/static region ["summoner-spell" summoner-spell-id])))

(defn featured-games
  "Returns the featured games for a particular region"
  [region]
  (if-region-valid regions region
    (query/observer region ["featured-games"])))

(defn current-game
  "Returns the current game for a particular summoner, on a particular platform"
  [region platform-id summoner-id]
  (if-region-valid regions region
    (if (contains? observer-platform-ids platform-id)
      (query/observer region ["current-game" platform-id summoner-id])
      (throw (Exception. (str "Invalid platform ID: " platform-id ". Valid platform IDs are: " observer-platform-ids))))))
