(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= (play-round [:heart 10] [:diamond :jack])
           :p2)))
  (testing "queens are higher rank than jacks"
    (is (= (play-round [:spade :queen] [:spade :jack])
           :p1)))
  (testing "kings are higher rank than queens"
    (is (= (play-round [:club :queen] [:club :king])
           :p2)))
  (testing "aces are higher rank than kings"
    (is (= (play-round [:diamond :ace] [:diamond :king])
           :p1)))
  (testing "if the ranks are equal, clubs beat spades"
    (is (= (play-round [:spade :jack] [:club :jack])
           :p2)))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= (play-round [:diamond 8] [:club 8])
           :p1)))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (= (play-round [:diamond 3] [:heart 3])
           :p2))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (= (play-game [[:heart :king]] [])
           :p1))
    (is (= (play-game [] [[:heart :king]])
           :p2))
    (is (= (play-game (vec (take 26 cards)) (vec (drop 26 cards)))
           :p2))))

