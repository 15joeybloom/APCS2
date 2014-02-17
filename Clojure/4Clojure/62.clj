(fn my-iterate
  [f init]
  (cons init (lazy-seq (my-iterate f (f init)))))
