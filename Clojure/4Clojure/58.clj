(defn compo
  ([f] f)
;;   ([f g]
;;    (fn [& args]
;;      (f (apply g args))))
  ([f & fs]
   (fn [& args]
     (f (apply (apply compo fs) args)))))

((compo (partial + 3) -) 2 1)

;; (f (apply g args))
;; (e (apply (f (apply g args)) args))