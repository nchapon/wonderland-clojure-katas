(ns alphabet-cipher.coder)


(def alphabet (seq "abcdefghijklmnopqrstuvwxyz"))

(def index-of-alphabet (fn [letter]
                         (.indexOf alphabet letter)))

(defn create-row
  "create substitution row"
  [letter]
  (flatten (reverse (split-at (index-of-alphabet letter) alphabet))))

(defn encoding [row col]
  (nth
   (create-row row)
   (index-of-alphabet col)))

(defn decoding [row col]
  (nth
   alphabet
   (.indexOf (create-row row) col)))

(defn make-key
  "Expand key to the message length if necessary"
  [keyword len]
  (take len (apply concat (repeat keyword))))

(defn cipher
  "Cipher"
  [f key message]
  (loop [k (make-key key (.length message))
        m message
        output []]
    (if (empty? m)
      (apply str output)
      (recur (rest k) (rest m) (conj output (f (first k) (first m)))))))

(defn decode [key message]
  (cipher decoding key message))

(defn encode [key message]
  (cipher encoding key message))
