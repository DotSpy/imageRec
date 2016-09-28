package com.bsuir.misoi.filters;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class SharpenFilter {

    private static SharpenFilter instance = new SharpenFilter();

    private SharpenFilter() {
    }

    public static SharpenFilter getInstance() {
        return instance;
    }

    public BufferedImage filter(BufferedImage image) {
        float[] sharpenMatrix =
                {-1.0f, -1.0f, -1.0f,
                        -1.0f, 9.0f, -1.0f,
                        -1.0f, -1.0f, -1.0f};
        BufferedImageOp sharpenFilter = new ConvolveOp(new Kernel(3, 3, sharpenMatrix),
                ConvolveOp.EDGE_NO_OP, null);
        return sharpenFilter.filter(image, null);
    }
}
