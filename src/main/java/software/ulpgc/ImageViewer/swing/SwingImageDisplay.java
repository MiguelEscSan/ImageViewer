package software.ulpgc.ImageViewer.swing;

import software.ulpgc.ImageViewer.interfaces.Image;
import software.ulpgc.ImageViewer.interfaces.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingImageDisplay extends JPanel implements ImageDisplay {

    private Image image;
    private BufferedImage bitmap;

    @Override
    public void show(Image image) {
        this.image = image;
        this.bitmap = load(image.name());
        this.repaint();
    }


    @Override
    public Image image() {
        return this.image;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Resizer resizer = new Resizer(new Dimension(this.getWidth(), this.getHeight()));
        Dimension resized = resizer.resize(new Dimension(bitmap.getWidth(), bitmap.getHeight()));
        int x = (this.getWidth() - resized.width) / 2;
        int y = (this.getHeight() - resized.height) / 2;
        g.drawImage(bitmap, x, y, resized.width, resized.height, null);
    }

    private BufferedImage load(String name){
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private class Resizer {
        private final Dimension dimension;

        public Resizer(Dimension dimension) {
            this.dimension = dimension;
        }

        public Dimension resize(Dimension original) {
            int newWidth = dimension.width;
            int newHeight = dimension.height;

            double widthRatio = (double) newWidth / original.width;
            double heightRatio = (double) newHeight / original.height;

            double ratio = Math.min(widthRatio, heightRatio);

            return new Dimension((int) (original.width * ratio), (int) (original.height * ratio));
        }
    }
}
