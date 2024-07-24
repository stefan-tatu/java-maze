package maze;

/**
 * @author Stefan Tatu
 * @version 28.04.2021
 */

public class MultipleExitException extends InvalidMazeException { 
	/**
	* This is the MultipleExitException class which extends InvalidMazeException.
	* It used isued for exceptions in case more then once exit is found in the maze.
	*/ 
    public MultipleExitException(String errorMessage) {
    	/**
		* The constructor sends to the super class the error message
		* @param errorMessage is the exception message in case.
		*/
        super(errorMessage);
    }
}
