package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.Random;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import characters.*;

import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class PlayGameScreen {

	private Player c = MainFrame.player;

	private static boolean hasRested = false;

	private static JPanel panel;
	private static JTextArea console;

	public PlayGameScreen() {
		panel = new JPanel();
		panel.setBounds(100, 100, 720, 720);
		panel.setLayout(null);

		JButton btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnContinue.setBounds(38, 77, 196, 57);
		btnContinue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Characters enc = Characters.setEncounter(Characters.setEncounter()); // Change Character
																									// encounter
				appendConsole(c.getName() + " encountered a " + enc.getName());
				MainFrame.setEncounterPanel(new Encounter(enc));
				MainFrame.setPanel(MainFrame.getEncounterPanel().getPanel());

			}
		});
		panel.add(btnContinue);

		JButton btnRest = new JButton("Rest");
		btnRest.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnRest.setBounds(38, 211, 196, 57);
		btnRest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rest();
			}
		});
		panel.add(btnRest);

		JButton btnItems = new JButton("Items");
		btnItems.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnItems.setBounds(38, 345, 196, 57);
		btnItems.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ItemsFrame itemF = new ItemsFrame(MainFrame.player);
				itemF.startGui(MainFrame.player);
			}
		});
		panel.add(btnItems);

		JButton btnStatistics = new JButton("Statistics");
		btnStatistics.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnStatistics.setBounds(38, 479, 196, 57);
		btnStatistics.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				appendConsole("Hp: " + c.getHp() + "/" + c.getMaxHp());
				appendConsole("Speed: " + c.getSpeed() + "/" + c.getMaxSpeed());
				appendConsole("Enemies defeated: " + c.getEnemiesDefeated());
				appendConsole("Level: " + c.getLevel());
				appendConsole("Exp (" + c.getExp() + "/" + c.getExpCap() +")");
				appendConsole("\n");
				

			}
		});
		panel.add(btnStatistics);

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnExit.setBounds(38, 613, 196, 57);
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.MAIN_FRAME.dispose();
			}
		});
		panel.add(btnExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(393, 74, 317, 539);
		panel.add(scrollPane);

		console = new JTextArea();
		console.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		console.setLineWrap(true);
		console.setWrapStyleWord(true);
		console.setText("");
		console.setEditable(false);
		scrollPane.setViewportView(console);

		JButton btnClearConsole = new JButton("Clear Console");
		btnClearConsole.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClearConsole.setBounds(483, 636, 137, 34);
		btnClearConsole.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				console.setText("");

			}
		});
		panel.add(btnClearConsole);
	}

	public static void appendConsole(String message) {
		message += "\n";
		console.append(message);
		console.setCaretPosition(console.getDocument().getLength());
	}

	public static JPanel getPanel() {
		return panel;
	}

	private SecureRandom random = new SecureRandom();

	public void rest() {

		if (hasRested == false) {
			if (c.getHp() < c.getMaxHp()) {
				int restoreHp = (c.getMaxHp() / 4) + random.nextInt(20);
				if (c.getHp() + restoreHp >= c.getMaxHp())
					restoreHp = c.getMaxHp() - c.getHp();
				c.addHp(restoreHp);
				appendConsole(c.getName() + " restored " + restoreHp + " HP.");
			}

			if (c.getSpeed() < c.getMaxSpeed()) {
				int restoreSpeed = ((c.getMaxSpeed() / 10) + random.nextInt(2));
				if (c.getSpeed() + restoreSpeed >= c.getMaxSpeed())
					restoreSpeed = c.getMaxSpeed() - c.getSpeed();
				c.addSpeed(restoreSpeed);
				appendConsole(c.getName() + " restored " + restoreSpeed + " speed.\n");
			}

			if (c.getStatusesList().isEmpty() == false) {
				c.removeAllStatuses();
				appendConsole("All statuses have been removed.");
			}

			hasRested = true;

		}

		else {
			appendConsole("You have already rested.  Encounter another enemy to rest again.");
		}

	}

	public static void setHasRested(boolean b) {
		hasRested = b;
	}

	public static boolean getHasRested() {
		return hasRested;
	}
}
