package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String[] o1TwoWord = o1.split("/", 2);
        String[] o2TwoWord = o2.split("/", 2);
        int result  = o2TwoWord[0].compareTo(o1TwoWord[0]);
        if (result == 0) {
            result = o1.compareTo(o2);
        }
        return result;
    }
}
