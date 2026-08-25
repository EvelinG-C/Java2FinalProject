package gameDefault;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// each turn won earn money or prizes
// and when they lose they can write into a file that shows what they earned: mone and prize

public class Gameplay {

	public static void main(String[] args) {
		Player player;
		HealerClass healer = new HealerClass();
		TankClass tank = new TankClass();
		WarriorClass warrior = new WarriorClass();
		Scanner scan = new Scanner(System.in);
		
		ArrayList<CharacterClass> heroList = new ArrayList<CharacterClass>();
		heroList.add(healer);
		heroList.add(tank);
		heroList.add(warrior);
		

		System.out.print("what is your first name? ");
		String fName = scan.next();
		
		System.out.print("What is your last name? ");
		String lName = scan.next();
		
		String nName = null;
		System.out.print("Would you like to use a nickname? (y or n) ");
		String nickNameChoice = scan.next();
		
		if (nickNameChoice.equalsIgnoreCase("y"))
		{
			System.out.print("What would you like your nick name to be? ");
			scan.nextLine();
			nName = scan.nextLine();
		}
		
		if (nName == null)
		{
			player = new Player(fName,lName);
		}
		else 
		{
			player = new Player(fName, lName, nName);
		}
			
		System.out.println();
		System.out.println("Hello! you been in charge of leading a group of heros into a dungeion.\n"
				+ "While you explore, you will encounter monsters that while try to defeat you and kill\n"
				+ "your party memebers. Keep them ALIVE! You will earn money and items as you progresss.\n"
				+ "Good Luck!");
		
		System.out.println();
		System.out.println(player.getPlayerInfo());
		System.out.println();
		
		GameTurn gameTurn = new GameTurn();
		gameTurn.startRound(player, scan, heroList);
		
	}

}
