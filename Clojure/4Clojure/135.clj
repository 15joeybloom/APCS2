(fn calculator
  [a op b & args]
  (if (empty? args)
    (op a b)
    (apply (partial calculator (op a b)) args)))
