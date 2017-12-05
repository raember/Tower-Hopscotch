package ch.zhaw.psit.towerhopscotch.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Loader for images
 * @author Nicolas Eckhart
 */
public class ImageUtil {

    /**
     * Load image from specified path
     * @param path Path
     * @return BufferedImage
     */
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageUtil.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
