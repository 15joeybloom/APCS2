(get '(1 2 3) 1)
(get #{1 2 3 5} 5)
(assoc [] 0 1)

(defn identifySeq [seq]
  (cond
   (-> seq (conj [200 3]) (get 200) (= 3))
     :map
   (-> seq (conj 300) (conj 200) (conj 400) first (= 400)) ;conj adds to beginning
     :list
   (= (-> seq (conj 300)) (-> seq (conj 300) (conj 300))) ;add once to set is the same as twice
     :set
   (-> seq (conj 300) (conj 500) (conj 400) last (= 400))
     :vector
   :else
     :yikes
   	))

(def testMap {})
(= :map (identifySeq {:a 1, :b 2}))

(def xxx
  #{}
;;   []
;;   '()
  )
(= (-> xxx (conj 300)) (-> xxx (conj 300) (conj 300))) ;set

(-> xxx (conj 300) (conj 500) (conj 400) last (= 400)) ;vector

(-> xxx (conj 300) (conj 200) (conj 400) first (= 400)) ;list