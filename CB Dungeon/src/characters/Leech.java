package characters;

import java.security.SecureRandom;

import items.StoneOfLeech;

public class Leech extends Characters {

	private static SecureRandom random = new SecureRandom();

	private static final String NAME = "Leech";
	private static final int ID = 4;

	private static int hp() {
		int hp = 25 + random.nextInt(15);
		maxHp = hp;
		return hp;
	}

	private static int maxHp;

	private static int speed() {
		int speed = 9 + random.nextInt(3);
		maxSpeed = speed;
		return speed;
	}

	private static int maxSpeed;

	private static int baseAtk = 10;
	private static int varientAtk = 10;

	public Leech() {
		super(NAME, ID, hp(), maxHp, speed(), maxSpeed, baseAtk, varientAtk);
		addInventory();
		addItem(new StoneOfLeech(1));
	}

	public void doAction(Characters c) {
		Abilities.absorb(this, c, 5, 7);
	}

}
