package maze.visualisation;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import maze.*;
import maze.Maze;
import maze.routing.RouteFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;
import java.io.*;

/**
 * @author Stefan Tatu
 * @version 29.04.2021
 */

public class CreateGrid {

	/**
	 * A Class helping building
	 * the visualisation by creating a gris
	 * based on the dimensions of the maze.
	*/

	private RouteFinder rf;

	public CreateGrid(RouteFinder obj){
		/**
		*A constructor which initialise
		* the RoutFinder attribute
		* of the class
		* @param obj which is a RouteFinder
		*/
		rf = obj;
		
	} 

	public GridPane getGrid(){
		/**
		* A method which build and return
		* A grid based on the dimeansions of 
		* a maze in the attribute RouteFinder.
		* @return a grid which is the maze.
		*/
		
		GridPane grd = new GridPane ();

		List<List<Tile>> t = rf.getMaze().getTiles();
		List<Tile> route = rf.getRoute();

		for (int i = 0; i< t.size(); i++){
			for (int j = 0; j < t.get(i).size(); j++){

				int size1 = 450/t.size();
								
			    Tile tile = t.get(i).get(j);

			    List<Tile> path = rf.getRoute();

		        if (tile.toString().equals("e")){
		        	Rectangle rec = new Rectangle();
		        	rec.setFill(Color.TOMATO);
		        	rec.setWidth(size1);
		        	rec.setHeight(size1);
		        	GridPane.setRowIndex(rec, i);
		        	GridPane.setColumnIndex(rec, j);
		        	grd.getChildren().addAll(rec);
		       		grd.setHgap(size1/10);
		        	grd.setVgap(size1/10);
		        }
		        else if (tile.toString().equals("x")){
		        	Rectangle rec = new Rectangle();
		        	rec.setFill(Color.TEAL);
		        	rec.setWidth(size1);
		        	rec.setHeight(size1);
		        	GridPane.setRowIndex(rec, i);
		        	GridPane.setColumnIndex(rec, j);
		        	grd.getChildren().addAll(rec);
		       		grd.setHgap(size1/10);
		        	grd.setVgap(size1/10);
		        }
		        else if (tile.toString().equals(".")){
		        	Rectangle rec = new Rectangle();
		        	
		        		        	
		        	rec.setFill(Color.LEMONCHIFFON);
					if (path.contains(t.get(i).get(j))){
		        		rec.setFill(Color.TAN);
		        	}
		        	else if (t.get(i).get(j).getVisited() == true){
		        		rec.setFill(Color.LIGHTSLATEGREY);
		        	}
		        	rec.setWidth(size1);
		        	rec.setHeight(size1);
		        	GridPane.setRowIndex(rec, i);
		        	GridPane.setColumnIndex(rec, j);
		        	grd.getChildren().addAll(rec);
		       		grd.setHgap(size1/10);
		        	grd.setVgap(size1/10);
		        }
		        else{
		        	Rectangle rec = new Rectangle();
		        	rec.setWidth(size1);
		        	rec.setFill(Color.DIMGREY);		        	
		        	rec.setHeight(size1);
		        	GridPane.setRowIndex(rec, i);
		        	GridPane.setColumnIndex(rec, j);
		        	grd.getChildren().addAll(rec);
		       		grd.setHgap(size1/10);
		        	grd.setVgap(size1/10);
		        }

		        
			}
		}

		grd.setAlignment(Pos.CENTER);
		return grd;
	}



	public int getX(){
		/**
		* A getter method which helps building the scene
		* based on the dimension of it.
		* @return an int to know the dimensions of the grid.
		*/
		List<List<Tile>> t = rf.getMaze().getTiles();
		return 450 + (450/t.size()/10*t.size());
	}

	public int getY(){
		/**
		* A getter method which helps building the scene
		* based on the dimension of it.
		* @return an int to know the dimensions of the grid.
		*/
		List<List<Tile>> t = rf.getMaze().getTiles();
		return t.get(t.size()-1).size()*(450/t.size() + 450/t.size()/10);
	}



}