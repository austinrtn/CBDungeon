package characters;

import javax.swing.DefaultListModel;

import items.*;

public class Player extends Characters {

	private static String name = "Player";
	private final static int id = 0;

	private static int playerHp = 100;
	private static int playerSpeed = 10;

	private static int playerBaseAtk = 5, playerVarAtk = 10;
	
	
	public int mana = 10;
	
	private DefaultListModel<String> spells = new DefaultListModel<String>();
	
	public DefaultListModel getSpells() {
		return spells;
	}
	
	private static int enemiesDefeated = 0;
	
	public void setMana(int mana) {
		this.mana = mana;
	}
	
	public void addMana (int mana) {
		this.mana += mana;
	}
	
	public void subMana(int mana) {
		this.mana -= mana;
	}
	
	public int getMana() {
		return mana;
	}
	
	public static void addEnemiesDefeated(int num) {
		enemiesDefeated += num;
	}
	
	public static int getEnemiesDefeated() {
		return enemiesDefeated;
	}
	

	public Player() {
		super(name, id, playerHp, playerHp, playerSpeed, playerSpeed, playerBaseAtk, playerVarAtk);
		addInventory();
		
		setExpCap(20);
		
	}

	public void doAction(Characters defender) {
		attack(defender);
		checkStatus();
	}
	
	public void addInventory() {
		addItem(new HpPotion(2));
		addItem(new Sword());
		addItem(new Shield());
		addItem(new StoneOfPower(1));
		addItem(new StoneOfLeech(1));
	}
}
