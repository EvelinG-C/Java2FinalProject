package gameDefault;

// PARENT CLASS - Project Requirement
public class CharacterClass
{
	// Variables
	protected String className = "";
	protected int health = 10;
	protected int attack = 0;
	protected int defence = 0;
	protected boolean isHero = true;
	
	// Calculates the damage taken
	public void damage(int damageTaken)
	{
		health -= Math.max(0, damageTaken - defence);
	}
	
	// SETTER METHODS
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	// GETTER METHODS
	public String getClassName()
	{
		return className;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getDefence()
	{
		return defence;
	}
	
	public int getAttack()
	{
		return attack;
	}
	
	// This method checks if the character is alive
	public boolean isAlive()
	{
		return getHealth() > 0;
	}
	
	// This method checks if the character is a hero
	public boolean isHero()
	{
		return isHero;
	}
	
	// This method resets the character's stats
	public void resetCharacterStats()
	{
		health = 10;
		attack = 0;
		defence = 10;
	}
	
	// This method decides which skill to use
	public int useSkill(int choice)
		throws InvalidSkillException
	{
		if (choice != 1)
		{
			throw new InvalidSkillException();
		}
		
		if (choice == 1)
		{
			return getAttack();
		}
		
		return 0;
	}
	
	// This method overload decides which skill to use on a character
	public int useSkill(int choice, CharacterClass character)
			throws InvalidSkillException
		{
			if (choice != 1)
			{
				throw new InvalidSkillException();
			}
			
			if (choice == 1)
			{
				return getAttack();
			}
			
			return 0;
		}
	
	// This method returns a string containing the character's skills
	public String skillSet()
	{
		return "1. Attack\n";
	}
	
	// This method returns a string containing the character's stats
	public String getClassStats()
	{
		return "Health: " + getHealth() + "\n"
				+ "Attack: " + getAttack() + "\n"
				+ "Defence: " + getDefence() + "\n";
	}
	
	
}
