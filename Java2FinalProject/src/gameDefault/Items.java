package gameDefault;

import java.util.Random;

public class Items implements Rewards{
	
	private String[] physicalRewards = {"diamond", "potions", "sword", "armour", "rations", "water"};
	public static String reward = "";
	
	public int getRandomPrize()
	{
		Random rand = new Random();
		int randomGift = rand.nextInt(0,6);
		return randomGift;
	}

	@Override
	public int displayWinnings(Player player, boolean didWin) {
		
		if (didWin == true)
		{
			reward = physicalRewards[getRandomPrize()];
			System.out.println(player.getFirstName() + ", You have won a(n) " + reward);
			System.out.println();
		}
		
		return 0;
	}

}
