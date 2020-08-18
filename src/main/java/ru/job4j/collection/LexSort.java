package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] rightArr = right.split("\\.");
        String[] leftArr = left.split("\\.");
        int first = Integer.parseInt(leftArr[0]);
        int second = Integer.parseInt(rightArr[0]);
        return Integer.compare(first, second);
    }
}
