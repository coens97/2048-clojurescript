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

;; Set highscore
(re-frame/reg-event-db
 ::set-highscore
 (fn-traced [db [_ newscore]]
            (do
              (.setItem (.-localStorage js/window) "highscore" newscore) ;; Store higscore in local storage
              (assoc db :highscore newscore))))

;; Increase score
(re-frame/reg-event-db
 ::increase-score
 (fn-traced [db [_ increaseby]]
            (let [{score :score
                   highscore :highscore} db ;; get from current state
                  newscore (+ score increaseby)] ;; calculate new score
              (if (< highscore newscore) ;; Check if highscore is beat
                (re-frame/dispatch [:game-2048.gameevents/set-highscore newscore]))
              (assoc db :score newscore))))