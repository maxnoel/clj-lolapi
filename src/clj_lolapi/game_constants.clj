(ns clj-lolapi.game-constants)

;; Matchmaking queues
(def queue-type { "CUSTOM" "Custom games"
									"NORMAL_5x5_BLIND" "Normal 5v5 Blind Pick games"
									"BOT_5x5" "Historical Summoner's Rift Coop vs AI games"
									"BOT_5x5_INTRO" "Summoner's Rift Coop vs AI Intro Bot games"
									"BOT_5x5_BEGINNER" "Summoner's Rift Coop vs AI Beginner Bot games"
									"BOT_5x5_INTERMEDIATE" "Historical Summoner's Rift Coop vs AI Intermediate Bot games"
									"NORMAL_3x3" "Normal 3v3 games"
									"NORMAL_5x5_DRAFT" "Normal 5v5 Draft Pick games"
									"ODIN_5x5_BLIND" "Dominion 5v5 Blind Pick games"
									"ODIN_5x5_DRAFT" "Dominion 5v5 Draft Pick games"
									"BOT_ODIN_5x5" "Dominion Coop vs AI games"
									"RANKED_SOLO_5x5" "Ranked Solo 5v5 games"
									"RANKED_PREMADE_3x3" "Ranked Premade 3v3 games"
									"RANKED_PREMADE_5x5" "Ranked Premade 5v5 games"
									"RANKED_TEAM_3x3" "Ranked Team 3v3 games"
									"RANKED_TEAM_5x5" "Ranked Team 5v5 games"
									"BOT_TT_3x3" "Twisted Treeline Coop vs AI games"
									"GROUP_FINDER_5x5" "Team Builder games"
									"ARAM_5x5" "ARAM games"
									"ONEFORALL_5x5" "One for All games"
									"FIRSTBLOOD_1x1" "Snowdown Showdown 1v1 games"
									"FIRSTBLOOD_2x2" "Snowdown Showdown 2v2 games"
									"SR_6x6" "Hexakill games"
									"URF_5x5" "Ultra Rapid Fire games"
									"BOT_URF_5x5" "Ultra Rapid Fire games played against AI games"
									"NIGHTMARE_BOT_5x5_RANK1" "Doom Bots Rank 1 games"
									"NIGHTMARE_BOT_5x5_RANK2" "Doom Bots Rank 2 games"
									"NIGHTMARE_BOT_5x5_RANK5" "Doom Bots Rank 5 games"
									"ASCENSION_5x5" "Ascension games"})

;; Maps
(def map-ids {1 "Summoner's Rift"
              2 "Summoner's Rift (Autumn Variant)"
              3 "The Proving Grounds"
              4 "Twisted Treeline (Old version)"
              8 "The Crystal Scar"
              10 "Twisted Treeline"
              12 "Howling Abyss"})

;; Game modes
(def game-modes {"CLASSIC" "Classic" ;; Summoner's Rift/Twisted Treeline
                 "ODIN" "Dominion"
                 "ARAM" "All Random All Mid"
                 "TUTORIAL" "Tutorial"
                 "ONEFORALL" "One for All games"
								 "ASCENSION" "Ascension games"
								 "FIRSTBLOOD" "Snowdown Showdown games"})


;; Game types
(def game-types {"CUSTOM_GAME" "Custom game"
                 "MATCHED_GAME" "Queue against other summoners"
                 "TUTORIAL_GAME" "Tutorial game"})




(def sub-type {"NONE" "Custom games"
							"NORMAL" "Summoner's Rift unranked games"
							"NORMAL_3x3" "Twisted Treeline unranked games"
							"ODIN_UNRANKED" "Dominion/Crystal Scar games"
							"ARAM_UNRANKED_5x5" "ARAM / Howling Abyss games"
							"BOT" "Summoner's Rift and Crystal Scar games played against Intro, Beginner, or Intermediate AI"
							"BOT_3x3" "Twisted Treeline games played against AI"
							"RANKED_SOLO_5x5" "Summoner's Rift ranked solo queue games"
							"RANKED_TEAM_3x3" "Twisted Treeline ranked team games"
							"RANKED_TEAM_5x5" "Summoner's Rift ranked team games"
							"ONEFORALL_5x5" "One for All games"
							"FIRSTBLOOD_1x1" "Snowdown Showdown 1x1 games"
							"FIRSTBLOOD_2x2" "Snowdown Showdown 2x2 games"
							"SR_6x6" "Hexakill games"
							"CAP_5x5" "Team Builder games"
							"URF" "Ultra Rapid Fire games"
							"URF_BOT" "Ultra Rapid Fire games played against AI"
							"NIGHTMARE_BOT" "Summoner's Rift games played against Nightmare AI"
							"ASCENSION" "Ascension games"})

(def player-stat-summary-type {"Unranked" "Summoner's Rift unranked games"
														"Unranked3x3" "Twisted Treeline unranked games"
														"OdinUnranked" "Dominion/Crystal Scar games"
														"AramUnranked5x5" "ARAM / Howling Abyss games"
														"CoopVsAI" "Summoner's Rift and Crystal Scar games played against AI"
														"CoopVsAI3x3" "Twisted Treeline games played against AI"
														"RankedSolo5x5" "Summoner's Rift ranked solo queue games"
														"RankedTeam3x3" "Twisted Treeline ranked team games"
														"RankedTeam5x5" "Summoner's Rift ranked team games"
														"OneForAll5x5" "One for All games"
														"FirstBlood1x1" "Snowdown Showdown 1x1 games"
														"FirstBlood2x2" "Snowdown Showdown 2x2 games"
														"SummonersRift6x6" "Hexakill games"
														"CAP5x5" "Team Builder games"
														"URF" "Ultra Rapid Fire games"
														"URFBots" "Ultra Rapid Fire games played against AI"
														"NightmareBot" "Summoner's Rift games played against Nightmare AI"
														"Ascension" "Ascension games"})
