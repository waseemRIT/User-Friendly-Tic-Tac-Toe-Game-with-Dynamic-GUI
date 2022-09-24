package com.example.tictactoegame;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class game extends Application {
    // My Initials - always static
    public static final int GUI_WIDTH = 600;
    public static final int GUI_HEIGHT = 600;

    // variables
    public String playerTurn = "X";


    // Player Turn Status
    Label statValue = new Label("The X player Turn");

    // My Cells
    Rectangle[][] cells = new Rectangle[3][3];

    // The Cells Counter -> responsible for checking for winner
    String[][] cellsCounter = new String[3][3];


    // the button
    Button resetBtn = new Button("Reset");

    // Used to Freeze the Game
    public boolean winnerFound = false;


    // GridPane -> the x o field
    GridPane GameGrid = new GridPane();

    @Override
    public void start(Stage primaryStage){

        // To Double-check that the counter is empty already
        resetCounter();


        VBox pane = new VBox(); // the grid of 3/3

        // add paddings
        GameGrid.setPadding(new Insets(60, 0, 50, 0));

        // set cells in center
        GameGrid.setAlignment(Pos.CENTER);

        // My cells
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                Rectangle cell = new Rectangle(100, 100);
                cell.setStroke(Color.BLACK);
                cell.setFill(Color.WHITE);
                GameGrid.add(cell, row, column);
                cells[row][column] = cell;

            }
        }

        // Makes Everything Interactive
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                int finalRow = row;
                int finalColumn = column;
                cells[row][column].setOnMouseClicked(e -> mouseClick(finalRow, finalColumn, GameGrid));
            }
        }


        // button SetUp
        resetBtn.setMaxWidth(85);
        resetBtn.setFont(Font. font("Courier",15));


        resetBtn.setOnAction(e -> resetCounter());


        // Add Status to the VBOX
        pane.getChildren().add(statValue);

        // add the grid to the VBOX
        pane.getChildren().add(GameGrid);

        // add the button
        pane.getChildren().add(resetBtn);


        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, GUI_WIDTH, GUI_HEIGHT);
        primaryStage.setTitle("XO GAME");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void mouseClick(int row, int column, GridPane pane){
        if (!winnerFound) {
            if (playerTurn.equals("X")) {
                pane.add(new XLine(), row, column);
                cellsCounter[row][column] = "X";
                playerTurn = "O";
                statValue.setText("The O player Turn");
                // check for winner
                checkForWinner();
            }
            else  {
                pane.add(new OShape(), row, column);
                playerTurn = "X";
                statValue.setText("The X player Turn");
                cellsCounter[row][column] = "O";
                // check for winner
                checkForWinner();
            }
        }
    }


    public void checkForWinner(){
        checkRowForWinner();
        checkColumnForWinner();
        checkTopLeftToBottomRightForWinner();
        checkTopRightBottomLeftForWinner();
        checkForTie();
    }



    public void checkRowForWinner() {
        for (int row =0; row<3; row++) {
            if (cellsCounter[row][0].equals(cellsCounter[row][1]) &&
                    cellsCounter[row][0].equals(cellsCounter[row][2]) &&
                    !cellsCounter[row][0].isEmpty()){
                //  winner found lol
                if (playerTurn.equals("X")){
                    // winner Found
                    statValue.setText("O player Wins");
                }
                else {
                    // winner Found
                    statValue.setText("X Player Wins");
                }
                winnerFound = true;
                statValue.setTextFill(Color.BLUE);
                return; //  winner found lol
            }
        }
    }


    public void checkColumnForWinner() {
        if (!winnerFound){ // supposed to freeze if there is a winner
            for (int column = 0; column < 3; column++) {
                if (cellsCounter[0][column].equals(cellsCounter[1][column]) &&
                        cellsCounter[0][column].equals(cellsCounter[2][column]) &&
                        !cellsCounter[0][column].isEmpty()){

                    //  winner found lol
                    if (playerTurn.equals("X")){
                        // winner Found
                        statValue.setText("O player Wins");
                    }
                    else {
                        // winner Found
                        statValue.setText("X player Wins");
                    }
                    statValue.setTextFill(Color.BLUE);
                    winnerFound = true;
                    return; //  winner found lol
                }
            }
        }
    }


    public void checkTopLeftToBottomRightForWinner() {

        if(!winnerFound){ // // since I already did some checks
            if(cellsCounter[0][0].equals(cellsCounter[1][1]) &&
                    cellsCounter[0][0].equals(cellsCounter[2][2]) &&
                    !cellsCounter[0][0].isEmpty()){
                //  winner found lol
                if (playerTurn.equals("X")){
                    // winner Found
                    statValue.setText("O player Wins");
                }
                else {
                    // winner Found
                    statValue.setText("X player Wins");
                }
                statValue.setTextFill(Color.BLUE);
                winnerFound = true;
                return; //  winner found lol
            }
        }
    }


    private void checkTopRightBottomLeftForWinner() {
        if(!winnerFound){ // since I already did some checks
            if(cellsCounter[0][2].equals(cellsCounter[1][1]) &&
                    cellsCounter[0][2].equals(cellsCounter[2][0]) &&
                    !cellsCounter[0][2].isEmpty()){
                //  winner found lol
                if (playerTurn.equals("X")){
                    // winner Found
                    statValue.setText("O player Wins");
                }
                else {
                    // winner Found
                    statValue.setText("X player Wins");
                }
                statValue.setTextFill(Color.BLUE);
                winnerFound = true;
                return; //  winner found lol
            }
        }
    }


    private void checkForTie() {
        if (!winnerFound) {
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    if (cellsCounter[row][column].isEmpty()) {
                        return;
                    }
                }
            }
            statValue.setText("No Winner");
            statValue.setTextFill(Color.BLUE);
            winnerFound = true;
            return; //  winner found lol

        }
    }



    public void resetCounter() {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                cellsCounter[row][column] = "";
            }
        }
        GameGrid.getChildren().clear();
        playerTurn = "X"; // GET BACK TO X
        statValue.setText("The X player Turn");
        statValue.setTextFill(Color.BLACK);
        winnerFound = false;

        // ReAdd cells
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                Rectangle cell = new Rectangle(100, 100);
                cell.setStroke(Color.BLACK);
                cell.setFill(Color.WHITE);
                GameGrid.add(cell, row, column);
                cells[row][column] = cell;
            }
        }


        // Makes Everything Interactive
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                int finalRow = row;
                int finalColumn = column;
                cells[row][column].setOnMouseClicked(e -> mouseClick(finalRow, finalColumn, GameGrid));
            }
        }
    }

    class XLine extends StackPane { //
        public XLine() {
            Line line1 = new Line(20, 20, 20, 20);
            line1.endXProperty().bind(widthProperty().subtract(20));
            line1.endYProperty().bind(heightProperty().subtract(20));
            getChildren().add(line1);

            Line line2 = new Line(20, 20, 20, 20);
            line2.startXProperty().bind(widthProperty().subtract(20));
            line2.endYProperty().bind(heightProperty().subtract(20));
            getChildren().add(line2);
        }
    }

    class OShape extends StackPane{
        public OShape(){
            Circle circle = new Circle(40);
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.WHITE);
            getChildren().add(circle);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
