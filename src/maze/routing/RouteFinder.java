package maze.routing;

import maze.*;
import maze.Maze;
import maze.Maze.Coordinate;
import maze.Maze.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;
import java.io.*;

/**
 * @author Stefan Tatu
 * @version 29.04.2021
 */

public class RouteFinder implements Serializable{

	/**
	 * This is a class which help finding
	 * a route for a maze, from the
	 * entrance to exit.
	*/


	private Maze maze;
	private Stack<Tile> route;
	private boolean finished;

	public RouteFinder(Maze mz){
		/**
		 * This a constructor method for RouteFinder
		 * which initialise the stack needed for the 
		 * route, as well as pushing the entrance 
		 * of a maze in the stack, initialising the
		 * maze object as well.
		 * @param mz which is a given maze object
		 * @throws NoRouteFoundException
		*/


		maze = mz;

		finished = false;

		route = new Stack<Tile>();

		route.push(maze.getEntrance());

	}


	public Maze getMaze(){
		/**
		 * A getter method which return the maze
		 * object of route finder.
		 * @return maze which is a maze object
		*/

		return maze;
	}

	public boolean getFinished(){
		/**
		 * A getter method which returns if
		 * the routing is done for the given maze.
		 * @return finished which represent is the routing is done
		*/

		return finished;
	}


	public List<Tile> getRoute(){
		/** 
		* A getter method which returns 
		* a lsit of Tiles which represent
		* the route for resolving the maze.
		* @return path which is a List of Tiles.
		*/

		List<Tile> path = new ArrayList<>();
		Tile t; 

		while (route.size() != 0){
			t = route.pop();
			path.add(t);
		}

		Collections.reverse(path);

		for (int i=0; i< path.size(); i++)
			route.push(path.get(i));
		return path;

	}


	public boolean isFinished(){
		/**
		* A boolean method which returns true
		* if the routing for resolving is done
		* @return true.
		*/

		finished = true;
		return true;
	}

	public void save(String nameOfFile){
		/**
		* A save method which saves the RouteFinder
		* object using Serializable
		* @param nameOfFile which is the name under the file will be saved
		* @throws IOException
		*/

		ObjectOutputStream objStream = null;
        try {
            objStream = new ObjectOutputStream(new FileOutputStream(nameOfFile));
            objStream.writeObject(this);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find " + nameOfFile);
        } catch (IOException e) {
            System.out.println("Error: There was an IOException which appeared when trying to write "+ nameOfFile);
        } finally {
            try {
                objStream.close();
            } catch (IOException e) {
                System.out.println("Error: There was an IOException which appeared when trying to close "+ nameOfFile);
            }
        }
    }


	public static RouteFinder load(String nameOfFile){
		/**
		* A load method which loads the RouteFinder
		* object from a text file using Serializable, 
		* the name of the file being the parameter
		* @param nameOfFile which is the name under the file can be found
		* @throws IOException
		*/

		ObjectInputStream objStream = null;
        try {
            objStream = new ObjectInputStream(new FileInputStream(nameOfFile));
            return (RouteFinder)objStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find " + nameOfFile);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: There was an IOException which appeared when trying to read "+ nameOfFile);
        } finally {
            try {
                objStream.close();
            } catch (IOException e) {
                System.out.println("Error: There was an IOException which appeared when trying to close "+ nameOfFile);
            }
        }
        return null;
	}


	public boolean step() throws NoRouteFoundException{
		/**
		* A method which resolve the maze,
		* making one move at a time
		* updating the stack accordingly
		* returning true if the routing is done
		* @throws NoRouteFoundException
		*/
		 
		Tile top = route.peek();

		top.setVisited();

		if(route.size() == 0){
            throw new NoRouteFoundException("No path found between entry and exit");
        }

		if (top.equals(maze.getExit())){
			return isFinished();
		}
		else{
			Tile tileN = maze.getAdjacentTile(top, Direction.NORTH);
			Tile tileS = maze.getAdjacentTile(top, Direction.SOUTH);
			Tile tileE = maze.getAdjacentTile(top, Direction.EAST);
			Tile tileW = maze.getAdjacentTile(top, Direction.WEST);



			if (tileN != null && tileN.isNavigable() && !tileN.getVisited()){
				route.push(tileN);
				return false;
			}
			else if (tileS != null && tileS.isNavigable() && !tileS.getVisited()){
				route.push(tileS);
				return false;
			}
			if (tileE != null && tileE.isNavigable() && !tileE.getVisited()){
				route.push(tileE);
				return false;
			}
			if (tileW != null && tileW.isNavigable() && !tileW.getVisited()){
				route.push(tileW);
				return false;
			}
		}
		
		route.pop();

		return false;
	}


	public String toString() {
		/**
		* A method which rhelps visualising
		* the partial or fully resolved maze
		* @return cout which is a string showing the partial/fully resolved maze.
		*/

    	String cout = "";
    	List<Tile> path = getRoute();

        for  (int k = 0; k < getMaze().getTiles().size(); k++)
        {
            for (int l = 0; l < getMaze().getTiles().get(k).size(); l++)
            	if ( (!path.contains(getMaze().getTiles().get(k).get(l)) && getMaze().getTiles().get(k).get(l).getVisited() == false )|| getMaze().getTiles().get(k).get(l) == getMaze().getExit()|| getMaze().getTiles().get(k).get(l) == getMaze().getEntrance())
                cout = cout + getMaze().getTiles().get(k).get(l).toString();
            	else if (path.contains(getMaze().getTiles().get(k).get(l)))
            		cout = cout + "-";
            	else cout = cout + "n";
            cout = cout + "\n";
        }


        return cout;
    }

}
