; day01: https://adventofcode.com/2019/day/1

(ns aoc.d01-2
  (:require
   [aoc.d01-data :refer [input]]
   [clojure.string :as str]))


(def data
  (map #(Integer/parseInt %) 
     (str/split-lines input)))

; Part One
(defn fuel-required [mass]
  (let [fuel (- (int (Math/floor (/ mass 3))) 2)]
    (if (< fuel 0) 0
      fuel)))

; Part Two
(defn full-fuel-requried [mass sum]
  (if (= 0 (fuel-required mass))
    sum
    (let [fuel-mass (fuel-required mass)]
      (recur fuel-mass (+ sum fuel-mass)))))
(assert (= (full-fuel-requried 100756 0) 50346))

(reduce + (map #(full-fuel-requried % 0) data))  ; 5057481
