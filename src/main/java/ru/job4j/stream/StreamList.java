package ru.job4j.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamList {
    public static void main(String[] args) {
        Integer[] arr = {0, -2, 2, -5, 6, -1, 3, 5, 7, -1};
        List<Integer> listInt = new ArrayList<>(Arrays.asList(arr));
        List<Integer> positive = listInt.stream().filter(x -> x > 0).collect(Collectors.toList());
    }
}
