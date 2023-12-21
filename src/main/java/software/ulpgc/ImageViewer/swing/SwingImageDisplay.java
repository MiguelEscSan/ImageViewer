package software.ulpgc.ImageViewer.swing;

import software.ulpgc.ImageViewer.interfaces.Image;
import software.ulpgc.ImageViewer.interfaces.ImageDisplay;
import software.ulpgc.ImageViewer.model.Resizer;
import software.ulpgc.ImageViewer.model.Picture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SwingImageDisplay extends JPanel implements ImageDisplay {

    private Image image;
    private BufferedImage bitmap;

    private Shift shift = Shift.Null;
    private Released released = Released.Null;
    private int initShift;
    private List<Picture> paints = new ArrayList<>();
    private Pressed pressed = Pressed.Null;

    public SwingImageDisplay() {
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
    }
    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                initShift = e.getX();
                pressed.in(initShift);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                released.at(e.getX() - initShift);

            }


            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) { }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

                shift.to(e.getX() - initShift);
            }
            @Override
            public void mouseMoved(MouseEvent e) {}
        };
    }


    @Override
    public Image image() {
        return image;
    }

    @Override
    public void paint(Image newImage, int offset) {
        this.image = newImage;
        bitmap = load(this.image.name());
        paints.add(new Picture(image, offset));
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        System.out.println(image.name());
        for (Picture paint : paints) {
            bitmap = load(paint.image().name());
            if (paint.image() != null) {
                Resizer resizer = new Resizer(new Dimension(this.getWidth(), this.getHeight()));
                Dimension resized = resizer.resize(new Dimension(bitmap.getWidth(), bitmap.getHeight()));
                int x = (this.getWidth() - resized.width) / 2;
                int y = (this.getHeight() - resized.height) / 2;
                g.drawImage(bitmap, paint.offset()+x, y, resized.width, resized.height, null);
            }
        }
    }


    @Override
    public void clear() {
        paints.clear();
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift != null ? shift : Shift.Null;
    }

    @Override
    public void on(Released released) {
        this.released = released != null ? released : Released.Null;
    }

    @Override
    public void on(Pressed pressed) {
        this.pressed = pressed != null ? pressed : Pressed.Null;
    }

    private BufferedImage load(String name){
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
