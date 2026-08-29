package gameDefault;

//INTERFACE CLASS - Project Requirement
public interface Rewards {
	
	// This abstract method is a blueprint that decides if the
	// player gets a reward depending if they won or lost
	public abstract int displayWinnings(Player player, boolean didWin);

}
