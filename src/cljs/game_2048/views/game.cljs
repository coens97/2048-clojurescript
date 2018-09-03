(ns game-2048.game)

(defn score []
  [:div.score
   [:h3 "Score"]
   [:p "0"]])

(defn game-panel []
  [:div#game-panel
   (score)
   (score)
   [:br.clear]])