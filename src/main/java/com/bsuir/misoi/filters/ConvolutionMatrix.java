package com.bsuir.misoi.filters;

import com.bsuir.misoi.common.SharpenMatrix;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConvolutionMatrix {

    public static final int SIZE = 3;

    private static ConvolutionMatrix instance = new ConvolutionMatrix();

    private ConvolutionMatrix() {
    }

    public static ConvolutionMatrix getInstance() {
        return instance;
    }

    public BufferedImage filter(BufferedImage src, SharpenMatrix matrix) {
        int width = src.getWidth();
        int height = src.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int a, r, g, b;
        int sumR, sumG, sumB;
        int[][] pixels = new int[SIZE][SIZE];

        for (int y = 0; y < height - 2; ++y) {
            for (int x = 0; x < width - 2; ++x) {

                for (int i = 0; i < SIZE; ++i) {
                    for (int j = 0; j < SIZE; ++j) {
                        pixels[i][j] = src.getRGB(x + i, y + j);
                    }
                }

                sumR = sumG = sumB = 0;

                for (int i = 0; i < SIZE; ++i) {
                    for (int j = 0; j < SIZE; ++j) {
                        Color c = new Color(pixels[i][j], true);
                        sumR += (c.getRed() * matrix.Matrix[i][j]);//TODO: must be generic matrix
                        sumG += (c.getGreen() * matrix.Matrix[i][j]);
                        sumB += (c.getBlue() * matrix.Matrix[i][j]);
                    }
                }

                r = (int) (sumR / matrix.Div + matrix.Offset);
                if (r < 0) {
                    r = 0;
                } else if (r > 255) {
                    r = 255;
                }

                g = (int) (sumG / matrix.Div + matrix.Offset);
                if (g < 0) {
                    g = 0;
                } else if (g > 255) {
                    g = 255;
                }

                b = (int) (sumB / matrix.Div + matrix.Offset);
                if (b < 0) {
                    b = 0;
                } else if (b > 255) {
                    b = 255;
                }
                Color mid = new Color(pixels[1][1], true);
                a = mid.getAlpha();
                int col = (a << 24) | (r << 16) | (g << 8) | b;
                result.setRGB(x + 1, y + 1, col);
            }
        }
        // final image
        return result;
    }
}
