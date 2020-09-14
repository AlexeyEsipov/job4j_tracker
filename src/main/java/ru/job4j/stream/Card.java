package ru.job4j.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Card {
    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public static void main(String[] args) {
        List<Card> cards = new ArrayList<>();
        cards = Arrays.stream(Suit.values())
                        .flatMap(s -> Arrays.stream(Value.values())
                                    .map(v -> new Card(s, v)))
                        .collect(Collectors.toList());
    }
}
