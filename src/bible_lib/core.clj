(ns bible-lib.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def data-loc "resources/")                                 ;local project directory
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

(defn data->list [file]
  "Takes FILE and turns it into a usable vector from the tab separated tsv file."
  (lazy-seq (for [data (lazy-seq(str/split-lines (slurp file)))]
              (lazy-seq (str/split data #"\t")))))

(defn format-verse-number [verse]
  "Takes verse vector and formats it to XX:XX"
  (str (nth verse 3) ":" (nth verse 4)))

(defn remove-punctuation [w]
  "Removes all punctuation from string(s)"
  (apply str (remove #((set "\"',.?!:;()$%&*/[]{}_") %) w)))

(defn string-data [data]
  "Turn bible data into a collection of strings."
  (map (fn [d] (last d)) data))

(defn frequency-data [data]
  "Create map displaying ([WORD, COUNT]...)"
  ;TODO - Hash map with labels; {:word "..." :count "..."}
  ;TODO - Further optimizations, rn it's ~ 19s to do kjv file
  (sort-by last (frequencies (str/split (str/lower-case (remove-punctuation (str/join " " (string-data data))))
                                        #" ")))) ;Print to file using write-file

(defn data-books-list [data]
  "Parse data file and gather information on the books within"
  (vec (dedupe (for [d data]
                 (first d)))))