package software.ulpgc.ImageViewer.command;

import software.ulpgc.ImageViewer.interfaces.ImageDisplay;

public class NextImageCommand implements Command{

    private final ImageDisplay display;

    public NextImageCommand(ImageDisplay imageDisplay) {
        this.display = imageDisplay;
    }

    @Override
    public void execute() {
       display.paint(display.image().next(), 0);
    }
}
