package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentsMap {
    private List<Student> students = new ArrayList<>();

    public void add(Student student) {
        this.students.add(student);
    }

    public Map<String, Student> collectMap(List<Student> students) {
        return students.stream()
                .distinct()
                .collect(Collectors.toMap(
                        Student::getSurname,
                        student -> student
                        )
                );

    }
}
