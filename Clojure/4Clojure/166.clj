(fn [lt a b]
  (cond (lt a b)
        	:lt
        (lt b a)
        	:gt
        :else
        	:eq))
