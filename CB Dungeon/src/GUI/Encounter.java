package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import characters.Abilities;
import characters.Characters;
import characters.Player;
import items.HpPotion;
import items.MaxHpPotion;
import items.MaxSpeedPotion;
import items.Shield;
import items.SpeedPotion;
import items.Sword;

public class Encounter {

	private SecureRandom random = new SecureRandom();

	private static Player player1;
	private static Characters player2;

	public static Characters getPlayer2() {
		return player2;
	}

	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextArea console;

	private JPanel player1Panel;
	private JPanel pnlName1;
	private JLabel lblName1;
	private JProgressBar hpPlayer1;
	private JProgressBar speedPlayer1;

	private JPanel player2Panel;
	private JProgressBar hpPlayer2;
	private JProgressBar speedPlayer2;
	private JLabel lblName2;
	private JButton btnAtk;

	public void appendConsole(String message) {
		message += "\n";
		console.append(message);
		console.setCaretPosition(console.getDocument().getLength());
		MainFrame.getEncounterPanel().updatePanel();
	}

	public void updatePanel() {

		// Player 1 Components

		lblName1.setText(player1.getName());
		hpPlayer1.setValue(player1.getHp());
		hpPlayer1.setMaximum(player1.getMaxHp());
		hpPlayer1.setString("HP (" + player1.getHp() + "/" + player1.getMaxHp() + ")");

		if (player1.getHp() <= (.25 * player1.getMaxHp()))
			hpPlayer1.setForeground(Color.RED);
		else if (player1.getHp() > (.25 * player1.getMaxHp()))
			hpPlayer1.setForeground(new Color(50, 205, 50));

		speedPlayer1.setValue(player1.getSpeed());
		speedPlayer1.setMaximum(player1.getMaxSpeed());
		speedPlayer1.setString("Speed (" + player1.getSpeed() + "/" + player1.getMaxSpeed() + ")");

		// Player 2 Components

		lblName2.setText(player2.getName());
		hpPlayer2.setValue(player2.getHp());
		hpPlayer2.setMaximum(player2.getMaxHp());
		hpPlayer2.setString("HP (" + player2.getHp() + "/" + player2.getMaxHp() + ")");
		if (player2.getHp() <= (.25 * player2.getMaxHp()))
			hpPlayer2.setForeground(Color.RED);
		else if (player2.getHp() > (.25 * player2.getMaxHp()))
			hpPlayer2.setForeground(new Color(50, 205, 50));
		speedPlayer2.setValue(player2.getSpeed());
		speedPlayer2.setMaximum(player2.getMaxSpeed());
		speedPlayer2.setString("Speed (" + player2.getSpeed() + "/" + player2.getMaxSpeed() + ")");

	}

	public Encounter(Characters c) {
		player1 = MainFrame.player;
		player2 = c;

		panel = new JPanel();
		panel.setBounds(0, 0, 750, 720);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 470, 730, 239);
		panel.add(scrollPane);

		console = new JTextArea();
		scrollPane.setViewportView(console);
		console.setWrapStyleWord(true);
		console.setLineWrap(true);
		console.setEditable(false);

		// Begin Player1 Components

		player1Panel = new JPanel();
		player1Panel.setBackground(new Color(255, 255, 255));
		player1Panel.setLayout(null);
		player1Panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		player1Panel.setBounds(10, 11, 356, 365);
		panel.add(player1Panel);

		pnlName1 = new JPanel();
		pnlName1.setBounds(92, 62, 172, 43);
		player1Panel.add(pnlName1);
		pnlName1.setLayout(null);

		lblName1 = new JLabel(player1.getName());
		lblName1.setBounds(0, 0, 172, 43);
		pnlName1.add(lblName1);
		lblName1.setHorizontalAlignment(SwingConstants.CENTER);
		lblName1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblName1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		hpPlayer1 = new JProgressBar();
		hpPlayer1.setValue(player1.getHp());
		hpPlayer1.setMaximum(player1.getMaxHp());
		hpPlayer1.setStringPainted(true);
		hpPlayer1.setString("HP (" + player1.getHp() + "/" + player1.getMaxHp() + ")");
		hpPlayer1.setForeground(new Color(50, 205, 50));
		hpPlayer1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		hpPlayer1.setBounds(10, 161, 336, 43);
		player1Panel.add(hpPlayer1);

		speedPlayer1 = new JProgressBar();
		speedPlayer1.setValue(player1.getSpeed());
		speedPlayer1.setStringPainted(true);
		speedPlayer1.setString("Speed (" + player1.getSpeed() + "/" + player1.getMaxSpeed() + ")");
		speedPlayer1.setMaximum(10);
		speedPlayer1.setForeground(Color.BLUE);
		speedPlayer1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		speedPlayer1.setBounds(10, 263, 336, 43);
		player1Panel.add(speedPlayer1);

		// Begin Player 2 components

		player2Panel = new JPanel();
		player2Panel.setBackground(new Color(255, 255, 255));
		player2Panel.setLayout(null);
		player2Panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		player2Panel.setBounds(384, 11, 356, 365);
		panel.add(player2Panel);
		hpPlayer2 = new JProgressBar();
		hpPlayer2.setMaximum(player2.getMaxHp());
		hpPlayer2.setValue(player2.getHp());

		hpPlayer2.setStringPainted(true);
		hpPlayer2.setString("HP (" + player2.getHp() + "/" + player2.getMaxHp() + ")");
		hpPlayer2.setForeground(new Color(50, 205, 50));
		hpPlayer2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		hpPlayer2.setBounds(10, 161, 336, 43);
		player2Panel.add(hpPlayer2);

		speedPlayer2 = new JProgressBar();
		speedPlayer2.setValue(player2.getSpeed());
		speedPlayer2.setStringPainted(true);
		speedPlayer2.setString("Speed (" + player2.getSpeed() + "/" + player2.getMaxSpeed() + ")");
		speedPlayer2.setMaximum(player2.getMaxSpeed());
		speedPlayer2.setForeground(Color.BLUE);
		speedPlayer2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		speedPlayer2.setBounds(10, 263, 336, 43);
		player2Panel.add(speedPlayer2);

		JPanel pnlName2 = new JPanel();
		pnlName2.setLayout(null);
		pnlName2.setBounds(92, 66, 172, 43);
		player2Panel.add(pnlName2);

		lblName2 = new JLabel(player2.getName());
		lblName2.setHorizontalAlignment(SwingConstants.CENTER);
		lblName2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblName2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblName2.setBounds(0, 0, 172, 43);
		pnlName2.add(lblName2);

		btnAtk = new JButton("Attack");
		btnAtk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtk.setBounds(18, 409, 165, 35);
		btnAtk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int x = 0;

				if (player1.getHp() > 0 && player2.getHp() > 0) {

					do {
						if (player1.getSpeed() >= player2.getSpeed()) {
							player1.doAction(player2);
							if (player1.getHp() <= 0 || player2.getHp() <= 0)
								break;
							player2.doAction(player1);
						} else if (player2.getSpeed() > player1.getSpeed()) {
							player2.doAction(player1);
							if (player1.getHp() <= 0 || player2.getHp() <= 0)
								break;
							player1.doAction(player2);
						}

						appendConsole("------------------------------------------");
					} while (x == 1);

					if (player1.getHp() <= 0 || player2.getHp() <= 0)
						declareWinner();

				} else
					MainFrame.setPanel(PlayGameScreen.getPanel());

				MainFrame.getEncounterPanel().appendConsole("\n");
				MainFrame.getEncounterPanel().updatePanel();

			}
		});
		panel.add(btnAtk);

		JButton btnSpells = new JButton("Spells");
		btnSpells.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSpells.setBounds(201, 409, 165, 35);
		btnSpells.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Abilities.fireAttack(player2, player1);

			}
		});
		panel.add(btnSpells);

		JButton btnItems = new JButton("Items");
		btnItems.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnItems.setBounds(384, 409, 165, 35);
		btnItems.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (player1.getItemList().isEmpty() == true) {
					player1.addItem(new HpPotion(2));
					player1.addItem(new Sword());
					player1.addItem(new Shield());
					player1.addItem(new MaxHpPotion(1));

					player1.addItem(new SpeedPotion(1));
					player1.addItem(new MaxSpeedPotion(2));

				}
				ItemsFrame i = new ItemsFrame(player1);
				i.startGui(player1);

			}
		});
		panel.add(btnItems);

		JButton btnFlee = new JButton("Flee");
		btnFlee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFlee.setBounds(567, 409, 165, 35);
		btnFlee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int fleeChance = 1 + random.nextInt(100);
				int targetNum;

				if (player1.getSpeed() >= player2.getSpeed())
					targetNum = 25;
				else
					targetNum = 75;

				if (fleeChance >= targetNum) {
					PlayGameScreen.appendConsole(
							"\n" + player1.getName() + " fled the battle.  They will not be able to rest.\n");
					MainFrame.setPanel(PlayGameScreen.getPanel());
				} else {
					appendConsole("\n" + player1.getName() + " failed to flee from the battle.");
					player2.doAction(player1);
				}
			}

		});
		panel.add(btnFlee);

	}

	public void declareWinner() {

		appendConsole("\n");

		if (player1.getHp() > 0) {// Player Wins
			appendConsole(player1.getName() + " defeated " + player2.getName());
			player1.addEnemiesDefeated(1);
			player2.dropInventory(player1);
			player1.dropExp(player2);
			PlayGameScreen.appendConsole(player1.getName() + " defeated " + player2.getName() + "\n");
		} else {
			appendConsole(player2.getName() + " defeated " + player1.getName());
			PlayGameScreen.appendConsole(player2.getName() + " defeated " + player1.getName() + "\n");
		}
		PlayGameScreen.setHasRested(false);
		btnAtk.setText("Continue");

	}

	public JPanel getPanel() {
		return panel;
	}
}
