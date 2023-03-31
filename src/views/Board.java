package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;

import engine.Game;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;

public class Board {

	private static JFrame frame;
	private static Game game;
	private static JTextArea LU;
	private static JTextArea LU2;
	private static JPanel boardPanel;
	private static JTextArea infoPanel;
	private JPanel actionPanel;
	private JPanel directionsPanel;
	private static Direction direction;
	private static JPanel abilityButtons;
	private static JPanel ability;
	private static JTextArea info;
	private static JTextArea abilityInfo;
	private JPanel leaderButtons;
	private static JPanel board;
	private static JTextArea effectPanel;
	private static JTextArea effect;
	private JLabel firstPlayerName;
	private JLabel secondPlayerName;
	private static JTextArea CHMPSHP;
	private static JTextArea CHMPSHP2;
	private static JTextArea abilityDetail;
	private static final Border border1 = BorderFactory.createLineBorder(Color.green, 4);
	private static final Border border2 = BorderFactory.createLineBorder(Color.red, 4);
	private static final Border border3 = BorderFactory.createLineBorder(Color.blue, 4);
	private static JLabel background;

	public Board(JFrame frame, Game game) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.frame = frame;
		// frame2.setLayout(null);
		frame.setLayout(null);
		try {
			game.loadChampions("Champions.csv");
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
        


		//images for the champions butons
        ImageIcon CAbutton = new ImageIcon("src/views/captain america icon.png");//Captain America
        ImageIcon DPbutton = new ImageIcon("src/views/deadpool icon.png");//Deadpool
        ImageIcon DSbutton = new ImageIcon("src/views/dr.strange icon.png");//Dr.Strange
        ImageIcon Electrobutton = new ImageIcon("src/views/electro icon.png");//Electro
        ImageIcon GRbutton = new ImageIcon("src/views/ghost rider icon.png");//Ghost Rider
        ImageIcon Helabutton = new ImageIcon("src/views/hela icon.png");//Hela
        ImageIcon Hulkbutton = new ImageIcon("src/views/Hulk icon 2.png");//Hulk
        ImageIcon IceManbutton = new ImageIcon("src/views/iceman icon.png");//Iceman
        ImageIcon IronManbutton = new ImageIcon("src/views/ironman icon 2.png");//Ironman
        ImageIcon Lokibutton = new ImageIcon("src/views/loki icon 2.png"); //Loki
        ImageIcon QSbutton = new ImageIcon("src/views/quicksilver icon.png");//Quicksilver
        ImageIcon SMbutton = new ImageIcon("src/views/spiderman icon 2.png");//Spiderman
        ImageIcon Thorbutton = new ImageIcon("src/views/thor icon.png");//Thor
        ImageIcon Venombutton = new ImageIcon("src/views/venom icon 2.png");//Venom
        ImageIcon YJbutton = new ImageIcon("src/views/yellow jacket icon.png");//Yellow Jacket

		firstPlayerName = new JLabel(game.getFirstPlayer().getName());
		firstPlayerName.setBounds(0, 0, 100, 10);
		firstPlayerName.setForeground(Color.blue);
		firstPlayerName.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));

		secondPlayerName = new JLabel(game.getSecondPlayer().getName());
		secondPlayerName.setBounds(0, 15, 100, 10);
		secondPlayerName.setForeground(Color.red);
		secondPlayerName.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));

		// image for background
        ImageIcon image = new ImageIcon("src/views/Marvel-Wallpaper.jpg");
        Image Transformedimage = image.getImage(); // transform it
        Image newimg = Transformedimage.getScaledInstance(1500, 700, java.awt.Image.SCALE_SMOOTH); // scale it the smppth way
        ImageIcon newImageIcon = new ImageIcon(newimg); // assign to a new ImageIcon instance

        // for background
        background = new JLabel();
        background.setIcon(newImageIcon);
        background.setBounds(0, 0, 1500, 700);

		// for board
		boardPanel = new JPanel();
		boardPanel.setBounds(50, 30, 600, 500);
		boardPanel.setBackground(Color.LIGHT_GRAY);
		boardPanel.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));

		if (game.getSecondPlayer().getTeam().contains(game.getCurrentChampion()))
			boardPanel.setBorder(border2);

		else if (game.getFirstPlayer().getTeam().contains(game.getCurrentChampion()))
			boardPanel.setBorder(border3);

		boardPanel.setLayout(new GridLayout(game.getBoardwidth(), game.getBoardheight()));
		for (int i = 0; i < game.getBoard().length; i++) {
          //  String name = champions.get(i).getName();
			for (int j = 0; j < game.getBoard().length; j++) {
				JButton button;
				
				if (game.getBoard()[i][j] instanceof Champion) {
					button = new JButton();
					button.setSize(100, 100);
					// b.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
					// for cap
					Image CATransformedimage = CAbutton.getImage(); // transform it 
					Image CAnewimg = CATransformedimage.getScaledInstance(button.getWidth(), button.getHeight(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
					ImageIcon CAnewImageIcon = new ImageIcon(CAnewimg);  // assign to a new ImageIcon instance
		
					//for DP
					Image DPTransformedimage = DPbutton.getImage();
					Image DPnewimg = DPTransformedimage.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon DPnewImageIcon = new ImageIcon(DPnewimg);
		
					//for strange
					Image DSTransformedimage = DSbutton.getImage();
					Image DSnewimg = DSTransformedimage.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon DSnewImageIcon = new ImageIcon(DSnewimg);
		
					//for electro
					Image ElectroTransformedimage = Electrobutton.getImage();
					Image Electronewimg = ElectroTransformedimage.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon ElectronewImageIcon = new ImageIcon(Electronewimg);
		
					//for GR
					Image GRTransformedimage = GRbutton.getImage();
					Image GRnewimg = GRTransformedimage.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon GRnewImageIcon = new ImageIcon(GRnewimg);
		
					//for hela
					Image HelaTransformedimage = Helabutton.getImage();
					Image Helanewimg = HelaTransformedimage.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon HelanewImageIcon = new ImageIcon(Helanewimg);
		
					//for hulk
					Image HulkTransformedimage = Hulkbutton.getImage();
					Image Hulknewimg = HulkTransformedimage.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon HulknewImageIcon = new ImageIcon(Hulknewimg);
		
		
					//for iceman
					Image IcemanTransformedimage = IceManbutton.getImage();
					Image Icemannewimg = IcemanTransformedimage.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon IcemannewImageIcon = new ImageIcon(Icemannewimg);
		
					//for ironman
					Image IronmanTransformedimage = IronManbutton.getImage();
					Image Ironmannewimg = IronmanTransformedimage.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon IronmannewImageIcon = new ImageIcon(Ironmannewimg);
		
					//for loki
					Image LokiTransformedimage = Lokibutton.getImage();
					Image Lokinewimg = LokiTransformedimage.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon LokinewImageIcon = new ImageIcon(Lokinewimg);
		
					//for QS
					Image QSTransformedimage = QSbutton.getImage();
					Image QSnewimg = QSTransformedimage.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon QSnewImageIcon = new ImageIcon(QSnewimg);
		
					//for SM
					Image SMTransformedimage = SMbutton.getImage();
					Image SMnewimg = SMTransformedimage.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon SMnewImageIcon = new ImageIcon(SMnewimg);
		
					//for thor
					Image ThorTransformedimage = Thorbutton.getImage();
					Image Thornewimg = ThorTransformedimage.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon ThornewImageIcon = new ImageIcon(Thornewimg);
		
					//for venom
					Image VenomTransformedimage = Venombutton.getImage();
					Image Venomnewimg = VenomTransformedimage.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon VenomnewImageIcon = new ImageIcon(Venomnewimg);
		
					//for YJ
					Image YJTransformedimage = YJbutton.getImage();
					Image YJnewimg = YJTransformedimage.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon YJnewImageIcon = new ImageIcon(YJnewimg);
		
					switch(((Champion) game.getBoard()[i][j]).getName()){
					case "Captain America":button.setIcon(CAnewImageIcon);break;
					case "Deadpool":button.setIcon(DPnewImageIcon);break;
					case "Dr Strange":button.setIcon(DSnewImageIcon);break;
					case "Electro":button.setIcon(ElectronewImageIcon);break;//NOTE: get a new image
					case "Ghost Rider":button.setIcon(GRnewImageIcon);break;//NOTE: get a new image
					case "Hela":button.setIcon(HelanewImageIcon);break;//NOTE: get a new image
					case "Hulk":button.setIcon(HulknewImageIcon);break;//NOTE: get a new image
					case "Iceman":button.setIcon(IcemannewImageIcon);break;//NOTE: get a new image
					case "Ironman":button.setIcon(IronmannewImageIcon);break;//NOTE: get a new image
					case "Loki":button.setIcon(LokinewImageIcon);break;//NOTE: get a new image
					case "Quicksilver":button.setIcon(QSnewImageIcon);break;//NOTE: get a new image
					case "Spiderman":button.setIcon(SMnewImageIcon);break;
					case "Thor":button.setIcon(ThornewImageIcon);break;
					case "Venom":button.setIcon(VenomnewImageIcon);break;//NOTE: get a new image
					case "Yellow Jacket":button.setIcon(YJnewImageIcon);break;
					default : button.setIcon(null);
					}
					if (game.getFirstPlayer().getTeam().contains(game.getBoard()[i][j])
							&& game.getBoard()[i][j] != game.getCurrentChampion()) {
						button.setBackground(Color.blue);
					} else if (game.getSecondPlayer().getTeam().contains(game.getBoard()[i][j])
							&& game.getBoard()[i][j] != game.getCurrentChampion()) {
						button.setBackground(Color.red);
					} else if (game.getBoard()[i][j] == game.getCurrentChampion()) {
						button.setBackground(Color.green);
					}
				} else if (game.getBoard()[i][j] instanceof Cover) {
					button = new JButton("Cover" + ((Cover) game.getBoard()[i][j]).getCurrentHP());
					button.setSize(100, 100);
					button.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
					button.setBackground(Color.black);
					button.setForeground(Color.white);
				} else {
					button = new JButton();
					button.setSize(100, 100);
				}
				boardPanel.add(button);
			}
		}

		CHMPSHP = new JTextArea();
		CHMPSHP.setBounds(1100, 10, 150, 200);
		if (game.getSecondPlayer().getTeam().contains(game.getCurrentChampion())) {
			CHMPSHP.setBackground(Color.orange);
			CHMPSHP.setForeground(Color.red);
			CHMPSHP.setBorder(border2);
		}

		else if (game.getFirstPlayer().getTeam().contains(game.getCurrentChampion())) {
			CHMPSHP.setForeground(Color.blue);
			CHMPSHP.setBackground(Color.cyan);
			CHMPSHP.setBorder(border3);
		}

		CHMPSHP.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		String s6 = "First Player:\n";
		for (int i = 0; i < game.getFirstPlayer().getTeam().size(); i++) {
			s6 += game.getFirstPlayer().getTeam().get(i).getName() + " HP: "
					+ game.getFirstPlayer().getTeam().get(i).getCurrentHP() + "\n";
		}
		s6 += "\nSecond Player:\n";
		for (int i = 0; i < game.getSecondPlayer().getTeam().size(); i++) {
			s6 += game.getSecondPlayer().getTeam().get(i).getName() + " HP: "
					+ game.getSecondPlayer().getTeam().get(i).getCurrentHP() + "\n";
		}
		CHMPSHP.setText(s6);
		CHMPSHP.setEditable(false);

		abilityInfo = new JTextArea();
		abilityInfo.setBounds(1050, 250, 200, 400);
		if (game.getFirstPlayer().getTeam().contains(game.getCurrentChampion())) {
			abilityInfo.setForeground(Color.blue);
			abilityInfo.setBackground(Color.cyan);
			abilityInfo.setBorder(border3);
		} else if (game.getSecondPlayer().getTeam().contains(game.getCurrentChampion())) {
			abilityInfo.setForeground(Color.red);
			abilityInfo.setBackground(Color.orange);
			abilityInfo.setBorder(border2);
		}

		abilityInfo.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		String s8 = "Champion's Abilities:\n";
		for (int i = 0; i < game.getCurrentChampion().getAbilities().size(); i++) {
			s8 += game.getCurrentChampion().getAbilities().get(i).getName() + " :\nCost: "
					+ game.getCurrentChampion().getAbilities().get(i).getManaCost() + "\nCooldown: "
					+ game.getCurrentChampion().getAbilities().get(i).getCurrentCooldown() + "\nRange: "
					+ game.getCurrentChampion().getAbilities().get(i).getCastRange() + "\nCast Area: "
					+ game.getCurrentChampion().getAbilities().get(i).getCastArea() + "\nActions Required: "
					+ game.getCurrentChampion().getAbilities().get(i).getRequiredActionPoints();

			if (game.getCurrentChampion().getAbilities().get(i) instanceof DamagingAbility)
				s8 += "\nDamage Amount: "
						+ ((DamagingAbility) game.getCurrentChampion().getAbilities().get(i)).getDamageAmount() + "\n";
			else if (game.getCurrentChampion().getAbilities().get(i) instanceof CrowdControlAbility)
				s8 += "\nEffect: "
						+ ((CrowdControlAbility) game.getCurrentChampion().getAbilities().get(i)).getEffect().getName()
						+ "\n";
			else if (game.getCurrentChampion().getAbilities().get(i) instanceof HealingAbility)
				s8 += "\nHeal Amount: "
						+ ((HealingAbility) game.getCurrentChampion().getAbilities().get(i)).getHealAmount() + "\n";

		}
		abilityInfo.setText(s8);

		effectPanel = new JTextArea();
		effectPanel.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		effectPanel.setForeground(Color.green);
		effectPanel.setBounds(660, 290, 300, 100);
		effectPanel.setBackground(Color.yellow);

		if (game.getFirstPlayer().getTeam().contains(game.getCurrentChampion())) {
			effectPanel.setForeground(Color.blue);
			effectPanel.setBackground(Color.cyan);
			effectPanel.setBorder(border3);
		} else if (game.getSecondPlayer().getTeam().contains(game.getCurrentChampion())) {
			effectPanel.setForeground(Color.red);
			effectPanel.setBackground(Color.orange);
			effectPanel.setBorder(border2);
		}
		String s9 = "Applied Effects: ";
		if (!game.getCurrentChampion().getAppliedEffects().isEmpty()) {
			for (int i = 0; i < game.getCurrentChampion().getAppliedEffects().size(); i++) {
				s9 += game.getCurrentChampion().getAppliedEffects().get(i).getName() + ", ";
			}
		}
		effectPanel.setText(s9);

		infoPanel = new JTextArea();
		infoPanel.setBounds(660, 10, 300, 150);
		if (game.getFirstPlayer().getTeam().contains(game.getCurrentChampion())) {
			infoPanel.setForeground(Color.blue);
			infoPanel.setBackground(Color.cyan);
			infoPanel.setBorder(border3);
		} else if (game.getSecondPlayer().getTeam().contains(game.getCurrentChampion())) {
			infoPanel.setForeground(Color.red);
			infoPanel.setBackground(Color.orange);
			infoPanel.setBorder(border2);
		}
		infoPanel.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		String s1 = "Info:\nName: " + game.getCurrentChampion().getName() + "\nAttack Damage: "
				+ game.getCurrentChampion().getAttackDamage() + "\nAttack Range: "
				+ game.getCurrentChampion().getAttackRange() + "\nHP: " + game.getCurrentChampion().getMaxHP()
				+ "\nMana: " + game.getCurrentChampion().getMana() + "\nSpeed: " + game.getCurrentChampion().getSpeed()
				+ "\nActions Per Turn: " + game.getCurrentChampion().getCurrentActionPoints() + "\nCondition: "
				+ game.getCurrentChampion().getCondition();// need to complete

		infoPanel.setEditable(false);
		infoPanel.setText(s1);

		actionPanel = new JPanel();
		actionPanel.setBounds(20, 550, 350, 100);
		actionPanel.setBackground(Color.GRAY);
		actionPanel.setLayout(new GridLayout(2, 2));

		directionsPanel = new JPanel();
		directionsPanel.setBounds(400, 550, 350, 100);
		directionsPanel.setBackground(Color.GRAY);
		directionsPanel.setLayout(new GridLayout(2, 2));

		LU = new JTextArea();
		LU.setBounds(760, 550, 200, 100);
		LU.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		if (game.getFirstPlayer().getTeam().contains(game.getCurrentChampion())) {
			LU.setForeground(Color.blue);
			LU.setBackground(Color.cyan);
			LU.setBorder(border3);
			if (game.isFirstLeaderAbilityUsed())
				LU.setText("First Player Leader Ability: True");
			else
				LU.setText("First Player Leader Ability: False");

		} else if (game.getSecondPlayer().getTeam().contains(game.getCurrentChampion())) {
			LU.setForeground(Color.red);
			LU.setBackground(Color.orange);
			LU.setBorder(border2);
			if (game.isSecondLeaderAbilityUsed())
				LU.setText("Second Player Leader Ability: True");
			else
				LU.setText("Second Player Leader Ability: False");
		}

		// for directions
		ArrayList<JButton> directions = new ArrayList<>();

		JButton up = new JButton("UP");
		up.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		up.setBackground(Color.yellow);
		up.setFocusable(false);
		directionsPanel.add(up);
		up.addActionListener(e -> {
			if (directions.size() < 1 && !directions.contains(up)) {
				directions.add(up);
				direction = Direction.DOWN;
				up.setBackground(Color.green);
			} else if (directions.contains(up)) {
				directions.remove(up);
				direction = null;
				up.setBackground(Color.yellow);
			}

		});

		JButton down = new JButton("DOWN");
		down.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		down.setBackground(Color.yellow);
		down.setFocusable(false);
		directionsPanel.add(down);
		down.addActionListener(e -> {
			if (directions.size() < 1 && !directions.contains(down)) {
				directions.add(down);
				direction = Direction.UP;
				down.setBackground(Color.green);
			} else if (directions.contains(down)) {
				directions.remove(down);
				direction = null;
				down.setBackground(Color.yellow);
			}
		});

		JButton left = new JButton("LEFT");
		left.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		left.setBackground(Color.yellow);
		left.setFocusable(false);
		directionsPanel.add(left);
		left.addActionListener(e -> {
			if (directions.size() < 1 && !directions.contains(left)) {
				directions.add(left);
				direction = Direction.LEFT;
				left.setBackground(Color.green);
			} else if (directions.contains(left)) {
				directions.remove(left);
				direction = null;
				left.setBackground(Color.yellow);
			}
		});

		JButton right = new JButton("RIGHT");
		right.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		right.setBackground(Color.yellow);
		right.setFocusable(false);
		directionsPanel.add(right);
		right.addActionListener(e -> {
			if (directions.size() < 1 && !directions.contains(right)) {
				directions.add(right);
				direction = Direction.RIGHT;
				right.setBackground(Color.green);
			} else if (directions.contains(right)) {
				directions.remove(right);
				direction = null;
				right.setBackground(Color.yellow);
			}
		});

		// for move
		JButton move = new JButton("MOVE");
		move.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		move.setBackground(Color.orange);
		move.setFocusable(false);
		actionPanel.add(move);
		move.addActionListener(e -> {
			try {
				game.move(direction);
				changeBoard();
				abilityInfo = abilityDetail;
				CHMPSHP = CHMPSHP2;
				boardPanel = board;
				abilityButtons = ability;
				infoPanel = info;
				effectPanel = effect;
				LU = LU2;
				showWinner();
			} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

			}
			frame.revalidate();
			frame.repaint();
		});

		// for attack
		JButton attack = new JButton("ATTACK");
		attack.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		attack.setBackground(Color.red);
		attack.setFocusable(false);
		actionPanel.add(attack);
		attack.addActionListener(e -> {
			try {
				game.attack(direction);
				changeBoard();
				abilityInfo = abilityDetail;
				CHMPSHP = CHMPSHP2;
				boardPanel = board;
				abilityButtons = ability;
				infoPanel = info;
				effectPanel = effect;
				LU = LU2;
				showWinner();
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e2) {
				JOptionPane.showMessageDialog(frame, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			frame.revalidate();
			frame.repaint();
		});

		// for cast ability

		abilityButtons = new JPanel(new GridLayout(3, 1));
		abilityButtons.setBounds(660, 175, 300, 100);

		ArrayList<Ability> abilities = new ArrayList<>();
		for (int i = 0; i < game.getCurrentChampion().getAbilities().size(); i++) {
			JButton ab = new JButton(game.getCurrentChampion().getAbilities().get(i).getName());
			ab.setBackground(Color.yellow);
			ab.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
			ab.setFocusable(false);
			abilityButtons.add(ab);
			Ability a = game.getCurrentChampion().getAbilities().get(i);

			ab.addActionListener(e -> {
				if (a.getCastArea() == AreaOfEffect.DIRECTIONAL) {
					try {
						game.castAbility(a, direction);
						changeBoard();
						abilityInfo = abilityDetail;
						CHMPSHP = CHMPSHP2;
						boardPanel = board;
						abilityButtons = ability;
						infoPanel = info;
						effectPanel = effect;
						LU = LU2;
						showWinner();

					} catch (AbilityUseException | NotEnoughResourcesException | CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					frame.revalidate();
					frame.repaint();
				} else if (a.getCastArea() == AreaOfEffect.SINGLETARGET) {
					String inputx = JOptionPane.showInputDialog("Enter your x coordinate");
					String inputy = JOptionPane.showInputDialog("Enter your x coordinate");

					if (inputx.equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter a number!!");
						inputx = JOptionPane.showInputDialog("Enter your x coordinate");
					} else if (inputy.equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter a number!!");
						inputy = JOptionPane.showInputDialog("Enter your x coordinate");
					}

					int x = Integer.parseInt(inputx);
					int y = Integer.parseInt(inputy);

					try {
						game.castAbility(a, x, y);
						changeBoard();
						abilityInfo = abilityDetail;
						CHMPSHP = CHMPSHP2;
						boardPanel = board;
						abilityButtons = ability;
						infoPanel = info;
						effectPanel = effect;
						LU = LU2;
						showWinner();

					} catch (AbilityUseException | NotEnoughResourcesException | InvalidTargetException
							| CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					frame.revalidate();
					frame.repaint();
				}

				else if (a.getCastArea() == AreaOfEffect.SELFTARGET || a.getCastArea() == AreaOfEffect.SURROUND
						|| a.getCastArea() == AreaOfEffect.TEAMTARGET) {
					try {
						game.castAbility(a);
						changeBoard();
						abilityInfo = abilityDetail;
						CHMPSHP = CHMPSHP2;
						boardPanel = board;
						abilityButtons = ability;
						infoPanel = info;
						effectPanel = effect;
						LU = LU2;
						showWinner();

					} catch (AbilityUseException | NotEnoughResourcesException | CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					frame.revalidate();
					frame.repaint();

				}

			});
		}
		// abilityButtons.setBorder(border5);

		// cast.addActionListener(e -> {
		// //int number = abilityButtons.getSelectedIndex();
		//
		//
		//
		// frame.revalidate();
		// frame.repaint();
		// });

		// for endturn
		JButton end = new JButton("ENDTURN");
		end.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		end.setBackground(Color.cyan);
		end.setFocusable(false);
		actionPanel.add(end);
		end.addActionListener(e -> {
			game.endTurn();
			changeBoard();
			abilityInfo = abilityDetail;
			CHMPSHP = CHMPSHP2;
			boardPanel = board;
			abilityButtons = ability;
			infoPanel = info;
			effectPanel = effect;
			LU = LU2;
			showWinner();

			frame.revalidate();
			frame.repaint();
		});

		// for leader ability
		leaderButtons = new JPanel();
		leaderButtons.setBounds(660, 400, 300, 100);
		leaderButtons.setLayout(new GridLayout(2, 1));
		// leaderButtons.setBorder(border5);
		JButton leader1 = new JButton("FIRST PLAYER LEADER ABILITY");
		leader1.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		leader1.setBackground(new Color(0, 0, 255));
		leader1.setFocusable(false);
		leaderButtons.add(leader1);
		JButton leader2 = new JButton("SECOND PLAYER LEADER ABILITY");
		leader2.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		leader2.setBackground(new Color(255, 0, 0));
		leader2.setFocusable(false);
		leaderButtons.add(leader2);
		leader1.addActionListener(e -> {
			try {
				game.useLeaderAbility();
				changeBoard();
				abilityInfo = abilityDetail;
				CHMPSHP = CHMPSHP2;
				boardPanel = board;
				abilityButtons = ability;
				infoPanel = info;
				effectPanel = effect;
				LU = LU2;
				showWinner();
			} catch (LeaderAbilityAlreadyUsedException | LeaderNotCurrentException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			frame.validate();
			frame.repaint();
		});

		leader2.addActionListener(e -> {
			try {
				game.useLeaderAbility();
				changeBoard();
				abilityInfo = abilityDetail;
				CHMPSHP = CHMPSHP2;
				boardPanel = board;
				abilityButtons = ability;
				infoPanel = info;
				effectPanel = effect;
				LU = LU2;
				showWinner();
			} catch (LeaderAbilityAlreadyUsedException | LeaderNotCurrentException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			frame.validate();
			frame.repaint();
		});

		frame.add(boardPanel);
		frame.add(infoPanel);
		// frame.add(abilityPanel);
		frame.add(actionPanel);
		frame.add(directionsPanel);
		frame.add(abilityButtons);
		frame.add(leaderButtons);
		frame.add(effectPanel);
		frame.add(firstPlayerName);
		frame.add(secondPlayerName);
		frame.add(CHMPSHP);
		frame.add(abilityInfo);
		frame.add(LU);
		frame.add(background);


		// frame.add(mainPanel);
		frame.validate();
		frame.repaint();

	}

	public static void changeBoard() {
		ImageIcon CAbutton = new ImageIcon("src/views/captain america icon.png");//Captain America
        ImageIcon DPbutton = new ImageIcon("src/views/deadpool icon.png");//Deadpool
        ImageIcon DSbutton = new ImageIcon("src/views/dr.strange icon.png");//Dr.Strange
        ImageIcon Electrobutton = new ImageIcon("src/views/electro icon.png");//Electro
        ImageIcon GRbutton = new ImageIcon("src/views/ghost rider icon.png");//Ghost Rider
        ImageIcon Helabutton = new ImageIcon("src/views/hela icon.png");//Hela
        ImageIcon Hulkbutton = new ImageIcon("src/views/Hulk icon 2.png");//Hulk
        ImageIcon IceManbutton = new ImageIcon("src/views/iceman icon.png");//Iceman
        ImageIcon IronManbutton = new ImageIcon("src/views/ironman icon 2.png");//Ironman
        ImageIcon Lokibutton = new ImageIcon("src/views/loki icon 2.png"); //Loki
        ImageIcon QSbutton = new ImageIcon("src/views/quicksilver icon.png");//Quicksilver
        ImageIcon SMbutton = new ImageIcon("src/views/spiderman icon 2.png");//Spiderman
        ImageIcon Thorbutton = new ImageIcon("src/views/thor icon.png");//Thor
        ImageIcon Venombutton = new ImageIcon("src/views/venom icon 2.png");//Venom
        ImageIcon YJbutton = new ImageIcon("src/views/yellow jacket icon.png");//Yellow Jacket
		// for board
		board = new JPanel();
		board.setBounds(50, 30, 600, 500);
		board.setBackground(Color.LIGHT_GRAY);

		if (game.getSecondPlayer().getTeam().contains(game.getCurrentChampion()))
			board.setBorder(border2);

		else if (game.getFirstPlayer().getTeam().contains(game.getCurrentChampion()))
			board.setBorder(border3);
		board.setLayout(new GridLayout(5, 5));

		for (int i = 0; i < game.getBoard().length; i++) {
			for (int j = 0; j < game.getBoard().length; j++) {
				JButton b;

				if (game.getBoard()[i][j] instanceof Champion) {
					b = new JButton();
					// b.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
					b.setSize(100, 100);
					Image CATransformedimage = CAbutton.getImage(); // transform it 
				Image CAnewimg = CATransformedimage.getScaledInstance(b.getWidth(), b.getHeight(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				ImageIcon CAnewImageIcon = new ImageIcon(CAnewimg);  // assign to a new ImageIcon instance
		
					//for DP
				Image DPTransformedimage = DPbutton.getImage();
				Image DPnewimg = DPTransformedimage.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_SMOOTH);
				ImageIcon DPnewImageIcon = new ImageIcon(DPnewimg);
		
					//for strange
				Image DSTransformedimage = DSbutton.getImage();
				Image DSnewimg = DSTransformedimage.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_SMOOTH);
				ImageIcon DSnewImageIcon = new ImageIcon(DSnewimg);
		
					//for electro
					Image ElectroTransformedimage = Electrobutton.getImage();
					Image Electronewimg = ElectroTransformedimage.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon ElectronewImageIcon = new ImageIcon(Electronewimg);
		
					//for GR
					Image GRTransformedimage = GRbutton.getImage();
					Image GRnewimg = GRTransformedimage.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon GRnewImageIcon = new ImageIcon(GRnewimg);
		
					//for hela
					Image HelaTransformedimage = Helabutton.getImage();
					Image Helanewimg = HelaTransformedimage.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon HelanewImageIcon = new ImageIcon(Helanewimg);
		
					//for hulk
					Image HulkTransformedimage = Hulkbutton.getImage();
					Image Hulknewimg = HulkTransformedimage.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon HulknewImageIcon = new ImageIcon(Hulknewimg);
		
		
					//for iceman
					Image IcemanTransformedimage = IceManbutton.getImage();
					Image Icemannewimg = IcemanTransformedimage.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon IcemannewImageIcon = new ImageIcon(Icemannewimg);
		
					//for ironman
					Image IronmanTransformedimage = IronManbutton.getImage();
					Image Ironmannewimg = IronmanTransformedimage.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon IronmannewImageIcon = new ImageIcon(Ironmannewimg);
		
					//for loki
					Image LokiTransformedimage = Lokibutton.getImage();
					Image Lokinewimg = LokiTransformedimage.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon LokinewImageIcon = new ImageIcon(Lokinewimg);
		
					//for QS
					Image QSTransformedimage = QSbutton.getImage();
					Image QSnewimg = QSTransformedimage.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon QSnewImageIcon = new ImageIcon(QSnewimg);
		
					//for SM
					Image SMTransformedimage = SMbutton.getImage();
					Image SMnewimg = SMTransformedimage.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon SMnewImageIcon = new ImageIcon(SMnewimg);
		
					//for thor
					Image ThorTransformedimage = Thorbutton.getImage();
					Image Thornewimg = ThorTransformedimage.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon ThornewImageIcon = new ImageIcon(Thornewimg);
		
					//for venom
					Image VenomTransformedimage = Venombutton.getImage();
					Image Venomnewimg = VenomTransformedimage.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon VenomnewImageIcon = new ImageIcon(Venomnewimg);
		
					//for YJ
					Image YJTransformedimage = YJbutton.getImage();
					Image YJnewimg = YJTransformedimage.getScaledInstance(b.getWidth(), b.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon YJnewImageIcon = new ImageIcon(YJnewimg);
		
					switch(((Champion) game.getBoard()[i][j]).getName()){
					case "Captain America":b.setIcon(CAnewImageIcon);break;
					case "Deadpool":b.setIcon(DPnewImageIcon);break;
					case "Dr Strange":b.setIcon(DSnewImageIcon);break;
					case "Electro":b.setIcon(ElectronewImageIcon);break;//NOTE: get a new image
					case "Ghost Rider":b.setIcon(GRnewImageIcon);break;//NOTE: get a new image
					case "Hela":b.setIcon(HelanewImageIcon);break;//NOTE: get a new image
					case "Hulk":b.setIcon(HulknewImageIcon);break;//NOTE: get a new image
					case "Iceman":b.setIcon(IcemannewImageIcon);break;//NOTE: get a new image
					case "Ironman":b.setIcon(IronmannewImageIcon);break;//NOTE: get a new image
					case "Loki":b.setIcon(LokinewImageIcon);break;//NOTE: get a new image
					case "Quicksilver":b.setIcon(QSnewImageIcon);break;//NOTE: get a new image
					case "Spiderman":b.setIcon(SMnewImageIcon);break;
					case "Thor":b.setIcon(ThornewImageIcon);break;
					case "Venom":b.setIcon(VenomnewImageIcon);break;//NOTE: get a new image
					case "Yellow Jacket":b.setIcon(YJnewImageIcon);break;
					default : b.setIcon(null);
					}
					
					if (game.getBoard()[i][j] == game.getCurrentChampion()) {
						b.setBackground(Color.green);
					} else if (game.getFirstPlayer().getTeam().contains(game.getBoard()[i][j])
							&& game.getBoard()[i][j] != game.getCurrentChampion()) {
						b.setBackground(Color.blue);
					} else if (game.getSecondPlayer().getTeam().contains(game.getBoard()[i][j])
							&& game.getBoard()[i][j] != game.getCurrentChampion()) {
						b.setBackground(Color.red);
					}

				} else if (game.getBoard()[i][j] instanceof Cover) {
					b = new JButton("Cover" + ((Cover) game.getBoard()[i][j]).getCurrentHP());
					b.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
					b.setBackground(Color.black);
					b.setForeground(Color.white);
					b.setSize(100, 100);
				} else {
					b = new JButton();
				}

				
				
				board.add(b);
			}
		}

		// for abilities

		abilityDetail = new JTextArea();
		abilityDetail.setBounds(1050, 250, 200, 400);
		if (game.getFirstPlayer().getTeam().contains(game.getCurrentChampion())) {
			abilityDetail.setForeground(Color.blue);
			abilityDetail.setBackground(Color.cyan);
			abilityDetail.setBorder(border3);
		} else if (game.getSecondPlayer().getTeam().contains(game.getCurrentChampion())) {
			abilityDetail.setForeground(Color.red);
			abilityDetail.setBackground(Color.orange);
			abilityDetail.setBorder(border2);
		}
		abilityDetail.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		String s8 = "Champion's Abilities:\n";
		for (int i = 0; i < game.getCurrentChampion().getAbilities().size(); i++) {
			s8 += game.getCurrentChampion().getAbilities().get(i).getName() + " :\nCost: "
					+ game.getCurrentChampion().getAbilities().get(i).getManaCost() + "\nCooldown: "
					+ game.getCurrentChampion().getAbilities().get(i).getCurrentCooldown() + "\nRange: "
					+ game.getCurrentChampion().getAbilities().get(i).getCastRange() + "\nCast Area: "
					+ game.getCurrentChampion().getAbilities().get(i).getCastArea() + "\nActions Required: "
					+ game.getCurrentChampion().getAbilities().get(i).getRequiredActionPoints();

			if (game.getCurrentChampion().getAbilities().get(i) instanceof DamagingAbility)
				s8 += "\nDamage Amount: "
						+ ((DamagingAbility) game.getCurrentChampion().getAbilities().get(i)).getDamageAmount() + "\n";
			else if (game.getCurrentChampion().getAbilities().get(i) instanceof CrowdControlAbility)
				s8 += "\nEffect: "
						+ ((CrowdControlAbility) game.getCurrentChampion().getAbilities().get(i)).getEffect().getName()
						+ "\n";
			else if (game.getCurrentChampion().getAbilities().get(i) instanceof HealingAbility)
				s8 += "\nHeal Amount: "
						+ ((HealingAbility) game.getCurrentChampion().getAbilities().get(i)).getHealAmount() + "\n";

		}
		abilityDetail.setText(s8);

		ability = new JPanel(new GridLayout(3, 1));
		ability.setBounds(660, 175, 300, 100);
		ArrayList<Ability> abilities = new ArrayList<>();
		for (int i = 0; i < game.getCurrentChampion().getAbilities().size(); i++) {
			JButton ab = new JButton(game.getCurrentChampion().getAbilities().get(i).getName());
			ab.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
			ab.setBackground(Color.yellow);
			ab.setFocusable(false);
			ability.add(ab);
			Ability a = game.getCurrentChampion().getAbilities().get(i);

			ab.addActionListener(e -> {
				if (a.getCastArea() == AreaOfEffect.DIRECTIONAL) {
					try {
						game.castAbility(a, direction);
						changeBoard();
						abilityInfo = abilityDetail;
						CHMPSHP = CHMPSHP2;
						boardPanel = board;
						abilityButtons = ability;
						infoPanel = info;
						effectPanel = effect;
						LU = LU2;
						showWinner();
					} catch (AbilityUseException | NotEnoughResourcesException | CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					frame.revalidate();
					frame.repaint();
				} else if (a.getCastArea() == AreaOfEffect.SINGLETARGET) {
					String inputx = JOptionPane.showInputDialog("Enter your x coordinate");
					String inputy = JOptionPane.showInputDialog("Enter your y coordinate");

					if (inputx.equals(""))
						JOptionPane.showMessageDialog(null, "Please enter a number!!");
					else if (inputy.equals(""))
						JOptionPane.showMessageDialog(null, "Please enter a number!!");

					int x = Integer.parseInt(inputx);
					int y = Integer.parseInt(inputy);

					try {
						game.castAbility(a, x, y);
						changeBoard();
						abilityInfo = abilityDetail;
						CHMPSHP = CHMPSHP2;
						boardPanel = board;
						abilityButtons = ability;
						infoPanel = info;
						effectPanel = effect;
						LU = LU2;
						showWinner();
					} catch (AbilityUseException | NotEnoughResourcesException | InvalidTargetException
							| CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					frame.revalidate();
					frame.repaint();
				}

				else if (a.getCastArea() == AreaOfEffect.SELFTARGET || a.getCastArea() == AreaOfEffect.SURROUND
						|| a.getCastArea() == AreaOfEffect.TEAMTARGET) {
					try {
						game.castAbility(a);
						changeBoard();
						abilityInfo = abilityDetail;
						CHMPSHP = CHMPSHP2;
						boardPanel = board;
						abilityButtons = ability;
						infoPanel = info;
						effectPanel = effect;
						LU = LU2;
						showWinner();
					} catch (AbilityUseException | NotEnoughResourcesException | CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					frame.revalidate();
					frame.repaint();

				}

			});
		}

		Border border2 = BorderFactory.createLineBorder(Color.red, 2);
		CHMPSHP2 = new JTextArea();
		CHMPSHP2.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		if (game.getFirstPlayer().getTeam().contains(game.getCurrentChampion())) {
			CHMPSHP2.setForeground(Color.blue);
			CHMPSHP2.setBackground(Color.cyan);
			CHMPSHP2.setBorder(border3);
		} else if (game.getSecondPlayer().getTeam().contains(game.getCurrentChampion())) {
			CHMPSHP2.setForeground(Color.red);
			CHMPSHP2.setBackground(Color.orange);
			CHMPSHP2.setBorder(border2);
		}
		CHMPSHP2.setBounds(1100, 10, 150, 200);
		String s6 = "First Player:\n";
		for (int i = 0; i < game.getFirstPlayer().getTeam().size(); i++) {
			s6 += game.getFirstPlayer().getTeam().get(i).getName() + " HP: "
					+ game.getFirstPlayer().getTeam().get(i).getCurrentHP() + "\n";
		}
		s6 += "\nSecond Player:\n";
		for (int i = 0; i < game.getSecondPlayer().getTeam().size(); i++) {
			s6 += game.getSecondPlayer().getTeam().get(i).getName() + " HP: "
					+ game.getSecondPlayer().getTeam().get(i).getCurrentHP() + "\n";
		}
		CHMPSHP2.setText(s6);
		CHMPSHP2.setEditable(false);

		info = new JTextArea();
		info.setBounds(660, 10, 300, 150);
		info.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		if (game.getFirstPlayer().getTeam().contains(game.getCurrentChampion())) {
			info.setForeground(Color.blue);
			info.setBackground(Color.cyan);
			info.setBorder(border3);
		} else if (game.getSecondPlayer().getTeam().contains(game.getCurrentChampion())) {
			info.setForeground(Color.red);
			info.setBackground(Color.orange);
			info.setBorder(border2);
		}
		// for champion info
		String s1 = "Info:\nName: " + game.getCurrentChampion().getName() + "\nAttack Damage: "
				+ game.getCurrentChampion().getAttackDamage() + "\nAttack Range: "
				+ game.getCurrentChampion().getAttackRange() + "\nHP: " + game.getCurrentChampion().getMaxHP()
				+ "\nMana: " + game.getCurrentChampion().getMana() + "\nSpeed: " + game.getCurrentChampion().getSpeed()
				+ "\nActions Per Turn: " + game.getCurrentChampion().getCurrentActionPoints() + "\nCondition: "
				+ game.getCurrentChampion().getCondition();// need to complete
		info.setEditable(false);
		info.setText(s1);

		effect = new JTextArea();
		effect.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		effect.setBounds(660, 290, 300, 100);
		if (game.getFirstPlayer().getTeam().contains(game.getCurrentChampion())) {
			effect.setForeground(Color.blue);
			effect.setBackground(Color.cyan);
			effect.setBorder(border3);
		} else if (game.getSecondPlayer().getTeam().contains(game.getCurrentChampion())) {
			effect.setForeground(Color.red);
			effect.setBackground(Color.orange);
			effect.setBorder(border2);
		}
		String s9 = "Applied Effects: ";
		if (!game.getCurrentChampion().getAppliedEffects().isEmpty()) {
			for (int i = 0; i < game.getCurrentChampion().getAppliedEffects().size(); i++) {
				s9 += game.getCurrentChampion().getAppliedEffects().get(i).getName() + ", ";
			}
		}
		effect.setText(s9);

		LU2 = new JTextArea();
		LU2.setBounds(760, 550, 200, 100);
		LU2.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
		if (game.getFirstPlayer().getTeam().contains(game.getCurrentChampion())) {
			LU2.setForeground(Color.blue);
			LU2.setBackground(Color.cyan);
			LU2.setBorder(border3);
			if (game.isFirstLeaderAbilityUsed())
				LU2.setText("First Player Leader Ability: True");
			else
				LU2.setText("First Player Leader Ability: False");

		} else if (game.getSecondPlayer().getTeam().contains(game.getCurrentChampion())) {
			LU2.setForeground(Color.red);
			LU2.setBackground(Color.orange);
			LU2.setBorder(border2);
			if (game.isSecondLeaderAbilityUsed())
				LU2.setText("Second Player Leader Ability: True");
			else
				LU2.setText("Second Player Leader Ability: False");
		}

		frame.add(board);
		frame.add(ability);
		frame.add(info);
		frame.add(effect);
		frame.add(CHMPSHP2);
		frame.add(abilityDetail);
		frame.add(LU2);
		frame.add(background);
		abilityInfo.setVisible(false);
		LU.setVisible(false);
		CHMPSHP.setVisible(false);
		effectPanel.setVisible(false);
		infoPanel.setVisible(false);
		abilityButtons.setVisible(false);
		boardPanel.setVisible(false);
	}

	public static void showWinner() {
		if (game.checkGameOver() == game.getFirstPlayer())
			JOptionPane.showMessageDialog(null, game.getFirstPlayer().getName() + " WON!!");

		else if (game.checkGameOver() == game.getSecondPlayer())
			JOptionPane.showMessageDialog(null, game.getSecondPlayer().getName() + " WON!!");
	}

}
