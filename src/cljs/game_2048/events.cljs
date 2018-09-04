(ns game-2048.events
  (:require
   [re-frame.core :as re-frame]
   [game-2048.db :as db]
   [re-pressed.core :as rp]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(re-frame/reg-event-db
 ::movedown
 (fn-traced [db [_ _]]
            (cljs.pprint/pprint db)
            db))

(defn randomcell
  [board]
  (let [randvalue (rand-int 4)]
    (assoc board randvalue
           (assoc (nth board randvalue) (rand-int 4) 2))))

(defn setkeyboardrules []
  (re-frame/dispatch
   [::rp/set-keydown-rules
    {:event-keys [[[:game-2048.events/movedown]
                   [{:which 83}]]] ;; s
     :clear-keys
     [[{:which 27}]]}]) ;; escape
  (re-frame/dispatch
   [::rp/set-keydown-rules
    {:event-keys [[[:game-2048.events/movedown]
                   [{:which 40}]]] ;; arrow down
     :clear-keys
     [[{:which 27}]]}]) ;; escape
)

(re-frame/reg-event-db
 ::start-game
 (fn-traced [db [_ _]]
            (setkeyboardrules)
            (assoc db :board (->
                              (into [] (map
                                        (fn [x] (into [] (map
                                                          (fn [x] 0)
                                                          (range 0 4))))
                                        (range 0 4)))
                              (randomcell)
                              (randomcell)))))