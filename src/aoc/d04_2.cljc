; day04-1: https://adventofcode.com/2019/day/4

(ns aoc.d04-1
 (:require
  [clojure.string :as str]))


(def input "256310-732736")

(def a (first (map #(Integer/parseInt %) (str/split input #"-"))))
(def b (second (map #(Integer/parseInt %) (str/split input #"-"))))

(defn filter-double [x]
  (filter #(re-find #"(.)\2" (str %)) x))

(defn is-sorted? [x]
  (apply <= (map 
              #(Integer/parseInt (str  %)) 
              (apply list (str x)))))

(defn filter-sorted [x]
  (filter is-sorted? x))

(defn remove-3-or-more [x]
  (remove #(re-find #"(.)\1{3}" (str %)) x))

; ans   
(->> (range a (inc b))
     filter-double
     filter-sorted
     remove-3-or-more
     count) ; 979


(defn filter-two [x]
  (filter #(re-find #"(.)\2" (str %)) x))

(def test [34566666 99944 1234 2222333333 2290 222290])
(filter #(re-find #"(.)\1+" (str %)) test)
(filter #(re-find #"(.){2}" (str %)) test)

(defn remove-3-or-more [x]
  (remove #(re-find #"(.)\1{2,}" (str %)) x))

(remove-3-or-more test)

(remove #(re-find #"(.)\1{3}" (str %)) test)
(remove #(re-find #"(.)\3\3" (str %)) test)



(defn same-counter [last-char number counter acc]
  "Takes a string and returs list of repeating numbers eg 99333 returns [2 3]"
  (if (empty? number)
    (conj acc counter)
    (let [char (first number)
          new-number (rest number)
          new-counter (if (= last-char char) (inc counter) 1)
          new-acc (if (= last-char char) acc (conj acc counter))]
      (println char)
      (println new-number)
      (println new-counter)
      (println new-acc)
      (recur char new-number new-counter new-acc))))
  


(def test-s "aaaabbbcc")
(first test-s)

(same-counter (first test-s) (seq test-s) 0 [])

(seq "abcd")

(first "abb")


(conj [] 3)
