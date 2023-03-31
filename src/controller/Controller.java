package controller;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.AttributeSet.ColorAttribute;

import engine.Game;
import engine.Player;
import model.world.Champion;
import views.Board;

public class Controller extends JFrame {
	public static Player player1;
	public static Player player2;
	public static Game game;

	public static void nameSelect(JFrame frame) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, frame.WIDTH, frame.HEIGHT);
		panel.setBackground(new Color(0, 0, 0));

		JTextField player1Name = new JTextField("Player 1");
		player1Name.setForeground(Color.blue);
		player1Name.setBackground(Color.cyan);
		JTextField player2Name = new JTextField("Player 2");
		player2Name.setForeground(Color.red);
		player2Name.setBackground(Color.orange);

		JButton start = new JButton("START");
		start.setFocusable(false);

		JLabel nameText = new JLabel("ENTER YOUR NAMES:");
		nameText.setBounds(200, 125, 400, 50);
		nameText.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		nameText.setForeground(new Color(255, 255, 255));

		player1Name.setBounds(200, 200, 200, 50);
		player2Name.setBounds(500, 200, 200, 50);
		player1Name.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		player2Name.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		start.setBounds(750, 500, 100, 50);
		start.setBackground(new Color(0, 0, 255));
		start.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		start.setForeground(new Color(0, 0, 0));

		ImageIcon startPanel = new ImageIcon("Start Panel.jpg");
		// imageLabel.setBounds(-55, 0, frame.WIDTH, frame.HEIGHT);
		// imageLabel.setIcon(new ImageIcon("Start Panel.jpg"));

		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(startPanel);
		// imageLabel.setBounds(0,0,frame.WIDTH,frame.HEIGHT);

		panel.add(nameText);
		panel.add(imageLabel);
		panel.add(player1Name);
		panel.add(player2Name);
		panel.add(start);
		frame.add(panel);

		start.addActionListener(e -> {
			player1 = new Player(player1Name.getText());
			player2 = new Player(player2Name.getText());
			game = new Game(player1, player2);
			// System.out.println("start");
			panel.setVisible(false);
			frame.remove(panel);
			try {
				chooseFirstPlayerChampions(frame);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

	public static void chooseFirstPlayerChampions(JFrame frame) throws IOException {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, frame.WIDTH, frame.HEIGHT);
		panel.setBackground(new Color(0, 0, 0));

		Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");

		

		ArrayList<Champion> champions = Game.getAvailableChampions();

		JLabel label = new JLabel(player1.getName() + " CHOOSE YOUR CHAMPIONS");
		label.setBounds(10, 10, 400, 50);
		label.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		label.setForeground(Color.blue);

		JButton nextBTN = new JButton("NEXT");
		nextBTN.setBounds(700, 500, 100, 50);
		nextBTN.setBackground(new Color(0, 0, 255));
		nextBTN.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		nextBTN.setForeground(new Color(0, 0, 0));
		nextBTN.setEnabled(false);
		nextBTN.setFocusable(false);
		
		Border border = BorderFactory.createLineBorder(Color.blue, 4);
		JTextArea detail = new JTextArea();
		detail.setBounds(150, 370, 350, 250);
		detail.setBackground(Color.cyan);
		detail.setOpaque(true);
		detail.setBorder(border);
		
		

		panel.add(label);
		panel.add(nextBTN);
		
		frame.add(panel);

		ArrayList<Champion> firstPlayerChamps = new ArrayList<>();

		for (int i = 0; i < champions.size(); i++) {
			String name = champions.get(i).getName();
			JButton button = new JButton(name);
			button.setBounds(100 + (i % 3) * 200, 100 + (i / 3) * 50, 200, 50);
			button.setBackground(new Color(0, 0, 255));
			button.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
			button.setForeground(new Color(0, 0, 0));
			button.setFocusable(false);

			panel.add(button);
			Champion champ = champions.get(i);

			button.addActionListener(e -> {
				if (firstPlayerChamps.size() < 3 && !firstPlayerChamps.contains(champ)) {
					firstPlayerChamps.add(champ);
					button.setBackground(new Color(0, 255, 0));
					String s =  "Info:\nChampions name: " + champ.getName() +"\nAttack Damage: " + champ.getAttackDamage()
					 + "\nAttack Range: " + champ.getAttackRange() + "\nHP: " + champ.getMaxHP() + 
					 "\nMana: " + champ.getMana() + "\nSpeed: " + champ.getSpeed()
					  + "\nActions Per Turn: " + champ.getMaxActionPointsPerTurn() + "\nAbilities: "
					   + champ.getAbilities(); 
					
					
					
					// System.out.println(champ.getName());
					// System.out.println(firstPlayerChamps.size());
					detail.setText(s);

				} else if (firstPlayerChamps.contains(champ)) {
					firstPlayerChamps.remove(champ);
					button.setBackground(new Color(0, 0, 255));
					detail.setText(null);
				}

				if (firstPlayerChamps.size() == 3) {
					nextBTN.setEnabled(true);
				} else {
					nextBTN.setEnabled(false);
				}

			});
			player1.setTeam(firstPlayerChamps);

			// player1.setLeader(firstPlayerChamps.get(0));

		}
		detail.setEditable(false);
		panel.add(detail);
		nextBTN.addActionListener(e -> {
			// System.out.println("next");
			panel.setVisible(false);
			frame.remove(panel);
			try {
				selectFirstPlayerLeader(frame);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

	}

	public static void selectFirstPlayerLeader(JFrame frame) throws IOException {
		JPanel select = new JPanel();
		select.setLayout(null);
		select.setBounds(0, 0, frame.WIDTH, frame.HEIGHT);
		select.setBackground(new Color(0, 0, 0));

		JLabel text = new JLabel(player1.getName() + " SELECT YOUR LEADER");
		text.setBounds(10, 10, 400, 50);
		text.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		text.setForeground(new Color(0, 0, 255));
		
		Border border = BorderFactory.createLineBorder(Color.blue, 4);
		JTextArea detail = new JTextArea();
		detail.setBounds(150, 370, 350, 250);
		detail.setBackground(Color.cyan);
		detail.setOpaque(true);
		detail.setBorder(border);

		select.add(text);
		JButton nextBTN = new JButton("NEXT");
		nextBTN.setBounds(680, 500, 100, 50);
		nextBTN.setBackground(new Color(0, 0, 255));
		nextBTN.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		nextBTN.setForeground(new Color(0, 0, 0));
		nextBTN.setEnabled(false);
		nextBTN.setFocusable(false);
		select.add(nextBTN);

		ArrayList<Champion> championLeader = new ArrayList<>();

		for (int i = 0; i < player1.getTeam().size(); i++) {
			String name = player1.getTeam().get(i).getName();
			JButton button = new JButton(name);
			button.setBounds(100 + (i % 3) * 200, 100 + (i / 3) * 50, 200, 50);
			button.setBackground(new Color(0, 0, 255));
			button.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
			button.setForeground(new Color(0, 0, 0));
			button.setFocusable(false);

			select.add(button);
			Champion champ = player1.getTeam().get(i);

			button.addActionListener(e -> {
				if (championLeader.size() < 1 && !championLeader.contains(champ)) {
					championLeader.add(champ);
					String s =  "Info:\nChampions name: " + champ.getName() +"\nAttack Damage: " + champ.getAttackDamage()
					 + "\nAttack Range: " + champ.getAttackRange() + "\nHP: " + champ.getMaxHP() + 
					 "\nMana: " + champ.getMana() + "\nSpeed: " + champ.getSpeed()
					  + "\nActions Per Turn: " + champ.getMaxActionPointsPerTurn() + "\nAbilities: "
					   + champ.getAbilities(); 
					button.setBackground(new Color(0, 255, 0));
					detail.setText(s);
				} else if (championLeader.contains(champ)) {
					championLeader.remove(champ);
					button.setBackground(new Color(0, 0, 255));
					detail.setText(null);
				}
				if (championLeader.size() == 1) {
					nextBTN.setEnabled(true);
					player1.setLeader(championLeader.get(0));
				} else {
					nextBTN.setEnabled(false);
				}

			});

		}

		detail.setEditable(false);
		select.add(detail);
		frame.add(select);
		

		nextBTN.addActionListener(e -> {
			// System.out.println("next");
			select.setVisible(false);
			frame.remove(select);
			try {
				chooseSecondPlayerChampions(frame);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
	}

	public static void chooseSecondPlayerChampions(JFrame frame) throws IOException {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, frame.WIDTH, frame.HEIGHT);
		panel.setBackground(new Color(0, 0, 0));


		ArrayList<Champion> champions = Game.getAvailableChampions();

		JLabel label = new JLabel(player2.getName() + " CHOOSE YOUR CHAMPIONS");
		label.setBounds(10, 10, 400, 50);
		label.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		label.setForeground(new Color(255, 0, 0));

		JButton nextBTN = new JButton("NEXT");
		nextBTN.setBounds(680, 500, 100, 50);
		nextBTN.setBackground(new Color(255, 0, 0));
		nextBTN.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		nextBTN.setForeground(new Color(0, 0, 0));
		nextBTN.setEnabled(false);
		nextBTN.setFocusable(false);
		
		Border border = BorderFactory.createLineBorder(Color.red, 4);
		JTextArea detail = new JTextArea();
		detail.setBounds(150, 370, 350, 250);
		detail.setBackground(Color.orange);
		detail.setOpaque(true);
		detail.setBorder(border);

		panel.add(label);
		panel.add(nextBTN);
		frame.add(panel);

		ArrayList<Champion> secondPlayerChamps = new ArrayList<>();

		for (int i = 0; i < champions.size(); i++) {
			String name = champions.get(i).getName();
			JButton button = new JButton(name);
			button.setBounds(100 + (i % 3) * 200, 100 + (i / 3) * 50, 200, 50);
			button.setBackground(new Color(255, 0, 0));
			button.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
			button.setForeground(new Color(0, 0, 0));
			button.setFocusable(false);

			panel.add(button);
			Champion champ = champions.get(i);

			button.addActionListener(e -> {
				if (secondPlayerChamps.size() < 3 && !secondPlayerChamps.contains(champ)) {
					secondPlayerChamps.add(champ);
					button.setBackground(new Color(0, 255, 0));
					String s =  "Info:\nChampions name: " + champ.getName() +"\nAttack Damage: " + champ.getAttackDamage()
					 + "\nAttack Range: " + champ.getAttackRange() + "\nHP: " + champ.getMaxHP() + 
					 "\nMana: " + champ.getMana() + "\nSpeed: " + champ.getSpeed()
					  + "\nActions Per Turn: " + champ.getMaxActionPointsPerTurn() + "\nAbilities: "
					   + champ.getAbilities();
					//System.out.println(champ.getName());
					//System.out.println(secondPlayerChamps.size());
					detail.setText(s);
				} else if (secondPlayerChamps.contains(champ)) {
					secondPlayerChamps.remove(champ);
					button.setBackground(new Color(255, 0, 0));
					detail.setText(null);
				}

				if (secondPlayerChamps.size() == 3) {
					nextBTN.setEnabled(true);
				} else {
					nextBTN.setEnabled(false);
				}

			});
			if(player1.getTeam().contains(champions.get(i))) {
				button.setEnabled(false);
			}
		}

		player2.setTeam(secondPlayerChamps);
		
		detail.setEditable(false);
		panel.add(detail);
		nextBTN.addActionListener(e -> {
			// System.out.println("next");
			panel.setVisible(false);
			frame.remove(panel);
			try {
				selectSecondPlayerLeader(frame);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

	}
	
	public static void selectSecondPlayerLeader(JFrame frame) throws IOException {
		JPanel select = new JPanel();
		select.setLayout(null);
		select.setBounds(0, 0, frame.WIDTH, frame.HEIGHT);
		select.setBackground(new Color(0, 0, 0));

		JLabel text = new JLabel(player2.getName() +" SELECT YOU LEADER");
		text.setBounds(10, 10, 400, 50);
		text.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		text.setForeground(new Color(255, 0, 0));
		
		Border border = BorderFactory.createLineBorder(Color.red, 4);
		JTextArea detail = new JTextArea();
		detail.setBounds(150, 370, 350, 250);
		detail.setBackground(Color.orange);
		detail.setOpaque(true);
		detail.setBorder(border);

		select.add(text);
		JButton nextBTN = new JButton("NEXT");
		nextBTN.setBounds(680, 500, 100, 50);
		nextBTN.setBackground(new Color(255, 0, 0));
		nextBTN.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		nextBTN.setForeground(new Color(0, 0, 0));
		nextBTN.setEnabled(false);
		nextBTN.setFocusable(false);
		select.add(nextBTN);

		ArrayList<Champion> championLeader = new ArrayList<>();

		for (int i = 0; i < player2.getTeam().size(); i++) {
			String name = player2.getTeam().get(i).getName();
			JButton button = new JButton(name);
			button.setBounds(100 + (i % 3) * 200, 100 + (i / 3) * 50, 200, 50);
			button.setBackground(new Color(255, 0, 0));
			button.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
			button.setForeground(new Color(0, 0, 0));
			button.setFocusable(false);

			select.add(button);
			Champion champ = player2.getTeam().get(i);

			button.addActionListener(e -> {
				if (championLeader.size() < 1 && !championLeader.contains(champ)) {
					championLeader.add(champ);
					button.setBackground(new Color(0, 255, 0));
					String s =  "Info:\nChampions name: " + champ.getName() +"\nAttack Damage: " + champ.getAttackDamage()
					 + "\nAttack Range: " + champ.getAttackRange() + "\nHP: " + champ.getMaxHP() + 
					 "\nMana: " + champ.getMana() + "\nSpeed: " + champ.getSpeed()
					  + "\nActions Per Turn: " + champ.getMaxActionPointsPerTurn() + "\nAbilities: "
					   + champ.getAbilities();
					detail.setText(s);
					
				} else if (championLeader.contains(champ)) {
					championLeader.remove(champ);
					button.setBackground(new Color(255, 0, 0));
					detail.setText(null);
				}
				if (championLeader.size() == 1) {
					nextBTN.setEnabled(true);
					player2.setLeader(championLeader.get(0));
				} else {
					nextBTN.setEnabled(false);
				}

			});

		}
		detail.setEditable(false);
		select.add(detail);
		frame.add(select);
		

		nextBTN.addActionListener(e -> {
			// System.out.println("next");
			select.setVisible(false);
			frame.remove(select);
			game = new Game(player1, player2);
			new Board(frame, game);

		});
	}

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Marvel Game");
		ImageIcon icon = new ImageIcon("marvel logo.jpg");
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setSize(1100, 700);
		nameSelect(frame);
		frame.setVisible(true);

		// System.out.println("Hello World");

		// frame.getContentPane().add(); // Adds Button to content pane of frame

	}
}
