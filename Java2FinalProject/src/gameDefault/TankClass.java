package gameDefault;

public class TankClass extends CharacterClass
{
	public TankClass() {
		className = "Tank";
		attack += 4;
		defence += 2;
	}

	public void boostDefence()
	{
		defence += 1; 
	}
	
	@Override
	public void resetCharacterStats()
	{
		health = 10;
		defence = 2;
	}
	
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
	
	@Override
	public String skillSet()
	{
		return "1. attack\n" +
				"2. boostDefence";
				
	}
	
}
