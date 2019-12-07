; day01: https://adventofcode.com/2019/day/3

(ns aoc.d03-1
 (:require
  [aoc.d03-data :refer [input]]
  [clojure.string :as str]))

(defn read-data [input]
  (map 
   #(str/split % #",") 
   (str/split-lines input)))
(def data (read-data input))

(defn read-direction [command]
  (str (first command)))
(defn read-distance [command]
  (Integer/parseInt (str/join (rest command))))

(assert (= "R" (read-direction "R30")))
(assert (= 30 (read-distance "R30")))

(defn R [point distance]
  (let [x (get point 0)
        y (get point 1)]
    (map (fn [n] (vector (+ x n) y)) (range 1 (+ 1 distance)))))

(defn L [point distance]
  (let [x (get point 0)
        y (get point 1)]
    (map (fn [n] (vector (- x n) y)) (range 1 (+ 1 distance)))))

(defn U [point distance]
  (let [x (get point 0)
        y (get point 1)]
    (map (fn [n] (vector x (+ y n))) (range 1 (+ 1 distance)))))

(defn D [point distance]
  (let [x (get point 0)
        y (get point 1)]
    (map (fn [n] (vector x (- y n))) (range 1 (+ 1 distance)))))

(assert (= 
         [[1 0] [2 0]]
         (R [0 0] 2)))
(assert (= 
         [[1 2] [0 2]]
         (L [2 2] 2)))
(assert (= 
         [[2 1] [2 0]]
         (D [2 2] 2)))
(assert (= 
         [[0 1] [0 2]]
         (U [0 0] 2)))

(defn run-command [point command]
  (let [direction (read-direction command)
        distance (read-distance command)]
    (cond
      (= "R" direction) (R point distance)
      (= "L" direction) (L point distance)
      (= "U" direction) (U point distance)
      (= "D" direction) (D point distance)
      :else (println "Error"))))

(assert (= 
         [[0 1] [0 2] [0 3] [0 4] [0 5]]
         (run-command [0 0] "U5")))

(defn get-points [point commands acc]
  (if (empty? commands)
    acc
    (let [command (first commands)
          rest-commands (rest commands)
          new-points (run-command point command)
          new-point (last new-points)
          new-acc (apply conj acc new-points)]
      (recur new-point rest-commands new-acc))))

(defn intersection [data]
  (let [a (get-points [0 0] (first data) [])
        b (get-points [0 0] (second data) [])]
    (clojure.set/intersection (apply hash-set a) (apply hash-set b))))

(defn ans [data]
  (apply min (map 
              #(+ (Math/abs (first %)) (Math/abs (second %))) 
              (intersection data))))

(def test "R75,D30,R83,U83,L12,D49,R71,U7,L72
U62,R66,U55,R34,D71,R55,D58,R83")
(def test-data (read-data test))
(assert (= 159 (ans test-data)))

(ans data) ; 266
