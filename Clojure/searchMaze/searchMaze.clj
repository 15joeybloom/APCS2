(defn longest
  "Returns the sequence with the most items,
   returning the first if two items sequences
   have the same number of items. Nil values are
   ignored; (longest nil '() nil) => '() even though
   the nil was first"
  ([s] s)
  ([s & ss]
   (let [longestSS (apply longest ss)]
   (if (or (nil? longestSS)
           (and s (>= (count s) (count longestSS))))
     s
     longestSS))))
;; (longest '() '(1) '() '(1 2) '(1 2 3) '(1)) ;; => (1 2 3)
;; (longest [] [1 2 3] '(1 2 3 4) [1 2 3 4]) ;; => (1 2 3 4)
;; (longest nil nil nil) ;; => nil
;; (longest nil '() nil) ;; => '()
;; (longest nil '() [] {} nil) ;;=> '()
;; (count nil) ;; => 0

(defn searchMaze
  "Searches the maze (a two-dimensional vector of 0s for empty spaces and 1s) for the longest possible path
  from (c, r) in (x, y) notation (default (0, 0)) such that you don't go over
  a space twice. Returns that path as a list of strings telling the directions,
  i.e. '(\"left\" \"right\" \"up\" \"left\" \"down\") "
  ([maze] (searchMaze maze 0 0))
  ([maze r c]
   (let [thisSpace (get-in maze [r c])]
     (if (or (nil? thisSpace) (= thisSpace 1)) ;if this space is a wall
       nil
       (let [next-maze (assoc-in maze
                                 [r c]
                                 1);make the current space a wall so you can't return
             temp-fn #(searchMaze next-maze %1 %2)
             paths (assoc {}
                     (temp-fn (inc r) c) "down"
                     (temp-fn (dec r) c) "up"
                     (temp-fn r (inc c)) "right"
                     (temp-fn r (dec c)) "left");;use assoc to prevent duplicate keys
             longg (apply longest (map first paths))]
         (if (nil? longg)
           '()
           (cons (paths longg) longg)))))))
(defn longestPath
  "Searches the maze (a two-dimensional vector of 0s for empty spaces and 1s) for the longest possible path
  from (c, r) in (x, y) notation (default (0,0)) such that you don't go over a space twice. Returns the
  length of that path."
  ([maze] (longestPath maze 0 0))
  ([maze r c] (-> (searchMaze maze r c) count inc)))

;; (searchMaze [[0 0 0 1 0 0 0 0]
;;              [0 0 0 1 0 0 0 0]
;;              [0 0 0 1 0 0 0 0]
;;              [0 1 1 1 0 0 0 1]
;;              [0 1 0 1 0 1 0 0]
;;              [0 1 0 0 0 0 1 0]
;;              [0 0 0 1 1 1 1 0]
;;              [0 1 0 0 0 0 0 0]]) ; => ("right" "right" "down" "down" "left" "up" "left" "down" "down" "down" "down" "down" "right" "right" "down" "right" "right" "right" "right" "right" "up" "up" "up" "left" "up" "left" "up" "right" "right" "up" "up" "left" "down" "left" "up" "left" "down" "down" "down" "down" "down" "left" "left" "up")


(defn countDimensions
  "Counts the number of \"dimensions\" of the argument.
  (countDimensions 23) => 0
  (countDimensions \"foo\") => 1
  (countDimensions '()) => 1
  (countDimensions '())"
  [x]
  (if (or (seq? x) (coll? x))
    (inc (apply (fn
                  ([] 0)
                  ([& args] (apply max args)))
                (map countDimensions x)))
    0))

(countDimensions "ddd")
(first "ddd")
;; (longestPath [[0 0 0 1]
;;               [0 1 0 0]])
;; (searchMaze [[1 1 0 1]
;;              [0 0 0 1]] 1 1)
;; (searchMaze [[0 0 1 1]
;;              [1 0 0 1]])

;; (def testVector [[1 2] [3 4] [5 6] [7 8] [9 10]])
;; (get testVector 0)

;; (assoc-in testVector [2 1] 60)
;; (get-in testVector [4 0])
;; (max 0 1 2 1 7 6 -1)
;; (max -1 -2 -4 -0.5 -0.7)







