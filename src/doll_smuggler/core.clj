(ns doll-smuggler.core)
(require '[clojure.java.io :as io])
(use '[clojure.string :only (blank? join split)])

(defstruct doll :name :weight :value)

; Read the list of dolls from the path. Place each doll record in a struct in a vector.
(defn get-dolls
  [path delimiter]
  (vec (let [doll-properties
    (let [lines (line-seq (io/reader path))]
      (map (fn [line] (split line delimiter)) lines))]
    (map #(apply struct doll %) doll-properties))))

; Forward declare for memoization function.
(declare memoized-compute-solution)

; Allow parsing of string to int. If the string provided is nil, return 0.
(defn parse-int
  [string]
  (cond
    ; If the string supplied is nil. Return 0.
    (blank? string) 0
    (= nil (re-find #"\d+" string)) 0
    ; TODO more validation necessary?
    ; Otherwise, this looks good. Try and parse the number.
    :else
    (Integer/parseInt (re-find #"\d+" string))))

; Compute the total value of the dolls provided.
(defn total-doll-value
  ; If only a list of dolls is supplied. Assume they want the value of all dolls in the list.
  ([dolls] (total-doll-value dolls (dec (count dolls))))
  ; If a list of dolls and an index is supplied. Tally doll value starting at the index and go to zero.
  ([dolls doll-index]
   (cond
     ; If supplied doll-index is < 0 then return 0.
     (< doll-index 0) 0
     ; If doll-index is <= 0 then return 0.
     (<= (count dolls) 0) 0
     ; If supplied doll-index is >= the number of elements in dolls this is unacceptable. Try with the maximum
     ; doll-index allowed. (0 based).
     (>= doll-index (count dolls)) (total-doll-value dolls (dec doll-index))
     :else
     ; Otherwise, take the value of the doll at the index, and sum with the doll at doll-index - 1.
     (+ (let [{doll-value :value} (get dolls doll-index)] (parse-int doll-value))
        (total-doll-value dolls (dec doll-index))))))

(defn compute-solution
  ([weight dolls] (compute-solution weight dolls (dec (count dolls))))
  ([weight dolls doll-index]
  (cond
    ; If doll-index is < 0 then we are done. Return an empty list.
    (< doll-index 0) []
    ; If weight remaining to assign is 0 then we are done. Return an empty list.
    (= weight 0) []
    ; Otherwise, some more recursion is necessary.
    :else
      (let [{doll-weight :weight doll-value :value} (get dolls doll-index)]
        (cond
          ; If the doll-weight is > the weight remaining the last item solution for this weight is optimal.
          (> (parse-int doll-weight) weight) (memoized-compute-solution weight dolls (dec doll-index))
          ; Otherwise, this new item MAY be a part of the optimal solution. Compute two lists. old-list is the
          ; optimal solution considering the last item solution for this weight. The new-list is optimal solution for
          ; the remaining weight if we think the current item is part of the optimal solution.
          :else
            (let [old-list (memoized-compute-solution weight dolls (dec doll-index))
                  new-list (memoized-compute-solution (- weight (parse-int doll-weight)) dolls (dec doll-index))]
              (cond
                ; If the current item + the optimal solution for the remaining weight is less optimal than the
                ; old-list, use the old-list.
                (<= (+ (total-doll-value new-list) (parse-int doll-value)) (total-doll-value old-list)) old-list
                :else
                ; We have found a MORE optimal solution! Conjoin the current item with the optimal solution for the
                ; remaining weight.
                (conj new-list (get dolls doll-index))
                )))))))

; Use memoization to cache input against a map of previous inputs for compute-solution.
(def memoized-compute-solution (memoize compute-solution))

; Prints a doll given the set and an index.
(defn print-dolls
  ([dolls]
   (println "Packed Dolls:")
   (println "name\tweight\tvalue")
   (print-dolls dolls (dec (count dolls))))
  ([dolls doll-index]
   (if (>= doll-index 0)
     (let [{doll-name :name doll-weight :weight doll-value :value} (get dolls doll-index)]
       (println (str doll-name "\t" doll-weight "\t" doll-value))
       (print-dolls dolls (dec doll-index))))))

; Print doll value.
(defn print-doll-value
  [solution]
  (println "Doll Value:")
  (println (total-doll-value solution))
  )

; Prints the solution set.
(defn print-solution
  [solution]
  (print-doll-value solution)
  (print-dolls solution)
  )

(defn -main
  ([max-carry-weight doll-file] (-main max-carry-weight doll-file #"\W+"))
  ([max-carry-weight doll-file delimiter]
   (cond
     ; If file supplied is not accessible. Output a useful error.
     (not (.exists (io/file doll-file))) (println "Must supply a valid file.")
     ; If weight provided is not a number. Output a useful error.
     (= nil (re-find #"\d+" (str max-carry-weight))) (println "Must supply a valid weight.")
     ; If weight provided is a negative number. Output a useful error.
     (<= (parse-int (str max-carry-weight)) 0) (println "Must supply a weight greater than zero.")
     :else
     (print-solution
       (let [dolls (get-dolls doll-file delimiter)]
         (let [solution (compute-solution (parse-int (str max-carry-weight)) dolls)] solution))))))