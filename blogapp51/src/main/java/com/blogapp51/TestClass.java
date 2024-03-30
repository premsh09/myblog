package com.blogapp51;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class TestClass {
    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> newData = data.stream().filter(TestClass::isPrime).collect(Collectors.toList());
        System.out.println(newData);
    }
    public static boolean isPrime(int data){
        if (data <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(data); i++) {
            if (data % i == 0) {
                return false;
            }
        }
        return true;
    }
}