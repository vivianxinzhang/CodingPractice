package com.company;
import java.util.*;

public class PlaceToPutTheChairII {
    public static void main(String[] args) {
        PlaceToPutTheChairII s = new PlaceToPutTheChairII();
        char[][] gym = new char[][] {
                {' ', 'E', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        System.out.println(s.putChair(gym));    // [0, 1]

        gym = new char[][] {
                {'E','E',' ',' ',' '},
                {' ','E',' ',' ','E'},
                {' ',' ','E',' ',' '},
                {' ',' ',' ',' ',' '}
        };
        System.out.println(s.putChair(gym));    // [1, 1]
    }

    // Assumptions:
    // 1. There is at least one equipment in the gym
    // 2. The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed to be not null
    // Method 1:
    // Time O(n^4 * logn)  <- O(n^2 * n^2 * log(n^2))
    // Space O(n^2)
    public List<Integer> putChair(char[][] gym) {
        int M = gym.length;
        int N = gym[0].length;
        List<Integer> result = Arrays.asList(-1, -1);
        int globalMin = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int[][] cost = new int[M][N];
                if (dijkstra(gym, cost, i, j)) {
                    int currMinDistance = computeTotaltal(gym, cost, M, N);
                    if (currMinDistance < globalMin) {
                        result.set(0, i);
                        result.set(1, j);
                        globalMin = currMinDistance;
                    }
                }
            }
        }
        return result;
    }

    private boolean dijkstra(char[][] gym, int[][] cost, int i, int j) {
        boolean[][] visited = new boolean[gym.length][gym[0].length];
        cost[i][j] = 0;
        visited[i][j] = true;
        int pathCost = 1;
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(i, j));
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                Pair cur = queue.poll();
                List<Pair> neis = getNeis(cur, gym);
                for (Pair nei : neis) {
                    if (!visited[nei.i][nei.j]) {
                        visited[nei.i][nei.j] = true;
                        cost[nei.i][nei.j] = pathCost;
                        queue.offer(nei);
                    }
                }
                size--;
            }
            pathCost++;
        }

        for (int l = 0; l < gym.length; l++) {
            for (int m = 0; m < gym[0].length; m++) {
                if (!visited[l][m] && gym[l][m] == 'E') {
                    return false;
                }
            }
        }
        return true;
    }

    private List<Pair> getNeis(Pair cur, char[][] gym) {
        int x = cur.i;
        int y = cur.j;
        int M = gym.length;
        int N = gym[0].length;
        List<Pair> neis = new ArrayList<>();
        if (x + 1 < M) {
            neis.add(new Pair(x + 1, y));
        }
        if (y + 1 < N) {
            neis.add(new Pair(x, y + 1));
        }
        if (x - 1 >= 0) {
            neis.add(new Pair(x - 1, y));
        }
        if (y - 1 >= 0) {
            neis.add(new Pair(x, y - 1));
        }
        return neis;
    }


    private int computeTotaltal(char[][] gym, int[][] cost, int m, int n) {
        int total = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (gym[i][j] == 'E') {
                    total += cost[i][j];
                }
            }
        }
        return total;
    }

    class Pair {
        int i;
        int j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
