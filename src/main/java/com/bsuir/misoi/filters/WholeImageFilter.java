package com.bsuir.misoi.filters;

import java.awt.*;
import java.awt.image.*;

public abstract class WholeImageFilter extends AbstractBufferedImageOp {

    protected Rectangle transformedSpace;

    protected Rectangle originalSpace;

    public WholeImageFilter() {
    }

    public BufferedImage filter( BufferedImage src) {
        return filter(src, null);
    }

    public BufferedImage filter( BufferedImage src, BufferedImage dst ) {
        int width = src.getWidth();
        int height = src.getHeight();
        int type = src.getType();
        WritableRaster srcRaster = src.getRaster();

        originalSpace = new Rectangle(0, 0, width, height);
        transformedSpace = new Rectangle(0, 0, width, height);
        transformSpace(transformedSpace);

        if ( dst == null ) {
            ColorModel dstCM = src.getColorModel();
            dst = new BufferedImage(dstCM, dstCM.createCompatibleWritableRaster(transformedSpace.width, transformedSpace.height), dstCM.isAlphaPremultiplied(), null);
        }
        WritableRaster dstRaster = dst.getRaster();

        int[] inPixels = getRGB( src, 0, 0, width, height, null );
        inPixels = filterPixels( width, height, inPixels, transformedSpace );
        setRGB( dst, 0, 0, transformedSpace.width, transformedSpace.height, inPixels );

        return dst;
    }

    protected void transformSpace(Rectangle rect) {
    }

    protected abstract int[] filterPixels( int width, int height, int[] inPixels, Rectangle transformedSpace );
}
