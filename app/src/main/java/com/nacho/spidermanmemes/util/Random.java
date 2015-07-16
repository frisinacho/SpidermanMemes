package com.nacho.spidermanmemes.util;

public class Random {
    public static int generate(int lastNumber) {
        return (int) (Math.random() * lastNumber);
        //return (int) ((Math.random() * 20) % lastNumber);
    }
}
