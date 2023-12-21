package software.ulpgc.ImageViewer.interfaces;

public interface ImageDisplay {

    Image image();
    void paint(Image image, int offset);
    int getWidth();
    void clear();
    void on(Shift shift);
    void on(Released released);
    void on(Pressed pressed);

    interface Shift {
        Shift Null = offset -> {};
        void to(int offset);
    }
    interface Released {
        Released Null = offset -> {};
        void at(int offset);
    }

    interface Pressed {
        Pressed Null = offset -> {};
        void in(int offset);
    }
}
