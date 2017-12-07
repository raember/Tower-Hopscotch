package ch.zhaw.psit.towerhopscotch.util;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Loader for fonts
 * @author Nicolas Eckhart
 */
public class FontLoader {

    /**
     * Load the font from the specified path
     * @param path Path
     * @param size Size
     * @return Font
     */
    public static Font loadFont(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(ImageUtil.class.getResource(path).getFile())).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
