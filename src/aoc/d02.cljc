; day01: https://adventofcode.com/2019/day/2

 (ns aoc.d02
  (:require
   [aoc.d02-data :refer [input]]
   [clojure.string :as str]))


(def data
  (map #(Integer/parseInt %) 
     (str/split input #",")))

; Part One
(def alarm-data (assoc (vec data) 1 12 2 2))
(println (count alarm-data))

(def l [99 1 2 3 4 5 6])

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


; Part Two
; wip

(def output 19690720)

(time 
 (for [x (range 0 2)
       y (range 0 2)]
  (let [data (assoc (vec data) 1 x 2 y)]
    (if (= output (ans data 0))
      (println x y)))))

(time 
 (ans (assoc (vec data) 1 1 2 1) 0))


(ans (assoc (vec data) 1 0 2 0) 0)
