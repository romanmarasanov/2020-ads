package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyStack stack = new MyStack();
        String command = in.next();
        while (!command.equals("exit")){
            switch (command){
                case "push":
                    int number = in.nextInt();
                    stack.push(number);
                    out.print("ok\n");
                    break;
                case "pop":
                    if (stack.size() == 0){
                        out.print("error\n");
                    } else{
                        out.print(stack.pop() + "\n");
                    }
                    break;
                case "back":
                    if (stack.size() == 0){
                        out.print("error\n");
                    } else{
                        out.print(stack.back() + "\n");
                    }
                    break;
                case "size":
                    out.print(stack.size() + "\n");
                    break;
                case "clear":
                    stack.clear();
                    out.print("ok\n");
                    break;
            }
            command = in.next();
        }
        out.print("bye");
    }

    private static class MyStack{
        private int size;
        private Node top;

        MyStack(){
            this.size = 0;
            this.top = null;
        }

        public void push(int n){
            Node newTop = new Node();
            newTop.next = this.top;
            newTop.value = n;
            this.top = newTop;
            size++;
        }

        public int pop(){
            if (size == 0){
                throw new NullPointerException();
            }
            int n = this.top.value;
            this.top = this.top.next;
            this.size--;
            return n;
        }

        public int back(){
            if (size == 0){
                throw new NullPointerException();
            }
            return this.top.value;
        }

        public int size(){
            return this.size;
        }

        public void clear(){
            this.size = 0;
            this.top = null;
        }
    }

    private static class Node{
        public Integer value;
        public Node next;

        Node(){
            this.value = null;
            this.next = null;
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