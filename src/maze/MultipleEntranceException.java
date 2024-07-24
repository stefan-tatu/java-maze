package maze;

/**
 * @author Stefan Tatu
 * @version 28.04.2021
 */

public class MultipleEntranceException extends InvalidMazeException { 
	/**
	* This is the MultipleEntranceException class which extends InvalidMazeException.
	* It used isued for exceptions in case more then once entrance is found in the maze.
	*/ 
    public MultipleEntranceException(String errorMessage) {
    	/**
		* The constructor sends to the super class the error message
		* @param errorMessage is the exception message in case.
		*/
        super(errorMessage);
    }
}
