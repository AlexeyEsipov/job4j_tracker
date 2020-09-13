package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class MatrixToListTest {

    @Test
    public void matrixToList() {
        MatrixToList matrixToList = new MatrixToList();
        Integer[][] matrix = {{1, 2}, {3, 4}};
        List<Integer> testConvert = matrixToList.convert(matrix);
        List<Integer> expected = new ArrayList<>();
        Collections.addAll(expected, 1, 2, 3, 4);
        assertEquals(expected, testConvert);
    }
}