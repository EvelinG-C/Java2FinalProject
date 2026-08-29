package gameDefault;

// CUSTOM EXCEPTION - Project Requirement
public class InvalidSkillException extends Exception{
	
	@Override
	public String getMessage()
	{
		return ("Invalid skill was chosen.\n");
	}

}
