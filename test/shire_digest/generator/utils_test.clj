(ns shire-digest.generator.utils-test
  (:require [clojure.test :refer :all]
            [shire-digest.generator.utils :refer :all])
  (:import [java.io.StringWriter]))

(deftest test-write-to
  (testing "Write to stream"
    (let [string-writer (java.io.StringWriter.)
          test-content "hello world"
          rv (write-to string-writer test-content)]
      (is (= test-content (-> string-writer .toString))))))
