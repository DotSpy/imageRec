package com.bsuir.misoi.common;

public class PixelUtils {

    public static int clamp(int c) {
        if (c < 0)
            return 0;
        if (c > 255)
            return 255;
        return c;
    }
}
