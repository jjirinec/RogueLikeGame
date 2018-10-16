package mapObjects;

import java.util.Random;

public class LootGenerator {
	
	private Random rand;
	private int gridSize;
	
	public LootGenerator(int gridSize)
	{
		this.rand = new Random();
		this.gridSize = gridSize;
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
		int itemNumber = rand.nextInt(3);
		switch(itemNumber)
		{
			case 0:
				item = genWeapon(itemBonus);
				break;
			case 1:
				item = genPotion(itemBonus);
				break;
			case 2:
				item = genArmor(itemBonus);
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
				potion = new HealthPotion(bonusHealth,bonusValue,gridSize);
				break;
			case 1:
				double bonusSpeed = .5 * itemBonus;
				potion = new SpeedPotion(bonusSpeed, bonusValue,gridSize);
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
				wepon = new Sword(itemBonus, extraValue,gridSize);
				break;
			case 1:
				wepon = new Dagger(itemBonus, extraValue,gridSize);
				break;
			case 2:
				wepon = new Bow(itemBonus, extraValue,gridSize);
				break;
			case 3:
				wepon = new Axe(itemBonus, extraValue,gridSize);
		}
		return wepon;
	}
	
	private Armor genArmor(int itemBonus)
	{
		Armor armor = null;
		int armorNumber = rand.nextInt(3);
		int extraValue = itemBonus * 10;
		switch(armorNumber)
		{
			case 0:
				armor = new LeatherArmor(itemBonus,extraValue,gridSize);
				break;
			case 1:
				armor = new ChainMail(itemBonus,extraValue,gridSize);
				break;
			case 2:
				armor = new PlateArmor(itemBonus,extraValue,gridSize);
				break;
		}
		return armor;
	}

}
