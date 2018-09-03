(ns game-2048.css
  (:require [garden.def :refer [defstyles]]
            [garden.stylesheet :refer [at-media]]))

(defstyles screen
  (at-media {:max-width "768px"}
            [:html {:font-size "0.75em"}]) ; REM is based on the font-size, scaling down the game on a small screen

  (at-media {:max-width "540px"}
            [:html {:font-size "0.5em"}])

  [:body {:background-color "#ABA8AA"
          :font-family "\"Lucida Console\", Monaco, monospace"
          :color "#3A3A47"}]

  [:#mainContainer {:background-color "#EDEFE5"
                    :width "40rem"
                    :margin "2rem auto"
                    :border-radius "1rem"
                    :padding "1rem"}]

  [:#header [:a {:float "right"}]]

  [:#game-panel {:background-color "#9DB6BB"
                 :border-radius "1rem"}]

  [:.score {:background-color "#727E8C"
            :border-radius "1rem"
            :padding "0.5rem"
            :margin "1rem"
            :color "#FFFFFF"
            :text-align "center"
            :width "17rem"
            :float "left"}]

  [:.clear {:clear "both"}])