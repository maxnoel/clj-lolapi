(ns clj-lolapi.query
  (:require [environ.core :as env]
            [clj-http.client :as http]
            [clojure.string :as string]))

;; Define this in the RIOT_API_KEY environment variable
(def api-key (env/env :riot-api-key))

;;
(defn server [region] (str "https://" region ".api.pvp.net"))

(defn live-root [region] (str (server region) "/api/lol"))

(defn observer-root [region] (str (server region) "/observer-mode/rest"))

;; All static data calls use sub-paths of /static-data
(defn static-root [region] (str (server region) "/api/lol/static-data"))

;; Only support the latest version (at the time of this writing) of each method.
(def live-method-versions
  {"champion" "v1.2"
   "game" "v1.3"
   "league" "v2.5"
   "stats" "v1.3"
   "summoner" "v1.4"
   "team" "v2.4"
   "lol-static-data" "v1.2"
   "lol-status" "v1.0"
   "match" "v2.2"
   "matchhistory" "v2.2"})

(def observer-methods
  {"current-game" "consumer/getSpectatorGameInfo"
   "featured-games" "featured"})

(defn live-method-version
  [method]
  ;; TODO Warn/error on unknown method?
  (get live-method-versions method "UNKNOWN"))

(defn static-method-version
  [method] "v1.2")

(defn- endpoint-fmt
  [version-getter root region path]
  (let [method (first path)
        version (version-getter method)]
    (string/join "/" (into [root region version] path))))

(defn- observer-endpoint-fmt
  [method-getter root path]
  (let [key (first path)
        method (method-getter key)
        path-parts (if (coll? path) (rest path) [])]
    (string/join "/" (into [root method] path-parts))))

(defn live-endpoint
  [region path]
  (endpoint-fmt live-method-version (live-root region) region path))

(defn static-endpoint
  [region path]
  (endpoint-fmt static-method-version (static-root region) region path))

(defn observer-endpoint
  [region path]
  (observer-endpoint-fmt observer-methods (observer-root region) path))

(defn- query
  "Make a HTTP request to the Riot API."
  [endpoint-fn region path params]
  (let [url (endpoint-fn region path)
        query-params (assoc params "api_key" api-key)
        response (http/get url {:as :json-strict
                                :query-params query-params})]
    (get response :body)))

(defn live
  ([region path] (live region path {}))
  ([region path params]
    (query live-endpoint region path params)))

(defn static
  ([region path] (static region path {}))
  ([region path params]
    (query static-endpoint region path params)))

(defn observer
  ([region path] (observer region path {}))
  ([region path params]
    (query observer-endpoint region path params)))
