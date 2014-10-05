(ns clj-lolapi.core)


;; All methods are not supported on all regions.
;; TODO Warn/error when attempting to access an unsupported method?
(def regions #{"br" "eune" "euw" "kr" "lan" "las" "na" "oce" "ru" "tr"})

