package com.bsuir.misoi.common;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileManipulator {

    private static FileManipulator instance = new FileManipulator();

    private static final String RESOURCE_PATH = "D:\\5course\\IMAGE\\imageRec\\src\\main\\resources";

    private FileManipulator() {
    }

    public static FileManipulator getInstance() {
        return instance;
    }

    public List<BufferedImage> getSourceImageFiles() {
        List<BufferedImage> bufferedImages = new ArrayList<>();
        File dir = new File(getResourcePath());
        for (File f : dir.listFiles()) { //TODO: java 8 stream
            try {
                BufferedImage in = ImageIO.read(f);
                bufferedImages.add(in);
            } catch (IOException e) {
                e.printStackTrace(); //TODO: logger
            }

        }
        return bufferedImages;
    }

    private String getResourcePath() {
        File resourcesDirectory = new File(RESOURCE_PATH + "/source"); //TODO: move to properties file
        return resourcesDirectory.getAbsolutePath();
    }

    public void saveImage(BufferedImage image, String filterName) {
        File outputfile = new File(RESOURCE_PATH + "/" + filterName + "/" + UUID.randomUUID().toString() + ".jpg");//TODO; сейвать файлы с их начальным именем?
        outputfile.getParentFile().mkdirs(); //TODO:вынести
        try {
            outputfile.createNewFile();
            ImageIO.write(image, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
