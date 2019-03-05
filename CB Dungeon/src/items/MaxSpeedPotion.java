package items;

import java.security.SecureRandom;

import GUI.MainFrame;
import GUI.PlayGameScreen;
import characters.Characters;
import items.Items.TYPE;

public class MaxSpeedPotion extends Items {

	SecureRandom random = new SecureRandom();

	private static String name = "Max Speed Potion";
	private static int id = 103;
	private static boolean isEquiped = false;
	private static TYPE type = TYPE.POTION;

	public MaxSpeedPotion(int uses) {
		super(name, id, uses, isEquiped, type);
	}

	public void useItem(Characters c) {
		int restore = 2 + random.nextInt(3);
		c.addMaxSpeed(restore);
		c.addSpeed(restore);

		String message = (c.getName() + " gained " + restore + " Max Speed\n");

		if (MainFrame.getPanel() == MainFrame.getEncounterPanel().getPanel())
			MainFrame.getEncounterPanel().appendConsole(message);
		else
			PlayGameScreen.appendConsole(message);
		subUses(1, c);
	}
}
