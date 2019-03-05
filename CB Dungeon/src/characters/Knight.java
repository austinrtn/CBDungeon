package characters;

import java.security.SecureRandom;

import GUI.MainFrame;
import items.LongSwoard;
import items.Shield;
import items.Sword;

public class Knight extends Characters {
	static SecureRandom random = new SecureRandom();

	private static final String NAME = "Knight";
	private static final int ID = 2;

	private static int knightHp() {
		int setHp = 50 + random.nextInt(15);
		knightMaxHp = setHp;
		return setHp;
	}

	private static int knightMaxHp;

	private static int knightSpeed() {
		int setSpeed = 10 + random.nextInt(2);
		knightMaxSpeed = setSpeed;
		return setSpeed;
	}

	private static int knightBaseAtk = 10;
	private static int knightVarAtk = 10;

	private static int knightMaxSpeed;

	// Name of Char, Char Id, Char HP, Char Max HP, Char Speed, Char MaxSpeed, Char
	// baseAtk, Char variantAtk
	public Knight() {
		super(NAME, ID, knightHp(), knightMaxHp, knightSpeed(), knightMaxSpeed, knightBaseAtk, knightVarAtk);
		addInventory();

		int chanceOfItem = 1 + random.nextInt(100);
		addItem(new LongSwoard());

		if (chanceOfItem <= 25) {
			boolean selWeapon = random.nextBoolean();

			if (selWeapon == true)
				setItemEquiped(new Sword());
			else
				setItemEquiped(new Shield());
			
			setName("Super Knight");
		}
	}

	public void doAction(Characters defender) {
		int sel = random.nextInt(4);

		if (sel != 0)
			attack(defender);
		else
			knightAbilities(this, defender);

	}

	private void knightAbilities(Characters attacker, Characters defender) {

		int sel = random.nextInt(2);

		if (sel == 0)
			Abilities.incSpeed(attacker, 1, 2);
		else if (sel == 1)
			Abilities.multiAtk(attacker, defender, 2);
	}

}
