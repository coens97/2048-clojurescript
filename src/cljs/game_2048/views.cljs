(ns game-2048.views
  (:require
   [re-frame.core :as re-frame]
   [re-pressed.core :as rp]
   [game-2048.events :as events]
   [game-2048.subs :as subs]
   ))

(defn dispatch-keydown-rules []
  (re-frame/dispatch
   [::rp/set-keydown-rules
    {:event-keys [[[::events/set-re-pressed-example "Hello, world!"]
                   [{:which 72} ;; h
                    {:which 69} ;; e
                    {:which 76} ;; l
                    {:which 76} ;; l
                    {:which 79} ;; o
                    ]]]

     :clear-keys
     [[{:which 27} ;; escape
       ]]}]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div.mainContainer
     [:h1 "2048"]
     ]))
