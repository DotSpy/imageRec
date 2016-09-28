package com.bsuir.misoi.common;

public class ImageOperations {

    public static int mixColor(int red, int green, int blue) {
        return red<<16|green<<8|blue;
    }

    public static int getRed(int color) {
        return (color & 0x00ff0000)  >> 16;
    }

    public static int getGreen(int color) {
        return	(color & 0x0000ff00)  >> 8;
    }

    public static int getBlue(int color) {
        return (color & 0x000000ff);

    }
}
