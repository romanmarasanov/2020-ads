package ru.mail.polis.ads.homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task2{
    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] arr = new int[n * m];
        String[] rightWayArray = new String[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[(n - i - 1) * m + j] = in.nextInt();
            }
        }
        int[] res = new int[n * m];
        fillResAndRightWayWithArr(arr, res, rightWayArray, n, m);

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int left = res[i * m + j - 1];
                int down = res[(i - 1) * m + j];
                if (left >= down) {
                    res[i * m + j] = left + arr[i * m + j];
                    rightWayArray[i * m + j] = rightWayArray[i * m + j - 1] + "R";
                } else {
                    res[i * m + j] = down + arr[i * m + j];
                    rightWayArray[i * m + j] = rightWayArray[(i - 1) * m + j] + "F";
                }
            }
        }
        out.println(rightWayArray[m * n - 1]);
    }

    private static void fillResAndRightWayWithArr(int[] arr, int[] res, String[] rightWayArray, int n, int m) {
        res[0] = arr[0];
        rightWayArray[0] = "";
        for (int i = 1; i < n; i++) {
            res[i * m] = res[(i - 1) * m] + arr[i * m];
            rightWayArray[i * m] = rightWayArray[(i - 1) * m] + "F";
        }
        for (int j = 1; j < m; j++) {
            res[j] = res[j - 1] + arr[j];
            rightWayArray[j] = rightWayArray[j-1] + "R";
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