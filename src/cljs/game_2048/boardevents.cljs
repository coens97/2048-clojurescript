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

;; Drop block recursively, move or merge if possible
(defn drop-block [board x y]
  (if (< y 3) ;; Stop condition for recursion
    (let [item (get-board board x y)
          item-below (get-board board x (inc y))]
      (cond
        (= item-below 0) (-> board ;; Move
                             (set-board x y 0)
                             (set-board x (inc y) item)
                             (drop-block x (inc y)))
        (= item-below item) (-> board ;; Merge
                                (set-board x y 0)
                                (set-board x (inc y) (* 2 item))
                                (drop-block x (inc y)))
        :else board))
    board))

;; Go through cells
(defn board-move-down [board-in]
  (reduce
   (fn [board [y x]]
     (let [item (->
                 board
                 (nth y)
                 (nth x))]
       (if (= item 0)
         board
         (drop-block board x y))))
   board-in
   (for [e1 (range 2 -1 -1)
         e2 (range 0 4)]
     (list e1 e2))))

(board-move-down [[0 2 2 0] [0 0 0 0] [0 0 2 0] [0 0 0 0]])
;; Event when user wants to move down
(re-frame/reg-event-db
 ::movedown
 (fn-traced [db [_ _]]
            (let [{board :board} db]
              (assoc db :board (board-move-down board)))))