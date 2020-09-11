package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;

public class FI {

    public static void main(String[] args) {
        Attachment[] atts = {
                new Attachment("image 1", 20),
                new Attachment("image  3", 120),
                new Attachment("image2", 23)
        };
        System.out.println(Arrays.toString(atts));
        Comparator<Attachment> comparator = (left, right) -> left.getSize() - right.getSize();
        Comparator<Attachment> cmpSize = (left, right) -> left.getName().length() - right.getName().length();
        Comparator<Attachment> cmpDescSize = (left, right) -> {
            System.out.println("cmpDescSize compare - " + right.getName().length()
                    + " : " + left.getName().length());
            return right.getName().length() - left.getName().length();
        };
        Comparator<Attachment> comparator1 = (left, right) -> {
            System.out.println("comparator1 compare - " + left.getSize() + " : " + right.getSize());
            return left.getSize() - right.getSize();
        };
        Arrays.sort(atts, cmpDescSize);
        System.out.println(Arrays.toString(atts));
    }
}
