(ns game-2048.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {:background-color "#EEEEEE"
          :font-family "\"Lucida Console\", Monaco, monospace"}]

  [:#mainContainer {:background-color "#FFFFFF"
                    :width "80%"
                    :margin "2rem auto"
                    :border-radius "1rem"
                    :padding "0.5rem"}]

  [:#header [:a {:float "right"}]])
