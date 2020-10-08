package ru.mail.polis.ads.homework2;

import java.math.BigInteger;
import java.util.Scanner;

public class Task4{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int k = Integer.parseInt(in.nextLine());

        String[] numbersInStringArray = in.nextLine().split(" ");
        int length = numbersInStringArray.length;
        BigInteger[] array = new BigInteger[length];
        for (int i = 0; i < length; i++){
            array[i] = new BigInteger(numbersInStringArray[i]);
        }

        System.out.println(array[findOrderStatistic(array, length - k)]);
    }

    public static int findOrderStatistic(BigInteger[] arrInt, int k){
        int begin = 0, end = arrInt.length;
        while (true) {
            int p = lomuto(arrInt, begin, end);
            if (p == k) return p;
            if (k > p) {
                begin = p + 1;
            } else {
                end = p;
            }
        }
    }

    private static int lomuto(BigInteger[] bigIntegers, int l, int r) {
        BigInteger p = bigIntegers[l];
        int j = l;
        for (int i = l + 1; i < r; i++){
            if (bigIntegers[i].compareTo(p) < 0) {
                j++;
                BigInteger temp = bigIntegers[j];
                bigIntegers[j] = bigIntegers[i];
                bigIntegers[i] = temp;
            }
        }
        BigInteger temp = bigIntegers[j];
        bigIntegers[j] = bigIntegers[l];
        bigIntegers[l] = temp;
        return j;
    }
}

