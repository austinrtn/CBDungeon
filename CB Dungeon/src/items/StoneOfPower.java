package items;

import java.security.SecureRandom;

import GUI.MainFrame;
import GUI.PlayGameScreen;
import characters.Characters;

public class StoneOfPower extends Items {
	
	private boolean usedStone;

	SecureRandom random = new SecureRandom();

	private static final String NAME = "Stone of Power";
	private static final int ID = 201;
	private static boolean isEquiped = false;
	private static TYPE type = TYPE.WEAPON;

	public StoneOfPower(int uses) {
		super(NAME, ID, uses, isEquiped, type);
	}

	public void useItem(Characters c) {
		String message = c.getName() + " used the stone of power.";		
		MainFrame.appendMessage(message);
		
		subUses(1, c);
	}
	
	public int getModifer(Characters c) {
		useItem(c);
		return 50 + random.nextInt(15);
	}
	
}

