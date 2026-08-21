package gameDefault;

public interface Monsters 
{
	public static final int HEALTH = 10;
	public static final int ATTACK = 2;
	public static final int DEFENCE = 2;
	
	public abstract int attack();
	public abstract int specialMove();

}
