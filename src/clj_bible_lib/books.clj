(ns clj-bible-lib.books
  (:require [clojure.java.io :as io]
            [clj-bible-lib.core :as cr]
            [clojure.string :as str]))

(defn update-books-list []
  "Parse data file and gather information on the books within"
  ;(if (not (cr/resource-data? "books")))
  (dedupe (for [data (str/split-lines (slurp (io/resource cr/data-bible)))]
            (first (str/split data #"\t")))))
;;TODO: Going to redo this by formatting the data as a vector and passing it to this function

(def books-list (memoize update-books-list))              ;cache books list

(defn save-books-to-file []
  (with-open [p (clojure.java.io/writer cr/data-bible-books-loc)]
    (doseq [line books-list]
      (.write p line))))
;;TODO: This may not be needed since the list is cached once BOOKS-LIST is referenced