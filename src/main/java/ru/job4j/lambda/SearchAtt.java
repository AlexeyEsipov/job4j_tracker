package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchAtt {

    public static List<Attachment> filterSize(List<Attachment> list) {
        Predicate<Attachment> pred = new Predicate<>() {
            @Override
            public boolean test(Attachment att) {
                return att.getSize() > 100;
            }
        };
        return filter(list, pred);
    }

    public static List<Attachment> filterName(List<Attachment> list) {
        Predicate<Attachment> pred = new Predicate<>() {
            @Override
            public boolean test(Attachment att) {
                return att.getName().contains("bug");
            }
        };
        return filter(list, pred);
    }

    private static List<Attachment> filter(List<Attachment> list, Predicate<Attachment> func) {
        List<Attachment> rsl = new ArrayList<>();
        for (Attachment att : list) {
            if (func.test(att)) {
                rsl.add(att);
            }
        }
        return rsl;
    }
}