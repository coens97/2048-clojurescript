(ns game-2048.game
  (:require
   [re-frame.core :as re-frame]
   [game-2048.events :as events]
   [game-2048.subs :as subs]))

;; Panel used to show Score and Best score
(defn score-panel [header value]
  [:div.score
   [:h3 header]
   [:p value]])

;; Displaying the game cell
(defn cellcolors [cellvalue]
  (case cellvalue
    0 "#BBBBBB"
    2 "#7fbfc7"
    4 "#82b4c2"
    8 "#86aabc"
    16 "#899fb7"
    32 "#8c95b1"
    64 "#908aac"
    128 "#937fa7"
    256 "#9675a1"
    512 "#996a9c"
    1024 "#9d5f97"
    2048 "#a05591"
    4096 "#a34a8c"
    8192 "#a74086"
    "default" "#aa3581"))

(defn cell-panel [index x]
  ^{:key index}
  [:div.board-cell
   {:style {:background-color (cellcolors x)}}
   (if (= x 0) "" x)])

;; Displaying the game board with the cells in it
(defn board-panel []
  (let [board (re-frame/subscribe [::subs/board])] ;; Get the current state of the board
    [:div.board
     ;(cljs.pprint/pprint board) ; debugging to console.log the board data
     (map-indexed ;; Each row of the board
      (fn [index x]
        ^{:key index}
        [:div.board-row
         (map-indexed ;; Each cell of the row
          cell-panel
          x)])
      @board)
     [:br.clear]]))

;; Gameover panel
(defn gameover-panel []
  [:div#gameover
   [:h1 "GAME OVER"]
   [:div.menubutton
    {:on-click #(re-frame/dispatch [::events/start-game])}
    [:h2 "NEW GAME"]]])

;; Main game panel
(defn game-panel []
  (let [score (re-frame/subscribe [::subs/score])
        highscore (re-frame/subscribe [::subs/highscore]);; Get the current state of the score
        gameover (re-frame/subscribe [::subs/gameover])]
    [:div#game-panel
     (score-panel "Score" @score)
     (score-panel "Best score" @highscore)
     [:br.clear]
     (if @gameover (gameover-panel))
     (board-panel)]))