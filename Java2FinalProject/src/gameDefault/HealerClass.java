package gameDefault;

// CHILD CLASS - Project Requirement
public class HealerClass extends CharacterClass
{
	// default constructor
	public HealerClass()
	{
		className = "Healer";
		attack += 2;
		defence += 1;
	}
	
	// variable
	int healing = 3;
	
	// This method heals a character's health
	public void healing(CharacterClass character)
	{
		int maxHealth = 10;
		int newHealth = Math.min(maxHealth, character.getHealth() + healing);
		character.setHealth(newHealth);
	}
	
	// OVERRIDEN METHOD - Project Requirement
	// This method resets the Healer's stats
	@Override
	public void resetCharacterStats()
	{
		health = 10;
	}
	
	// OVERRIDEN METHOD - Project Requirement
	// This method decides which skill to use
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
	
	// OVERRIDEN METHOD - Project Requirement
	// This method returns a string with the healer's skills
	@Override
	public String skillSet()
	{
		return "Healer's Skills: \n" +
				"1. Attack\n" +
				"2. Heal\n";
	}
}
