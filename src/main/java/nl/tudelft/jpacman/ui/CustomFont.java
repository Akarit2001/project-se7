package nl.tudelft.jpacman.ui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class CustomFont {

    private static final String fileName = "src\\main\\resources\\font\\AtariST8x16SystemFont.ttf";
    private float sizeFont = 24f;

    private Font customFont;

    public CustomFont() {
        super();
    }

    public Font fontFormat() {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fileName));
            customFont = customFont.deriveFont(getSizeFont());

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (FontFormatException ignored) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customFont;
    }

    public float getSizeFont() {
        return sizeFont;
    }

    public void setSizeFont(float sizeFont) {
        this.sizeFont = sizeFont;
    }

}
