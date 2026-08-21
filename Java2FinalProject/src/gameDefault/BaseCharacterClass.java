package gameDefault;

public class BaseCharacterClass 
{
	protected String className = "";
	protected int health = 10;
	protected int attack = 2;
	protected int defence = 1;
	private int money = 0;
	
	public void damage(int damageTaken)
	{
		health -= Math.max(0, damageTaken - defence);
	}
	
	public int attack()
	{
		return attack;
	}
	
	public String getClassName()
	{
		return className;
	}
	
	public int getMoney()
	{
		return money;
	}
	
	public String getClassStats()
	{
		return "Health: " + health + "\n"
				+ "Attack: " + attack + "\n"
				+ "Defence: " + defence + "\n"
				+ "Money: " + getMoney();
	}
}
