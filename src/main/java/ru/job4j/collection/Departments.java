package ru.job4j.collection;

import java.util.*;

public class Departments {

    public static List<String> fillGaps(List<String> deps) {
        LinkedHashSet<String> tmp = new LinkedHashSet<>();
        for (String dep : deps) {
            String[] departament = dep.split("/");
            String start = "";
            for (String s : departament) {
                if (start.equals("")) {
                    start = s;
                } else {
                    start = start + "/" + s;
                }
                tmp.add(start);
            }
        }
        return new ArrayList<>(tmp);
    }

    public static void sortAsc(List<String> orgs) {
        orgs.sort(Comparator.naturalOrder());
    }

    public static void sortDesc(List<String> orgs) {
        orgs.sort(new DepDescComp().thenComparing(Comparator.naturalOrder()));
    }
}
