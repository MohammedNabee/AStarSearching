package validation;

/**
 * InputValidationException       Creates a custom exception used in file validation.
 * @author                        Mohammed Nabee
 */
@SuppressWarnings("serial")
class InputValidationException extends Exception
{
	public InputValidationException(){}
	
	public InputValidationException(String errorMessage)
	{
		super(errorMessage);
	}
}
