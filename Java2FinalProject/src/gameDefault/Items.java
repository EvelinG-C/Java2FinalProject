package gameDefault;

import java.util.Random;

// IMPLEMENTED CLASS - Project Requirement
public class Items implements Rewards{
	
	// Object Instantiation
	Random rand = new Random();
	
	// Variables
	private String[] physicalRewards = {"diamond", "potions", "sword", "armour", "rations", "water"};
	public static String reward = "";
	
	// This method gets a random prize
	public int getRandomPrize()
	{
		int randomGift = rand.nextInt(0,6);
		return randomGift;
	}
	
	// OVERRIDEN METHOD - Project Requirement
	// This method gives the player a physical reward
	// based on if they won or lost
	@Override
	public int displayWinnings(Player player, boolean didWin) 
	{
		
		if (didWin == true)
		{
			reward = physicalRewards[getRandomPrize()];
			GUI.gameDialogueArea.append(player.getFirstName() + ", You have won a(n) " + reward + "\n");
		}
		
		return 0;
	}
}
