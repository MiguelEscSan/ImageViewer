package software.ulpgc.ImageViewer.command;

import software.ulpgc.ImageViewer.interfaces.ImageDisplay;

public class PreviousImageCommand implements Command{
    private final ImageDisplay display;

    public PreviousImageCommand(ImageDisplay imageDisplay) {
        this.display = imageDisplay;
    }

    @Override
    public void execute() {
        display.show(display.image().previous());
    }
}
