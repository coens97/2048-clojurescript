(ns game-2048.events
  (:require
   [re-frame.core :as re-frame]
   [game-2048.db :as db]
   [re-pressed.core :as rp]
   [game-2048.boardevents :as boardevents]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

;; Initialize database event
(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

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

;; Initialize game event
(re-frame/reg-event-db
 ::start-game
 (fn-traced [db [_ _]]
            (setkeyboardrules)
            (assoc db :board (boardevents/empty-board))))

;; Game over
(re-frame/reg-event-db
 ::gameover
 (fn-traced [db [_ _]]
            db));; TODO!