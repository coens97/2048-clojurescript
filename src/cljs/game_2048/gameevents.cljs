(ns game-2048.gameevents
  (:require
   [re-frame.core :as re-frame]
   [game-2048.db :as db]

   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

;; Game over
(re-frame/reg-event-db
 ::gameover
 (fn-traced [db [_ _]]
            db));; TODO!

;; Increase score
(re-frame/reg-event-db
 ::increase-score
 (fn-traced [db [_ increaseby]]
            (let [{score :score} db]
              (assoc db :score (+ score increaseby)))))