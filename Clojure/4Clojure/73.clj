(fn tictactoe
  [v]
  (let [test-fn       #(if (and (= %1 %2 %3)
                                (not= %1 :e))
                         %1
                         nil)
        firstNonNil   (fn firstNonNil
  ;;                       "Returns the first item in the sequence
  ;;                       that is not nil, or returns nil if all
  ;;                       items are nil"
                        [s]
                        (cond (empty? s)
                                nil
                              (first s)
                                (first s)
                              :firstIsNil
                                (-> s rest firstNonNil)))
        colsWinner  (firstNonNil (apply (partial map test-fn) v))
        rowsWinner  (firstNonNil (map (partial apply test-fn) v))
        diagonalWinner (firstNonNil)]
    (cond colsWinner
            colsWinner
          rowsWinner
            rowsWinner)))
