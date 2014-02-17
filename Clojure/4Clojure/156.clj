(fn [default my-keys]
  (reduce #(conj %1 [%2 default]) {} my-keys))
