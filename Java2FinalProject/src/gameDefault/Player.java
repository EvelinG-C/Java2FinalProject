package gameDefault;

public class Player
{
	private String firstName;
	private String lastName;
	private String nickName;
	private BaseCharacterClass characterClass;
	
	public Player(String fName, String lName, BaseCharacterClass charClass)
	{
		firstName = fName;
		lastName = lName;
		characterClass = charClass;
	}
	
	public Player(String fName, String lName, String nName, BaseCharacterClass charClass)
	{
		firstName = fName;
		lastName = lName;
		nickName = nName;
		characterClass = charClass;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
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
	
	public String getCharacterClassName()
	{
		return characterClass.getClassName();
	}
	
	public String getPlayerInfo()
	{
		return "First name: " + getFirstName() +"\n"
				+ "Last name: " + getLastName() + "\n"
				+ "Nick name: " + getNickName() + "\n"
				+ "Character class: " + getCharacterClassName();
	}
}
