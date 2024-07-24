package maze;

/**
 * @author Stefan Tatu
 * @version 28.04.2021
 */

public class InvalidMazeException extends Exception  {
/**
* This is the InvalidMazeException class which extends Exception.
* It used isued for broader exceptions
*/ 
    public InvalidMazeException(String errorMessage) {
    	/**
		* The constructor sends to the super class the error message
		* @param errorMessage is the exception message.
		*/

        super(errorMessage);
    }
}