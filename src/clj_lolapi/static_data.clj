(ns clj-lolapi.static-data
  (:require [clj-lolapi.core :as core]))

;; Note that requests made by functions in this namespace are
;; never counted in your rate limit. Spam away!

(defn champions
  "Retrieve static data for all the League of Legends champions.

  Return a map of {id -> champion data}.
  Champion data is a map with the following keys:

  - :id: The champion text ID (their name without punctuation signs or spaces,
    e.g. JarvanIV, ChoGath, DrMundo or Fiora).
    Note that Wukong's ID is MonkeyKing, not Wukong.
  - :key: The champion's numeric ID. It's actually a string, not an int.
  - :name: The champion's full name, including punctuation and spaces.
  - :title: The champion's title.


  XXX None of this matches what's documented at
  https://developer.riotgames.com/api/methods#!/378/1344

  This method should return far more detailed information, including all spells
  and attributes, for the champions.
  "
  [region]
  (:data (core/static-query region "champion")))

(defn champion
  "Retrieve static data for one League of Legends champion.

  Return one champion data map, same as documented in the champions function.

  The champion-id argument is the :key entry in the map (e.g. 36, not DrMundo)"
  [region champion-id]
  (core/static-query region "champion" champion-id))

(defn items
  [region]
  (:data (core/static-query region "item")))

(defn item
  [region item-id]
  (core/static-query region "item" item-id))

(defn masteries
  [region]
  (:data (core/static-query region "mastery")))

(defn mastery
  [region mastery-id]
  (core/static-query region "mastery" mastery-id))

(defn runes
  [region]
  (:data (core/static-query region "rune")))

(defn rune
  [region rune-id]
  (core/static-query region "rune" rune-id))

(defn summoner-spells
  [region]
  (:data (core/static-query region "summoner-spell")))

(defn summoner-spell
  [region summoner-spell-id]
  (core/static-query region "summoner-spell" summoner-spell-id))

;; TODO Realm

