package characters;

import java.security.SecureRandom;

import items.SlimeDrop;

public class Slime extends Characters {

	private static SecureRandom random = new SecureRandom();

	private final static String name = "Slime";
	private final static int id = 1;

	private static int slimeHp;
	private static int maxSlimeSpeed;

	private static int slimeBaseAtk = 6;
	private static int slimeVarAtk = 4;

	private static int slimeHp() {
		int HP = 30 + random.nextInt(10);
		slimeHp = HP;
		return HP;
	}

	private static int slimeSpeed() {
		int slimeSpeed = 5 + random.nextInt(3);
		maxSlimeSpeed = slimeSpeed;
		return slimeSpeed;
	}

	public Slime() {
		super(name, id, slimeHp(), slimeHp, slimeSpeed(), maxSlimeSpeed, slimeBaseAtk, slimeVarAtk);
		addInventory();
		addItem(new SlimeDrop(1 + random.nextInt(2)));
		
	}

	public void doAction(Characters defender) {
		int sel = random.nextInt(2);

		if (sel == 0)
			attack(defender);
		else if (sel == 1)
			slimeAbilities(defender);
	}

	public void slimeAbilities(Characters defender) {
		int sel = random.nextInt(3);

		if (sel == 0)
			Abilities.regenerate(this, 5, 15);
		else if (sel == 1)
			Abilities.absorb(this, defender, 4, 4);
		else if (sel == 2)
			Abilities.slowPlayer(this, defender);
	}

}
