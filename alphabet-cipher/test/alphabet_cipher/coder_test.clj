(ns alphabet-cipher.coder-test
  (:require [clojure.test :refer :all]
            [alphabet-cipher.coder :refer :all]))

(deftest test-encode
  (testing "can encode give a secret keyword"
    (is (= "hmkbxebpxpmyllyrxiiqtoltfgzzv"
           (encode "vigilance" "meetmeontuesdayeveningatseven")))
    (is (= "egsgqwtahuiljgs"
           (encode "scones" "meetmebythetree")))))

(deftest test-decode
  (testing "can decode an cyrpted message given a secret keyword"
    (is (= "meetmeontuesdayeveningatseven"
           (decode "vigilance" "hmkbxebpxpmyllyrxiiqtoltfgzzv")))
    (is (= "meetmebythetree"
           (decode "scones" "egsgqwtahuiljgs")))))

(deftest test-create-row
  (testing "creation of table substitution lines"
    (is (= \e
           (first (create-row \e))))
    (is (= \a
           (first (create-row \a))))))

(deftest test-encode-one
  (testing "Encode one"
    (is (= \e
           (encode-one \s \m)))
    (is (= \g
           (encode-one \c \e)))))

(deftest test-decode-one
  (testing "Encode one"
    (is (= \m
           (decode-one \s \e)))
    (is (= \e
           (decode-one \c \g)))))
