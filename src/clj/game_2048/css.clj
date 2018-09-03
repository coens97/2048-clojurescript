(ns game-2048.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:body {:background-color "#ABA8AA"
          :font-family "\"Lucida Console\", Monaco, monospace"
          :color "#3A3A47"}]

  [:#mainContainer {:background-color "#EDEFE5"
                    :width "50rem"
                    :margin "2rem auto"
                    :border-radius "1rem"
                    :padding "0.5rem"}]

  [:#header [:a {:float "right"}]]

  [:#game-panel {:background-color "#9DB6BB"
                 :margin "1rem"
                 :border-radius "1rem"
                 :padding "0.5rem"}]

  [:.score {:background-color "#727E8C"
            :margin "1rem"
            :border-radius "1rem"
            :padding "0.5rem"
            :color "#FFFFFF"
            :text-align "center"
            :width "20rem"
            :float "left"}]

  [:.clear {:clear "both"}])