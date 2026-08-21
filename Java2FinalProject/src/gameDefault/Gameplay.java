package gameDefault;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Gameplay {

	public static void main(String[] args) {
		Player player;
		HealerClass healer = new HealerClass();
		TankClass tank = new TankClass();
		WarriorClass warrior = new WarriorClass();
		Scanner scan = new Scanner(System.in);
		
		// max size of player list is 3; should i make an arraylist as well
		Player[] currentPlayers = new Player[3];
		ArrayList<BaseCharacterClass> classNames = new ArrayList<BaseCharacterClass>();
		classNames.add(healer);
		classNames.add(tank);
		classNames.add(warrior);
		
		// should i have it so that 3 players must be in it
//		System.out.print("How many players? (3 max) ");
//		
//		while(!scan.hasNextInt())
//		{
//			System.out.println("Please enter a number: ");
//			scan.next();
//		}
//		
//		int players = scan.nextInt();
		
		for (int idx = 0; idx < currentPlayers.length; idx++)
		{
			System.out.print("what is your first name? ");
			String fName = scan.next();
			
			System.out.print("What is your last name? ");
			String lName = scan.next();
			
			String nName = null;
			System.out.print("Would you like to use a nickname? (y or n) ");
			String nickNameChoice = scan.next();
			
			if (nickNameChoice.equalsIgnoreCase("y"))
			{
				System.out.println("What would you like your nick name to be? ");
				scan.nextLine();
				nName = scan.nextLine();
			}
			
			System.out.println("Which class would you like?");
			
			for (int idx2 = 0; idx2 < classNames.size(); idx2++)
			{
				System.out.println(idx2 + 1 + ". " + classNames.get(idx2).getClassName());
			}
			
			System.out.println("Please chose: ");
			
			while(!scan.hasNextInt())
			{
				System.out.println("Please enter a number: ");
				scan.next();
			}
			
			int choice = scan.nextInt();
			
			switch(choice)
			{
				case 1:
					if (nName == null)
					{
						currentPlayers[idx] = new Player(fName,lName,classNames.get(0));
					}
					else
					{
						currentPlayers[idx] = new Player(fName,lName,nName,classNames.get(0));
					}
					
					break;
				case 2:
					if (nName == null)
					{
						currentPlayers[idx] = new Player(fName,lName,classNames.get(1));
					}
					else
					{
						currentPlayers[idx] = new Player(fName,lName,nName,classNames.get(1));
					}
					
					break;
				case 3:
					if (nName == null)
					{
						currentPlayers[idx] = new Player(fName,lName,classNames.get(2));
					}
					else
					{
						currentPlayers[idx] = new Player(fName,lName,nName,classNames.get(2));
					}
					break;
			}
		}
		
		for (int i = 0; i < currentPlayers.length; i++)
		{
			System.out.println(currentPlayers[i].getPlayerInfo());
			System.out.println();
		}
	}

}
