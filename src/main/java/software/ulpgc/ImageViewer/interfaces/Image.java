package software.ulpgc.ImageViewer.interfaces;

public interface Image {
    String name();
    Image next();
    Image previous();
}
