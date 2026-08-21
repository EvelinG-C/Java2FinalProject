package gameDefault;

public class Goblin implements Monsters{
			
	@Override
	public int attack() {
		return ATTACK;
	}

	@Override
	public int specialMove() {
		return DEFENCE + 2;
	}

}
