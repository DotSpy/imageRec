package com.bsuir.misoi;

import com.bsuir.misoi.logic.FilterProcessing;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FilterProcessing filterProcessing = FilterProcessing.getInstance();

        filterProcessing.startFilterChain();
/*        BufferedImage image = ImageIO.read(
                new File("D:\\5course\\IMAGE\\imageRec\\src\\main\\resources\\Lenna.png"));

        int w = image.getWidth();
        int h = image.getHeight();

        int[] dataBuffInt = image.getRGB(0, 0, w, h, null, 0, w);

        Color c = new Color(dataBuffInt[100]);

        System.out.println(c.getRed());   // = (dataBuffInt[100] >> 16) & 0xFF
        System.out.println(c.getGreen()); // = (dataBuffInt[100] >> 8)  & 0xFF
        System.out.println(c.getBlue());  // = (dataBuffInt[100] >> 0)  & 0xFF
        System.out.println(c.getAlpha()); // = (dataBuffInt[100] >> 24) & 0xFF*/
    }
}
