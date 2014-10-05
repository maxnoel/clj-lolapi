(ns clj-lolapi.query
  (:require [environ.core :as env]
            [clj-http.client :as http]
            [clojure.string :as string]))

;; Define this in the RIOT_API_KEY environment variable
(def api-key (env/env :riot-api-key))

;; TODO Add PBE/prod servers
;; (function? env. variables?)
(def server "http://global.api.pvp.net")

(def live-root (str server "/api/lol"))

;; All static data calls use sub-paths of /static-data
(def static-root (str server "/api/lol/static-data"))

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
   "match" "v2.2"})

(defn live-method-version
  [method]
  ;; TODO Warn/error on unknown method?
  (get live-method-versions method "UNKNOWN"))

(defn static-method-version
  [method] "v1.2")

(defn- endpoint
  [version-getter root region path]
  (let [method (first path)
        version (version-getter method)]
    (string/join "/" (into [root region version] path))))

(defn live-endpoint
  [region path]
  (endpoint live-method-version live-root region path))

(defn static-endpoint
  [region path]
  (endpoint static-method-version static-root region path))

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
