(ns game-2048.views
  (:require
   [re-frame.core :as re-frame]
   [game-2048.events :as events]
   [game-2048.game :as game]))

(defn main-panel []
  [:div#mainContainer
   [:h1#header "2048"
    [:a {:href "https://github.com/coens97/2048-clojurescript" :target "_blank"}
     "GitHub"]]
   (game/game-panel)])
