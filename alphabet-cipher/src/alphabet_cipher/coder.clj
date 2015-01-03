(ns alphabet-cipher.coder)


(def alphabet (seq "abcdefghijklmnopqrstuvwxyz"))

(def index-of-alphabet (fn [letter]
                         (.indexOf alphabet letter)))

(defn create-row
  "create substitution row"
  [letter]
  (flatten (reverse (split-at (index-of-alphabet letter) alphabet))))

(defn encode-char [row col]
  (nth
   (create-row row)
   (index-of-alphabet col)))

(defn decode-char [row col]
  (nth
   alphabet
   (.indexOf (create-row row) col)))

(defn expand-key
  "Expand key to the message length if necessary"
  [key message]
  (loop [expanded-key key]
    (if (> (.length expanded-key) (.length message))
      expanded-key
      (recur (str expanded-key key)))))


(defn encode [key message]
  (loop [k (expand-key key message)
        m message
        encoded []]
    (if (empty? m)
      (apply str encoded)
      (recur (rest k) (rest m) (conj encoded (encode-char (first k) (first m)))))))

(defn decode [key message]
  (loop [k (expand-key key message)
        m message
        decoded []]
    (if (empty? m)
      (apply str decoded)
      (recur (rest k) (rest m) (conj decoded (decode-char (first k) (first m)))))))
