(ns game-2048.events
  (:require
   [re-frame.core :as re-frame]
   [game-2048.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(defn randomcell
  [board]
  (let [randvalue (rand-int 4)]
    (assoc board randvalue
           (assoc (nth board randvalue) (rand-int 4) 2))))

(re-frame/reg-event-db
 ::start-game
 (fn-traced [db [_ _]]
            (assoc db :board (->
                              (into [] (map
                                        (fn [x] (into [] (map
                                                          (fn [x] 0)
                                                          (range 0 4))))
                                        (range 0 4)))
                              (randomcell)
                              (randomcell)))))