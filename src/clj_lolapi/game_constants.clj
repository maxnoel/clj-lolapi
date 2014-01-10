(ns clj-lolapi.game-constants)

;; Matchmaking queues
(def queue-ids {2 "Normal 5v5 Blind Pick"
                4 "Ranked Solo 5v5"
                7 "Coop VS AI 5v5"
                8 "Normal 3v3"
                14 "Normal 5v5 Draft Pick"
                16 "Dominion 5v5 Blind Pick"
                17 "Dominion 5v5 Draft Pick"
                25 "Dominion Coop VS AI"
                41 "Ranked Team 3v3"
                42 "Ranked Team 5v5"
                52 "Twisted Treeline Coop VS AI"
                65 "ARAM"
                67 "ARAM Coop VS AI"
                70 "One for All 5v5"
                72 "Snowdown Showdown 1v1"
                73 "Snowdown Showdown 2v2"})

;; Maps
(def map-ids {1 "Summoner's Rift"
              2 "Summoner's Rift (Autumn Variant)"
              3 "The Proving Grounds"
              4 "Twisted Treeline (Old version)"
              8 "The Crystal Scar"
              10 "Twisted Treeline"
              12 "Howling Abyss"})

;; Queue types
(def game-types {"CUSTOM_GAME" "Custom game"
                 "MATCHED_GAME" "Queue against other summoners"
                 "CO_OP_VS_AI_GAME" "Queue against bots"
                 "TUTORIAL_GAME" "Tutorial game"})

;; Game modes
(def game-modes {"CLASSIC" "Classic" ;; Summoner's Rift/Twisted Treeline
                 "ODIN" "Dominion"
                 "ARAM" "All Random All Mid"
                 "TUTORIAL" "Tutorial"})
