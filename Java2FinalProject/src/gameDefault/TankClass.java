package gameDefault;

// CHILD CLASS - Project Requirement
public class TankClass extends CharacterClass
{
	// Default Constructor
	public TankClass() {
		className = "Tank";
		attack += 4;
		defence += 2;
	}

	// This method increase the tank's defence
	public void boostDefence()
	{
		defence += 1; 
	}
	
	// OVERRIDEN METHOD - Project Requirement
	// This method resets the tanks's stats
	@Override
	public void resetCharacterStats()
	{
		health = 10;
		defence = 2;
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
			boostDefence();
		}
		
		return 0;
	}
	
	// OVERRIDEN METHOD - Project Requirement
	// This method returns a string with the tank's skill
	@Override
	public String skillSet()
	{
		return "Tank's Skills: \n" +
				"1. Attack\n" +
				"2. Boost Defence\n";
				
	}
	
}
