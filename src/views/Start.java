package views;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;


public class Start {

    public Start(JFrame frame){
		JPanel panel = new JPanel();
        panel.setLayout(null);
		panel.setBounds(0, 0, frame.WIDTH, frame.HEIGHT);
		panel.setBackground(new Color(0, 0, 0));

        ImageIcon image = new ImageIcon("src/views/Marvel-Wallpaper.jpg");
        Image Transformedimage = image.getImage(); // transform it 
        Image newimg = Transformedimage.getScaledInstance(1500, 700,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon newImageIcon = new ImageIcon(newimg);  // assign to a new ImageIcon instance
       

        JLabel welcome = new JLabel();
        welcome.setBounds(0, 0, 1500, 700);
		welcome.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 25));
		welcome.setForeground(new Color(255, 255, 255));
        welcome.setIcon(newImageIcon);

        JButton start = new JButton("START");
        start.setFocusable(false);
        start.setBackground(new Color(240, 10, 0));
        start.setBounds(1350, 600, 100, 50);
		start.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		start.setForeground(new Color(0, 0, 0));

        panel.add(start);
        panel.add(welcome);
        frame.add(panel);

        start.addActionListener(e->{
            try {
                new NameSelect(frame);
                panel.setVisible(false);
                frame.remove(panel);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
    }
    
}
