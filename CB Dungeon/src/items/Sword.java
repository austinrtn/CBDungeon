package items;

import java.security.SecureRandom;

import GUI.MainFrame;
import characters.Abilities;
import characters.Characters;

public class Sword extends Items {

	static SecureRandom random = new SecureRandom();

	private static String iName = "Swoard";
	private static int iId = 300;

	private static int iUses() {
		return 10 + random.nextInt(5);
	}

	private static boolean equiped = false;
	private static TYPE type = TYPE.WEAPON;

	public Sword() {
		super(iName, iId, iUses(), equiped, type);
	}

	public void useItem(Characters c) {
		subUses(1, c);
		if (getUses() <= 0)
			MainFrame.appendMessage(c.getName() + "'s sword has broke.\n");
	}

	public int getModifer(Characters c) {
		useItem(c);
		return 5 + random.nextInt(15);
	}
}
