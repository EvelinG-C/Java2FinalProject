package gameDefault;

public class TankClass extends BaseCharacterClass
{
	public TankClass() {
		className = "Tank";
		defence += 2;
	}

	public void boostDefence()
	{
		defence += 1; 
	}
}
