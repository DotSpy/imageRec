package com.bsuir.misoi.logic;

import com.bsuir.misoi.common.FileManipulator;
import com.bsuir.misoi.common.SharpenMatrix;
import com.bsuir.misoi.filters.ConvolutionMatrix;
import com.bsuir.misoi.filters.InvertFilter;
import com.bsuir.misoi.filters.SharpenFilter;
import com.bsuir.misoi.filters.ThresholdFilter;

import java.awt.image.BufferedImage;

public class FilterProcessing {

    private static FilterProcessing instance = new FilterProcessing();

    private FileManipulator fileManipulator;

    private ThresholdFilter thresholdFilter;

    private InvertFilter invertFilter;

    private SharpenFilter sharpenFilter;

    private ConvolutionMatrix convolutionMatrix;

    private SharpenMatrix sharpenMatrix;

    private FilterProcessing() {
        fileManipulator = FileManipulator.getInstance();
        thresholdFilter = ThresholdFilter.getInstance();
        invertFilter = InvertFilter.getInstance();
        sharpenFilter = SharpenFilter.getInstance();
        convolutionMatrix = ConvolutionMatrix.getInstance();
        sharpenMatrix = new SharpenMatrix();
    }

    public static FilterProcessing getInstance() {
        return instance;
    }

    public void startFilterChain() {
        for (BufferedImage f : fileManipulator.getSourceImageFiles()) { //TODO: stream
            fileManipulator.saveImage(thresholdFilter.filter(f), "thresholdFilter");//TODO : string move
            fileManipulator.saveImage(invertFilter.filter(f), "invertFilter");
            fileManipulator.saveImage(convolutionMatrix.filter(f, sharpenMatrix), "sharpenFilter");
        }
    }

}
