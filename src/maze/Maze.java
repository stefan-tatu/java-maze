package maze;

import java.util.ArrayList;
import java.util.List;
import java.io.*;


/**
 * @author Stefan Tatu
 * @version 28.04.2021
 */

public class Maze implements Serializable{

	/**
	 * A Maze class which helps building mazes,
	 * locating diffrent diffrent tiles in maze,
	 * organising our work into diffrent methods.
	*/

	private Tile entrance;
	private Tile exit;
    private List<List<Tile>> tiles = new ArrayList<>();
	
	private Maze (){
		/**
		 *Emtpy contructor for Maze.
		*/
	}

	public static Maze fromTxt (String cin) throws FileNotFoundException, IOException, InvalidMazeException {
		/**
		 *A method which build mazes from text files
		 *by also throwing diffrent exeptions if met
		 *such as Invalid Charter in the maze, Multiple
		 *Entrances or Exits, No Entrance or Exit,
		 *Rugged Maze.
		 * @param cin is the text input.
		 * @return a Maze object created based on input.
		 * @throws InvalidMazeException,
		 * @throws MultipleEntranceException,
		 * @throws MultipleExitException,
		 * @throws NoExitException,
		 * @throws RaggedMazeException,
		 * @throws NoEntranceException
		*/

		File input = new File(cin);
		Maze mz = new Maze();

		int counter = 0;
		int noOfLines = 0;

		int noExt = 0;
		int noEntry = 0; 

		String verify = ".#ex";

		BufferedReader br = new BufferedReader(new FileReader(input));

		while ((cin = br.readLine()) != null){

			
			mz.tiles.add(new ArrayList<Tile>());
			counter = 0;

			while (counter < cin.length() && cin.charAt(counter) != '\n'){

				if (verify.indexOf(cin.charAt(counter)) == -1){
					throw new InvalidMazeException("An invalid character was found in the maze");
				}

				mz.tiles.get(noOfLines).add(Tile.fromChar(cin.charAt(counter)));

				if (cin.charAt(counter) == 'e') {
					
					if (noEntry> 0){
						throw new MultipleEntranceException("More than one entry found");
					}
					else {
						Tile inT = mz.tiles.get(noOfLines).get(mz.tiles.get(noOfLines).size() - 1);
						if (mz.getTileLocation(inT) == null)
        					throw new InvalidMazeException("This tile is not in the maze");
						mz.setEntrance(inT);
						noEntry ++;}
				}

				else if (cin.charAt(counter) == 'x') {
					
					if (noExt > 0){
						throw new MultipleExitException("More than one exit found");
					}

					else {
						Tile outT = mz.tiles.get(noOfLines).get(mz.tiles.get(noOfLines).size() - 1);
						mz.setExit(outT);
						noExt ++;}
				}

				counter ++;
			}
			if (noOfLines > 0)
				if (mz.tiles.get(noOfLines).size() != mz.tiles.get(noOfLines - 1).size())
					throw new RaggedMazeException("Invalid format of the maze");

			noOfLines ++;
		}

		if (noExt == 0){
			throw new NoExitException("No exit found");
		}

		if (noEntry == 0){
			throw new NoEntranceException("No entrance found");
		}

		return mz;
	}

	public Tile getAdjacentTile(Tile tile, Direction d){
	    /**
         * Method which return the adjacent tile of 
         * a given one, depenging on the direction
         * provided 
         * @param d iwhich is a direction.
         * @param tile which is a tile.
		 * @return a Maze object created based on input.
        */

        Coordinate coord = getTileLocation(tile);
        int xCoord = coord.getX();
        int yCoord = coord.getY();

        if(d == Direction.WEST && xCoord > 0)
        	return tiles.get(tiles.size() - yCoord - 1).get(xCoord - 1);
		else if(d == Direction.EAST && xCoord < tiles.get(0).size() - 1)
				return tiles.get(tiles.size() - yCoord -1).get(xCoord + 1);
			else if(d == Direction.SOUTH && yCoord > 0)
					return tiles.get(tiles.size() - yCoord).get(xCoord);
				else if(d == Direction.NORTH && yCoord < tiles.size() - 1)
					return tiles.get(tiles.size()- yCoord - 2).get(xCoord);
				return null;
    }

	public Tile getEntrance(){
		/**
		 * Getter method for the entrance found
		 * in the maze
		 @return the entrance of the maze.
		*/
        
        return entrance;
    }

    public Tile getExit(){
    	/**
		 * Getter method for the exit found
		 * in the maze
		 * @return the exit of the maze.
		*/
        
        return exit;
    }

    public Tile getTileAtLocation (Coordinate coord){
    	/**
		 * Method which returns a tile
		 * corresponding to given
		 * coordinate provided. If not
		 * coresponding to any, returns
		 * null.
		 * @param coord which is a coordinate.
         * @return a tile to gives coordinate.
		*/

        int xCoord = coord.getX();
        int yCoord = coord.getY();

        if (yCoord < tiles.size() && xCoord < tiles.get(0).size()) {
            return  tiles.get(tiles.size() - yCoord - 1).get(xCoord);
        }
        else
            return null;
    }

    public Coordinate getTileLocation(Tile t) {
    	/**
		 * Method which returns a
		 * coordinate corresponding
		 * to given tile provided. If
		 * not corresponding to any, 
		 * returns null.
		 * @param t which is a tile.
         * @return a tile coordinate.
		*/
        
        for (int k = 0; k < tiles.size(); k++)
        {
            for (int l = 0; l < tiles.get(k).size(); l++) {
                if (tiles.get(k).get(l) == t) {
                    return new Coordinate(l, (tiles.size() - k - 1));
                }
            }
        }
      return null;
    }

    public List<List<Tile>> getTiles(){
        /**
		 * Getter method for the two dimensional
		 * array which contains the tiles found
		 * in the maze.
		 * @return the list of tiles.
		*/

        return tiles;
    }

    private void setEntrance(Tile t) throws InvalidMazeException, IllegalArgumentException{
    	/**
         * Setter method for the entrance,
         * also throwing exceptions in case of
         * multiple entrances or trying to set
         * a tile which is not in the maze as
         * the entrance
         * @param t which is a tile we want to set as entrance.
         * @throws IllegalArgumentException
         * @throws IllegalArgumentException
         */

    	if (entrance != null)
            throw new MultipleEntranceException("More than one entry found");

        else if (getTileLocation(t) == null)
        	throw new IllegalArgumentException("This tile is not in the maze");
    	
    	else entrance = t;
    }

    private void setExit(Tile t) throws InvalidMazeException, IllegalArgumentException{
    	/**
         * Setter method for the exit,
         * also throwing exceptions in case of
         * multiple entrances or trying to set
         * a tile which is not in the maze as
         * the exit
         * @param t which is a tile we want to set as exit.
         * @throws MultipleExitException
         * @throws IllegalArgumentException
         */

    	if (exit != null)
            throw new MultipleExitException("More than one exit found");

        else if (getTileLocation(t) == null)
        	throw new IllegalArgumentException("This tile is not in the maze");
    	
    	else exit = t;
    }

    public String toString() {
    	/**
         * Method converting the Maze into
         * a String, so making our Maze easier
         * to read if displayed.
         * @return a string of converted tiles.
         */

    	String cout = "";

        for  (int k = 0; k < tiles.size(); k++)
        {
            for (int l = 0; l < tiles.get(k).size(); l++)
                cout = cout + tiles.get(k).get(l).toString();
            cout = cout + "\n";
        }

        return cout;
    }

    public class Coordinate {
    	/**
    	 * Nested class which keeps shows
    	 * the coordinates of each tile in
    	 * the maze
    	*/

        private int x, y;

        public Coordinate(int in1, int in2){
            /**
             * Constructor which has two
             * parameters, initialising the two
             * coorinates with the parameters
             * provided
             * @param in1 which is the x coord.
             * @param in2 which is the y coord.
             */

            x = in1;
            y = in2;
        }

        public int getX(){
        	/**
             * Getter method for the x coordinate
             * @return x which is a coordinate.
             */
            return x;
        }

        public int getY(){
        	/**
             * Getter method for the x coordinate
             * @return y which is a coordinate.
             */
            return y;
        }

        @Override
        public String toString(){
        	/** Method converting coordinates
        	 * into a string, making it more
        	 * readable for the user, if needed
        	 * @return a string s of two coordinates of a tile.
        	*/

            String s;
            s = "(" + String.valueOf(x) + ", " + String.valueOf(y) + ")";
            return s;
        }
    }

    public enum Direction {
    	/** 
    	 * Enumeration of the four
		 * direction helpful for
		 * adjacent tiles method in
		 * Maze.
		*/

        NORTH,
        SOUTH,
        EAST,
        WEST
    }
}