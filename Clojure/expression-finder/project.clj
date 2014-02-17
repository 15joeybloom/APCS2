(defproject expression-finder "0.1.0-SNAPSHOT"
  :description "forms an expression that evaluates to a target value
  using a list of numbers and +-*/"
;  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :main ^:skip-aot expression-finder.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
