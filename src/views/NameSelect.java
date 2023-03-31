package views;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.plaf.*;

import engine.Game;
import engine.Player;

public class NameSelect {
    public static Game game;

    public NameSelect(JFrame frame) throws IOException{
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, frame.WIDTH, frame.HEIGHT);
		panel.setBackground(new Color(0, 0, 0));


		//image for background
		ImageIcon image = new ImageIcon("src/views/Marvel-Wallpaper.jpg");
        Image Transformedimage = image.getImage(); // transform it 
        Image newimg = Transformedimage.getScaledInstance(1500, 700,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon newImageIcon = new ImageIcon(newimg);  // assign to a new ImageIcon instance

		//for background
		JLabel background = new JLabel();
		background.setIcon(newImageIcon);
		background.setBounds(0, 0, 1500, 700);


		//for entering players name
		JTextField player1Name = new JTextField("Player 1");
		player1Name.setForeground(Color.blue);
		player1Name.setBackground(Color.cyan);
		JTextField player2Name = new JTextField("Player 2");
		player2Name.setForeground(Color.red);
		player2Name.setBackground(Color.orange);

		player1Name.setBounds(10, 75, 200, 50);
		player2Name.setBounds(300, 75, 200, 50);
		player1Name.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		player2Name.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		

		//Text to welcome
		JLabel nameText = new JLabel("ENTER YOUR NAMES:");
		nameText.setBounds(10, 10, 400, 50);
		nameText.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		nameText.setForeground(new Color(255, 255, 255));

		
		
		//button to move to selecting champions
		JButton next = new JButton("NEXT");
		next.setFocusable(false);
		next.setBounds(1350, 600, 100, 50);
		next.setBackground(new Color(240, 10, 0));
		next.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		next.setForeground(new Color(0, 0, 0));


		panel.add(nameText);
		panel.add(player1Name);
		panel.add(player2Name);
		panel.add(next);
		panel.add(background);
		frame.add(panel);

		next.addActionListener(e -> {
			Player player1 = new Player(player1Name.getText());
			Player player2 = new Player(player2Name.getText());
			game = new Game(player1, player2);
			// System.out.println("start");
			panel.setVisible(false);
			frame.remove(panel);
			try {
                new FirstPlayerChamps(frame);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
		});
	}
}
