(ns game-2048.events
  (:require
   [re-frame.core :as re-frame]
   [game-2048.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(re-frame/reg-event-db
 ::set-re-pressed-example
 (fn [db [_ value]]
   (assoc db :re-pressed-example value)))

(re-frame/reg-event-db
 ::start-game
 (fn-traced [db [_ _]]
            (assoc db :board (->
                              (into [] (map
                                        (fn [x] (into [] (map
                                                          (fn [x] 0)
                                                          (range 0 4))))
                                        (range 0 4)))
                              (assoc (rand-int 4) [0 2 2 0])))))