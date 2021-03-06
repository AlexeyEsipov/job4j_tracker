package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class StudentsMapTest {

    @Test
    public void whenOnlyCollectMap() {
        StudentsMap schoolMap = new StudentsMap();
        List<Student> students = new ArrayList<>();
        students.add(new Student("Junior", 48));
        students.add(new Student("Middle", 55));
        students.add(new Student("Senor", 90));
        Map<String, Student> testMap = schoolMap.collectMap(students);
        assertEquals(new Student("Middle", 55), testMap.get("Middle"));
    }

    @Test
    public void whenNoDuplicateCollectMap() {
        StudentsMap schoolMap = new StudentsMap();
        List<Student> students = new ArrayList<>();
        students.add(new Student("Junior", 48));
        students.add(new Student("Middle", 55));
        students.add(new Student("Senor", 90));
        Map<String, Student> testMap = schoolMap.collectMap(students);
        assertEquals(3, testMap.size());
    }

    @Test
    public void whenDuplicateCollectMap() {
        StudentsMap schoolMap = new StudentsMap();
        List<Student> students = new ArrayList<>();
        students.add(new Student("Junior", 48));
        students.add(new Student("Junior", 48));
        students.add(new Student("Middle", 55));
        students.add(new Student("Senor", 90));
        Map<String, Student> testMap = schoolMap.collectMap(students);
        assertEquals(3, testMap.size());
    }
}