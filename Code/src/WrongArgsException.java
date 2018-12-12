/**
* This class is used to handle errors related to the
* input parameters of the {@link main} method.
*
* This class inherits the {@link Exception} class.
*
* @author	M. Meurisse
*/
class WrongArgsException extends Exception {
	private static final long serialVersionUID = 123456L;
	
	/**
	* This method executes the constructor of its
	* parent class.
	*/
	public WrongArgsException() {
		super();
	}

	/**
	* This method executes the constructor of its
	* parent class with a message.
	*
	* @param message 	the error message, passed
	*					to the constructor of the
	*					parent class
	*/
	public WrongArgsException(String message) {
		super(message);
	}
}
