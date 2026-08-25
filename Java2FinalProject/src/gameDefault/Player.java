package gameDefault;

public class Player
{
	private String firstName;
	private String lastName;
	private String nickName;
	private int money;
	
	public Player(String fName, String lName)
	{
		firstName = fName;
		lastName = lName;
	}
	
	public Player(String fName, String lName, String nName)
	{
		firstName = fName;
		lastName = lName;
		nickName = nName;
	}
	
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
	
	public int addMoney(int amount)
	{
		return money + amount;
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
	
	public String getPlayerInfo()
	{
		return "First name: " + getFirstName() +"\n"
				+ "Last name: " + getLastName() + "\n"
				+ "Nick name: " + getNickName() + "\n"
				+ "Coins: " + getMoney();
	}
}
