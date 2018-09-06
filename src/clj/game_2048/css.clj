(ns game-2048.css
  (:require [garden.def :refer [defstyles]]
            [garden.stylesheet :refer [at-media]]))

(defstyles screen
  (at-media {:max-width "768px"}
            [:html {:font-size "0.85em"}]) ; REM is based on the font-size, scaling down the game on a small screen

  (at-media {:max-width "540px"}
            [:html {:font-size "0.75em"}])

  (at-media {:max-width "360px"}
            [:html {:font-size "0.6em"}])

  [:body :html {:position "fixed"}]

  [:body {:background-color "#ABA8AA"
          :font-family "\"Lucida Console\", Monaco, monospace"
          :color "#3A3A47"
          :width "100%"}]

  [:#mainContainer {:background-color "#EDEFE5"
                    :width "26.5rem"
                    :height "35rem"
                    :margin "2rem auto"
                    :border-radius "0.5rem"
                    :padding "1rem"}]

  [:#header [:a {:float "right"}]]

  ;[:#game-panel {:background-color "#9DB6BB"
  ;               :border-radius "1rem"
  ;               :padding-bottom "0.5rem"}]

  [:.score {:background-color "#727E8C"
            :border-radius "0.5rem"
            :padding "0.5rem"
            :margin "0.62rem"
            :color "#FFFFFF"
            :text-align "center"
            :width "11rem"
            :float "left"}
   [:h3 :p {:margin "0"}]]

  [:.board {:background-color "#DDDDDD"
            :border-radius "0.5rem"
            :padding "0.5rem"
            :position "absolute"
            :width "25rem"
            :margin "0.25rem"}]

  [:.board-row {:clear "both"}]

  [:.board-cell {:background-color "#BBBBBB"
                 :border-radius "0.25rem"
                 :padding "0.5rem"
                 :float "left"
                 :width "5rem"
                 :height "3.5rem"
                 :text-align "center"
                 :font-size "2rem"
                 :padding-top "1.5rem"
                 :margin "0.125rem"}]

  [:#gameover {:width "26.5rem"
               :height "24.5rem"
               :position "absolute"
               :background-color "rgb(255, 235, 122, 0.3)"
               :z-index "2"}]

  [:#gameover [:h1 {:margin-top "4rem"
                    :margin-bottom "2.75rem"
                    :text-align "center"
                    :font-size "3rem"}]]

  [:.menubutton [:h2 {:text-align "center"
                      :border "0.25rem solid #9DB6BB"
                      :border-radius "1rem"
                      :margin "0 auto"
                      :width "14rem"
                      :padding "0.5rem"
                      :cursor "pointer"}]]

  [:.clear {:clear "both"}])