(ns clj-bible-lib.search
  (:require [clj-bible-lib.core :as cr]
            [clj-bible-lib.books :as book]
            [clucie.core :as core]
            [clucie.analysis :as analysis]
            [clucie.store :as store]))
;; Clucie Lib - https://github.com/federkasten/clucie

;(core/add! index-store (cr/data-bible))

(defn search-by-book [book]
  (for [x (cr/data->formatted-vector cr/data-bible-name)
        :when (= book (first x))]
    x))
;; Ex. (search-by-book (nth (books-list) 1)) ;=> returns all verses for that book selected

(defn search-by-keyword [key]
  (prn "test"))

