(ns game-2048.events
  (:require
   [re-frame.core :as re-frame]
   [game-2048.db :as db]
   [re-pressed.core :as rp]
   [game-2048.boardevents :as boardevents]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(defn setkeyboardrules []
  (re-frame/dispatch
   [::rp/set-keydown-rules
    {:event-keys [[[::boardevents/movedown]
                   [{:which 83}]]] ;; s
     :clear-keys
     [[{:which 27}]]}]) ;; escape
)

(re-frame/reg-event-db
 ::start-game
 (fn-traced [db [_ _]]
            (setkeyboardrules)
            (assoc db :board (boardevents/empty-board))))