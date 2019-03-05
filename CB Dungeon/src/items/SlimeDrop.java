package items;

import java.security.SecureRandom;

import GUI.Encounter;
import GUI.MainFrame;
import characters.Characters;

public class SlimeDrop extends Items {
	
	private static SecureRandom random = new SecureRandom();

	public static final String NAME = "Slime";
	public static final int ID = 200;
	public static boolean isEquiped = false;

	
	public SlimeDrop(int uses) {
		super(NAME, ID, uses, isEquiped, TYPE.POTION);
	}
	
	public void useItem(Characters c) {
		int speed = 1 + random.nextInt(3);
		
		Encounter.getPlayer2().subtractSpeed(speed);

		String message = c.getName() + " slowed down " + Encounter.getPlayer2().getName();
		MainFrame.appendMessage(message);
		subUses(1, c);
	}

}
