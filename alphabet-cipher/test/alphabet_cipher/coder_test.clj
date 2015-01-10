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

(deftest test-encoding-char
  (testing "Encode char"
    (is (= \e
           (encoding \s \m)))
    (is (= \g
           (encoding \c \e)))))

(deftest test-decoding-char
  (testing "Encode char"
    (is (= \m
           (decoding \s \e)))
    (is (= \e
           (decoding \c \g)))))

(deftest test-make-key
  (testing "key should be expanded if necessary")
  (is (= 7
         (count (make-key "keyverylong" 7))))
  (is (= 15
         (count (make-key "shortkey" 15)))))
