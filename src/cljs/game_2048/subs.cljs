(ns game-2048.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::board
 (fn [db]
   (:board db)))

(re-frame/reg-sub
 ::score
 (fn [db]
   (:score db)))

(re-frame/reg-sub
 ::highscore
 (fn [db]
   (:highscore db)))

(re-frame/reg-sub
 ::gameover
 (fn [db]
   (:gameover db)))
