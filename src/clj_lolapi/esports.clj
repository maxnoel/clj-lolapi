(ns clj-lolapi.esports
  (:require [clj-lolapi.query :as query]))

(defn leagues
  "Returns basic information on all existing leagues of lolesports"
  ([]
   (query/esports ["leagues"]))
  ([league-name]
   (query/esports ["leagues"] {:slug league-name})))
