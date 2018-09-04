(ns game-2048.db)

(def default-db
  {:board (map
           (fn [x] (map
                    (fn [x] 0)
                    (range 0 4)))
           (range 0 4))})
