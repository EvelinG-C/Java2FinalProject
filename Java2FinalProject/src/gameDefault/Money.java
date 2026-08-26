package gameDefault;

import java.util.Random;

public class Money implements Rewards{
	Random rand = new Random();
	private int moneyEarned;
	private int moneyLost;

	@Override
	public int displayWinnings(Player player, boolean didWin) {
		
		moneyEarned = rand.nextInt(100) + 1;
		moneyLost = rand.nextInt(100) + 1; 
		
		if (didWin)
		{
			player.addMoney(moneyEarned);
			System.out.println("You won " + moneyEarned + " coins!");
			System.out.println();
		}
		else
		{
			player.addMoney(-moneyLost);
			System.out.println("You lost " + moneyLost + " coins!");
			System.out.println();
		}
		return 0;
	}

}
