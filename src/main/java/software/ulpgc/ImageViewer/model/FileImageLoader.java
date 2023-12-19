package software.ulpgc.ImageViewer.model;

import software.ulpgc.ImageViewer.interfaces.Image;
import software.ulpgc.ImageViewer.interfaces.ImageLoader;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Set;

public class FileImageLoader implements ImageLoader {

    private final File[] file;

    public FileImageLoader(File folder) {
        this.file = folder.listFiles(isImage());
    }

    private static final Set<String> imageExtensions = Set.of(".jpg",".png", ".jpeg");

    private static FilenameFilter isImage() {
        return (dir, name) -> imageExtensions.stream().anyMatch(extension -> name.endsWith(extension));
    }

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String name() {
                return file[i].getAbsolutePath();
            }

            @Override
            public Image next() {
                return imageAt((i + 1) % file.length);
            }

            @Override
            public Image previous() {
                return imageAt((i - 1 + file.length) % file.length);
            }
        };
    }

}
