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

;; Initialize game event
(re-frame/reg-event-db
 ::start-game
 (fn-traced [db [_ _]]
            (-> db
                (assoc :board (boardevents/empty-board))
                (assoc :gameover false)
                (assoc :score 0))))