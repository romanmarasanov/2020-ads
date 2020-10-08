package ru.mail.polis.ads.homework2;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());

        int[] array = new int[n];
        for (int i = 0; i < n; i++){
            array[i] = in.nextInt();
        }

        trickyBubbleSort(array, n);

        for (int elem : array){
            System.out.print(elem + " ");
        }
    }

    public static boolean trickyCompare(int r, int l){
        if (l % 10 != r % 10){
            return l % 10 > r % 10;
        }
        return l > r;
    }

    public static void trickyBubbleSort(int[] arr, int n){
        for (int i = 0; i < n - 1; i++){
            for (int j = 0; j < n - 1 - i; j++){
                if (!trickyCompare(arr[j], arr[j + 1])){
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

}
