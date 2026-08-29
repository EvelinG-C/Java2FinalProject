package gameDefault;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GameMechanics 
{
	public enum InputType
	{
		SKILL,
		MONSTER,
		HEAL
	}
	
	/* Data Types */
	
	// Object instantiate
	Random rand = new Random();
	CharacterClass currentCharacter;
	HealerClass healer = new HealerClass();
	TankClass tank = new TankClass();
	WarriorClass warrior = new WarriorClass();
	Money money = new Money();
	Items item = new Items();

	//Variables
	public static Player player;
	private InputType waitingFor;
	private int selectedSkill;
	private int heroDamage;
	public static boolean endOfTurn = false;
	
	// QUEUE - project requirement
	Queue<CharacterClass> turnQueue = new LinkedList<>();
	
	// ArrayLists
	ArrayList<CharacterClass> monsters = new ArrayList<>();
	ArrayList<CharacterClass> heroList = new ArrayList<CharacterClass>();
	public static ArrayList<String> itemsWon = new ArrayList<String>();
	
	// This method adds heros to the heroList
	public void addHeros()
	{
		heroList.add(healer);
		heroList.add(tank);
		heroList.add(warrior);
	}
	
	// This method restarts the game
	public void restartGame()
	{
		turnQueue.clear();
		monsters.clear();
		heroList.clear();
		
		waitingFor = null;
		currentCharacter = null;
		
		endOfTurn = false;
		
		addHeros();
		gameIntroduction();	
	}
	
	// This method gives the player a reward if they won
	public void giveReward(boolean wonGame)
	{
		if (wonGame)
			{
				int randomPrize = rand.nextInt(2);
				// get money
				if (randomPrize == 0)
				{
					money.displayWinnings(player, wonGame);
				}
				// get items
				else
				{
					item.displayWinnings(player, wonGame);
					itemsWon.add(item.reward);
				}
			}
		else
		{
			money.displayWinnings(player, wonGame);
		}
		
		endOfTurn = true;
	}
	
	// This method checks if the heros or monsters lose,
	// then gets the next charcter's turn in the queue
	public void nextTurn()
	{
		if (monsters.isEmpty()) 
		{
			determineWinner(heroList);
			return;
		}
		
		if (heroList.isEmpty())
		{
			determineWinner(heroList);
			return;
		}
		
		currentCharacter = turnQueue.poll();
		
		if (currentCharacter == null)
		{
			return;
		}
		
		if (currentCharacter.isHero())
		{
			heroDialogue();
		}
		else
		{
			monsterTurn();
		}
	}
	
	// This method displays the monster's turn and what they do
	public void monsterTurn()
	{
		GUI.gameDialogueArea.append("Monster's turn!\n");
		monsterAttacks(currentCharacter, heroList);
		removeDeadHero(heroList);
		
		if (!heroList.isEmpty())
		{
			getHeroHealth(heroList);
		}
		
		if (currentCharacter.isAlive())
		{
			turnQueue.offer(currentCharacter);
		}
		
		nextTurn();
	}
	
	// This method introduces the player to the game
	public void gameIntroduction()
	{
		GUI.gameDialogueArea.append("Hello! You've been in charge of leading a group of heros into a dungeon.\n"
				+ "While you explore, you will encounter monsters that while try to defeat you and kill\n"
				+ " your party memebers. Keep them ALIVE! You will earn money and items as you progress.\n"
				+ "Good Luck!\n");
		
		if (turnQueue.isEmpty())
		{
			for (CharacterClass hero: heroList)
			{
				turnQueue.offer(hero);
				hero.resetCharacterStats();
			}
			
			int numOfMonsters = rand.nextInt(5) + 1;
			
			for (int idx2 = 0; idx2 < numOfMonsters; idx2++)
			{
				CharacterClass monster = monsterType();
				turnQueue.offer(monster);
				monsters.add(monster);
			}
		}
		
		GUI.gameDialogueArea.append("There are monsters in the dungeon: \n");
		getMonsterHealth();
		
		nextTurn();
	}
	
	// This method displays the hero's dialogue
	public void heroDialogue()
	{
		waitingFor = InputType.SKILL;
		
		GUI.gameDialogueArea.append(player.getFirstName() + ", What would you like to do?\n");
		GUI.gameDialogueArea.append(currentCharacter.skillSet());
		GUI.gameDialogueArea.append("Enter your skill choice.\n");
	}
	
	// This method determines the input type and 
	// based on the input type determines whether
	// the player is choosing a skill to use , a 
	// monster to kill, or a hero to heal
	public void reciveChoice(int choice)
	{
		if (waitingFor == null)
		{
			return;
		}
		
		switch(waitingFor)
		{
			case SKILL:
				recieveSkillChoice(choice);
				break;
				
			case MONSTER:
				recieveMonsterChoice(choice);
				break;
				
			case HEAL:
				useHealerSkill(choice);
				break;
		}
	}

	// This method receives the hero's choice
	private void recieveSkillChoice(int choice) 
	{
		selectedSkill = choice;
		
		if (currentCharacter.getClassName().equals("Healer") && selectedSkill == 2)
		{
			requestHero();
		}
		else
		{
			requestMonster();
		}
	}
	
	//This method requests which monster to attack
	private void requestMonster() 
	{
		waitingFor = InputType.MONSTER;
		
		GUI.gameDialogueArea.append("Whick monster would you like to attack?\n");
		getMonsterHealth();
		GUI.gameDialogueArea.append("Enter your choice:\n");
	}
	
	// THROWS CUSTOM EXCEPTION - Project Requirement
	// This method receives the monster the hero would like to 
	// use their skill on
	private void recieveMonsterChoice(int monsterChoice)
	{
		if (monsterChoice < 1 || monsterChoice > monsters.size()) 
			{
				GUI.gameDialogueArea.append("Invalid input. Try again");
				return;
			}
		
		CharacterClass monsterTarget = monsters.get(monsterChoice - 1);
		
		try
		{
			heroDamage = currentCharacter.useSkill(selectedSkill);
			
			monsterTarget.damage(heroDamage);
			
			GUI.gameDialogueArea.append(currentCharacter.getClassName() + " attacked " + 
			monsterTarget.getClassName() + " for " + heroDamage + " damage!\n");
		}
		catch (InvalidSkillException e)
		{
			GUI.gameDialogueArea.append(e.getMessage() + "\n");
			
			endHeroTurn();
			return;
		}
		
		getMonsterHealth();
		
		if (!monsterTarget.isAlive())
		{
			GUI.gameDialogueArea.append(monsterTarget.getClassName() + " has been defeated!\n");
			
			monsters.remove(monsterTarget);
			turnQueue.remove(monsterTarget);
		}
		
		endHeroTurn();
	}

	// This method ends the hero's turn
	private void endHeroTurn() {
		waitingFor = null;
		
		if (currentCharacter.isAlive())
		{
			turnQueue.offer(currentCharacter);
		}
		
		nextTurn();
	}

	// This method requests which hero the would like to heal
	private void requestHero() {
		waitingFor = InputType.HEAL;
		
		GUI.gameDialogueArea.append("Who would you like to heal?\n");
		
		getHeroHealth(heroList);
		
		GUI.gameDialogueArea.append("Enter your choice:\n");
		
	}
	
	// This method heals one of the heros
	public void useHealerSkill(int heroChoice)
	{
		if (heroChoice < 1 || heroChoice > heroList.size())
		{
			GUI.gameDialogueArea.append("Invalid hero choice. Try again!\n");
			return;
		}
		
		try 
		{
			currentCharacter.useSkill(selectedSkill, heroList.get(heroChoice - 1));
			GUI.gameDialogueArea.append(currentCharacter.getClassName() + " used skill " + " on " +
			heroList.get(heroChoice - 1).getClassName() + "\n");
		}
		catch (InvalidSkillException e) 
		{
			GUI.gameDialogueArea.append(e.getMessage() + "\n");
		}
			
		endHeroTurn();
	}
	
	// This method helps determine if hero lose or win
	public boolean determineWinner(ArrayList<CharacterClass> heroList)
	{
		if (heroList.isEmpty())
		{
			GUI.gameDialogueArea.append("The party lost!\n");
			giveReward(false);

			turnQueue.clear();
			return false;
		}
		else if (monsters.isEmpty())
		{
			GUI.gameDialogueArea.append("The party won!\n");
			giveReward(true);

			turnQueue.clear();
			return true;
		}
		
		return false;
	}
	
	// This method lowers the health of a hero
	public void monsterAttacks(CharacterClass monster, ArrayList<CharacterClass> heroList)
	{
		int heroChoice = rand.nextInt(heroList.size());
		int skillChoice = 1;
		
		CharacterClass target = heroList.get(heroChoice);
		
		try {
			int damage = monster.useSkill(skillChoice);
			target.damage(damage);
			GUI.gameDialogueArea.append("The monster has attacked the " + heroList.get(heroChoice).getClassName() +
					" for " + damage + " damage!\n");
			
		} catch (InvalidSkillException e) {
			GUI.gameDialogueArea.append(e.getMessage() + "\n");
		}
	}
	
	// This method decides whether the monster will be a goblin or a wolf
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
	
	// This method removes any dead heros from the turn queue and hero list
	public void removeDeadHero(ArrayList<CharacterClass> heroList)
	{
		for (int idx = heroList.size() - 1 ; idx >= 0; idx--)
		{
			CharacterClass hero = heroList.get(idx);
			
			if(!hero.isAlive())
			{
				GUI.gameDialogueArea.append(hero.getClassName() + " is dead.\n");
				heroList.remove(idx);
				turnQueue.remove(hero);
			}
		}
	}
	
	// This method gets the health of all the monsters
	public void getMonsterHealth()
	{
		for (int idx = 0; idx < monsters.size(); idx++)
		{
			int number = idx + 1;
			GUI.gameDialogueArea.append(
					number + ". " + monsters.get(idx).getClassName() + ": health = " + monsters.get(idx).getHealth() + "\n");
		}
	}
	
	// This method gets the health of all the heros
	public void getHeroHealth(ArrayList<CharacterClass> heroList)
	{
		for(int idx = 0; idx < heroList.size(); idx++)
		{
			int number = idx + 1;
			GUI.gameDialogueArea.append(
					number + ". " + heroList.get(idx).getClassName() + ": health = " + heroList.get(idx).getHealth() + "\n");
		}
	}
}
