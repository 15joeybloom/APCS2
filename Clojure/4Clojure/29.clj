(require 'clojure.string)
(def x

(fn [s]
  (apply str (filter #(Character/isUpperCase %) s)))

  )
(x "Hi!")
