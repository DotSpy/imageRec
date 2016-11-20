package com.bsuir.misoi.logic;

import com.bsuir.misoi.common.FileManipulator;
import com.bsuir.misoi.common.SharpenMatrix;
import com.bsuir.misoi.filters.*;

import java.awt.image.BufferedImage;

public class FilterProcessing {

    private static FilterProcessing instance = new FilterProcessing();

    private FileManipulator fileManipulator;

    private ThresholdFilter thresholdFilter;

    private InvertFilter invertFilter;

    private SharpenFilter sharpenFilter;

    private ConvolutionMatrix convolutionMatrix;

    private SharpenMatrix sharpenMatrix;

    private LaplaceFilter laplaceFilter;

    private EdgeFilter edgeFilter;

    private DistanceTransform distanceTransform;

    private FilterProcessing() {
        fileManipulator = FileManipulator.getInstance();
        thresholdFilter = ThresholdFilter.getInstance();
        invertFilter = InvertFilter.getInstance();
        sharpenFilter = SharpenFilter.getInstance();
        convolutionMatrix = ConvolutionMatrix.getInstance();
        sharpenMatrix = new SharpenMatrix();
        laplaceFilter = new LaplaceFilter();
        edgeFilter = new EdgeFilter();
        distanceTransform = new DistanceTransform();
    }

    public static FilterProcessing getInstance() {
        return instance;
    }

    public void startFilterChain() {
        for (BufferedImage f : fileManipulator.getSourceImageFiles()) { //TODO: stream
            fileManipulator.saveImage(thresholdFilter.filter(f, 140), "thresholdFilter");//TODO : string move
            fileManipulator.saveImage(invertFilter.filter(f), "invertFilter");
            fileManipulator.saveImage(convolutionMatrix.filter(f, sharpenMatrix), "sharpenFilter");
            fileManipulator.saveImage(laplaceFilter.filter(f), "laplaceFilter");
            fileManipulator.saveImage(thresholdFilter.filter(edgeFilter.filter(f), 140), "edgeFilter");
            fileManipulator.saveImage(distanceTransform.filter(thresholdFilter.filter(edgeFilter.filter(f), 140)), "distanceTransformFilter");
        }
    }

}
