(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn- index
  [x coll]
  (first (keep-indexed #(when (= %2 x) %1) coll)))

(defn play-round
  [[p1-suit p1-rank] [p2-suit p2-rank]]
  (let [p1-suit-index (index p1-suit suits)
        p2-suit-index (index p2-suit suits)
        p1-rank-index (index p1-rank ranks)
        p2-rank-index (index p2-rank ranks)]
    (cond (> p1-rank-index p2-rank-index) :p1
          (< p1-rank-index p2-rank-index) :p2
          (> p1-suit-index p2-suit-index) :p1
          (< p1-suit-index p2-suit-index) :p2)))

(defn- rest-vec
  [v]
  (subvec v 1))

(defn play-game
  [p1-cards p2-cards]
  (cond (empty? p1-cards) :p2
        (empty? p2-cards) :p1
        :else (let [p1-card (first p1-cards)
                    p2-card (first p2-cards)
                    winner (play-round p1-card p2-card)]
                (case winner
                  :p1 (recur (conj (rest-vec p1-cards) p1-card p2-card)
                             (rest-vec p2-cards))
                  :p2 (recur (rest-vec p1-cards)
                             (conj (rest-vec p2-cards) p1-card p2-card))))))

