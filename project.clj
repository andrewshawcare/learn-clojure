(defproject learn-clojure "0.1.0-SNAPSHOT"
  :description "Code examples from the, \"Learn Clojure\" guide on the Clojure web site"
  :url "https://github.com/andrewshawcare/learn-clojure"
  :license {:name "The MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :main ^:skip-aot learn-clojure.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})