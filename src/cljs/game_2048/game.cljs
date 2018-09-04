(ns game-2048.game
  (:require
   [re-frame.core :as re-frame]
   [game-2048.subs :as subs]))

;; Panel used to show Score and Best score
(defn score-panel [header value]
  [:div.score
   [:h3 header]
   [:p value]])

;; Displaying the game cell
(defn cell-panel [x]
  [:div.board-cell
   (if (= x 0) "" x)])

;; Displaying the game board with the cells in it
(defn board-panel []
  (let [board (re-frame/subscribe [::subs/board])] ;; Get the current state of the board
    [:div.board
     ;(cljs.pprint/pprint board) ; debugging to console.log the board data
     (map ;; Each row of the board
      (fn [x]
        [:div.board-row (map ;; Each cell of the row
                         cell-panel
                         x)])
      @board)
     [:br.clear]]))

;; Main game panel
(defn game-panel []
  [:div#game-panel
   (score-panel "Score" 0)
   (score-panel "Best score" 0)
   [:br.clear]
   (board-panel)])