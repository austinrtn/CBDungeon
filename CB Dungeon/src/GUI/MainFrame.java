package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import characters.*;

public class MainFrame {
	
	private static boolean printIds = true;

	public static final Player player = new Player();

	public static final JFrame MAIN_FRAME = new JFrame();
	private static JPanel currentPanel;

	private static Encounter encounterPanel;

	public static void setEncounterPanel(Encounter encounter) {
		encounterPanel = encounter;
	}

	public static Encounter getEncounterPanel() {
		return encounterPanel;
	}

	public static void main(String[] args) {
		startGui();
	}

	public static void startGui() {
		
		if (printIds == true)
			Entities.printIds();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.MAIN_FRAME.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {

		MAIN_FRAME.setResizable(false);
		MAIN_FRAME.setTitle("CB Dungeon");
		MAIN_FRAME.setBounds(100, 100, 755, 750);
		MAIN_FRAME.setLocationRelativeTo(null);
		MAIN_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		PlayGameScreen pgs = new PlayGameScreen();
		setPanel(pgs.getPanel());
	}

	public static void appendMessage(String message) {
		if (getEncounterPanel() == null)
			PlayGameScreen.appendConsole(message);

		else if (getPanel() == getPanel())
			getEncounterPanel().appendConsole(message);
		else
			PlayGameScreen.appendConsole(message);
	}

	public static void setPanel(JPanel panel) {
		if (currentPanel != null)
			currentPanel.setVisible(false);

		currentPanel = panel;
		MAIN_FRAME.getContentPane().add(panel);

		currentPanel.setVisible(true);
	}

	public static JPanel getPanel() {
		return currentPanel;
	}

}