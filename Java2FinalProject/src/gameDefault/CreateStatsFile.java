package gameDefault;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

// creates a file that writes the player's stats, money, and gifts earned
public class CreateStatsFile {
	
	public void createCharacterFile(Player player, ArrayList<String> rewards)
	{
		Path path = Paths.get("characterStats.txt");
		String content = player.getPlayerInfo() + "\n"
				+ "Rewards: " + rewards.toString();
		
		
		try 
		{
			Files.writeString(path,content);
			System.out.println("File written succesfully!");
		}
		catch (IOException e)
		{
			System.out.println("File unsuccesfully written!");
		}
	}
	

}
