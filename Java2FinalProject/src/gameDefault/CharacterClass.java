package gameDefault;

public class CharacterClass
{
	protected String className = "";
	protected int health = 10;
	protected int attack = 0;
	protected int defence = 0;
	protected boolean isHero = true;
	
	public void damage(int damageTaken)
	{
		health -= Math.max(0, damageTaken - defence);
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	
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
	
	public boolean isAlive()
	{
		return getHealth() > 0;
	}
	
	public boolean isHero()
	{
		return isHero;
	}
	
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
	
	public String skillSet()
	{
		return "1. attack";
	}
	
	public String getClassStats()
	{
		return "Health: " + getHealth() + "\n"
				+ "Attack: " + getAttack() + "\n"
				+ "Defence: " + getDefence() + "\n";
	}
	
	
}
