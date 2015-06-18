(ns clj-lolapi.esports
  (:require [clj-lolapi.query :as query]))

(defn player
  "Returns basic information about a player including name, bio, and profile url on lolesports"
  [player-id]
  nil)

(defn all-leagues
  "Returns basic information on all existing leagues of lolesports"
  []
  nil)

(defn league
  "Returns basic information about a league on lolesports"
  [league-id]
  nil)

(defn all-series
  "Returns basic information on all existing series of lolesports"
  []
  nil)

(defn series
  "Returns basic information about a series on lolesports"
  [series-id]
  nil)

(defn team
  "Returns basic information about a team including players, name, and profile url on lolesports"
  [team-id]
  nil)

(defn match
  "Returns basic information about a match including name, tournament information, and live streams"
  [match-id]
  nil)

(defn game
  "Returns basic information about a game including players, tournament information, and videos on demand"
  [game-id]
  nil)

(defn schedule
  "Returns the schedule of matches for the specified tournament"
  [tournament-id]
  nil)

(defn standings
  "Returns the standings for the specified tournament"
  [tournament-id]
  nil)

(defn tournaments
  "Returns basic information about all tournaments including contestants, and beginning and end dates"
  []
  nil)

(defn tournament
  "Returns basic information about a tournament including contestants, and beginning and end dates"
  [tournament-id]
  nil)

(defn news
  "Returns the latest news from lolesports"
  []
  nil)

(defn stat-leaders
  "Returns the greatest stat for a particular tournament"
  [stat tournament-id]
  nil)

(defn player-stats
  "Returns a player's stats for the entire tournament or tournament series"
  [player-id]
  nil)

(defn team-stats
  "Returns a team's stats for the entire tournament or tournament series"
  [team-id]
  nil)

(defn all-player-stat-highlights
  "Returns kda, average gold and gpm for all players in a tournament"
  [tournament-id] ;; team-id is optional, although not on api docs
  nil)

(defn player-stat-highlights
  "Returns kda, average gold and gpm for one player in one tournament"
  [player-id tournament-id]
  nil)

(defn player-champs
  "Returns kda, average gold and gpm per champion for one player in one tournament"
  [player-id tournament-id]
  nil)

(defn fantasy-stats
  "Returns fantasy stats for entire tournament"
  [tournament-id]
  nil)

(defn programming-week
  "Returns a weeks programming blocks"
  [date offset]
  nil)

(defn programming
  "Returns all programming blocks"
  ([] nil)
  ([id] nil))
