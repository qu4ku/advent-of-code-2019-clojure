; day02-1: https://adventofcode.com/2019/day/2

 (ns aoc.d02-1
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

(def test-data (map #(Integer/parseInt %) (str/split "1,9,10,3,2,3,11,0,99,30,40,50" #",")))
(assert (= 3500 (ans (vec test-data) 0)))

(ans alarm-data 0)  ; 2894520
