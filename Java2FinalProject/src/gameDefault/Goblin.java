package gameDefault;

// CHILD CLASS - Project Requirement
public class Goblin extends CharacterClass{

	// Default constructor
	public Goblin()
	{
		className = "Goblin";
		attack += 2;
		health += 15;
		isHero = false;
	}
	
	// OVERRIDEN METHOD - Project Requirement
	// This method decides which skill to use
	@Override
	public int useSkill(int choice)
	{
		if (choice == 1)
		{
			GUI.gameDialogueArea.append("Goblin attacks!\n");
			return getAttack();
		}
		
		return 0;
	}
}
