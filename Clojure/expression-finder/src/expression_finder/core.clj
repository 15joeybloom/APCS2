(ns core
  (:use clojure.walk))

(defn infix
  "Returns an infix expression
  that shows op applied to str1 and str2"
  [str1 op str2]
  (str "(" str1 " " op " " str2 ")"))

(defn combine
  "Takes a sequence of maps with two entries, :num and :str.
  The numbers are the numbers to be combined and
  the strings will be used to represent their
  corresponding numbers.

  Returns a sequence of length at most 5 of maps with two entries as described above.
  The numbers returned are the result of adding,
  subtracting, dividing, and multiplying
  a and b, and the strings are the infix
  notation corresponding to the operation that was performed."
  [[{a :num aStr :str :as firstArg}  {b :num bStr :str :as secondArg}]]
  (let [returnMe (list {:num (+ a b) :str (infix aStr "+" bStr)}
                       {:num (- a b) :str (infix aStr "-" bStr)}
                       {:num (- b a) :str (infix bStr "-" aStr)}
                       {:num (* a b) :str (infix aStr "*" bStr)})]
    (if (and (not= b 0) (= (mod a b) 0))
      (conj returnMe {:num (/ a b) :str (infix aStr "/" bStr)})
      (if (and (not= a 0) (= (mod b a) 0))
        (conj returnMe {:num (/ b a) :str (infix bStr "/" aStr)})
        returnMe ;if a and b are coprime
        ))))
;(combine (makeMap '(2 8)))
;; (combine {9 "(4 + 5)" 4 "(8 / 2)"})
;; (count (combine {2 "2" 8 "8"}))
;; (count (combine {9 "(4 + 5)" 4 "(8 / 2)"}))
;(combine (list {:num 5 :str "(2 + 3)"} {:num 4 :str 4}))
(defn rotate
  "Returns the first element of the argument sequence
  appended to the rest"
  [seq]
  (concat (drop 1 seq) (take 1 seq)))
;; (rotate '())
(defn rotateRest
  "Returns the second element of the argument sequence
   appended to the first and rest"
  [seq]
  (if (empty? seq)
    seq
    (conj (-> (rest seq) rotate) (first seq))))
;; (rotateRest '())
(rotateRest '(1 2 3 4 5))
(defn makeMap
  "Takes a sequence, which cannot be a map and
  whose arguments should all be numbers.

  Returns a sequence of maps of two entries, :num and :str."
  [notMap]
  (if (empty? notMap)
    {}
    (cons {:num (first notMap) :str (-> notMap first str)} (-> notMap rest makeMap))))
(defn firstNonNil
  "Returns the first item in the sequence
  that is not nil, or returns nil if all
  items are nil"
  [seq]
  (cond (empty? seq)
          nil
        (first seq)
          (first seq)
        :firstIsNil
          (-> seq rest firstNonNil)))
(defn wellFormed?
  "Returns true if the argument
  is a sequence of maps
  with two entries, :num and :str"
  [seq]
  (reduce #(and %1 (and (map? %2) (contains? %2 :num) (contains? %2 :str))) :true seq))
;; (wellFormed? '({:num 2 :str ""} {:num 5 :str ""}))
;; (wellFormed? '())
;; (wellFormed? '({}))
;; (wellFormed? '({:num 5 :str "4"} {:num 6} {:str "7"}))

(defn findExpression
  "Takes a target number and a sequence of numbers.

  Uses +-*/ to combine the operands, and
  returns an expression that uses all the operands to get
  the target number or returns nil if none exists"
  ([target operands]
     (findExpression target (if (wellFormed? operands) operands (makeMap operands)) (count operands) (-> operands count dec)))
  ([target operands fullCount restCount] (findExpression target operands fullCount restCount fullCount restCount))
  ([target operands fullRotations restRotations fullCount restCount]
   (cond (= fullCount 0)
           nil
         (= fullCount 1)
           (if (-> operands first :num (= target)) ;if the one operand is equal to the target
             (-> operands first :str)              ;then return the string that represents the operand
             nil)                                 ;else return nil
         (= fullRotations 0)
           nil;no expression found
         (= restRotations 0)
           (findExpression target (rotate operands) (dec fullRotations) restCount fullCount restCount)
         :else
           (let [exp (->> operands
                          (take 2)                                                    ;take the first two operands
                          combine                                                     ;combine them with +-*/
                          (walk #(findExpression target (cons % (drop 2 operands))) firstNonNil)   ;test all of the combinations to see if any have found an expression and get the first expression found, or nil if none found
                          )]
             (if exp ;if exp is not nil
               exp

               (findExpression target
                               (rotateRest operands)
                               fullRotations
                               (dec restRotations)
                               fullCount restCount))
             ))))
(def testList '(2 3 4 5 6))
(def testMap (makeMap testList))
;; (take 2 testMap)
;; (def a (->> testMap
;;             (take 2)))
;; (def b (->> a
;;             combine))
;; (def c (->> b
;;             (walk #(findExpression 20 (cons % (drop 4 testMap))) firstNonNil)))
;(findExpression 5 (list {:num 20, :str "((2 + 3) * 4)"}))
;; (findExpression 13 '(2 3 5 6 8))
;; (findExpression 5 '(2 3))
;; (findExpression 7 '(3 2 3))
;; (findExpression 1 '(3 5))
;; (findExpression 18 '(3 4 6 7))
;; (findExpression 21 '(8 3 1 4))
(findExpression 16 '(1 1 3 3))

;; (walk (partial + 2) identity '(1 2 3 4))
;; (walk #(str (cons 3 %)) identity (list (list '(1 2) '(4 5)) (list 3 4)))
;; (walk #(cons % (drop 4 testMap)) identity b)
;; (walk #(findExpression 20 (cons % (drop 4 testMap))) identity b)
;; (findExpression 1 (list {:num 5, :str "(2 + 3)"} {:num 6, :str "6"}))
