package ru.mail.polis.ads.homework9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// task: https://www.e-olymp.com/ru/problems/4856

public class Task4 {

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int start = in.nextInt() - 1;
        int finish = in.nextInt() - 1;
        final int INF = Integer.MAX_VALUE;

        int[][] graph = new int[n][n];
        for (int i = 0; i < m; i++) {
            int b = in.nextInt();
            int e = in.nextInt();
            int w = in.nextInt();
            graph[b - 1][e - 1] = w;
            graph[e - 1][b - 1] = w;
        }

        boolean[] visited = new boolean[n];
        int[] distance = new int[n];
        int[] previous = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = INF;
            previous[i] = -1;
        }

        visited[start] = true;
        distance[start] = 0;
        int minDistance = 0;
        int minVertex = start;

        while (minDistance < INF) {
            int current = minVertex;
            visited[current] = true;
            for (int i = 0; i < n; i++) {
                if (graph[current][i] != 0) {
                    if (distance[current] + graph[current][i] < distance[i]) {
                        distance[i] = distance[current] + graph[current][i];
                        previous[i] = current;
                    }
                }
            }
            minDistance = INF;
            for (int j = 0; j < n; ++j) {
                if (!visited[j] && distance[j] < minDistance) {
                    minDistance = distance[j];
                    minVertex = j;
                }
            }
        }
        if (distance[finish] != INF) {
            out.write(distance[finish] + "\n");
            List<Integer> path = new ArrayList<>();
            path.add(finish);
            int j = previous[finish];
            while (j != -1) {
                path.add(j);
                j = previous[j];
            }
            for (int i = path.size() - 1; i >= 0; i--) {
                out.write((path.get(i) + 1) + " ");
            }
        } else {
            out.write("-1");
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

    }
}
