package ru.job4j.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StudentsMap {
    private List<Student> students = new ArrayList<>();
    
    public void add(Student student) {
        this.students.add(student);
    }

    public Map<String, Student> collectMap(List<Student> students) {
        return students.stream()
                .collect(Collectors.toMap(
                        Student::getSurname,
                        Function.identity(),
                        (s1, s2) -> s1)
                );
    }
}
