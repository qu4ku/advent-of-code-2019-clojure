; day04-2: https://adventofcode.com/2019/day/4

(ns aoc.d04-2
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

(defn same-counter [last-char number counter acc]
  "Takes a string and returs list of repeating numbers eg 99333 returns [2 3]"
  (if (empty? number)
    (conj acc counter)
    (let [char (first number)
          new-number (rest number)
          new-counter (if (= last-char char) (inc counter) 1)
          new-acc (if (= last-char char) acc (conj acc counter))]
      (recur char new-number new-counter new-acc))))
  
(defn is-double-only? [x]
  (let [x (str x)
        result (same-counter (first x) (seq x) 0 [])]
    (some #(= 2 %) result)))

(defn filter-double-only [x]
  (filter is-double-only? x))

; ans   
(->> (range a (inc b))
     filter-double
     filter-sorted
     filter-double-only
     count) ; 635
