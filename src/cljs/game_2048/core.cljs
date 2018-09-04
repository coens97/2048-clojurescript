(ns game-2048.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [re-pressed.core :as rp]
   [game-2048.events :as events]
   [game-2048.boardevents :as boardevents]
   [game-2048.views :as views]
   [cljsjs.hammer]
   [game-2048.config :as config]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

;; Listen to keyboard events
(defn setkeyboardrules []
  (re-frame/dispatch
   [::rp/set-keydown-rules
    {:event-keys [[[::boardevents/moveleft]
                   [{:which 65}] ;; a
                   [{:which 37}]] ;; arrow
                  [[::boardevents/moveright]
                   [{:which 68}] ;; d
                   [{:which 39}]] ;; arrow
                  [[::boardevents/moveup]
                   [{:which 87}] ;; w
                   [{:which 38}]] ;; arrow
                  [[::boardevents/movedown]
                   [{:which 83}] ;; s
                   [{:which 40}]]] ;; arrow
     :clear-keys
     [[{:which 27}]]}]) ;; escape
)

;; Create swipe event for touch
(defn init-touch []
  (let [mc (new js/Hammer js/document)]
    (js-invoke ;; (js/hammertime.get('swipe').set({ direction: Hammer.DIRECTION_ALL }))
     (js-invoke mc "get" "swipe")
     "set"
     #js{:direction js/Hammer.DIRECTION_ALL})
    (js-invoke mc "on" "swipe" ;; event listener on swipe
               (fn [event]
                 (let [eventparsed (js->clj event :keywordize-keys true)
                       {:keys [direction]} eventparsed]
                   (case direction
                     2 (re-frame/dispatch [::boardevents/moveleft])
                     4 (re-frame/dispatch [::boardevents/moveright])
                     8 (re-frame/dispatch [::boardevents/moveup])
                     16 (re-frame/dispatch [::boardevents/movedown])))))))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (re-frame/dispatch-sync [::rp/add-keyboard-event-listener "keydown"])
  (dev-setup)
  (mount-root)
  (re-frame/dispatch-sync [::events/start-game])
  (setkeyboardrules)
  (init-touch))
