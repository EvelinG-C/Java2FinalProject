package gameDefault;

public class Goblin extends CharacterClass{

	public Goblin()
	{
		className = "Goblin";
		attack += 2;
		health += 15;
		isHero = false;
	}
	
	@Override
	public int useSkill(int choice)
	{
		if (choice == 1)
		{
			System.out.println("Goblin attacks!");
			return getAttack();
		}
		
		return 0;
	}
	
	public String skillSet()
	{
		return "1. attack\n";
	}
	
}
