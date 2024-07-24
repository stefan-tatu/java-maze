import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.*;
import javafx.scene.layout.Background.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import maze.Maze;
import maze.Tile;
import maze.routing.RouteFinder;
import maze.routing.NoRouteFoundException;
import maze.InvalidMazeException;
import maze.visualisation.CreateGrid;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;
import java.io.*;


import java.io.*;

/**
 * @author Stefan Tatu
 * @version 28.04.2021
 */

public class MazeApplication extends Application {

/**
* This is a class which helps visualising Mazes
* using JavaFX.
*/

    GridPane initMaze;
    HBox cont;
    HBox menu;
    VBox root;
    Scene new_scene;
    RouteFinder rf;
    CreateGrid repr;
    Maze mz;

    @Override
    public void start(Stage s){

        /**
        * This is a method which builds
        * the diffrent stages we needed
        * to create and visualise a maze,
        * as well as to resolve it
        * @param s which is a stage
        * @throws NoRouteFoundException
        * @throws IOException
        * @throws InvalidMazeException
        */

        s.setTitle("Maze Application");

        GridPane textFile = new GridPane();
        textFile.setPadding(new Insets(15));
        textFile.setHgap(5);
        textFile.setVgap(5);
        textFile.setAlignment(Pos.CENTER);

        Scene input = new Scene(textFile, 300, 100);

        textFile.add(new Label("Enter the file name:"), 0, 0);
        TextField textPath = new TextField();
        textFile.add(textPath, 0, 1);
        Button pathEnter = new Button("Load Map");
        textFile.add(pathEnter, 1, 1);

        pathEnter.setOnAction (e -> {
            try{
            String path = String.valueOf(textPath.getText());
            
            	try{

                mz = Maze.fromTxt(path);
    			
    			rf = new RouteFinder(mz);
                repr = new CreateGrid(rf);
    			initMaze = repr.getGrid();
                initMaze.setAlignment(Pos.CENTER);

                cont = new HBox(0, initMaze);
                cont.setBackground(new Background(new BackgroundFill(Color.OLDLACE, CornerRadii.EMPTY, Insets.EMPTY)));  
                GridPane grid = new GridPane ();
                Button stepButton = new Button("Step");
                Button saveButton = new Button("Save Route");

                stepButton.setPrefHeight(50);
                stepButton.setPrefWidth(200);

                saveButton.setPrefHeight(25);
                saveButton.setPrefWidth(120);

                grid.add(stepButton, 10, 70, 10, 10);
                grid.add(saveButton, 30, 10, 10, 10);

                TextField textPathSave = new TextField("save.obj");
                grid.add(textPathSave, 10, 10, 10, 10);

                Button loadButton = new Button("Load Route");

                loadButton.setPrefHeight(25);
                loadButton.setPrefWidth(120);

                grid.add(loadButton, 30, 30, 10, 10);

                Button loadMapButton = new Button("Load New Map");

                loadMapButton.setPrefHeight(25);
                loadMapButton.setPrefWidth(120);

                grid.add(loadMapButton, 30, 50, 10, 10);

                TextField textPathLoad = new TextField("load_route.obj");
                grid.add(textPathLoad, 10, 30, 10, 10);

                TextField textMapLoad = new TextField("load_map.txt");
                grid.add(textMapLoad, 10, 50, 10, 10);

                cont.setAlignment(Pos.CENTER);

                menu = new HBox(50, grid);

                menu.setSpacing(10);

                root = new VBox(20, menu, cont);

                menu.setAlignment(Pos.CENTER);

                root.setAlignment(Pos.CENTER);

                new_scene = new Scene(root, repr.getY()+30, repr.getX()+180);

    			s.setScene(new_scene);

    			s.show();

                stepButton.setOnAction (c -> {
                    if (rf.getFinished() == false){
                        try{
                        rf.step();
                        initMaze = repr.getGrid();
                        initMaze.setAlignment(Pos.CENTER);
                        menu = new HBox(50, grid);
                        cont = new HBox(0, initMaze);
                        cont.setAlignment(Pos.CENTER);
                        menu.setAlignment(Pos.CENTER);
                        cont.setBackground(new Background(new BackgroundFill(Color.OLDLACE, CornerRadii.EMPTY, Insets.EMPTY)));
                        root = new VBox(20, menu, cont);
                        root.setAlignment(Pos.CENTER);
                        new_scene = new Scene(root, repr.getY()+30, repr.getX()+180);
                        s.setScene(new_scene);
                        s.show();
                        }
                        catch (NoRouteFoundException a){}
                    }
                });
    		      

                saveButton.setOnAction (f -> {
                    String path2 = String.valueOf(textPathSave.getText());
                    if (path2!=null)
                        rf.save(path2);
                });

                loadButton.setOnAction (f -> {
                    String path3 = String.valueOf(textPathLoad.getText());
                    
                        rf = rf.load(path3);
                        repr = new CreateGrid(rf);
                        initMaze = repr.getGrid();
                        initMaze.setAlignment(Pos.CENTER);
                        menu = new HBox(50, grid);
                        cont = new HBox(0, initMaze);
                        cont.setAlignment(Pos.CENTER);
                        menu.setAlignment(Pos.CENTER);
                        cont.setBackground(new Background(new BackgroundFill(Color.OLDLACE, CornerRadii.EMPTY, Insets.EMPTY)));
                        root = new VBox(20, menu, cont);
                        root.setAlignment(Pos.CENTER);
                        new_scene = new Scene(root, repr.getY()+30, repr.getX()+180);
                        s.setScene(new_scene);
                        s.show();
        
                });

                loadMapButton.setOnAction (g -> {
                    String path4 = String.valueOf(textMapLoad.getText());
                        try{
                        mz = Maze.fromTxt(path4);
                        rf = new RouteFinder(mz);
                        repr = new CreateGrid(rf);
                        initMaze = repr.getGrid();
                        initMaze.setAlignment(Pos.CENTER);
                        menu = new HBox(50, grid);
                        cont = new HBox(0, initMaze);
                        cont.setAlignment(Pos.CENTER);
                        menu.setAlignment(Pos.CENTER);
                        cont.setBackground(new Background(new BackgroundFill(Color.OLDLACE, CornerRadii.EMPTY, Insets.EMPTY)));
                        root = new VBox(20, menu, cont);
                        root.setAlignment(Pos.CENTER);
                        new_scene = new Scene(root, repr.getY()+30, repr.getX()+180);
                        s.setScene(new_scene);
                        s.show();
                    }
                    catch (InvalidMazeException a){}
                    catch (IOException ex){
            
                    System.out.println(ex);
                    }
        
                });

    			}
    			catch (InvalidMazeException a){}
                

    		}
    		catch (IOException ex){
    		
       		   System.out.println(ex);
    		}
    	
    			
            
            
            
        });

        s.setScene(input);

        s.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}