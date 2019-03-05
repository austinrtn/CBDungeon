package characters;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.DefaultListModel;

import GUI.MainFrame;
import characters.Abilities.Statuses;
import items.*;
import items.Items.TYPE;

public class Characters extends Entities {

	private static SecureRandom random = new SecureRandom();

	// Main Charactaristics
	private int level, exp, expCap;
	
	private int hp, maxHp;
	private int speed, maxSpeed;

	private int baseAtk, variantAtk;

	private Items itemEquipped; // The item that the Character has equipped

	private ArrayList<Items> itemsList = new ArrayList(); // List of items possessed by character
	private DefaultListModel<String> itemNamesList = new DefaultListModel<>();

	public enum STATUS {
		BURN
	};

	private ArrayList<STATUS> statusesList = new ArrayList();

	private int burnCounter = 0;

	// Name of Char, Char Id, Char HP, Char Max HP, Char Speed, Char MaxSpeed, Char
	// baseAtk, Char variantAtk
	public Characters(String name, int id, int hp, int maxHp, int speed, int maxSpeed, int baseAtk, int variantAtk) {
		super(name, id);
		this.maxHp = maxHp;
		this.hp = hp;
		this.speed = speed;
		this.maxSpeed = maxSpeed;
		this.baseAtk = baseAtk;
		this.variantAtk = variantAtk;
		this.level = level;
		
		if(this.getId() == 0)
			setLevel(1);			
		else 
			initLevel();
		
		increaseStats();
		this.setName(getName() + " (Lvl " + this.level +")");
		
	}

	// Set Encounter

	public static Characters setEncounter() {
		int sel = random.nextInt(4);
		if (sel == 0)
			return new Slime();
		else if (sel == 1)
			return new Knight();
		else if (sel == 2)
			return new Leech();
		else
			return new Cyclops();
	}

	public static Characters setEncounter(Characters c) {
		return c;
	}

	// Character Selects Action

	public void doAction(Characters defender) {
		System.out.println("Do action from character class");
	}
	
	// Level
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void addLevel(int level) { 
		this.level += level;
	}
	
	
	public int getLevel() {
		return level;
	}
	
	public void initLevel() {
		
		int baseInt = MainFrame.player.getLevel() - 2;
		
		if(baseInt <= 0)
			baseInt = 1;
		
		int level = baseInt + random.nextInt(2);
			
		this.level = level;
	}
	
	public void increaseStats() {
		if (level > 1) {
			int mult = 5 * level;
			
			
			maxHp += mult;
			maxSpeed += mult;
			hp = maxHp;
			speed = maxSpeed;
			hp += mult;
			baseAtk += mult;
			
			expCap *= 2;
		}
		
			if(getId() != 0)
				exp += level + random.nextInt(5);
			else {
				exp = 0;
			}
	}
	
	// Exp
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public void addExp(int exp) {
		this.exp += exp;
	}
	
	public void dropExp(Characters c) {
		MainFrame.appendMessage(getName() + "gained " + c.getExp() + " exp.");
		addExp(c.getExp());
		System.out.println(c.getExp());
		if (exp >= expCap) {
			exp = 0;
			level++;
			increaseStats();
			MainFrame.appendMessage(getName() + " has leveled up!");
		}
	}
	
	public int getExp() {
		return exp;
	}
	
	// Exp Cap
	
	public void setExpCap(int cap) {
		expCap = cap;
	}
	
	public void addExpCap(int cap) {
		exp += cap;
	}
	
	public int getExpCap() {
		return expCap;
	}

	// Edit Character Health Points

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void addHp(int hp) {
		this.hp += hp;
	}

	public void subtractHp(int hp) {
		this.hp -= hp;
	}

	public int getHp() {
		if (hp < 0) {
			hp = 0;
		} else if (hp > maxHp)
			hp = maxHp;

		return hp;
	}

	// Edit Character Max HP

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public void addMaxHp(int maxHp) {
		this.maxHp += maxHp;
	}

	public void subMaxHp(int maxHp) {
		this.maxHp -= maxHp;
	}

	public int getMaxHp() {
		if (maxHp < 0)
			maxHp = 0;
		return maxHp;
	}

	// Edit Character speed

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void addSpeed(int speed) {
		this.speed += speed;
	}

	public void subtractSpeed(int speed) {
		this.speed -= speed;
	}

	public int getSpeed() {
		if (speed < 0)
			speed = 0;
		if (speed > maxSpeed)
			speed = maxSpeed;

		return speed;
	}

	// Edit Character max speed

	public void setMaxSpeed(int speed) {
		this.maxSpeed = speed;
	}

	public void addMaxSpeed(int speed) {
		this.maxSpeed += speed;
	}

	public void subtractMaxSpeed(int speed) {
		this.maxSpeed -= speed;
	}

	public int getMaxSpeed() {
		if (maxSpeed < 0)
			maxSpeed = 0;

		return maxSpeed;
	}

	// Edit Base Attack (Starting Point for random number)

	public void setBaseAtk(int atk) {
		baseAtk = atk;
	}

	public void addBaseAtk(int atk) {
		baseAtk += atk;
	}

	public void subBaseAtk(int atk) {
		baseAtk -= atk;
	}

	// Edit Variant Attack

	public void setVarAtk(int atk) {
		variantAtk = atk;
	}

	public void addVarAtk(int atk) {
		variantAtk += atk;
	}

	public void subVarAtk(int atk) {
		variantAtk -= atk;
	}

	// Attack Methods

	public void attack(Characters c) { // attack for characters
		int atk = getAtk(c, 0, 0);

		if (atk > 0) {
			atk += getModifiers(c, atk);
		}

		c.subtractHp(atk);

		if (atk > 0)
			MainFrame.getEncounterPanel()
					.appendConsole(getName() + " attacked " + c.getName() + " with " + atk + " damage.\n");

		MainFrame.getEncounterPanel().updatePanel();
	}

	public void attack(Characters c, int baseAttack, int varientAttack) { // Attack for abilliteis
		int atk = getAtk(c, baseAttack, varientAttack);

		if (atk > 0) {
			atk += getModifiers(c, atk);
		}

		c.subtractHp(atk);

		MainFrame.getEncounterPanel().updatePanel();
	}

	public int getAtk(Characters c, int baseAttack, int varientAttack) {
		int atk = 0;
		if (baseAttack > 0)
			atk = baseAttack + random.nextInt(varientAttack);
		else
			atk = (this.baseAtk + random.nextInt(this.variantAtk));

		if (dodge(c, atk) == true && baseAttack == 0)
			atk = 0;
		else if (random.nextInt(3) == 0) {
			MainFrame.getEncounterPanel()
					.appendConsole(getName() + " is attacking " + c.getName() + " with " + atk + " damage.");
			atk *= 2;
			MainFrame.appendMessage("It was a critical attack.");
		}
		return atk;
	}

	public int getModifiers(Characters c, int atk) {

		int modifier = 0;

		if (itemEquipped != null && itemEquipped.getType() == Items.TYPE.WEAPON)
			modifier += getItemEquipped().getModifer(this);

		if (c.itemEquipped != null && c.getItemEquipped().getType() == Items.TYPE.DEFENSE) {

			MainFrame.getEncounterPanel()
					.appendConsole(getName() + " is attacking " + c.getName() + " with " + atk + " damage.");

			modifier = c.getItemEquipped().getModifer(c);
			atk += modifier;
			if (atk <= modifier) {
				atk = 0;
				modifier = 0;
				MainFrame.getEncounterPanel().appendConsole(c.getName() + " shielded the attack entirely");
			} else {
				MainFrame.getEncounterPanel().appendConsole(c.getName() + " shielded the attack by " + (modifier * -1));
			}
		}

		return modifier;
	}

	public boolean dodge(Characters c, int atk) {
		int chanceOfDodge = -1;
		if (c.getSpeed() >= 10 && c.getSpeed() < 15)
			chanceOfDodge = random.nextInt(4);
		else if (c.getSpeed() >= 15)
			chanceOfDodge = random.nextInt(3);

		if (chanceOfDodge == 0) {
			MainFrame.getEncounterPanel()
					.appendConsole(getName() + " is attacking " + c.getName() + " with " + atk + " damage.");
			MainFrame.getEncounterPanel().appendConsole(c.getName() + " dodged the attack");
			return true;
		} else
			return false;
	}

	// Change/check status

	public void addStatus(STATUS s) {
		statusesList.add(s);
	}

	public void removeStatus(STATUS s) {
		statusesList.remove(s);
		if (s == STATUS.BURN)
			burnCounter = 0;

	}

	public void removeAllStatuses() {
		removeStatus(STATUS.BURN);
	}

	public void checkStatus() { //Checks statuses and tests for level up

		for (int i = 0; i < statusesList.size(); i++) {
			if (statusesList.get(i) == STATUS.BURN)
				Statuses.doBurnStatus(this);
		}
		
	}

	public void setBurnCounter(int burnCounter) {
		this.burnCounter = burnCounter;
	}

	public void addBurnCounter(int burnCounter) {
		this.burnCounter += burnCounter;
	}

	public int getBurnCounter() {
		return burnCounter;
	}

	public ArrayList getStatusesList() {
		return statusesList;
	}

	// Add items to list

	public void addItem(Items item) {

		Items selectItem = null;
		boolean checkIfHasItem = false;

		for (int i = 0; i < itemsList.size(); i++) {
			selectItem = itemsList.get(i);
			if (selectItem.getId() == item.getId()) {
				checkIfHasItem = true;
				break;
			}
		}

		if (checkIfHasItem == true)
			selectItem.addUses(item.getUses());
		else
			itemsList.add(item);
		
		Collections.sort(itemsList, new Comparator<Items>() {
			public int compare(Items item1, Items item2) {
			return Integer.valueOf(item1.getId()).compareTo(item2.getId());
			}
		});
		
	}

	public void removeItem(Items item) {
		itemsList.remove(item);
	}


	public void dropItem(Characters c, Items item) {
		MainFrame.getEncounterPanel()
				.appendConsole(getName() + " dropped a " + item.getName() + "(" + item.getUses() + ")");

		removeItem(item);
		c.addItem(item);
	}

	public void addInventory() {
		
			addItem(new HpPotion(1));
			
		if (hp > 50) {
			addItem(new HpPotion(1));
			addItem(new SpeedPotion(1));
		}
		if (hp > 60)
			addItem(new StrengthPotion(1));

		if (hp > 70) {
			addItem(new HpPotion(1));
			addItem(new MaxHpPotion(1));
			addItem(new MaxSpeedPotion(1));
			addItem(new SpeedPotion(1));
			addItem(new Sword());
			addItem(new Shield());
		}

	}
	
	public void dropInventory(Characters c) {
		int amountDropped;
		amountDropped = 1;

		if (hp >= 30 && hp <= 49)
			amountDropped = 1 + random.nextInt(2);
		else if (hp >= 50 && hp <= 90)
			amountDropped = 2 + random.nextInt(1);
		else
			amountDropped = 3 + random.nextInt(2);

		for (int amountSelected = 0; amountSelected <= amountDropped; amountSelected++) {
			if (itemsList.isEmpty() == true)
				break;

			Items item = (Items) getItemList().get(random.nextInt(getItemList().size()));
			dropItem(c, item);
		}
	}

	public ArrayList getItemList() {
		return itemsList;
	}

	public DefaultListModel<String> getItemNamesList() {
		itemNamesList.removeAllElements();

		for (int i = 0; i < getItemList().size(); i++) {
			Items item = (Items) getItemList().get(i);
			String name = (item.getName() + " (" + item.getUses() + ")");
			if (item.getIsEquiped() == true)
					name += "*";
			itemNamesList.addElement(name);
		}

		return itemNamesList;
	}

	// Edit itemEquiped

	public void setItemEquiped(Items i) {

		if (itemEquipped == null) {
			itemEquipped = i;
			itemEquipped.setIsEquiped(true);
		} else if (i.getId() == itemEquipped.getId()) {
			itemEquipped.setIsEquiped(false);
			itemEquipped = null;
		} else if (i.getId() != itemEquipped.getId()) {
			itemEquipped.setIsEquiped(false);
			itemEquipped = i;
			itemEquipped.setIsEquiped(true);
		}

	}

	public Items getItemEquipped() {
		return itemEquipped;
	}
}
