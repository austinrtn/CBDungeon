package items;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import characters.*;

public class Items extends Entities {

	private SecureRandom random = new SecureRandom();

	private int uses;
	private boolean isEquiped;

	public static enum TYPE {
		WEAPON, POTION, DEFENSE
	};

	private TYPE type;

	public int getModifer(Characters c) {
		return 0;
	}

	public Items(String name, int id, int uses, boolean isEquiped, TYPE type) {
		super(name, id);
		this.uses = uses;
		this.isEquiped = isEquiped;
		this.type = type;
	}

	// Edit Uses

	public void setUses(int uses) {
		this.uses = uses;
	}

	public void addUses(int uses) {
		this.uses += uses;
	}

	public void subUses(int uses, Characters c) {
		if (getUses() <= 1)
			c.getItemList().remove(this);

		if (this == c.getItemEquipped() && getUses() <= 1)
			c.setItemEquiped(this);

		this.uses -= uses;
	}

	public int getUses() {
		return uses;
	}

	// Edit isEquiped

	public void setIsEquiped(boolean isEquiped) {
		this.isEquiped = isEquiped;
	}

	public boolean getIsEquiped() {
		return isEquiped;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public TYPE getType() {
		return type;
	}

	//

	public void useItem(Characters c) {

	}

}
