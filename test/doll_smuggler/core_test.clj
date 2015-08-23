(ns doll-smuggler.core-test
  (:require [clojure.test :refer :all]
            [doll-smuggler.core :refer :all]))

; Define doll-set-0
(def doll-set-0 '())

; Define doll-set-1
(def doll-set-1 '[{:name "luke", :weight "9", :value "150"}
                {:name "anthony", :weight "13", :value "35"}
                {:name "candice", :weight "153", :value "200"}
                {:name "dorothy", :weight "50", :value "160"}
                {:name "puppy", :weight "15", :value "60"}
                {:name "thomas", :weight "68", :value "45"}
                {:name "randal", :weight "27", :value "60"}
                {:name "april", :weight "39", :value "40"}
                {:name "nancy", :weight "23", :value "30"}
                {:name "bonnie", :weight "52", :value "10"}
                {:name "marc", :weight "11", :value "70"}
                {:name "kate", :weight "32", :value "30"}
                {:name "tbone", :weight "24", :value "15"}
                {:name "tranny", :weight "48", :value "10"}
                {:name "uma", :weight "73", :value "40"}
                {:name "grumpkin", :weight "42", :value "70"}
                {:name "dusty", :weight "43", :value "75"}
                {:name "grumpy", :weight "22", :value "80"}
                {:name "eddie", :weight "7", :value "20"}
                {:name "tory", :weight "18", :value "12"}
                {:name "sally", :weight "4", :value "50"}
                {:name "babe", :weight "30", :value "10"}])

; Define doll-solution-1
(def doll-solution-1 '[{:name "luke", :weight "9", :value "150"}
                       {:name "anthony", :weight "13", :value "35"}
                       {:name "candice", :weight "153", :value "200"}
                       {:name "dorothy", :weight "50", :value "160"}
                       {:name "puppy", :weight "15", :value "60"}
                       {:name "randal", :weight "27", :value "60"}
                       {:name "marc", :weight "11", :value "70"}
                       {:name "grumpkin", :weight "42", :value "70"}
                       {:name "dusty", :weight "43", :value "75"}
                       {:name "grumpy", :weight "22", :value "80"}
                       {:name "eddie", :weight "7", :value "20"}
                       {:name "sally", :weight "4", :value "50"}
                       ])

; Define doll-set-2
(def doll-set-2 '[{:name "justin", :weight "4", :value "12"}
                {:name "david", :weight "6", :value "10"}
                {:name "kristen", :weight "5", :value "8"}
                {:name "edward", :weight "7", :value "11"}
                {:name "pauline", :weight "3", :value "15"}
                {:name "nathalie", :weight "1", :value "7"}
                {:name "frank", :weight "6", :value "9"}])

; Define doll-solution-2
(def doll-solution-2 '[{:name "justin", :weight "4", :value "12"}
                  {:name "david", :weight "6", :value "10"}
                  {:name "kristen", :weight "5", :value "8"}
                  {:name "pauline", :weight "3", :value "15"}])

; Define doll-set-3
(def doll-set-3 '[{:name "justin", :weight "4", :value nil}
                {:name "david", :weight "6", :value "10"}
                {:name "kristen", :weight "5", :value "8"}
                {:name "edward", :weight "7", :value "11"}
                {:name "pauline", :weight "3", :value "15"}
                {:name "nathalie", :weight "1", :value "7"}
                {:name "frank", :weight "6", :value "9"}])

; Define doll-solution-3
(def doll-solution-3 '[{:name "david", :weight "6", :value "10"}
                  {:name "edward", :weight "7", :value "11"}
                  {:name "pauline", :weight "3", :value "15"}
                  {:name "nathalie", :weight "1", :value "7"}])

; Define doll-solution-4
(def doll-solution-4 '[{:name "luke", :weight "9", :value "150"}
                       ])

; Test the get-dolls function against the test input 0 which is an empty file.
(deftest get-dolls-test-input-0
  (testing "Population of dolls for doll-set-0"
    (def solution '())
    (is (= (get-dolls "resources/doll-set-0" #"\W+") solution))))

; Test the get-dolls function against the test input 1.
(deftest get-dolls-test-input-1
  (testing "Population of dolls for doll-set-1"
    (is (= (get-dolls "resources/doll-set-1" #"\W+") doll-set-1))))

; Test the get-dolls function against the test input 2.
(deftest get-dolls-test-input-2
  (testing "Population of dolls for doll-set-2"
    (is (= (get-dolls "resources/doll-set-2" #"\W+") doll-set-2))))

; Test the get-dolls function against the test input 3 which is missing a field.
(deftest get-dolls-test-input-3
  (testing "Population of dolls for doll-set-3"
    (is (= (get-dolls "resources/doll-set-3" #"\W+") doll-set-3))))

; Test the parse int function with a nil input.
(deftest parse-int-nil-string
  (testing "Pass a nil into the parse-int function"
    (def solution 0)
    (is (= (parse-int nil) solution))))

; Test the parse int function with a empty string as input.
(deftest parse-int-empty-string
  (testing "Pass an empty string into the parse-int function"
    (def solution 0)
    (is (= (parse-int "") solution))))

; Test the parse int function with a valid string as input.
(deftest parse-int-valid-string
  (testing "Pass an valid string into the parse-int function"
    (def solution 1)
    (is (= (parse-int "01") solution))))

; Test the parse int function with a invalid string as input.
(deftest parse-int-invalid-string
  (testing "Pass an invalid string into the parse-int function"
    (def solution 0)
    (is (= (parse-int "AA") solution))))

; Test the total-doll-value function against the test input 0 which is an empty file.
(deftest total-doll-value-test-input-0
  (testing "Population of dolls for doll-set-0"
    (def solution 0)
    (is (= (total-doll-value doll-set-0) solution))))

; Test the total-doll-value function against the test input 1.
(deftest total-doll-value-test-input-1
  (testing "Population of dolls for doll-set-1"
    (def solution 1272)
    (is (= (total-doll-value doll-set-1) solution))))

; Test the total-doll-value function against the test input 2.
(deftest total-doll-value-test-input-2
  (testing "Population of dolls for doll-set-2"
    (def solution 72)
    (is (= (total-doll-value doll-set-2) solution))))

; Test the total-doll-value function against the test input 3 which is missing a field.
(deftest total-doll-value-test-input-3
  (testing "Population of dolls for doll-set-3"
    (def solution 60)
    (is (= (total-doll-value doll-set-3) solution))))

; Test the compute-solution function against the test input 0 which is an empty file.
(deftest compute-solution-test-input-0
  (testing "Population of dolls for doll-set-0"
    (def solution [])
    (is (= (compute-solution 100 doll-set-0) solution))))

; Test the compute-solution function against the test input 1.
(deftest compute-solution-test-input-1
  (testing "Population of dolls for doll-set-1"
    (is (= (compute-solution 400 doll-set-1) doll-solution-1))))

; Test the compute-solution function against the test input 2.
(deftest compute-solution-test-input-2
  (testing "Population of dolls for doll-set-2"
    (is (= (compute-solution 18 doll-set-2) doll-solution-2))))

; Test the compute-solution function against the test input 3.
(deftest compute-solution-test-input-3
  (testing "Population of dolls for doll-set-3"
    (is (= (compute-solution 18 doll-set-3) doll-solution-3))))

; Test the compute-solution function against the test input 4.
(deftest compute-solution-test-input-4
  (testing "Population of dolls for doll-set-1"
    (is (= (compute-solution 10 doll-set-1) doll-solution-4))))