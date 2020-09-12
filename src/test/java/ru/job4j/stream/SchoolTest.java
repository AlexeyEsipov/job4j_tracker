package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SchoolTest {

    @Test
    public void when0to50collect() {
        School school = new School();
        List<Student> students = new ArrayList<>();
        students.add(new Student("Junior", 48));
        students.add(new Student("Middle", 47));
        students.add(new Student("Senor", 90));
        Predicate<Student> cl0to50 = student -> student.getScore() > 0
                                        && student.getScore() < 50;
        List<Student> classC = school.collect(students, cl0to50);
        assertThat(classC.size(), is(2));
    }

    @Test
    public void when50to70collect() {
        School school = new School();
        List<Student> students = new ArrayList<>();
        students.add(new Student("Junior", 48));
        students.add(new Student("Middle", 67));
        students.add(new Student("Senor", 90));
        Predicate<Student> cl50to70 = student -> student.getScore() >= 50
                                        && student.getScore() < 70;
        List<Student> classB = school.collect(students, cl50to70);
        assertThat(classB.size(), is(1));
    }

    @Test
    public void when70to100collect() {
        School school = new School();
        List<Student> students = new ArrayList<>();
        students.add(new Student("Junior", 48));
        students.add(new Student("Middle", 67));
        students.add(new Student("Senor", 90));
        Predicate<Student> cl70to100 = student -> student.getScore() >= 70
                                        && student.getScore() <= 100;
        List<Student> classA = school.collect(students, cl70to100);
        assertThat(classA.size(), is(1));
    }
}