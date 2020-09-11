package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Functions {
    public static List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < end - start; i++) {
            result.add(i, func.apply((double) (start + i)));
        }
        return result;
    }
}
