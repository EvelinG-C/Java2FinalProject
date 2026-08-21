package gameDefault;

public class Wolf implements Monsters {

	@Override
	public int attack() {
		return ATTACK;
	}

	@Override
	public int specialMove() {
		return ATTACK + 1;
	}

}
