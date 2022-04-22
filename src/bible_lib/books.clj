(ns bible-lib.books
  (:require [bible-lib.core :as cr]))

(defn update-books-list []
  "Parse data file and gather information on the books within"
  ;(if (not (cr/resource-data? "books"))) ;TODO: Make sure checks work
  (vec (dedupe (for [data (cr/bible-data)]
                 (first data)))))

(def books-list (memoize update-books-list)) ;cache books list
