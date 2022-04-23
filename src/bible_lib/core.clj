(ns bible-lib.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def data-loc "resources/")                                 ;can also use io/resource for this
(def data-bible-name "kjv.tsv")                             ;name for data file
(def data-bible-loc (str data-loc data-bible-name))         ;used for .exists

(defn write-file [data file]
  (with-open [w (io/writer file)]
    (.write w data)))                                       ;TODO - Check best method for writing

(defn resource-data? [file]
  "Check if resource files needed are there and returns boolean"
  (cond (= file "data") (if (.exists (io/file data-bible-loc)) true false)
        ;(= file "books") (if (.exists (io/file data-bible-books-loc)) true false)
        :else (if (.exists (io/file data-bible-loc)) true false)))

(defn data->formatted-vector []
  "Takes FILE and turns it into a usable vector from the tab separated tsv file."
  (vec (for [data (str/split-lines (slurp data-bible-loc))]
    (str/split data #"\t"))))

;Memoize bible data to speed up all other actions requiring this data
(def bible-data (memoize data->formatted-vector))           ;ex. (first (data-bible)) => < .1 msec

(defn format-verse-number [verse]
  "Takes verse vector and formats it to XX:XX"
  (str (nth verse 3) ":" (nth verse 4)))

(defn remove-punctuation [w]
  "Removes all punctuation from string(s)"
  (apply str (remove #((set "\"',.?!:;()$%&*/[]{}_") %) w)))

(defn string-data [data]
  "Turn bible data into a collection of strings - punctuation"
  (str/join " " (for [d data]
                  (remove-punctuation (last d)))))          ;TODO - Change for to map or other func

(defn frequency-data [data]
  "Create lazyseq displaying ([WORD, COUNT]...)"
  ;TODO - Map data to vector with labels
  ;TODO - Change Case for all words
  (sort-by last (frequencies (str/split (string-data data) #" ")))) ;Print to file using write-file



(defn update-books-list []
  "Parse data file and gather information on the books within"
  (vec (dedupe (for [data (cr/bible-data)]
                 (first data)))))

(def data-books-list (memoize update-books-list))           ;cache books list
