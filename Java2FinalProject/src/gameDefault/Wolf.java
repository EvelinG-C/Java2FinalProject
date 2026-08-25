package gameDefault;

public class Wolf extends CharacterClass{
	public Wolf()
	{
		className = "Wolf";
		attack += 3;
		health += 10;
		isHero = false;
	}
	
	@Override
	public int useSkill(int choice)
	{
		if (choice == 1)
		{
			System.out.println("Wolf attacks!");
			return getAttack();
		}
		
		return 0;
	}
	
	public String skillSet()
	{
		return "1. attack\n";
	}
}
