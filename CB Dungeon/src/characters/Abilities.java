package characters;

import java.security.SecureRandom;
import java.util.stream.Collector.Characteristics;

import GUI.MainFrame;
import characters.Characters.STATUS;

public class Abilities {

	private static SecureRandom random = new SecureRandom();

	public static void regenerate(Characters attacker, int baseInt, int varInt) {
		int addHp = baseInt + random.nextInt(varInt);
		attacker.addHp(addHp);
		MainFrame.getEncounterPanel().appendConsole(attacker.getName() + " regenerated " + addHp + " HP.");
	}

	public static void absorb(Characters attacker, Characters defender, int baseInt, int varInt) {
		int absorb = baseInt + random.nextInt(varInt);
		attacker.addHp(absorb);
		defender.subtractHp(absorb);
		MainFrame.getEncounterPanel()
				.appendConsole(attacker.getName() + " absorbed " + absorb + " from " + defender.getName());
	}

	public static void slowPlayer(Characters attacker, Characters defender) {
		int speed = 1 + random.nextInt(2);
		defender.subtractSpeed(speed);
		MainFrame.getEncounterPanel().appendConsole(attacker.getName() + " slowed down " + defender.getName());
	}

	public static void incSpeed(Characters attacker, int baseInt, int varInt) { // increase speed
		int inc = baseInt + random.nextInt(varInt);
		attacker.addMaxSpeed(inc);
		attacker.addSpeed(inc);
		MainFrame.getEncounterPanel().appendConsole(attacker.getName() + " increased their speed by " + inc);
	}

	public static void multiAtk(Characters attacker, Characters defender, int maxAtks) {
		MainFrame.getEncounterPanel().appendConsole(
				attacker.getName() + "'s multi-attack ability allows it to attack " + maxAtks + " times.\n");

		for (int i = 0; i < maxAtks; i++) {
			attacker.attack(defender);
		}

	}

	public static void fireAttack(Characters attacker, Characters defender) {
		attacker.attack(defender, 5, 10);
		String message = attacker.getName() + " attacked " + defender.getName() + " with fire\n" + defender.getName()
				+ " is now burned.";
		defender.addStatus(STATUS.BURN);
		MainFrame.getEncounterPanel().appendConsole(message);
	}

	public static class Statuses {

		public static void doBurnStatus(Characters c) {

			int fireDmg = 5 + random.nextInt(5);
			MainFrame.getEncounterPanel().appendConsole(c.getName() + " took " + fireDmg + " damage from burn.\n");
			c.subtractHp(fireDmg);
			c.addBurnCounter(1);

			if (c.getBurnCounter() >= 3) {
				c.removeStatus(Characters.STATUS.BURN);
				MainFrame.getEncounterPanel().appendConsole(c.getName() + " is no longer burned.");
			}

		}
	}

	public static class Spells {

	}
}
