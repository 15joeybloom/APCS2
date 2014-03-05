(ns connect_four.t-core
  (:require [midje.sweet :refer :all])
  (:require [connect_four.core :refer :all]))

(fact "Empty-board returns a vector of seven empty vectors"
      (let [empty-board? (fn empty-board? [xx]
                           (and (vector? xx)
                                (= (count xx) 7)
                                (every? #(and (vector? %) (empty? %)) xx)))]
        (empty-board) => #(empty-board? %)))
