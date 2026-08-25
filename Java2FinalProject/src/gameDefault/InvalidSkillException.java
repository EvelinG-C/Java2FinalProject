package gameDefault;

public class InvalidSkillException extends Exception{
	
	public InvalidSkillException()
	{
		
	}
	
	@Override
	public String getMessage()
	{
		return ("Invalid skill was chosen.");
	}

}
