package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class SearchAtt {

    public static List<Attachment> filterSize(List<Attachment> list) {
        Supplier<Integer> initValueInt = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 100;
            }
        };
        BiPredicate<Attachment, Supplier> predBi = new BiPredicate<>() {
            @Override
            public boolean test(Attachment att, Supplier supp) {
                return att.getSize() > (int) supp.get();
            }
        };
        return filter(list, predBi, initValueInt);
    }

    public static List<Attachment> filterName(List<Attachment> list) {
        Supplier<String> initValueString = new Supplier<String>() {
            @Override
            public String get() {
                return "bug";
            }
        };
        BiPredicate<Attachment, Supplier> predBi = new BiPredicate<>() {
            @Override
            public boolean test(Attachment att, Supplier supp) {
                return att.getName().contains((String) supp.get());
            }
        };
        return filter(list, predBi, initValueString);
    }

    private static List<Attachment> filter(List<Attachment> list,
                                       BiPredicate<Attachment, Supplier> func, Supplier initValue) {
        List<Attachment> rsl = new ArrayList<>();
        var kriteria  = initValue.get();
        for (Attachment att : list) {
            if (func.test(att, (Supplier) kriteria)) {
                rsl.add(att);
            }
        }
        return rsl;
    }
}