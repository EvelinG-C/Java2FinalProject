package gameDefault;

// CHILD CLASS - Project Requirement
public class WarriorClass extends CharacterClass
{
	// Default Constructor
	public WarriorClass() {
		className = "Warror";
		attack += 5;
		defence += 1;
	}
	
	// This method increases the warrior's attack
	public void boostAttack()
	{
		attack += 1;
	}
	
	// OVERRIDEN METHOD - Project Requirement
	// This method resets the warrior's stats
	@Override
	public void resetCharacterStats()
	{
		health = 10;
		attack = 5;
	}
	
	// OVERRIDEN METHOD - Project Requirement
	// This method decides which skill to use
	@Override
	public int useSkill(int choice) 
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
			boostAttack();
		}
		
		return 0;
	}
	
	// OVERRIDEN METHOD - Project Requirement
	// This method returns a string with the warrior's skill
	@Override
	public String skillSet()
	{
		return "Warrior's Skills: \n" +
				"1. Attack\n" +
				"2. Boost Attack\n";
				
	}
}
