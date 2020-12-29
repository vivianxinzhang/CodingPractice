package com.company;
import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        WordLadder s = new WordLadder();
         List<String> dict = Arrays.asList("git", "hit", "hog", "hot", "got");
         System.out.println(s.ladderLength("git", "hot", dict));    // 3
         // git -> hit -> hot  3
         System.out.println(s.ladderLengthI("git", "hot", dict));    // 3

        dict = Arrays.asList("bcd","adc","bdd","baa","bbd","cbd","ddb");
        System.out.println(s.ladderLength("ddb", "cbd", dict));
        // 0
        System.out.println(s.ladderLengthI("ddb", "cbd", dict));
    }

    // Assumptions:
    // 1. All words have the same length.
    // 2. All words contain only lowercase alphabetic characters.
    // 3. There are no duplicates in the word list.
    // 4.The beginWord and endWord are non-empty and are not the same.
    // Solution 1: BFS
    // total nodes = b^0 + b^1 + b^2 ... b^d   -> O(b^d)
    // Time O(word.length * 26 * wordList.length)
    // Space O(wordList.length)
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = buildSet(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        int step = 0;
        int length = beginWord.length();
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int count = 0; count < size; count++) {
                // expand intermediate word
                char[] word = queue.poll().toCharArray();
                for (int i = 0; i < length; i++) {
                    char originChar = word[i];
                    for (int ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == originChar) {
                            continue;
                        }
                        word[i] = (char)ch;
                        String newWord = new String(word);
                        // find the solution
                        if (newWord.equals(endWord)) {
                            return step + 1;
                        }
                        // generate neighbor word in the wordList
                        if (dict.contains(newWord)) {
                            queue.offer(newWord);
                            dict.remove(newWord);
                        }
                    }
                    word[i] = originChar;
                }
            }
        }
        return 0;
    }

    private Set<String> buildSet(List<String> wordList) {
        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }
        return set;
    }

    // Solution 2: Bidirectional BFS
    // total nodes = 2(b^0 + b^1 + b^2 ... b^(d/2))   -> O(b^(d/2))
    // Time O(word.length * 26 * wordList.length)
    // Space O(wordList.length)
    public int ladderLengthI(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = buildSet(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        int step = 0;
        int length = beginWord.length();
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        q1.add(beginWord);
        q2.add(endWord);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            step++;
            // always expand the smaller queue first - try to balance
            if (q1.size() > q2.size()) {
                swap(q1, q2);
            }
            Set<String> q = new HashSet<>();
            for (String w : q1) {
                // expand intermediate word
                char[] word = w.toCharArray();
                for (int i = 0; i < length; i++) {
                    char originChar = word[i];
                    for (int ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == originChar) {
                            continue;
                        }
                        word[i] = (char)ch;
                        String newWord = new String(word);
                        // find the solution
                        if (q2.contains(newWord)) {
                            return step + 1;
                        }
                        // generate neighbor word in the wordList
                        if (dict.contains(newWord)) {
                            q.add(newWord);
                            dict.remove(newWord);
                        }
                    }
                    word[i] = originChar;
                }
                q1 = q;
            }
        }
        return 0;
    }

    private void swap(Set<String> q1, Set<String> q2) {
        Set<String> tmp = q1;
        q1 = q2;
        q2 = tmp;
    }

    // another implementation:
    public int ladderLengthII(String beginWord, String endWord, List<String> wordList) {
        int endIndex = wordList.indexOf(endWord);
        if (endIndex == -1) {
            return 0;
        }

        List<String> words;
        int beginIndex = wordList.indexOf(beginWord);
        if (beginIndex == -1) {
            // the input wordList might not be appendable (e.g., the result of Arrays.asList)
            words = new ArrayList<>(wordList);
            words.add(beginWord);
            beginIndex = words.size() - 1;
        } else {
            words = wordList;
        }

        NeighborFinder finder = new NeighborFinder(words);

        Queue<Integer> queue = new ArrayDeque<>();
        int[] step = new int[words.size()];
        Arrays.fill(step, -1);

        queue.offer(beginIndex);
        step[beginIndex] = 0;
        Tracer tracer = new Tracer(words);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            if (x == endIndex) {
                return step[endIndex] + 1;
            }

            List<Integer> result = finder.findNeighbors(x);

            for (int y : finder.findNeighbors(x)) {
                if (step[y] == -1) {
                    queue.offer(y);
                    step[y] = step[x] + 1;
                }
                if (step[x] + 1 == step[y]) {
                    tracer.addPredecessor(x, y);
                }
            }
        }
        return 0;
    }

    static class NeighborFinder {
        public NeighborFinder(List<String> words) {
            for (int i = 0; i < words.size(); i++) {
                String word = words.get(i);
                wordIndex.put(word, i);
            }
            this.words = words;
        }

        public List<Integer> findNeighbors(int i) {
            List<Integer> neighbors = new ArrayList<>(16);
            String word = words.get(i);
            StringBuilder wordModifier = new StringBuilder(word);
            for (int j = 0; j < wordModifier.length(); j++) {
                char orig = word.charAt(j);
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == orig) {
                        continue;
                    }
                    wordModifier.setCharAt(j, c);
                    int neighbor = wordIndex.getOrDefault(wordModifier.toString(), -1);
                    if (neighbor != -1) {
                        neighbors.add(neighbor);
                    }
                }
                wordModifier.setCharAt(j, orig);
            }
            return neighbors;
        }

        private Map<String, Integer> wordIndex = new HashMap<>();
        private List<String> words;
    }

    static class Tracer {
        public Tracer(List<String> words) {
            this.words = words;

            preds = new ArrayList<>(words.size());
            for (int i = 0; i < words.size(); i++) {
                preds.add(new ArrayList<>(16));
            }
        }

        public void addPredecessor(int x, int y) {
            preds.get(y).add(x);
        }

        public List<List<String>> findLadders(int beginIndex, int y) {
            List<List<String>> ladders = new ArrayList<>();
            List<String> trace = new ArrayList<>();
            trace.add(words.get(y));
            findLaddersHelper(beginIndex, y, trace, ladders);
            return ladders;
        }

        private void findLaddersHelper(int beginIndex, int y, List<String> trace, List<List<String>> ladders) {
            if (beginIndex == y) {
                List<String> ladder = new ArrayList<>(trace);
                Collections.reverse(ladder);
                ladders.add(ladder);
                return;
            }
            for (int x : preds.get(y)) {
                trace.add(words.get(x));
                findLaddersHelper(beginIndex, x, trace, ladders);
                trace.remove(trace.size() - 1);
            }
        }

        private List<String> words;
        private List<List<Integer>> preds;
    }
}
