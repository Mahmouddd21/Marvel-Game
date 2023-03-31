package views;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.FontUIResource;

import engine.Game;
import engine.Player;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.Champion;

public class GameView extends JFrame {
	public static Player player1;
	public static Player player2;
	public static Game game;
	private static Start start;

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Marvel Game");
		ImageIcon icon = new ImageIcon("marvel logo.jpg");
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setSize(1500, 700);
		frame.setLocationRelativeTo(null);
		new Start(frame);
		frame.setVisible(true);

		// System.out.println("Hello World");

		// frame.getContentPane().add(); // Adds Button to content pane of frame

	}

}
