package utils;

import javax.swing.*;

public class RocketMan extends JFrame{

    private static final long serialVersionUID = 1L;
    boolean success;
    transient Icon imgIcon;
    
    public RocketMan(boolean success) {
        this.success = success;

        if(success){
            // We suppose you have already set your JFrame 
            imgIcon = new ImageIcon(this.getClass().getResource("../gifs/thumbs.gif"));
        }
        else {
            imgIcon = new ImageIcon(this.getClass().getResource("../gifs/ohno.gif"));
        }
        JLabel label = new JLabel(imgIcon);
        this.getContentPane().add(label);
        this.setSize(300,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}