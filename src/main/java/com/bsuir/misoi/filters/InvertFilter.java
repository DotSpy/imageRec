package com.bsuir.misoi.filters;

import java.awt.image.BufferedImage;

public class InvertFilter {

    private static InvertFilter instance = new InvertFilter();

    private InvertFilter() {
    }

    public static InvertFilter getInstance() {
        return instance;
    }

    public BufferedImage filter(BufferedImage img) {
        int height = img.getHeight();
        int width = img.getWidth();
        BufferedImage finalThresholdImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int color = img.getRGB(x, y);
                int a = color & 0xff000000;
                finalThresholdImage.setRGB(x, y, a | (~color & 0x00ffffff));
            }
        }
        return finalThresholdImage;
    }
}
