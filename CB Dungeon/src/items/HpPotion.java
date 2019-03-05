package items;

import java.security.SecureRandom;

import GUI.MainFrame;
import GUI.PlayGameScreen;
import characters.Characters;

public class HpPotion extends Items {

	SecureRandom random = new SecureRandom();

	private static String name = "HP Potion";
	private static int id = 100;
	private static boolean isEquiped = false;
	private static TYPE type = TYPE.POTION;

	public HpPotion(int uses) {
		super(name, id, uses, isEquiped, type);
	}

	public void useItem(Characters c) {
		int restore = 20 + random.nextInt(5);
		if (c.getHp() + restore >= c.getMaxHp())
			restore = c.getMaxHp() - c.getHp();
		c.addHp(restore);

		String message = c.getName() + " restored " + restore + " HP\n";

		MainFrame.appendMessage(message);
		subUses(1, c);
	}

}
