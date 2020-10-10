package ru.job4j.stream;

import java.util.List;

public class Pupil {
    private String name;                // имя ученика
    private List<Subject> subjects;     // список школьных предметов

    public Pupil(String name, List<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
}
