(ns game-2048.boardevents
  (:require
   [re-frame.core :as re-frame]
   [game-2048.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

(defn randomcell
  [board]
  (let [randvalue (rand-int 4)]
    (assoc board randvalue
           (assoc (nth board randvalue) (rand-int 4) 2))))

(defn empty-board []
  (->
   (into [] (map
             (fn [x] (into [] (map
                               (fn [x] 0)
                               (range 0 4))))
             (range 0 4)))
   (randomcell)
   (randomcell)))

(re-frame/reg-event-db
 ::movedown
 (fn-traced [db [_ _]]
            (cljs.pprint/pprint db)
            db))