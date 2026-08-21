package gameDefault;

public class WarriorClass extends BaseCharacterClass
{
	public WarriorClass() {
		className = "Warror";
	}

	public void boostAttack()
	{
		attack += 1;
	}
}
