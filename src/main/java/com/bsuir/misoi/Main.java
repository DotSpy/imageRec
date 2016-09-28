package com.bsuir.misoi;

import com.bsuir.misoi.logic.FilterProcessing;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FilterProcessing filterProcessing = FilterProcessing.getInstance();

        filterProcessing.startFilterChain();
    }
}
