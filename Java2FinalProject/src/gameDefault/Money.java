package gameDefault;

import java.util.Random;

// IMPLEMENTED CLASS - Project Requirement
public class Money implements Rewards{
	// Object Instantiation
	Random rand = new Random();
	
	// Variable
	private int moneyEarned;
	private int moneyLost;

	// OVERRIDEN METHOD - Project Requirement
	// This method gives or takes away coins based on if the
	// player won or lost
	@Override
	public int displayWinnings(Player player, boolean didWin) {
		
		moneyEarned = rand.nextInt(100) + 1;
		moneyLost = rand.nextInt(100) + 1; 
		
		if (didWin)
		{
			player.addMoney(moneyEarned);
			GUI.gameDialogueArea.append("You won " + moneyEarned + " coins!\n");
		}
		else
		{
			player.addMoney(-moneyLost);
			GUI.gameDialogueArea.append("You lost " + moneyLost + " coins!\n");
		}
		return 0;
	}

}
