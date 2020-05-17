package jmichel;

import java.util.*;

public class Markov {

    private static Map<String, ArrayList<String>> train(String[] words) {
        Map<String, ArrayList<String>> field = new HashMap<>();

        for (int i = 0; i < (words.length - 1); i++) {
            if (field.get(words[i]) != null) {
                field.get(words[i]).add(words[i + 1]);
            } else {
                field.put(words[i], new ArrayList<>(Collections.singletonList(words[i + 1])));
            }
        }

        String longestKey = null;
        int maxLength = 0;

        for (Map.Entry<String, ArrayList<String>> entry : field.entrySet()) {
            if (entry.getValue().size() > maxLength) {
                maxLength = entry.getValue().size();
                longestKey = entry.getKey();
            }
        }
        System.out.println("longestKey: " + longestKey);
        String lastWord = words[words.length - 1];

        String key = longestKey;
        field.computeIfAbsent(lastWord, k -> new ArrayList<>(Collections.singletonList(key)));

        return field;
    }

    public static void main(String[] args) {
        final String pattern = "R/0.04843749999999858 D5/0.5833333333333334 R/0.5078125 A5/0.1234375 R/0.02083333333333215 G5/0.0484375 R/0.0442708333333357 F5/0.12083333333333333 R/0.0390625 C5/0.4817708333333333 R/0.11093749999999858 A5/0.140625 R/0.014062500000001421 G5/0.052083333333333336 R/0.04322916666666643 F5/0.11197916666666667 R/0.02708333333333357 C5/0.45208333333333334 R/0.16145833333333215 R/0.16666666666666607 G5T. R/0.036458333333333925 G5I R/0.040104166666667496 G5/0.203125 R/0.04843749999999858 C5/0.5833333333333334 RH BB5Q R/0.16666666666666607 G5T. R/0.036458333333333925 EB5I R/0.040104166666667496 G5/0.203125 R/0.04843749999999858 C5/0.5833333333333334 RH BB5Q R/0.16666666666666607 G5T. R/0.036458333333333925 G5I R/0.040104166666667496 G5/0.203125 R/0.04843749999999858 C5/0.5833333333333334 RH BB5Q R/0.16666666666666607 G5T. R/0.036458333333333925 EB5I R/0.040104166666667496 A5/0.203125 R/0.04843749999999858 C5/0.5833333333333334 RH BB5Q R/0.16666666666666607 G5T. R/0.036458333333333925 G5I R/0.040104166666667496 G5/0.203125 R/0.04843749999999858 C5/0.5833333333333334 RH BB5Q R/0.16666666666666607 A5T. R/0.036458333333333925 EB5I R/0.040104166666667496 G5/0.203125 R/0.04843749999999858 C5/0.5833333333333334 RH BB5Q R/0.16666666666666607 G5T.";
        System.out.println("pattern: " + pattern);

        String[] words = pattern.split(" ");
        System.out.println("words: " + Arrays.toString(words));

        Map<String, ArrayList<String>> field = train(words);
        System.out.println("field: " + field);
    }

}
