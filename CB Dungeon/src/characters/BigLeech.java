package characters;

import java.security.SecureRandom;

import GUI.MainFrame;
import items.StoneOfLeech;

public class BigLeech extends Characters {

	static private SecureRandom random = new SecureRandom();

	private static final String NAME = "Big Leach";
	private static final int ID = 5;

	private static int hp() {
		int hp = 70 + random.nextInt(15);
		maxHp = hp;
		return hp;
	}

	private static int maxHp;

	private static int speed() {
		int speed = 11 + random.nextInt(3);
		maxSpeed = speed;
		return speed;
	}

	private static int maxSpeed;

	private static int baseAtk = 1;
	private static int varientAtk = 1;
	

	public BigLeech() {
		super(NAME, ID, hp(), maxHp, speed(), maxSpeed, baseAtk, varientAtk);
		addInventory();
		addItem(new StoneOfLeech(2));
	}

	public void doAction(Characters c) {
		int chanceOfAbilitiy = random.nextInt(6);

		if (chanceOfAbilitiy == 0)
			Abilities.regenerate(this, (getMaxHp() / 2), 1);
		else
			Abilities.absorb(this, c, 5, 10);
		
		
	}
	
	static class f{
		public static void h() {
			System.out.println();
		}
	}

}
