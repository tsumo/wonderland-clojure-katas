(ns alphabet-cipher.coder)

(def alphabet (cycle "abcdefghijklmnopqrstuvwxyz"))
(def start (int \a))

(defn- encode-one [k m]
  (nth alphabet (+ (- (int k) start) (- (int m) start))))

(defn encode [keyword message]
  (apply str (map encode-one (cycle keyword) message)))

(defn- decode-one [k m]
  (nth alphabet (+ 7 (int m) (- start (int k)))))

(defn decode [keyword message]
  (apply str (map decode-one (cycle keyword) message)))

(defn- find-cycle
  ([code] (find-cycle code (count code) 1))
  ([code length n]
   (let [substring (subs code 0 n)]
     (if (= code (apply str (take length (cycle substring))))
       substring
       (recur code length (inc n))))))

(defn decipher [cipher message]
  (find-cycle (apply str (map decode-one message cipher))))

