package gameDefault;

public class Player
{
	// Variables
	private String firstName;
	private String lastName;
	private String nickName;
	private int money = 0;
	
	// Default Constructor
	public Player(String fName, String lName)
	{
		firstName = fName;
		lastName = lName;
	}
	
	// OVERLOADED CONSTRUCTOR - Project Requirement
	public Player(String fName, String lName, String nName)
	{
		firstName = fName;
		lastName = lName;
		nickName = nName;
	}
	
	// GETTER METHODS
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public int getMoney()
	{
		return money;
	}
	
	public String getNickName()
	{
		if (nickName == null)
		{
			return "";
		}
		else
		{
			return nickName;
		}
	}
	
	// Method changes the value of the money variable
	public void addMoney(int amount)
	{
		money += amount;
	}
	
	// Method returns a string with the Player's information
	public String getPlayerInfo()
	{
		return "First name: " + getFirstName() +"\n"
				+ "Last name: " + getLastName() + "\n"
				+ "Nick name: " + getNickName() + "\n"
				+ "Coins: " + getMoney();
	}
}
