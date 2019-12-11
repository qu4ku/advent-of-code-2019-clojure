; day04-1: https://adventofcode.com/2019/day/4

(ns aoc.d04-1
 (:require
  [clojure.string :as str]))


(def input "256310-732736")

(def a (first (map #(Integer/parseInt %) (str/split input #"-"))))
(def b (second (map #(Integer/parseInt %) (str/split input #"-"))))

(defn filter-double [x]
  (filter #(re-find #"(.)\1+" (str %)) x))

(defn is-sorted? [x]
  (apply <= (map 
              #(Integer/parseInt (str  %)) 
              (apply list (str x)))))

(defn filter-sorted [x]
  (filter is-sorted? x))
  
; ans   
(->> (range a (inc b))
     filter-double
     filter-sorted
     count) ; 979
