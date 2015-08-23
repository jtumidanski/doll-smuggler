(ns doll-smuggler.core-test
  (:require [clojure.test :refer :all]
            [doll-smuggler.core :refer :all]))

; Test the get-dolls function against the test input 0 which is an empty file.
(deftest get-dolls-test-input-0
  (testing "Population of dolls for doll-set-0"
    (def solution '())
    (is (= (get-dolls "resources/doll-set-0" #"\W+") solution))))

; Test the get-dolls function against the test input 1.
(deftest get-dolls-test-input-1
  (testing "Population of dolls for doll-set-1"
    (def solution '({:name "luke", :weight "9", :value "150"}
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
                     {:name "babe", :weight "30", :value "10"}))
    (is (= (get-dolls "resources/doll-set-1" #"\W+") solution))))

; Test the get-dolls function against the test input 2.
(deftest get-dolls-test-input-2
  (testing "Population of dolls for doll-set-2"
    (def solution '({:name "justin", :weight "4", :value "12"}
                     {:name "david", :weight "6", :value "10"}
                     {:name "kristen", :weight "5", :value "8"}
                     {:name "edward", :weight "7", :value "11"}
                     {:name "pauline", :weight "3", :value "15"}
                     {:name "nathalie", :weight "1", :value "7"}
                     {:name "frank", :weight "6", :value "9"}))
    (is (= (get-dolls "resources/doll-set-2" #"\W+") solution))))

; Test the get-dolls function against the test input 3 which is missing a field.
(deftest get-dolls-test-input-3
  (testing "Population of dolls for doll-set-3"
    (def solution '({:name "justin", :weight "4", :value nil}
                     {:name "david", :weight "6", :value "10"}
                     {:name "kristen", :weight "5", :value "8"}
                     {:name "edward", :weight "7", :value "11"}
                     {:name "pauline", :weight "3", :value "15"}
                     {:name "nathalie", :weight "1", :value "7"}
                     {:name "frank", :weight "6", :value "9"}))
    (is (= (get-dolls "resources/doll-set-3" #"\W+") solution))))
