package maze.routing;

/**
 * @author Stefan Tatu
 * @version 28.04.2021
 */

public class NoRouteFoundException extends Exception  { 
	/**
	* This is the NoRouteFoundException class which extends Exception.
	* It used isued in case no route was found in the maze
	*/ 
    public NoRouteFoundException(String errorMessage) {
    	/**
		* The constructor sends to the super class the error message
		* @param errorMessage is the exception message in case.
		*/
        super(errorMessage);
    }
}
