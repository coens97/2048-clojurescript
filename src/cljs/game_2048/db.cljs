(ns game-2048.db)

;; Initial state of the game
(def default-db
  {:board (map
           (fn [x] (map
                    (fn [x] 0)
                    (range 0 4)))
           (range 0 4))
   :score 0
   :highscore 0})
