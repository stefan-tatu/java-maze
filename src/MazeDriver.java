import maze.Maze;
import maze.routing.RouteFinder;
import maze.routing.NoRouteFoundException;
import maze.InvalidMazeException;

import java.io.*;

/**
 * @author Stefan Tatu
 * @version 29.04.2021
 */

public class MazeDriver {
    /**
     * A MazeDriver class which helps
     * visualising a maze and its solution
     * in console.
    */

    public static void main(String args[]) throws InvalidMazeException, NoRouteFoundException{
        /**
         *A main which returns mazez based from text files
         *by also throwing diffrent exeptions if met
         *and finding and displaying on console
         *the route from entrance to exit.
         * @throws InvalidMazeException,
         * @throws NoRouteFoundException,
         * @throws IOException,
        */


    	try
    	{
    	Maze mz = Maze.fromTxt("/home/csimage/Year1/Java/comp16412-coursework-2_t23839st/resources/mazes/maze2.txt");
    	System.out.println(mz.toString());

        RouteFinder rf = new RouteFinder(mz);

        while (rf.getFinished() == false){
            rf.step();
        }

        System.out.println(rf.toString());

    	}
    	catch (IOException ex)  
    	{
        System.out.println(ex);
    	}
	}
}
