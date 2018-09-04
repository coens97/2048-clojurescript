(ns game-2048.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::board
 (fn [db]
   (:board db)))
