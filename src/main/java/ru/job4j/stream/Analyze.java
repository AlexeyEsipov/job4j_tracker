package ru.job4j.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {
    public static double averageScore(Stream<Pupil> stream) {
        return stream
               .flatMap(e -> e.getSubjects()
                              .stream())
               .mapToInt(Subject::getScore)
               .average()
               .orElse(0D);
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream
               .map(e -> {
                          double averageScore = e.getSubjects().stream()
                                           .mapToInt(Subject::getScore)
                                           .average()
                                           .orElse(0D);
                          return new Tuple(e.getName(), averageScore);
               })
               .collect(Collectors.toList());
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream
               .map(Pupil::getSubjects)
               .flatMap(Collection::stream)
               .collect(Collectors.groupingBy(Subject::getName,
                                            LinkedHashMap::new,
                                            Collectors.averagingDouble(Subject::getScore)))
               .entrySet().stream()
                          .map(e -> new Tuple(e.getKey(), e.getValue()))
                          .collect(Collectors.toList());
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream
               .map(e -> {
                   double sumScore = e.getSubjects().stream()
                           .mapToInt(Subject::getScore)
                           .sum();
                   return new Tuple(e.getName(), sumScore);
               })
               .max((t0, t1) -> (int) (t0.getScore() - t1.getScore()))
               .orElse(new Tuple("нет", 0D));
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream
                .map(Pupil::getSubjects)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Subject::getName,
                        Collectors.summingDouble(Subject::getScore)))
                .entrySet().stream()
                           .map(e -> new Tuple(e.getKey(), e.getValue()))
                           .max((t0, t1) -> (int) (t0.getScore() - t1.getScore()))
                           .orElse(new Tuple("нет", 0D));
    }
}
