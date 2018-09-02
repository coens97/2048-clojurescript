  (set-env! 
    :source-paths #{"src/clj" "src/cljs"}
    :dependencies '[[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [reagent "0.7.0"]
                 [re-frame "0.10.5"]
                 [garden "1.3.5"]
                 [ns-tracker "0.3.1"]
                 [re-pressed "0.2.2"]
                 [tolitius/boot-check "0.1.11"]]
    )

(require '[tolitius.boot-check :as check])

(deftask check []
  (comp
    (check/with-yagni)
    (check/with-eastwood)
    (check/with-kibit)
    (check/with-bikeshed)))