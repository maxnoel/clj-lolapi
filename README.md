# clj-lolapi

Clojure interface to Riot Games's League of Legends API.

## Prerequisites

Set your Riot Games API key via an environment variable named `RIOT_API_KEY'

    export RIOT_API_KEY=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx

## Examples

``` clojure

;; libspec
(require '[clj-lolapi.core :as lol])

;; find a summoner's details
(lol/summoner-by-name "euw" "acron0")
=> {:acron0 {:id 28373320, :name "acron0", :profileIconId 767, :summonerLevel 30, :revisionDate 1434237298000}}

;; threading to fetch an id
(-> (lol/summoner-by-name "euw" "acron0") first val :id)
=> 28373320

;; stats for a summoner (as an array of game types)
(lol/stats "euw" 28373320)
=> [{:playerStatSummaryType "RankedSolo5x5" ...} ...]

;; match history for a particular summoner
(lol/match-history "euw" 28373320)
=> {:matches [...]}

;; threading to fetch id of most recent match
(-> (lol/match-history "euw" 101259) first val first :matchId)
=> 2031216090

;; detailed match report include timeline
(lol/match "euw" 2031216090 {:timeline true})
=> {:matchDuration 2195 ...}

;; featured games
(lol/featured-games "euw")
=> {:gameList [...]}

```

For more details, including the relevant data structures, view the Riot Games API documentation
https://developer.riotgames.com/api/methods
