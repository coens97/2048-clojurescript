(ns game-2048.boardevents
  (:require
   [re-frame.core :as re-frame]
   [game-2048.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

;; Get board value
(defn get-board
  [board x y]
  (-> board
      (nth y)
      (nth x)))

;; Set board value
(defn set-board
  [board x y value]
  (assoc board y
         (assoc (nth board y) x value)))

;; Add cell 2 to random position of the board
(defn randomcell
  [board]
  (set-board board (rand-int 4) (rand-int 4) 2))

;; Generate empty board used at the start of the game
(defn empty-board
  []
  (->
   (into [] (map
             (fn [x] (into [] (map
                               (fn [x] 0)
                               (range 0 4))))
             (range 0 4)))
   (randomcell)
   (randomcell)))

(defn board-move-down
  [board]
  (for [y (range 2 -1 -1)] ;; (2 1 0)
    (for [x (range 0 4)] ;; (2 1 0)
      (let [item (->
                  board
                  (nth y)
                  (nth x))]
        (cljs.pprint/pprint item))))
  board)

(board-move-down [[0 2 2 0] [0 0 0 0] [0 0 2 0] [0 0 0 0]])
;; Event when user wants to move down
(re-frame/reg-event-db
 ::movedown
 (fn-traced [db [_ _]]
            (let [{board :board} db]
              (assoc db :board (board-move-down board)))))
