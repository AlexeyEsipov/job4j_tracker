package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        if (Character.isDigit(left.charAt(0)) && Character.isDigit(right.charAt(0))) {
            int leftDigit = 0;
            int rightDigit = 0;
            for (int i = 0; i < left.length(); i++) {
                char charLeft = left.charAt(i);
                if (Character.isDigit(charLeft)) {
                    leftDigit = leftDigit * 10 + Character.getNumericValue(charLeft);
                } else {
                    break;
                }
            }
            for (int i = 0; i < right.length(); i++) {
                char charRight = right.charAt(i);
                if (Character.isDigit(charRight)) {
                    rightDigit = rightDigit * 10 + Character.getNumericValue(charRight);
                    } else {
                        break;
                    }
            }
        return Integer.compare(leftDigit, rightDigit);
        }
        return left.compareTo(right);
    }

}
