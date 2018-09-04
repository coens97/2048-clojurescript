(ns game-2048.boardevents
  (:require
   [re-frame.core :as re-frame]
   [game-2048.db :as db]
   [game-2048.events :as events]
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

;; Event after move when new block is created
(defn new-block [board]
  (let [empty-cells
        (filter ;; get all empty cells
         (fn [[x y]] (= (get-board board x y) 0))
         (for [e1 (range 0 4)
               e2 (range 0 4)]
           (list e1 e2)))
        empty-count (count empty-cells)];; count empty cells
    (if (= empty-count 0)
      (do ; game-over if no cells left
        (re-frame/dispatch [::events/gameover])
        board)
      (let [[x y] (nth empty-cells (rand-int empty-count))] ;; randomly pick cell
        (set-board
         board x y
         (if (= (rand-int 4) 0) 4 2)))))) ;; 75% change on 2

(re-frame/reg-event-db
 ::newblock
 (fn-traced [db [_ _]]
            (let [{board :board} db]
              (assoc db :board (new-block board)))))

;; Drop block recursively, move or merge if possible
(defn drop-block [board x y]
  (if (< y 3) ;; Stop condition for recursion
    (let [item (get-board board x y)
          item-below (get-board board x (inc y))]
      (cond
        (= item 0) board ;; Skip
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
  (let [board-result
        (reduce
         (fn [board [y x]]
           (drop-block board x y))
         board-in
         (for [e1 (range 2 -1 -1)
               e2 (range 0 4)]
           (list e1 e2)))]

    (if (not= board-in board-result) ;; If blocks have moved
      (re-frame/dispatch [:game-2048.boardevents/newblock]))
    board-result))

;; Event when user wants to move down
(re-frame/reg-event-db
 ::movedown
 (fn-traced [db [_ _]]
            (let [{board :board} db]
              (assoc db :board (board-move-down board)))))

(defn rotate-right [coll]
  (apply mapv #(into [] %&)
         (vec (reverse coll))))

(defn rotate-left [coll]
  (->
   coll
   reverse
   rotate-right
   reverse
   vec))

;; Event when user wants to move left
(defn board-move-left [board]
  (->
   board
   rotate-left
   board-move-down
   rotate-right))

(re-frame/reg-event-db
 ::moveleft
 (fn-traced [db [_ _]]
            (let [{board :board} db]
              (assoc db :board (board-move-left board)))))

;; Event when user wants to move right
(defn board-move-right [board]
  (->
   board
   rotate-right
   board-move-down
   rotate-left))

(re-frame/reg-event-db
 ::moveright
 (fn-traced [db [_ _]]
            (let [{board :board} db]
              (assoc db :board (board-move-right board)))))

;; Event when user wants to move up
(defn board-move-up [board]
  (->
   board
   rotate-right
   board-move-down
   rotate-left))

(re-frame/reg-event-db
 ::moveup
 (fn-traced [db [_ _]]
            db))
