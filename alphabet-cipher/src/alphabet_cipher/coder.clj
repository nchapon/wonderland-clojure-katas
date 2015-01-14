(ns alphabet-cipher.coder)


(def alphabet (seq "abcdefghijklmnopqrstuvwxyz"))

(def index-of-alphabet (fn [letter]
                         (.indexOf alphabet letter)))

(defn create-row
  "create substitution row"
  [letter]
  (flatten (reverse (split-at (index-of-alphabet letter) alphabet))))

(defn encode-one [row col]
  (nth
   (create-row row)
   (index-of-alphabet col)))

(defn decode-one [row col]
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
  (apply str (map f (take (.length message) (cycle key)) message)))

(defn decode [key message]
  (cipher decode-one key message))

(defn encode [key message]
  (cipher encode-one key message))
