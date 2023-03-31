package views;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;

import model.abilities.*;
import model.world.*;

public class FirstPlayerLeader {
    
    public FirstPlayerLeader(JFrame frame) throws IOException{
        JPanel select = new JPanel();
		select.setLayout(null);
		select.setBounds(0, 0, frame.WIDTH, frame.HEIGHT);
		select.setBackground(new Color(0, 0, 0));


		//images for the champions butons
        ImageIcon CAbutton = new ImageIcon("src/views/captain america icon 2.png");//Captain America
        ImageIcon DPbutton = new ImageIcon("src/views/deadpool icon 2.png");//Deadpool
        ImageIcon DSbutton = new ImageIcon("src/views/dr.strange icon 2.png");//Dr.Strange
        ImageIcon Electrobutton = new ImageIcon("src/views/electro icon 3.png");//Electro
        ImageIcon GRbutton = new ImageIcon("src/views/ghost rider icon 2.png");//Ghost Rider
        ImageIcon Helabutton = new ImageIcon("src/views/hela icon 2.png");//Hela
        ImageIcon Hulkbutton = new ImageIcon("src/views/Hulk icon.png");//Hulk
        ImageIcon IceManbutton = new ImageIcon("src/views/iceman icon 2.png");//Iceman
        ImageIcon IronManbutton = new ImageIcon("src/views/ironman icon.png");//Ironman
        ImageIcon Lokibutton = new ImageIcon("src/views/loki icon.png"); //Loki
        ImageIcon QSbutton = new ImageIcon("src/views/quicksilver icon 2.png");//Quicksilver
        ImageIcon SMbutton = new ImageIcon("src/views/spiderman icon.png");//Spiderman
        ImageIcon Thorbutton = new ImageIcon("src/views/thor icon 2.png");//Thor
        ImageIcon Venombutton = new ImageIcon("src/views/venom icon.png");//Venom
        ImageIcon YJbutton = new ImageIcon("src/views/yellow jacket icon 2.png");//Yellow Jacket


		JLabel text = new JLabel(NameSelect.game.getFirstPlayer().getName() + " SELECT YOUR LEADER");
		text.setBounds(10, 10, 400, 50);
		text.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		text.setForeground(new Color(0, 0, 255));
		
		Border border = BorderFactory.createLineBorder(Color.blue, 4);
		//to display champion detail
        JTextArea champDetail = new JTextArea();
        champDetail.setBounds(700, 10, 350, 250);
        champDetail.setBackground(Color.cyan);
        champDetail.setForeground(Color.blue);
        champDetail.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
        champDetail.setOpaque(true);
        champDetail.setBorder(border);
		
		Border border2 = BorderFactory.createLineBorder(Color.red, 4);
		//to display list of abilities the champ has
        JTextArea abilityDetail = new JTextArea();
        abilityDetail.setBounds(700, 270, 350, 375);
        abilityDetail.setBackground(Color.cyan);
        abilityDetail.setForeground(Color.blue);
        abilityDetail.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
        abilityDetail.setOpaque(true);
        abilityDetail.setBorder(border);

		select.add(text);
		JButton nextBTN = new JButton("NEXT");
		nextBTN.setBounds(1350, 600, 100, 50);
		nextBTN.setBackground(new Color(0, 0, 255));
		nextBTN.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
		nextBTN.setForeground(new Color(0, 0, 0));
		nextBTN.setEnabled(false);
		nextBTN.setFocusable(false);
		select.add(nextBTN);

		//to display the player's leader
		JTextArea leader = new JTextArea();
        leader.setBounds(1060, 10, 350, 200);
        leader.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
        leader.setForeground(Color.blue);
        leader.setBackground(Color.cyan);
        leader.setBorder(border);

		// image for background
        ImageIcon image = new ImageIcon("src/views/Marvel-Wallpaper.jpg");
        Image Transformedimage = image.getImage(); // transform it
        Image newimg = Transformedimage.getScaledInstance(1500, 700, java.awt.Image.SCALE_SMOOTH); // scale it the smppth way
        ImageIcon newImageIcon = new ImageIcon(newimg); // assign to a new ImageIcon instance

        // for background
        JLabel background = new JLabel();
        background.setIcon(newImageIcon);
        background.setBounds(0, 0, 1500, 700);

		JPanel champSelect = new JPanel();
        champSelect.setLayout(new GridLayout(1, 3));
        champSelect.setBounds(50, 50, 600, 400);

		ArrayList<Champion> championLeader = new ArrayList<>();

		for (int i = 0; i < NameSelect.game.getFirstPlayer().getTeam().size(); i++) {
			String name = NameSelect.game.getFirstPlayer().getTeam().get(i).getName();
			JButton button = new JButton();
			button.setBackground(new Color(0, 0, 255));
            button.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 18));
            button.setForeground(new Color(0, 0, 0));
            button.setFocusable(false);
            button.setSize(200, 400);

			//for cap
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

            switch(name){
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

			champSelect.add(button);
			
			Champion champ = NameSelect.game.getFirstPlayer().getTeam().get(i);

			button.addActionListener(e -> {
				if (championLeader.size() < 1 && !championLeader.contains(champ)) {
					championLeader.add(champ);
					String s1 = NameSelect.game.getFirstPlayer().getName() + "'s leader: " + championLeader.toString();

					String s =  "Info:\nChampions name: " + champ.getName() +"\nAttack Damage: " + champ.getAttackDamage()
					 + "\nAttack Range: " + champ.getAttackRange() + "\nHP: " + champ.getMaxHP() + 
					 "\nMana: " + champ.getMana() + "\nSpeed: " + champ.getSpeed()
					  + "\nActions Per Turn: " + champ.getMaxActionPointsPerTurn() + "\nAbilities: "
					   + champ.getAbilities(); 
					
					String a = "Champion's Abilities:\n";
					for (int j = 0; j < champ.getAbilities().size(); j++) {
						a += champ.getAbilities().get(j).getName() + " :\nCost: "
								+ champ.getAbilities().get(j).getManaCost() + "\nCooldown: "
								+ champ.getAbilities().get(j).getCurrentCooldown() + "\nRange: "
								+ champ.getAbilities().get(j).getCastRange() + "\nCast Area: "
								+ champ.getAbilities().get(j).getCastArea() + "\nActions Required: "
								+ champ.getAbilities().get(j).getRequiredActionPoints();

						if (champ.getAbilities().get(j) instanceof DamagingAbility)
							a += "\nDamage Amount: "
									+ ((DamagingAbility) champ.getAbilities().get(j)).getDamageAmount() + "\n";
						else if (champ.getAbilities().get(j) instanceof CrowdControlAbility)
							a += "\nEffect: "
									+ ((CrowdControlAbility) champ.getAbilities().get(j)).getEffect().getName() + "\n";
						else if (champ.getAbilities().get(j) instanceof HealingAbility)
							a += "\nHeal Amount: "
									+ ((HealingAbility) champ.getAbilities().get(j)).getHealAmount() + "\n";

					}
					
					//button.setBackground(new Color(0, 255, 0));
					champDetail.setText(s);
					abilityDetail.setText(a);
					leader.setText(s1);
				} else if (championLeader.contains(champ)) {
					championLeader.remove(champ);
					//button.setBackground(new Color(0, 0, 255));
					String s1 = NameSelect.game.getFirstPlayer().getName() + "'s leader: " + championLeader.toString();
					champDetail.setText(null);
					abilityDetail.setText(null);
					leader.setText(s1);
				}
				if (championLeader.size() == 1) {
					nextBTN.setEnabled(true);
					NameSelect.game.getFirstPlayer().setLeader(championLeader.get(0));
				} else {
					nextBTN.setEnabled(false);
				}

			});
            champDetail.setEditable(false);
		abilityDetail.setEditable(false);
		select.add(champDetail);
		select.add(abilityDetail);
		select.add(leader);
		select.add(champSelect);
		select.add(background);
		frame.add(select);
		

		nextBTN.addActionListener(e -> {
			// System.out.println("next");
			select.setVisible(false);
			frame.remove(select);
			try {
                new SecondPlayerChamps(frame);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

		});
    }
}
}
