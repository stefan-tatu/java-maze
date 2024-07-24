package maze;

/**
 * @author Stefan Tatu
 * @version 28.04.2021
 */

public class NoEntranceException extends InvalidMazeException { 
	/**
	* This is the NoEntranceException class which extends InvalidMazeException.
	* It used isued for exceptions in case no entrance is found in the maze.
	*/ 
    public NoEntranceException(String errorMessage) {
    	/**
		* The constructor sends to the super class the error message
		* @param errorMessage is the exception message in case.
		*/
        super(errorMessage);
    }
}
