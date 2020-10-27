package ru.mail.polis.ads.homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task5 {
    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        out.println(countInv(arr, 0, arr.length - 1));
    }

    private static int countInv(int[] arr, int l, int r) {
        if (r <= l) {
            return 0;
        }
        int mid = (r + l) / 2;
        return countInv(arr, l, mid)
                + countInv(arr, mid + 1, r)
                + countInvWhenMerge(arr, l, mid, r);
    }

    private static int countInvWhenMerge(int[] arr, int l, int mid, int r) {
        int lInRightPart = mid + 1;
        int lInLeftPart = l;
        int invCount = 0;
        int[] res = new int[r - l + 1];
        int tempI = 0;
        while (lInLeftPart <= mid && lInRightPart <= r) {
            if (arr[lInLeftPart] <= arr[lInRightPart]) {
                res[tempI++] = arr[lInLeftPart++];
            } else {
                res[tempI++] = arr[lInRightPart++];
                invCount += (mid - lInLeftPart + 1);
            }
        }
        while (lInLeftPart <= mid) {
            res[tempI++] = arr[lInLeftPart++];
        }
        while (lInRightPart <= r) {
            res[tempI++] = arr[lInRightPart++];
        }
        System.arraycopy(res, 0, arr, l, res.length);
        return invCount;
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
