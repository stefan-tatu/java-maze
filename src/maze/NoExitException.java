package maze;

/**
 * @author Stefan Tatu
 * @version 28.04.2021
 */

public class NoExitException extends InvalidMazeException { 
	/**
	* This is the NoExitException class which extends InvalidMazeException.
	* It used isued for exceptions in case no exit is found in the maze.
	*/ 
    public NoExitException(String errorMessage) {
    	/**
		* The constructor sends to the super class the error message
		* @param errorMessage is the exception message in case.
		*/
        super(errorMessage);
    }
}
