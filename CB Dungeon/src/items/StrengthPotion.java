package items;

import java.security.SecureRandom;

import GUI.MainFrame;
import GUI.PlayGameScreen;
import characters.Characters;

public class StrengthPotion extends Items {

	SecureRandom random = new SecureRandom();

	private static String name = "Strenth Potion";
	private static int id = 105;
	private static boolean isEquiped = false;
	private static TYPE type = TYPE.POTION;

	public StrengthPotion(int uses) {
		super(name, id, uses, isEquiped, type);
	}

	public void useItem(Characters c) {
		int strength = 4 + random.nextInt(4);
		c.addBaseAtk(strength);
		subUses(1, c);

		String message = (c.getName() + " gained " + strength + " attack points.");
		MainFrame.appendMessage(message);
	}

}
