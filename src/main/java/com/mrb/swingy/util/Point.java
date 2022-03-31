package com.mrb.swingy.util;

import lombok.Data;

/**
 * Created by chvs on 19.06.2018.
 */
@Data
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
