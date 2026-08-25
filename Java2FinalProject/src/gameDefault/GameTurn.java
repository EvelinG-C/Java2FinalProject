package gameDefault;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class GameTurn 
{
	// queue list
	Queue<CharacterClass> turnQueue = new LinkedList<>();
	
	ArrayList<CharacterClass> monsters = new ArrayList<>();
	
	Random rand = new Random();
	
	public boolean startRound(Player player, Scanner scan, ArrayList<CharacterClass> heroList)
	{
		for (CharacterClass hero: heroList)
		{
			turnQueue.offer(hero);
		}
		
		int numOfMonsters = rand.nextInt(5) + 1;
		
		for (int idx2 = 0; idx2 < numOfMonsters; idx2++)
		{
			CharacterClass monster = monsterType();
			turnQueue.offer(monster);
			monsters.add(monster);
		}
		
		System.out.println("There is " +  numOfMonsters + " monsters.");
		System.out.println("Heros start first. " + player.getFirstName() +", What would you like to do? ");
		
		while(!monsters.isEmpty() && !heroList.isEmpty())
		{
			CharacterClass currentCharacter = turnQueue.poll();
			
			if (currentCharacter.isHero())
			{
				System.out.println(currentCharacter.getClassName() + ":");
				System.out.println(currentCharacter.skillSet());
				System.out.print("Choice: ");
				System.out.println();
				
				while (!scan.hasNextInt())
				{
					System.out.print("Please enter an integer: ");
					scan.next();
				}
				
				int chosenSkill = scan.nextInt();
				
				System.out.println();
				
				int heroDamage = 0;
				
				if (chosenSkill == 2)
				{
					try {
						if (currentCharacter.getClassName().equals("Healer"))
						{
							System.out.println("Who would you like to heal?");
							getHeroHealth(heroList);
							
							System.out.print("Choice: ");
							System.out.println();
							
							int heroChoice = 1;

							boolean correctInput = false;
							
							while(!correctInput)
							{
								while(!scan.hasNextInt())
								{
									System.out.print("Please enter an integer: ");
									scan.next();
								}
								
								heroChoice = scan.nextInt();
								
								if (heroChoice < 1 || heroChoice > heroList.size())
								{
									System.out.println("Invalid input.");
								}
								else
								{
									correctInput = true;
								}
							}
							currentCharacter.useSkill(chosenSkill, heroList.get(heroChoice - 1));
						}
						else
						{
							currentCharacter.useSkill(chosenSkill);
						}
					} catch (InvalidSkillException e) {
						System.out.println(e.getMessage());
						System.out.println("Hero turn skipped!");
					}
					
					if (currentCharacter.isAlive())
					{
						turnQueue.offer(currentCharacter);
					}
				}
				else
				{
					try {
						heroDamage = currentCharacter.useSkill(chosenSkill);
					} catch (InvalidSkillException e) {
						System.out.println(e.getMessage());
						System.out.println("Hero turn skipped!");
					}
					System.out.println("Which monster would you like to attack?");
					
					for (int idx3 = 0; idx3 < monsters.size(); idx3++)
					{
						int number = idx3 + 1;
						System.out.println(number + ". " + monsters.get(idx3).getClassName());
					}
					
					System.out.print("Chose: ");
					System.out.println();
					
					boolean correctInput = false;
					int monsterChoice = 1; 
					
					while(!correctInput)
					{
						while (!scan.hasNextInt())
						{
							System.out.print("Please enter an integer: ");
							scan.next();
						}
						
						monsterChoice = scan.nextInt();
						
						if (monsterChoice < 1 || monsterChoice > monsters.size()) 
						{
							System.out.println("Invalid input.");
						}
						else
						{
							correctInput = true;
						}
					}
					
					CharacterClass monsterTarget = monsters.get(monsterChoice - 1);
					
					monsterTarget.damage(heroDamage);
					getMonsterHealth();
					
					if (!monsterTarget.isAlive())
					{
						System.out.println(monsterTarget.getClassName() + " has been defeated!");
						monsters.remove(monsterTarget);
						turnQueue.remove(monsterTarget);
					}
					
					if (currentCharacter.isAlive())
					{
						turnQueue.offer(currentCharacter);
					}
				}
			}
			else
			{
				monsterAttacks(currentCharacter, heroList);
				removeDeadHero(heroList);
				getHeroHealth(heroList);
				
				if (currentCharacter.isAlive())
				{
					turnQueue.offer(currentCharacter);
				}
			}
		}
		
		return determineWinner(heroList);
	}
	
	public boolean determineWinner(ArrayList<CharacterClass> heroList)
	{
		if (heroList.isEmpty())
		{
			System.out.println("The party lost!");
			return false;
		}
		else
		{
			System.out.println("The party won!");
			return true;
		}
	}
	
	
	public CharacterClass monsterType()
	{
		int monsterType = rand.nextInt(2);
		if ( monsterType == 0)
		{
			return new Goblin();
		}
		else
		{
			return new Wolf();
		}
	}
	
	public void monsterAttacks(CharacterClass monster, ArrayList<CharacterClass> heroList)
	{
		int heroChoice = rand.nextInt(heroList.size());
		int skillChoice = 1;
		
		System.out.println("The monster has attacked the " + heroList.get(heroChoice).getClassName());
		try {
			heroList.get(heroChoice).damage(monster.useSkill(skillChoice));
		} catch (InvalidSkillException e) {
			System.out.println(e.getMessage());
			System.out.println("Monster turn skipped!");
		}
		
	}
	
	public void getMonsterHealth()
	{
		for (int idx = 0; idx < monsters.size(); idx++)
		{
			int number = idx + 1;
			System.out.println(number + ". " + monsters.get(idx).getClassName() + ": health = " + monsters.get(idx).getHealth());
		}
	}
	
	public void removeDeadHero(ArrayList<CharacterClass> heroList)
	{
		for (int idx = heroList.size() - 1 ; idx >= 0; idx--)
		{
			CharacterClass hero = heroList.get(idx);
			
			if(!hero.isAlive())
			{
				// may or may not keep line below. idk if i wan to remove hero
				// from herolist
				heroList.remove(idx);
				turnQueue.remove(hero);
			}
		}
	}
	
	public void getHeroHealth(ArrayList<CharacterClass> heroList)
	{
		for(int idx = 0; idx < heroList.size(); idx++)
		{
			int number = idx + 1;
			System.out.println(number + ". " + heroList.get(idx).getClassName() + ": health = " + heroList.get(idx).getHealth());
		}
	}
}
