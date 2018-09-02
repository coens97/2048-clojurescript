(ns game-2048.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {
    :background-color "#EEEEEE"
    }]
  
  [:.mainContainer {
    :background-color "#FFFFFF"
    :width "80%"
    :margin "0 auto"
  }]
)
