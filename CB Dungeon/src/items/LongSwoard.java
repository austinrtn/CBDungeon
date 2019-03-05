package items;

import java.security.SecureRandom;

import GUI.MainFrame;
import GUI.PlayGameScreen;
import characters.Characters;

public class LongSwoard extends Items {

	static SecureRandom random = new SecureRandom();

	private static final String NAME = "Long Swoard";
	private static final int ID = 301;

	private static int uses() {
		return 5 + random.nextInt(3);
	}

	private static boolean isEquiped = false;
	private static TYPE type = TYPE.WEAPON;

	public LongSwoard() {
		super(NAME, ID, uses(), isEquiped, type);
	}

	public void useItem(Characters c) {
		subUses(1, c);
		MainFrame.appendMessage(c.getName() + "'s sword has broke.\n");
	}
	
	public int getModifer(Characters c) {
		useItem(c);
		return 10 + random.nextInt(15);
	}

}
