package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public static void main(String[] args) {
        List<Card> cards = new ArrayList<>();
        for (Suit suit: Suit.values()) {
            for (Value value: Value.values()) {
                cards.add(new Card(suit, value));
            }
        }
    }
}
