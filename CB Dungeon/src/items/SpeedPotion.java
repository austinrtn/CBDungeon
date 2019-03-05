package items;

import java.security.SecureRandom;

import GUI.MainFrame;
import GUI.PlayGameScreen;
import characters.Characters;
import items.Items.TYPE;

public class SpeedPotion extends Items {

	SecureRandom random = new SecureRandom();

	private static String name = "Speed Potion";
	private static int id = 102;
	private static boolean isEquiped = false;
	private static TYPE type = TYPE.POTION;

	public SpeedPotion(int uses) {
		super(name, id, uses, isEquiped, type);
	}

	public void useItem(Characters c) {
		c.setSpeed(c.getMaxSpeed());

		String message = (c.getName() + " restored their speed.\n");

		if (MainFrame.getPanel() == MainFrame.getEncounterPanel().getPanel())
			MainFrame.getEncounterPanel().appendConsole(message);
		else
			PlayGameScreen.appendConsole(message);

		subUses(1, c);
	}
}
