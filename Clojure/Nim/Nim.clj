;Joey Bloom
;Assignment #
;A simple game of Nim, with the heaps beginning at 3, 4, and 5

(require '[clojure.string :as string])
(println "hello")
(println "welcome to Nim")
(println "What is player one's name?")
(def p1 (read-line))
(println "What is player two's name?")
(def p2 (read-line))
(println (str "Welcome " p1 " and " p2 "."))
(println "Heaps A, B, and C start at 3, 4 and 5 respectively. To make a move,")
(println "type the letter of the pile followed by the number to remove")
(println "from that pile. Example: \"A5\"")
(defn getWinnerName [pOnesTurn] (if pOnesTurn p1 p2))
(def winner ((fn nim[a b c pOnesTurn]
  (cond
    (= 0 a b c)
		(getWinnerName pOnesTurn)
	(or (< a 0) (< b 0) (< c 0))
		(do
		  	(println "You took too many from the heap! You lose!")
			(getWinnerName pOnesTurn)
		)
	:else
		(do
		  	(print (str a " " b " " c " " (if pOnesTurn p1 p2) "'s turn: "))
		  	(def input (string/capitalize (read-line)))
		  	(println input)
		  	(let [letter (str (first input))
				  number (. Integer parseInt (subs input 1))]
		  		(cond
			    	(= "A" letter)
				    	(nim (- a number) b c (not pOnesTurn))
			    	(= "B" letter)
				    	(nim a (- b number) c (not pOnesTurn))
			    	(= "C" letter)
				    	(nim a b (- c number) (not pOnesTurn))
			)
		  )
		)
  )
) 3 4 5 true))
(println (str "Winner: " winner "!!!"))

;;;;;;Input 1:
;Joey1
;Svetty2
;A1
;a1
;A1
;B2
;B1
;B1
;C2
;C4

;;;;;;Output 1:
;hello
;welcome to Nim
;What is player one's name?
;What is player two's name?
;Welcome Joey1 and Svetty2.
;Heaps A, B, and C start at 3, 4 and 5 respectively. To make a move,
;type the letter of the pile followed by the number to remove
;from that pile. Example: "A5"
;3 4 5 Joey1's turn: A1
;2 4 5 Svetty2's turn: A1
;1 4 5 Joey1's turn: A1
;0 4 5 Svetty2's turn: B2
;0 2 5 Joey1's turn: B1
;0 1 5 Svetty2's turn: B1
;0 0 5 Joey1's turn: C2
;0 0 3 Svetty2's turn: C4
;You took too many from the heap! You lose!
;Winner: Joey1!!!

;;;;;;Input 2:
;Joey1
;Svetty2
;A1
;a1
;A1
;B2
;B1
;B1
;C2
;C3

;;;;;;Output 2:
;hello
;welcome to Nim
;What is player one's name?
;What is player two's name?
;Welcome Joey1 and Svetty2.
;Heaps A, B, and C start at 3, 4 and 5 respectively. To make a move,
;type the letter of the pile followed by the number to remove
;from that pile. Example: "A5"
;3 4 5 Joey1's turn: A1
;2 4 5 Svetty2's turn: A1
;1 4 5 Joey1's turn: A1
;0 4 5 Svetty2's turn: B2
;0 2 5 Joey1's turn: B1
;0 1 5 Svetty2's turn: B1
;0 0 5 Joey1's turn: C2
;0 0 3 Svetty2's turn: C3
;Winner: Joey1!!!