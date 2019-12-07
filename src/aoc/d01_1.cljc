; day01: https://adventofcode.com/2019/day/1

(ns aoc.d01-1
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

(assert (= (fuel-required 100756) 33583))
(assert (= (fuel-required 2) 0))

(reduce + (map fuel-required data)) ; 3373568
