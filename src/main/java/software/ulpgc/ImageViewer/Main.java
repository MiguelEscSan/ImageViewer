package software.ulpgc.ImageViewer;

import software.ulpgc.ImageViewer.command.NextImageCommand;
import software.ulpgc.ImageViewer.command.PreviousImageCommand;
import software.ulpgc.ImageViewer.interfaces.Image;
import software.ulpgc.ImageViewer.model.FileImageLoader;

import java.io.File;

public class Main {
    private static String root = "src/main/resources/";

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Image image = new FileImageLoader(new File(root)).load();
        frame.imageDisplay().show(image);
        frame.addCommand("<", new PreviousImageCommand(frame.imageDisplay()));
        frame.addCommand(">", new NextImageCommand(frame.imageDisplay()));
        frame.setVisible(true);

    }
}
