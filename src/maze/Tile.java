package maze;

import java.io.*;

/**
 * @author Stefan Tatu
 * @version 28.04.2021
 */

public class Tile implements Serializable{

	/**
	 * A Tile class which helps defining
	 * diffrent types of tiles located in 
	 * the Maze
	*/

	private Type type;

	private boolean visited;

	private Tile(Type t){
		/**
		 * Contrsuctor for Tile
		 * setting the tile's type
		 * and making each of the
		 * new declared tile's 
		 * parameter called "visited"
		 * false.
		 * @param t which is a type for a tile.
		*/
		type = t;
		visited = false;
	}

	protected static Tile fromChar(char c){
		/**
		 * Method which transform a given
		 * char into a Tile type, if that
		 * char corresponds to a type of
		 * tile, returning null in the 
		 * other case.
		 * @param c which is a char corresponding to a type of tile.
		 * @return a tile corresponding to the char provided.
		*/

		if (c == 'e'){
			Tile t = new Tile (Type.ENTRANCE);
			return t;
		}
		else if (c == 'x'){
			Tile t = new Tile (Type.EXIT);
			return t;
		}
		else if (c == '#'){
			Tile t = new Tile (Type.WALL);
			return t;
		}
		else if (c == '.'){
			Tile t = new Tile (Type.CORRIDOR);
			return t;
		}
		return null;
	}

	public Type getType(){
		/**
		 * Getter method for the 
		 * type of the tile. 
		 * @return type of a tile.
		*/

		return type;
	}

	public boolean getVisited(){
		/**
		 * Getter method for to 
		 * see if the tile was
		 * visited when trying
		 * to find the route. 
		 * @return is a tile was visited.
		*/

		return visited;
	}

	public void setVisited(){
		/**
		 * Setter method for the 
		 * visited, making it true,
		 * if a tile is visited. 
		*/

		visited = true;
	}

	public boolean isNavigable(){
		/**
		 * Method determining if a
		 * given tile has a type 
		 * which can be navigable.
		 * @return is a tile is navigable.
		*/

		if (type == Type.WALL){
			return false;
		}
		else return true;
	}

	public String toString(){
		/**
		 * Method converting the type
		 * of a tile in a corresponding
		 * char value, making it more
		 * readable for user.
		 * @return a string corresponding to a tile's type.
		*/

		if (type == Type.CORRIDOR){
			return ".";
		}

		else if (type == Type.WALL){
			return "#";
		}
		else if (type == Type.ENTRANCE){
			return "e";
		}
		else if (type == Type.EXIT){
			return "x";
		}
		else return null;
	}

	public enum Type {
		/** 
		 * Enumeration of the four
		 * possible types that a tile
		 * can take
		*/

        CORRIDOR,
        ENTRANCE,
        EXIT,
        WALL
    }
}