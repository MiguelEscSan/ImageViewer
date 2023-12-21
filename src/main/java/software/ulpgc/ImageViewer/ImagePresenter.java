package software.ulpgc.ImageViewer;

import software.ulpgc.ImageViewer.interfaces.Image;
import software.ulpgc.ImageViewer.interfaces.ImageDisplay;
import software.ulpgc.ImageViewer.interfaces.ImageDisplay.Released;

import static java.lang.Math.abs;
import static software.ulpgc.ImageViewer.interfaces.ImageDisplay.*;

public class ImagePresenter {

    private final ImageDisplay display;
    private Image image;

    public ImagePresenter(Image image, ImageDisplay display) {
        this.display = display;
        this.image = image;
        this.display.on((Shift) offset -> shift(offset));
        this.display.on((Released) offset -> released(offset));
        this.display.on((Pressed) offset -> pressed(offset));
        this.display.clear();
    }

    private void pressed(int offset) {
        setImage();
    }

    public void setImage() {
        this.image = this.display.image();
    }

    private void shift(int offset) {
        display.clear();
        display.paint(image, offset);
        if (offset > 0)
            display.paint(image.previous(), offset - display.getWidth());
        else
            display.paint(image.next(), display.getWidth() + offset);

    }

    private void released(int offset) {
        if (Math.abs(offset) >= display.getWidth() / 2)
            image = offset > 0 ? image.previous() : image.next();
        repaint();
    }


    public void show() {
        repaint();
    }

    private void repaint() {
        this.display.clear();
        this.display.paint(image, 0);
    }
}
