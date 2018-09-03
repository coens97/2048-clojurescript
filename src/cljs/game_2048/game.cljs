(ns game-2048.game
  (:require
   [re-frame.core :as re-frame]
   [game-2048.subs :as subs]))

(defn score-panel [header value]
  [:div.score
   [:h3 header]
   [:p value]])

(defn cell-panel [x]
  [:div.board-cell x])

(defn board-panel []
  (let [board (re-frame/subscribe [::subs/board])]
    [:div.board
     ;(cljs.pprint/pprint board) ; debugging to console.log the board date
     (map
      (fn [x]
        [:div.board-row (map
                         cell-panel
                         x)])
      @board)
     [:br.clear]]))

(defn game-panel []
  [:div#game-panel
   (score-panel "Score" 0)
   (score-panel "Best score" 0)
   [:br.clear]
   (board-panel)])