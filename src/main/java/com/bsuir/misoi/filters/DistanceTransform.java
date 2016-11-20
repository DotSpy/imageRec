package com.bsuir.misoi.filters;

import java.awt.image.BufferedImage;

public class DistanceTransform {

    public BufferedImage filter(BufferedImage src) {

        int w = src.getWidth();
        int h = src.getHeight();
        float[] dpix = src.getData().getSamples(0, 0, w, h, 0, (float[] )null);
        final float K1 = 1;
        final float K2 = (float)Math.sqrt(2);

        for (int v = 0; v < h; v++) {
            for (int u = 0; u < w; u++) {
                int i = v * w + u;
                if (dpix[i] > 0) // this is a foreground pixel
                    dpix[i] = 0; // zero distance to foregorund
                else
                    dpix[i] = Float.POSITIVE_INFINITY;
            }
        }

        for (int v = 0; v < h; v++) {
            for (int u = 0; u < w; u++) {
                int i = v * w + u;
                if (dpix[i]>0) { //not a foreground pixel
                    //compute distances via neighboring pixels
                    float d1 = Float.POSITIVE_INFINITY;
                    float d2 = Float.POSITIVE_INFINITY;
                    float d3 = Float.POSITIVE_INFINITY;
                    float d4 = Float.POSITIVE_INFINITY;
                    if (u>0) 			d1 = K1 + dpix[v*w+u-1];
                    if (u>0 && v>0) 	d2 = K2 + dpix[(v-1)*w+u-1];
                    if (v>0)			d3 = K1 + dpix[(v-1)*w+u];
                    if (v>0 && u<w-1)	d4 = K2 + dpix[(v-1)*w+u+1];

                    float dmin = dpix[i];
                    if (d1<dmin) dmin = d1;
                    if (d2<dmin) dmin = d2;
                    if (d3<dmin) dmin = d3;
                    if (d4<dmin) dmin = d4;
                    dpix[i] = dmin;
                }
            }
        }

        for (int v = h - 1; v >= 0; v--) {
            for (int u = w - 1; u >= 0; u--) {
                int i = v * w + u;
                if (dpix[i] > 0) { //not a foreground pixel
                    //compute distances via neighboring pixels
                    float d1 = Float.POSITIVE_INFINITY;
                    float d2 = Float.POSITIVE_INFINITY;
                    float d3 = Float.POSITIVE_INFINITY;
                    float d4 = Float.POSITIVE_INFINITY;
                    if (u<w-1) 			d1 = K1 + dpix[v*w+u+1];
                    if (u<w-1 && v<h-1)	d2 = K2 + dpix[(v+1)*w+u+1];
                    if (v<h-1)			d3 = K1 + dpix[(v+1)*w+u];
                    if (v<h-1 && u>0)	d4 = K2 + dpix[(v+1)*w+u-1];

                    float dmin = dpix[i];
                    if (d1<dmin) dmin = d1;
                    if (d2<dmin) dmin = d2;
                    if (d3<dmin) dmin = d3;
                    if (d4<dmin) dmin = d4;
                    dpix[i] = dmin;
                }
            }
        }

        float maxval = getRealMaxValue(dpix);
        for (int i=0; i<dpix.length; i++){
            if (dpix[i]>maxval)
                dpix[i] = maxval;
        }

        BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
        dst.getRaster().setSamples(0, 0, w, h, 0, dpix);
        return src;
    }

    static float getRealMaxValue(float[] pix){
        float maxval = Float.NEGATIVE_INFINITY;
        for (int i = 0; i < pix.length; i++) {
            if (pix[i] < Float.POSITIVE_INFINITY && pix[i] > maxval)
                maxval = pix[i];
        }
        return maxval;
    }
}
