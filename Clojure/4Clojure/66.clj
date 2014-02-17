(def gcd

(fn gcd
  ([a b]
   (if (> a b)
     (gcd a b nil)
     (gcd b a nil)))
  ([a b x]
   (let [r (rem a b)]
     (if (zero? r)
       b
       (gcd b r nil)))))

  )
(gcd 2 4)
