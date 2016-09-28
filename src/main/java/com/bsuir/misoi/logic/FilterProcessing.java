package com.bsuir.misoi.logic;

import com.bsuir.misoi.common.FileManipulator;
import com.bsuir.misoi.filters.ThresholdFilter;

import java.awt.image.BufferedImage;

public class FilterProcessing {

    private static FilterProcessing instance = new FilterProcessing();

    private FileManipulator fileManipulator;

    private ThresholdFilter thresholdFilter;

    private FilterProcessing() {
        fileManipulator = FileManipulator.getInstance();
        thresholdFilter = ThresholdFilter.getInstance();
    }

    public static FilterProcessing getInstance() {
        return instance;
    }

    public void startFilterChain() {
        for (BufferedImage f : fileManipulator.getSourceImageFiles()) { //TODO: stream
            fileManipulator.saveImage(thresholdFilter.filter(f), "thresholdFilter");//TODO : string move
        }
    }

}
