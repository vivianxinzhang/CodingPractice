package com.company;
import java.util.*;

public class WordLadderII {
    public static void main(String[] args) {
        WordLadderII s = new WordLadderII();
        List<String> dict = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(s.findLadders("hit", "cog", dict));
        /*
                          dot  --->  dog
                       /                  \
         hit ---> hot                       cog
                       \                  /
                          lot  --->   log
        */

        dict = Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee");
        System.out.println(s.findLadders("red", "tax", dict));
        /*
                          rex  --->  tex
                       /          /       \
                 red            /            tax
                       \      /           /
                          ted  --->  tad
        */
    }

    // Step 1: BFS 创建 graph
    // Step 2: DFS 找出所有路径
    // Time O(n * 26^l)    l = word.length   n = wordlist.size
    // Space O(n + k*l)
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = buildSet(wordList);
        List<List<String>> result = new ArrayList<>();
        if (!dict.contains(endWord)) {
            return result;
        }
        Map<String, Integer> steps = new HashMap<>();
        Map<String, List<String>> parents = new HashMap<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        int length = beginWord.length();
        boolean found = false;
        int step = 0;
        // if already found, need to finish expand words in the current level
        while (!queue.isEmpty() && !found) {
            step++;
            for (int count = queue.size(); count > 0; count--) {
                String parentWord = queue.poll();
                char[] word = parentWord.toCharArray();
                for (int i = 0; i < length; i++) {
                    char originChar = word[i];
                    // enumerate all possible neighbor word
                    for (int ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == originChar) {
                            continue;
                        }
                        word[i] = (char)ch;
                        String neiWord = new String(word);
                        if (steps.containsKey(neiWord)) {
                            System.out.println(step + " " + neiWord + " " + steps.get(neiWord));
                        }
                        // find the solution
                        if (neiWord.equals(endWord)) {
                            found = true;
                            List<String> neiWordParents = parents.getOrDefault(neiWord, new ArrayList<>());
                            neiWordParents.add(parentWord);
                            parents.put(neiWord, neiWordParents);
                        } else if (steps.containsKey(neiWord) && step + 1 == steps.get(neiWord)) {
                            List<String> neiWordParents = parents.get(neiWord);
                            neiWordParents.add(parentWord);
                            parents.put(neiWord, neiWordParents);
                        }
                        if (dict.contains(neiWord)) {
                            dict.remove(neiWord);
                            queue.offer(neiWord);
                            steps.put(neiWord, step + 1);
                            List<String> neiWordParents = new ArrayList<>();
                            neiWordParents.add(parentWord);
                            parents.put(neiWord, neiWordParents);
                        }
                    }
                    word[i] = originChar;
                }
            }
        }
        if (found) {
            List<String> curr = new LinkedList<>();
            curr.add(endWord);
            findPath(endWord, beginWord, parents, curr, result);
        }
        return result;
    }

    private void findPath(String word, String beginWord, Map<String, List<String>> map, List<String> curr, List<List<String>> result) {
        if (word == beginWord) {
            result.add(new ArrayList<>(curr));
            return;
        }
        List<String> parents = map.get(word);
        for (int i = 0; i < parents.size(); i++) {
            curr.add(0, parents.get(i));
            findPath(parents.get(i), beginWord, map, curr, result);
            curr.remove(0);
        }
    }


    private Set<String> buildSet(List<String> wordList) {
        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }
        return set;
    }
}
