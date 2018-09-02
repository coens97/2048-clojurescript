(ns game-2048.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [game-2048.core-test]))

(doo-tests 'game-2048.core-test)
