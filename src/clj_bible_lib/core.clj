(ns clj-bible-lib.core
  (:require [clojure.java.io :as io]))

(def data-loc "resources/")                                  ;can also use io/resource for this
(def data-bible "kjv.tsv")                           ;name for data file
(def data-bible-loc (str data-loc data-bible))        ;used for .exists
(def data-bible-books "books.txt")                     ;converted data for easier parsing
(def data-bible-books-loc (str data-loc data-bible-books))                     ;converted data for easier parsing

(defn resource-data? [file]
  "Check if resource files needed are there and returns boolean"
  (cond (= file "data") (if (.exists (io/file data-bible-loc)) true false)
        (= file "books") (if (.exists (io/file data-bible-books-loc)) true false)
        :else (if (.exists (io/file data-bible-loc)) true false)))
