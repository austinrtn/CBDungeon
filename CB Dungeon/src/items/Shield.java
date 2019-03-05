package items;

import java.security.SecureRandom;

import GUI.MainFrame;
import characters.Characters;
import items.Items.TYPE;

public class Shield extends Items {

	static SecureRandom random = new SecureRandom();

	private static String name = "Shield";
	private static int id = 350;

	private static int uses() {
		return 10 + random.nextInt(5);
	}

	private static boolean isEquiped = false;
	private static TYPE type = TYPE.DEFENSE;

	public Shield() {
		super(name, id, uses(), isEquiped, type);

	}

	public void useItem(Characters c) {
		subUses(1, c);
		if (getUses() <= 0)
			MainFrame.appendMessage(c.getName() + "'s shield has broke.\n");
				
	}

	public int getModifer(Characters c) {
		useItem(c);
		int shield = (5 + random.nextInt(5));
		shield *= -1;
		return shield;
	}

}
