package gameDefault;

// CHILD CLASS - Project Requirement
public class Wolf extends CharacterClass{
	
	// Default Constructor
	public Wolf()
	{
		className = "Wolf";
		attack += 3;
		health += 10;
		isHero = false;
	}
	
	// OVERRIDEN METHOD - Project Requirement
	// This method decides which skill to use
	@Override
	public int useSkill(int choice)
	{
		if (choice == 1)
		{
			GUI.gameDialogueArea.append("Wolf attacks!\n");
			return getAttack();
		}
		
		return 0;
	}
}
