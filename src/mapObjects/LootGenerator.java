package mapObjects;

import java.util.Random;

public class LootGenerator {
	
	private Random rand;
	
	
	public LootGenerator()
	{
		this.rand = new Random();
	}
	
	public Loot generate(int itemRating)
	{
		Loot item;
		int itemBonus = 0;
		if(itemRating > 0)
			itemBonus = rand.nextInt(itemRating);
		
		item = genItem(itemBonus);
		return item;
	}
	
	private Loot genItem(int itemBonus)
	{
		Loot item = null;
		int itemNumber = rand.nextInt(2);
		switch(itemNumber)
		{
			case 0:
				item = genWeapon(itemBonus);
				break;
			case 1:
				item = genPotion(itemBonus);
				break;
			case 2:
				//item = genArmor(itemBonus);
				break;

		}
		return item;
	}
	private Consumable genPotion(int itemBonus)
	{
		Consumable potion = null;
		int potionNumber = rand.nextInt(2);
		int bonusValue = itemBonus * 10;
		switch(potionNumber)
		{
			case 0:
				int bonusHealth = 10*itemBonus;
				potion = new HealthPotion(bonusHealth,bonusValue);
				break;
			case 1:
				double bonusSpeed = .5 * itemBonus;
				potion = new SpeedPotion(bonusSpeed, bonusValue);
				break;
		}
		
		return potion;
	}
	
	private Wepon genWeapon(int itemBonus)
	{
		Wepon wepon = null;
		int weaponNumber = rand.nextInt(4);
		//int extraDmg = itemBonus;
		int extraValue = itemBonus * 10;
		switch(weaponNumber)
		{
			case 0:
				wepon = new Sword(itemBonus, extraValue);
				break;
			case 1:
				wepon = new Dagger(itemBonus, extraValue);
				break;
			case 2:
				wepon = new Bow(itemBonus, extraValue);
				break;
			case 3:
				wepon = new Axe(itemBonus, extraValue);
		}
		return wepon;
	}

}
