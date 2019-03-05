package items;

import java.security.SecureRandom;

import GUI.Encounter;
import GUI.MainFrame;
import GUI.PlayGameScreen;
import characters.Abilities;
import characters.Characters;

public class StoneOfLeech extends Items {
	
	private boolean usedStone;

	SecureRandom random = new SecureRandom();

	private static final String NAME = "Stone of Leech";
	private static final int ID = 202;
	private static boolean isEquiped = false;
	private static TYPE type = TYPE.POTION;

	public StoneOfLeech(int uses) {
		super(NAME, ID, uses, isEquiped, type);
	}

	public void useItem(Characters c) {
		String message = c.getName() + " used the stone of leech.";		
		MainFrame.appendMessage(message);
		
		Abilities.absorb(c, Encounter.getPlayer2(), 20, 10);
		
		subUses(1, c);
	}
	
	
}

