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
        String lastWord = words[words.length - 1];

        String key = longestKey;
        field.computeIfAbsent(lastWord, k -> new ArrayList<>(Collections.singletonList(key)));

        return field;
    }

    public static String andreyUp(String pattern) {
        String[] words = pattern.split(" ");
        Map<String, ArrayList<String>> field = train(words);

        int idx = new Random().nextInt(words.length);
        ArrayList<String> chain = new ArrayList<>(Collections.singletonList((words[idx])));

        for (int i = 0; i < 80; i++) {
            String lastLink = chain.get(chain.size() - 1);
            Random random = new Random();
            int rnd = random.nextInt(field.get(lastLink).size());
            chain.add(field.get(lastLink).get(rnd));
        }

        StringBuilder builder = new StringBuilder();
        for (String s : chain) {
            builder.append(s).append(" ");
        }
        return builder.toString();
    }

}
