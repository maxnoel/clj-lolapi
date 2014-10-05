(ns clj-lolapi.core
  (:require [clj-lolapi.query :as query]))

;; All methods are not supported on all regions.
;; TODO Warn/error when attempting to access an unsupported method?
(def regions #{"br" "eune" "euw" "kr" "lan" "las" "na" "oce" "ru" "tr"})

(defn champion-status
  "Retrieve the live data for all League of Legends champions.

  This will only give you the basic 4 ratings, whether or not the champion is
  enabled, and whether the champion is currently free to play.

  For more detailed information (e.g. hard numbers),
  use clj-lolapi.static-data/champion"
  [region]
  (:champions (query/live region ["champion"])))

(defn games
  "Retrieve recent games for the target summoner."
  [region summoner-id]
  (:games (query/live region ["game" "by-summoner" summoner-id "recent"])))

(defn leagues
  "Retrieve league data for the target summoner.
  Returns an id -> league map with one entry for solo queue (key = summoner ID)
  and one for each ranked team queue that the summoner has a team in
  (key = team ID)."
  [region summoner-id]
  (query/live region ["league" "by-summoner" summoner-id]))

(defn stats
  "Retrieves player stats summaries by summoner ID.

  TODO Implement season filtering."
  [region summoner-id]
  (:playerStatSummaries (query/live region ["stats" "by-summoner" summoner-id "summary"])))

(defn ranked-stats
  "Retrieve per-champion ranked stats by summoner ID.

  TODO Implement season filtering."
  [region summoner-id]
  (:champions (query/live region ["stats" "by-summoner" summoner-id "ranked"])))

(defn match-history
  "Retrieve match history for a summoner"
  [region summoner-id]
  (query/live region ["matchhistory" summoner-id]))

(defn match
  "Retrieve match history for a summoner"
  [region match-id]
  (query/live region ["match" match-id]))

(defn summoner
  "Retrieve the target summoner (by ID)."
  [region summoner-id]
  (query/live region ["summoner" summoner-id]))

(defn summoner-by-name
  "Retrieve the target summoner (by name)."
  [region & summoner-names]
  (let [joined-names (clojure.string/join "," summoner-names)]
    (query/live region ["summoner" "by-name" joined-names])))

(defn summoner-masteries
  "Retrieve the mastery pages for the target summoner."
  [region & summoner-ids]
  (let [joined-ids (clojure.string/join "," summoner-ids)]
    (query/live region ["summoner" joined-ids "masteries"])))

(defn summoner-runes
  "Retrieve the rune pages for the target summoner."
  [region & summoner-ids]
  (let [joined-ids (clojure.string/join "," summoner-ids)]
    (query/live region ["summoner" joined-ids "runes"])))

(defn summoner-names
  "Retrieve the names used by the target summoners (yes, summoner-ids is a list)."
  [region & summoner-ids]
  (let [joined-ids (clojure.string/join "," summoner-ids)]
    (query/live region ["summoner" joined-ids "name"])))

(defn teams
  "Retrieve the teams the target summoner is part of."
  [region summoner-id]
  (query/live region ["team" "by-summoner" summoner-id]))

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
  (:data (query/static region ["champion"] {"champData" "all"})))

(defn champion
  "Retrieve static data for one League of Legends champion.

  Return one champion data map, same as documented in the champions function.

  The champion-id argument is the :key entry in the map (e.g. 36, not DrMundo)"
  [region champion-id]
  (query/static region ["champion" champion-id] {"champData" "all"}))

(defn items
  [region]
  (:data (query/static region ["item"])))

(defn item
  [region item-id]
  (query/static region ["item" item-id]))

(defn masteries
  [region]
  (:data (query/static region ["mastery"])))

(defn mastery
  [region mastery-id]
  (query/static region ["mastery" mastery-id]))

(defn runes
  [region]
  (:data (query/static region ["rune"])))

(defn rune
  [region rune-id]
  (query/static region ["rune" rune-id]))

(defn summoner-spells
  [region]
  (:data (query/static region ["summoner-spell"])))

(defn summoner-spell
  [region summoner-spell-id]
  (query/static region ["summoner-spell" summoner-spell-id]))
