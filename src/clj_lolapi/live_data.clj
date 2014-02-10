(ns clj-lolapi.live-data
  (:require [clj-lolapi.query :as query]))

(defn champions
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

(defn summoner
  "Retrieve the target summoner (by ID)."
  [region summoner-id]
  (query/live region ["summoner" summoner-id]))

(defn summoner-by-name
  "Retrieve the target summoner (by name)."
  [region summoner-name]
  (query/live region ["summoner" "by-name" summoner-name]))

(defn summoner-masteries
  "Retrieve the mastery pages for the target summoner."
  [region summoner-id]
  (:pages (query/live region ["summoner" summoner-id "masteries"])))

(defn summoner-runes
  "Retrieve the rune pages for the target summoner."
  [region summoner-id]
  (:pages (query/live region ["summoner" summoner-id "runes"])))

(defn summoner-names
  "Retrieve the names used by the target summoners (yes, summoner-ids is a list)."
  [region summoner-ids]
  (let [joined-ids (clojure.string/join "," summoner-ids)]
    (:summoners (query/live region ["summoner" joined-ids "name"]))))

(defn teams
  "Retrieve the teams the target summoner is part of."
  [region summoner-id]
  ;; clj-http (Cheshire) deserializes that as a list instead of a vector.
  ;; TODO Remove once :as :json-strict is supported.
  (vec (query/live region ["team" "by-summoner" summoner-id])))
