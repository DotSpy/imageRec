package com.bsuir.misoi.filters;

import com.bsuir.misoi.common.ImageOperations;

import java.awt.image.BufferedImage;

// TODO: Заставить это работать или написать заново
public class DistanceTransform {

    public BufferedImage filter(BufferedImage src) {
        int height = src.getHeight();
        int width = src.getWidth();
        BufferedImage dst = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage pass1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage pass2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (src.getRGB(x, y) != ImageOperations.mixColor(0,0,0)) {
                    pass1.setRGB(x, y, 1);
                    pass2.setRGB(x, y, 1);
                }
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (pass1.getRGB(x, y) == 1) {
                    int a = pass1.getRGB(x - 1, y - 1) + 4;
                    int b = pass1.getRGB(x, y -1) + 3;
                    int c = pass1.getRGB(x + 1, y - 1) + 4;
                    int d = pass1.getRGB(x - 1, y) + 3;
                    int z = Math.min(Math.min(a, b), Math.min(c, d));
                    pass1.setRGB(x, y, z);
                }
            }
        }

        for (int y = height - 1; y >=0; y--) {
            for (int x = width - 1; x >= 0; x--) {
                if (pass2.getRGB(x, y) == 1) {
                    int f = pass2.getRGB(x + 1, y) + 3;
                    int g = pass2.getRGB(x - 1, y + 1) + 4;
                    int h = pass2.getRGB(x, y + 1) + 3;
                    int i = pass2.getRGB(x + 1, y + 1) + 4;
                    int z = Math.min(Math.min(f, g), Math.min(h, i));
                    pass2.setRGB(x, y, z);
                }
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                dst.setRGB(x, y, Math.min(pass1.getRGB(x, y), pass2.getRGB(x, y)));
            }
        }

        return dst;
    }
}
