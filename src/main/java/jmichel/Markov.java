package jmichel;

import java.util.*;

public class Markov {
    /**
     * Train probability set based on input word slice
     */
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

    /**
     * Generate note sequence based on Markov chain
     */
    public static String andreyUp(String pattern, int chainLen) {
        String[] words = pattern.split(" ");
        Map<String, ArrayList<String>> field = train(words);

        int idx = new Random().nextInt(words.length);
        ArrayList<String> chain = new ArrayList<>(Collections.singletonList((words[idx])));

        for (int i = 0; i < chainLen; i++) {
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
