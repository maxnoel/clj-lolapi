(ns clj-lolapi.static-data
  (:require [clj-lolapi.query :as query]))

;; Note that requests made by functions in this namespace are
;; never counted in your rate limit. Spam away!

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

;; TODO Realm

