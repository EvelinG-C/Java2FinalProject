package gameDefault;

public class HealerClass extends BaseCharacterClass
{
	public HealerClass()
	{
		className = "Healer";
	}
	
	int healing = 2;
	
	public int healing()
	{
		return healing;
	}
}
