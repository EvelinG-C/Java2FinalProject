package gameDefault;

public class HealerClass extends CharacterClass
{
	public HealerClass()
	{
		className = "Healer";
		defence += 1;
	}
	
	int healing = 3;
	
	public void healing(CharacterClass character)
	{
		int maxHealth = 10;
		int newHealth = Math.min(maxHealth, character.getHealth() + healing);
		character.setHealth(newHealth);
	}
	
	@Override
	public int useSkill(int choice, CharacterClass character) 
			throws InvalidSkillException
	{
		if (choice != 1 && choice != 2)
		{
			throw new InvalidSkillException();
		}
		
		if (choice == 1)
		{
			return getAttack();
		}
		else if (choice == 2)
		{
			healing(character);
		}
		
		return 0;
	}
	
	@Override
	public String skillSet()
	{
		return "1. attack\n" +
				"2. healing";
	}
}
