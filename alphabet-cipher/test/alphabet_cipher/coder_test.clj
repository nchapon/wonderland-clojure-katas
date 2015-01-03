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

(deftest test-encode-char
  (testing "Encode char"
    (is (= \e
           (encode-char \s \m)))
    (is (= \g
           (encode-char \c \e)))))

(deftest test-decode-char
  (testing "Encode char"
    (is (= \m
           (decode-char \s \e)))
    (is (= \e
           (decode-char \c \g)))))

(deftest test-expand-key
  (testing "key should be expanded if necessary")
  (is (= "keyverylong"
         (expand-key "keyverylong" "message")))
  (is (= "shortkeyshortkey"
         (expand-key "shortkey" "messageverylong"))))
