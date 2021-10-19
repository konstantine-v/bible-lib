(ns clj-bible-lib.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def data-loc "resources/")                                 ;can also use io/resource for this
(def data-bible-name "kjv.tsv")                             ;name for data file
(def data-bible-loc (str data-loc data-bible-name))         ;used for .exists
;(def data-bible-books "books.txt")                          ;converted data for easier parsing
;(def data-bible-books-loc (str data-loc data-bible-books))  ;converted data for easier parsing

(defn resource-data? [file]
  "Check if resource files needed are there and returns boolean"
  (cond (= file "data") (if (.exists (io/file data-bible-loc)) true false)
        ;(= file "books") (if (.exists (io/file data-bible-books-loc)) true false)
        :else (if (.exists (io/file data-bible-loc)) true false)))

(defn data->formatted-vector [file]
  "Takes FILE and turns it into a usable vector from the tab separated tsv file."
  (vec (for [data (clojure.string/split-lines (slurp (io/resource file)))]
    (str/split data #"\t"))))

(def data-bible (memoize data->formatted-vector))           ;ex. (first (data-bible)) => < .1 msec

(defn format-verse-number [verse]
  "Takes verse vector and formats it to XX:XX"
  (str (nth verse 3) ":" (nth verse 4)))