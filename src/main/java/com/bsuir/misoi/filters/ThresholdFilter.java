package com.bsuir.misoi.filters;

import com.bsuir.misoi.common.ImageOperations;

import java.awt.image.BufferedImage;

public class ThresholdFilter {

    private static ThresholdFilter instance = new ThresholdFilter();

    private Integer requiredThresholdValue = 140;

    private ThresholdFilter() {
    }

    public static ThresholdFilter getInstance() {
        return instance;
    }

    public BufferedImage filter(BufferedImage img) {
        int height = img.getHeight();
        int width = img.getWidth();
        int red, green, blue;
        BufferedImage finalThresholdImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int color = img.getRGB(x, y);
                red = ImageOperations.getRed(color);
                green = ImageOperations.getGreen(color);
                blue = ImageOperations.getBlue(color);
                if ((red + green + blue) / 3 < requiredThresholdValue) {
                    finalThresholdImage.setRGB(x, y, ImageOperations.mixColor(0, 0, 0));
                } else {
                    finalThresholdImage.setRGB(x, y, ImageOperations.mixColor(255, 255, 255));
                }
            }
        }
        return finalThresholdImage;
    }

    public Integer getRequiredThresholdValue() {
        return requiredThresholdValue;
    }

    public void setRequiredThresholdValue(Integer requiredThresholdValue) {
        this.requiredThresholdValue = requiredThresholdValue;
    }
}
