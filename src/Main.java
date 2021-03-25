import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    protected JButton button;

    public Main() {
        ImageIcon middleButtonIcon = createImageIcon("gifs/go.gif");

        button = new JButton("Start Missions!", middleButtonIcon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setMnemonic(KeyEvent.VK_M);
        button.setMargin(new Insets(20, 20, 20, 20));


        //Listen for actions on buttons 1 and 3.
        button.addActionListener(this);

        button.setToolTipText("Click this button to start the program.");

        //Add Components to this container, using the default FlowLayout.
        add(button);
    }

    public void actionPerformed(ActionEvent e) {
        primaryClasses.GroundControl.main(null);
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Main.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("The Rocket Scientist Problem");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        Main newContentPane = new Main();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }
}

