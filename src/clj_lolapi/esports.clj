(ns clj-lolapi.esports
  (:require [clj-lolapi.query :as query]))

;; http://na.lolesports.com/api/swagger

(def stat-keys {:stat/kda "kda"
                :stat/kill-participation "killparticipation"
                :stat/gpm "gpm"
                :stat/total-gold "totalgold"
                :stat/kills "kills"
                :stat/deaths "deaths"
                :stat/assists "assists"
                :stat/minions-killed "minionskilled"})

(defn clean-hash-map
  [candidates]
  (into {} (remove (comp nil? second) candidates)))

(defn player
  "Returns basic information about a player including name, bio, and profile url on lolesports"
  [player-id]
  (query/esports ["player" player-id]))

(defn leagues
  "Returns basic information on all existing leagues of lolesports"
  []
  (query/esports ["league"] {"parameters[method]" "all"}))

(defn league
  "Returns basic information about a league on lolesports"
  [league-id]
  (query/esports ["league" league-id]))

(defn series
  "Returns basic information about a series on lolesports"
  ([]
   (query/esports ["series"]))
  ([series-id]
   (query/esports ["series" series-id])))

(defn team
  "Returns basic information about a team including players, name, and profile url on lolesports"
  [team-id]
   (query/esports ["team" team-id]))

(defn match
  "Returns basic information about a match including name, tournament information, and live streams"
  [match-id]
  (query/esports ["match" match-id]))

(defn game
  "Returns basic information about a game including players, tournament information, and videos on demand"
  [game-id]
  (query/esports ["game" game-id]))

(defn schedule
  "Returns the schedule of matches for the specified tournament"
  ([tournament-id] (schedule tournament-id {}))
  ([tournament-id {:keys [include-finished include-future include-live] }]
   (query/esports ["schedule"] (clean-hash-map
                                {"tournamentId"    tournament-id
                                 "includeFinished" include-finished
                                 "includeFuture"   include-future
                                 "includeLive"     include-live}))))

(defn standings
  "Returns the standings for the specified tournament"
  [tournament-id]
  (query/esports ["standings"] {"tournamentId" tournament-id}))

(defn tournaments
  "Returns basic information about all tournaments including contestants, and beginning and end dates"
  []
  (query/esports ["tournament"]))

(defn tournament
  "Returns basic information about a tournament including contestants, and beginning and end dates"
  [tournament-id]
  (query/esports ["tournament" tournament-id]))

(defn news
  "Returns the latest news from lolesports"
  ([] (news {}))
  ([{:keys [limit offset taxonomy-id lang]}]
   (query/esports ["news"] (clean-hash-map
                            {"limit"      limit
                             "offset"     offset
                             "taxonomyId" taxonomy-id
                             "lang"       lang}))))

(defn stat-leaders
  "Returns the greatest stat for a particular tournament"
  [stat tournament-id]
  (if (contains? stat-keys stat)
    (query/esports ["statLeaders"] {"stat" (get stat-keys stat) "tournamentId" tournament-id})
    {:error (str (name stat) " is not a valid stat. See stat-keys.")}))

(defn player-stats
  "Returns a player's stats for the entire tournament or tournament series"
  ([player-id] (player-stats player-id {}))
  ([player-id {:keys [tournament-id]}] ;; for some reason, adding this makes no difference to results
   (query/esports ["playerStats"] (clean-hash-map
                                  {"playerId" player-id
                                   "tournamentId" tournament-id}))))

(defn team-stats
  "Returns a team's stats for the entire tournament or tournament series"
  ([team-id] (team-stats team-id {}))
  ([team-id {:keys [tournament-id]}] ;; for some reason, adding this makes no difference to results
   (query/esports ["teamStats"] (clean-hash-map
                                  {"teamId" team-id
                                   "tournamentId" tournament-id}))))

(defn all-player-stat-highlights
  "Returns kda, average gold and gpm for all players in a tournament"
  []
  (query/esports ["all-player-stats"]))

(defn player-stat-highlights
  "Returns kda, average gold and gpm for one player in one tournament"
  [player-id tournament-id]
  (query/esports ["all-player-stats" player-id] {"tournamentId" tournament-id}))

(defn player-champs
  "Returns kda, average gold and gpm per champion for one player in one tournament"
  [player-id tournament-id]
  (query/esports ["all-player-champs" player-id] {"tournamentId" tournament-id}))

(defn fantasy-stats
  "Returns fantasy stats for entire tournament" ;; date filtering is broken so don't use it
  [tournament-id]
  (query/esports ["gameStatsFantasy"] {"tournamentId" tournament-id}))

(defn programming-week
  "Returns a weeks programming blocks - date is a string in format YYYY-MM-DD, offset is an int"
  ([date] (programming-week date {}))
  ([date {:keys [offset] :or {offset 0}}] ;; make date a proper DateTime
   (query/esports ["programmingWeek" date offset])))

(defn programming
  "Returns all programming blocks"
  ([] (programming {}))
  ([{:keys [id winner expand tournament-id]}]
   (if (nil? id)
     (query/esports ["programming"] (clean-hash-map
                                     {"parameters[method]" "all"
                                      "parameters[expand_matches]" (if expand 1 0)
                                      "parameters[winner]" 1 ;; we always send winner 1 because of some weird conflicts with tournament id
                                      "parameters[tournament]" tournament-id}))
     (query/esports ["programming" id] (clean-hash-map
                                        {"expand_matches" (if expand 1 0)})))))
