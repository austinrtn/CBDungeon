package characters;

import java.security.SecureRandom;

import GUI.MainFrame;
import items.*;

public class Cyclops extends Characters {

	static SecureRandom random = new SecureRandom();

	private static final String NAME = "Cyclops";
	private static final int ID = 3;

	private static int hp() {
		int setHp = 100 + random.nextInt(30);
		maxHp = setHp;
		return setHp;
	}

	private static int maxHp;

	private static int speed() {
		int setSpeed = 4 + random.nextInt(4);
		maxSpeed = setSpeed;
		return setSpeed;
	}

	private static int baseAtk = 20;
	private static int varAtk = 15;

	private static int maxSpeed;

	// Name of Char, Char Id, Char HP, Char Max HP, Char Speed, Char MaxSpeed, Char
	// baseAtk, Char variantAtk
	public Cyclops() {
		super(NAME, ID, hp(), maxHp, speed(), maxSpeed, baseAtk, varAtk);
		
		addInventory();
		addItem(new StoneOfPower(1));

	}

	public void doAction(Characters defender) {
		int sel = random.nextInt(5);

		if (sel == 0 && defender.getStatusesList().contains(STATUS.BURN) == false)
			Abilities.fireAttack(this, defender);
		else
			attack(defender);

	}		

}
