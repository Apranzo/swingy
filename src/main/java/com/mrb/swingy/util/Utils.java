package com.mrb.swingy.util;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    public static int getRandom(int from, int untill) {
        return Math.abs(ThreadLocalRandom.current().nextInt(from, untill));
    }
}
