(ns browser.core
  (:require [etaoin.api :as eapi]))

(def default {:headless false
              :driver :firefox
              :size [1280 720]})

(defn create-driver
  ([] (create-driver {}))
  ([opts]
   (let [config (merge default
                       (select-keys opts [:driver :headless]))
         driver-creator (case (:driver config)
                          :chrome eapi/chrome
                          :firefox eapi/firefox
                          eapi/firefox)]
     (eapi/with-wait-timeout 15 (driver-creator config)))))

