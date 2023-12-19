package software.ulpgc.ImageViewer;

import software.ulpgc.ImageViewer.command.Command;
import software.ulpgc.ImageViewer.interfaces.ImageDisplay;
import software.ulpgc.ImageViewer.swing.SwingImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {

    private ImageDisplay imageDisplay;
    private final Map<String, Command> commands;

    public MainFrame() throws HeadlessException {
        this.commands = new HashMap<>();
        setTitle("Image Viewer");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(createImageDisplay());
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel();
        panel.add(creteButton("<"));
        panel.add(creteButton(">"));
        return panel;
    }

    private Component creteButton(String option) {
        JButton button = new JButton(option);
        button.addActionListener(e -> commands.get(option).execute());
        return button;
    }

    public void addCommand(String name, Command command)  {
        commands.put(name, command);
    }

    private Component createImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;
        return display;
    }

    public ImageDisplay imageDisplay() {
        return imageDisplay;
    }
}
