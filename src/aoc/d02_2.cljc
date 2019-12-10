; day02-2: https://adventofcode.com/2019/day/2

 (ns aoc.d02-2
  (:require
   [aoc.d02-data :refer [input]]
   [clojure.string :as str]))


(def data
  (map #(Integer/parseInt %) 
     (str/split input #",")))

; Part One
(def alarm-data (assoc (vec data) 1 12 2 2))

(defn ans [data n]
  (if (= 99 (nth data n))
    (nth data 0)
    (let [idx-a (nth data (+ n 1))
          idx-b (nth data (+ n 2))
          a (nth data idx-a)
          b (nth data idx-b)
          idx (nth data (+ n 3))
          opcode (nth data n)
          new-n (+ n 4)]
      (if (= opcode 1)
        (ans (assoc data idx (+ a b)) new-n)
        (ans (assoc data idx (* a b)) new-n)))))

(def output 19690720)

(for [x (range 0 99)
       y (range 0 99)]
  (let [data (assoc (vec data) 1 x 2 y)]
    (if (= output (ans data 0))
      (println x y))))
