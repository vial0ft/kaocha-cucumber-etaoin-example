(ns wiki-steps
  (:require [browser.core :refer [create-driver]]
            [lambdaisland.cucumber.dsl :as c :refer :all]
            [etaoin.api :as eapi]
            [etaoin.keys :as ekeys]
            [clojure.test :refer [is]]))


(Given "Browser (firefox|chrome) is (showed|headless)" [state [driver headless]]
  (assoc state :driver (create-driver {:headless (= (keyword headless) :headless)
                                       :driver (keyword driver)})))

(And "Start with page '(.*)'" [{:keys [driver] :as state} page]
  (eapi/go driver page)
  state)

(When "I search '(.*)'" [{:keys [driver] :as state} searching]
  (eapi/fill-el driver (eapi/query driver {:name "q"}) searching)
  (eapi/fill-active driver ekeys/enter)
  state)

(And "I click to '(.*)'" [{:keys [driver] :as state} link-text]
  (eapi/click-visible driver {:fn/text "Wikipedia, the free encyclopedia"})
  state)

(And "I search '(.*)' topic" [{:keys [driver] :as state} topic]
  (eapi/fill-el driver (eapi/query driver {:name "search"}) topic)
  (eapi/fill-active driver ekeys/enter)
  state)

(Then "I should see text '(.*)'" [{:keys [driver] :as state} text]
  (eapi/wait-visible driver {:tag :span
                             :class "mw-page-title-main"})
  (is (= (eapi/has-text? driver text) true))
  state)

(Then "I close browser" [{:keys [driver] :as state}]
  (eapi/quit driver))
