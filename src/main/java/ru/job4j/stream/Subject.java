package ru.job4j.stream;

public class Subject {
    private String name; // наименование школьного предмета
    private int score;   // балл по этому предмету

    public Subject(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
