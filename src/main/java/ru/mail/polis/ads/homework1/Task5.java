package ru.mail.polis.ads.homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyQueue queue = new MyQueue();
        String command = in.next();
        while (!command.equals("exit")){
            switch (command){
                case "push":
                    int n = in.nextInt();
                    queue.push(n);
                    out.print("ok\n");
                    break;
                case "pop":
                    out.println(queue.pop());
                    break;
                case "front":
                    out.println(queue.front());
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    out.print("ok\n");
            }
            command = in.next();
        }
        out.print("bye");
    }

    private static class MyQueue{
        private static final int MAX_LENGTH = 100;
        private final int[] data;
        private int size, frontIndex, backIndex;

        MyQueue(){
            this.data = new int[MAX_LENGTH];
            this.size = 0;
            this.backIndex = 99;
            this.frontIndex = 0;
        }
        public void push(int n){
            if (this.backIndex == MAX_LENGTH - 1){
                this.backIndex = 0;
                this.data[0] = n;
            } else{
                this.backIndex++;
                this.data[this.backIndex] = n;
            }
            this.size++;
        }
        public int pop(){
            this.size--;
            if (this.frontIndex == MAX_LENGTH - 1){
                this.frontIndex = 0;
                return this.data[MAX_LENGTH - 1];
            } else{
                this.frontIndex++;
                return this.data[this.frontIndex - 1];
            }
        }
        public int front(){
            return this.data[this.frontIndex];
        }
        public int size(){
            return this.size;
        }
        public void clear(){
            this.size = 0;
            this.frontIndex = 0;
            this.backIndex = 99;
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}