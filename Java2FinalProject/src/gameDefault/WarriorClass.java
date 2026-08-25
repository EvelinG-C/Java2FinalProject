package gameDefault;

public class WarriorClass extends CharacterClass
{
	public WarriorClass() {
		className = "Warror";
		attack += 5;
		defence += 2;
	}

	public void boostAttack()
	{
		attack += 1;
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
			boostAttack();
		}
		
		return 0;
	}
	
	@Override
	public String skillSet()
	{
		return "1. attack\n" +
				"2. boostAttack";
				
	}
}
