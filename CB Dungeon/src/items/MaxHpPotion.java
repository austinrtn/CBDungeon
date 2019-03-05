package items;

import java.security.SecureRandom;

import GUI.MainFrame;
import GUI.PlayGameScreen;
import characters.Characters;

public class MaxHpPotion extends Items {

	SecureRandom random = new SecureRandom();

	private static String name = "Max HP Potion";
	private static int id = 101;
	private static boolean isEquiped = false;
	private static TYPE type = TYPE.POTION;

	public MaxHpPotion(int uses) {
		super(name, id, uses, isEquiped, type);
	}

	public void useItem(Characters c) {
		int restore = 20 + random.nextInt(5);
		c.addMaxHp(restore);
		c.addHp(restore);
		
		String message = (c.getName() + " gained " + restore + " Max HP\n");
		
		if (MainFrame.getPanel() == MainFrame.getEncounterPanel().getPanel())
			MainFrame.getEncounterPanel().appendConsole(message);
		else
			PlayGameScreen.appendConsole(message);
		
		subUses(1, c);
	}
}
