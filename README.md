# kaocha-cucumber-etaoin-example

This is quick demo for [kaocha-cucumber](https://github.com/lambdaisland/kaocha-cucumber) with [etaoin](https://github.com/clj-commons/etaoin).

## Before start
You have to have a path to a web-driver (like `chromedriver` or `geckodriver`) in your `PATH` for `etaoin` or provide it by configuration (see [etaoin docs](https://github.com/clj-commons/etaoin/blob/master/doc/01-user-guide.adoc#driver-options)). 
## Get started

1. Add libs
  - lambdaisland/kaocha
  - lambdaisland/kaocha-cucumber
  - etaoin/etaoin

2. Add `:main-opts ["-m" "kaocha.runner"]` to `deps.edn` for running tests by kaocha.
3. Create `tests.edn` in the root of the project and add:
```clojure
{:kaocha/tests [{:kaocha.testable/id   :unit
                 :kaocha.testable/type :kaocha.type/clojure.test
				         :kaocha/source-paths  ["src"]
                 :kaocha/test-paths    ["test/unit"]
                 :kaocha/ns-patterns   [".*"]}

                {:kaocha.testable/id   :features
                 :kaocha.testable/type :kaocha.type/cucumber
                 :kaocha/source-paths  ["src"]
                 :kaocha/test-paths    ["test/features"]
                 :cucumber/glue-paths  ["test/steps_definitions"]
                 :kaocha/ns-patterns   [".*"]}]
;; other confirmation
}
```
Here we created 2 groups of tests `unit` and `features`. `features` group is specific for `kaocha-cucumber`. We are able to run it by `clojure -M:test features`.

4. Create directories  `test/features` (for `*.feature` files) and `test/steps_definitions` (implementation of steps for  `*.feature` files).
5. Fill features files and steps-definitions files. See example:  [`wiki.feature`](https://github.com/vial0ft/kaocha-cucumber-etaoin-example/blob/main/test/features/wiki.feature) and [`wiki_steps.clj`](https://github.com/vial0ft/kaocha-cucumber-etaoin-example/blob/main/test/steps_definitions/wiki_steps.clj)
6. Run `clojure -M:test features`
