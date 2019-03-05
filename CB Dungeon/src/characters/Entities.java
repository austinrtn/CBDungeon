package characters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import GUI.MainFrame;
import items.HpPotion;
import items.Items;
import items.LongSwoard;
import items.MaxHpPotion;
import items.MaxSpeedPotion;
import items.StoneOfPower;
import items.Shield;
import items.SlimeDrop;
import items.SpeedPotion;
import items.StrengthPotion;
import items.Sword;

public class Entities {

	public static final ArrayList<Entities> entitiesList = new ArrayList<>();

	private String name;
	private int id;

	public Entities(String name, int id) {
		this.name = name;
		this.id = id;
	}

	// Set/Get entity name

	public void setName(String name) {
		this.name = name;
	}
	

	public String getName() {
		return name;
	}

	// Set/Get entity ID

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static void printIds() {
		// Characters
		entitiesList.add(MainFrame.player); 
		entitiesList.add(new Slime());
		entitiesList.add(new Knight());
		entitiesList.add(new Cyclops());
		entitiesList.add(new Leech());
		entitiesList.add(new BigLeech());
		
		//Potions (Items)
		entitiesList.add(new HpPotion(1));
		entitiesList.add(new MaxHpPotion(1));
		entitiesList.add(new SpeedPotion(1));
		entitiesList.add(new MaxSpeedPotion(1));
		entitiesList.add(new StrengthPotion(1));

		//Misc (Items)
		entitiesList.add(new SlimeDrop(1));
		entitiesList.add(new StoneOfPower(1));

		//Weapons (Items)
		entitiesList.add(new Sword());
		entitiesList.add(new LongSwoard());
		
		//Defense (Items)
		entitiesList.add(new Shield());

		Collections.sort(entitiesList, new Comparator<Entities>() {
			public int compare(Entities e1, Entities e2) {
				return Integer.valueOf(e1.id).compareTo(e2.id);
			}
		});
		
		for (int i = 0; i < entitiesList.size(); i++) {
			Entities e = entitiesList.get(i);
			System.out.println(e.getName() + ": " + e.getId());
			
		}

	}

}
