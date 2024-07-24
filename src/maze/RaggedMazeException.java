package maze;

/**
 * @author Stefan Tatu
 * @version 28.04.2021
 */

public class RaggedMazeException extends InvalidMazeException {
	/**
	* This is the RaggedMazeException class which extends InvalidMazeException.
	* It used isued for exceptions in case the format of the maze is not uniform.
	*/  
    public RaggedMazeException(String errorMessage) {
    	/**
		* The constructor sends to the super class the error message
		* @param errorMessage is the exception message in case.
		*/
        super(errorMessage);
    }
}
