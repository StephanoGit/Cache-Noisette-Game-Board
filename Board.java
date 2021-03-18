import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

/**
 * This class creates the board of the game c and implements it's mechanics.
 * @author Stefan Popovici
 */
public class Board implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JPanel board;

    private JFrame selectionFrame;
    private JPanel selectionPanel;
    private JPanel selectionBoard;

    private JButton selectionButtons [];
    private JButton moveCounter;

    private Square matrixSquare [][];
    private Hole arrayHoles [];
    private Squirrel arraySquirrels [];
    private Flower arrayFlowers [];

    private Squirrel clickedSquirrel = new Squirrel();

    private Picture arrowUpP =  new Picture("BigArrow.png", 0);
    private JButton arrowUp = new JButton(arrowUpP);

    private Picture arrowDownP =  new Picture("BigArrow.png", 180);
    private JButton arrowDown = new JButton(arrowDownP);

    private Picture arrowLeftP =  new Picture("Arrow.png", 270);
    private JButton arrowLeft = new JButton(arrowLeftP);

    private Picture arrowRightP =  new Picture("Arrow.png", 90);
    private JButton arrowRight = new JButton(arrowRightP);

    private Picture emptySquare = new Picture("Empty.png", 0);
    private Picture holeNut = new Picture("HoleNut.png", 0);

    private int counter = 0;        //for number of moves
    private int nutHeads = 4;       //if equal to 0, the level is won

    /**
     * This is the constructor that creates a JFrame where are added
     * the 4x4 board and the directional arrows.
     */
    public Board() {
        //create the frame
        frame = new JFrame();
        frame.setSize(601, 601);                                //set size of the frame

        //let the program know when on of the directional buttons is pressed
        arrowUp.addActionListener(this);
        arrowRight.addActionListener(this);
        arrowDown.addActionListener(this);
        arrowLeft.addActionListener(this);

        //create the panel of the board
        board = new JPanel();
        board.setLayout(new GridLayout(4, 4, 1, 1));            //set the layout of the board, it's size and spacing

        matrixSquare = new Square[4][4];

        //generate a 4x4 board that has as icons empty squares - no holes yet
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {       
                matrixSquare[i][j] = new Square(emptySquare,"empty",true,-1);
                matrixSquare[i][j].getButton().addActionListener(this);
                board.add(matrixSquare[i][j].getButton());
            }
        }    

        selectionTab();         //open the level selection Tab

        selectLevel("Level 1"); //start game with level 1

        //create the bigger panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());    //set the layout of the bigger panel where the board and the directional buttons are going to be placed in

        //set no boarders to the directional buttons
        arrowUp.setBorder(null);
        arrowRight.setBorder(null);
        arrowDown.setBorder(null);
        arrowLeft.setBorder(null);

        //arrange where the buttons and the board panel are placed in the bigger panel
        panel.add("North", arrowUp);
        panel.add("East", arrowRight);
        panel.add("South", arrowDown);
        panel.add("West", arrowLeft);
        panel.add("Center", board);

        frame.setResizable(false);                              //don't let the player to rezise the window of the game - avoids responsizeness problems
        frame.setContentPane(panel);                            //add the bigger panel to the frame 
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //close the JFrame after the players has exited the game

    }

    /**
     * This method creates a new JFrame from which the player is able to select the levels
     * and see the number of moves he/she has done.
     */
    public void selectionTab(){
        //create the frame
        selectionFrame = new JFrame();
        selectionFrame.setTitle("Select Level!");       //set title
        selectionFrame.setSize(200, 400);               //set size

        //create the bigger panel
        selectionBoard = new JPanel();
        selectionBoard.setLayout(new GridLayout(3, 2));     //set layout

        selectionButtons = new JButton[6];

        //create the buttons for levels
        for (int i = 0; i < 6; i++){
            selectionButtons[i] = new JButton("Level " + (i+1));
            selectionButtons[i].addActionListener(this);
            selectionBoard.add(selectionButtons[i]);
        }

        //create the move counter display
        moveCounter = new JButton();
        moveCounter.setText("Moves: " + counter);

        //create the bigger panel
        selectionPanel = new JPanel();
        selectionPanel.setLayout(new BorderLayout());

        //arrange the smaller panels in the bigger one
        selectionPanel.add("Center", selectionBoard);
        selectionPanel.add("South", moveCounter);

        selectionFrame.setResizable(false);
        selectionFrame.setContentPane(selectionPanel);
        selectionFrame.setVisible(true);
        selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * This method creates and array of Holes.
     * Each hole object has a picture, position x and y in the board matrix and type (hole or nutHole)
     */
    public void addHolesToBoard(){
        arrayHoles = null;              //reset the array of the Holes whene selecting a new level
        arrayHoles = new Hole[4];

        Picture holeSquare = new Picture("Hole.png", 0);
        arrayHoles[0] = new Hole(holeSquare, 0, 2, "hole");
        arrayHoles[1] = new Hole(holeSquare, 1, 0, "hole");
        arrayHoles[2] = new Hole(holeSquare, 2, 1, "hole");
        arrayHoles[3] = new Hole(holeSquare, 3, 3, "hole");
    }

    /**
     * This method sets the postions of squirrels and flowers on the board.
     * The positions, rotations and colour of the squirrels are different for every level.
     * @param selectedLevel the level selected determins the positions.
     */
    public void selectLevel(String selectedLevel){
        ResetBoard();

        counter = 0;        //reset counter
        moveCounter.setText("Moves: " + counter);

        arraySquirrels = null;              //reset the arrays when choosing a new level
        arrayFlowers = null;

        frame.setTitle("Cache Noisette! - " + selectedLevel);    //change the title of the JFrame

        addHolesToBoard();

        if (selectedLevel == "Level 1"){
            nutHeads = 2;                          //set the number of nutHeads the level has

            //create the squirrels and set their location
            arraySquirrels = new Squirrel[2]; 
            Picture redSquirrelHeadNut = new Picture("RedSquirrel1Nut.png", 270);
            Picture redSquirrelTail = new Picture("RedSquirrel2.png", 270);
            arraySquirrels[0] = new Squirrel(redSquirrelHeadNut, redSquirrelTail, 1, 1, 1, 2, "red", "headNut");

            Picture greySquirrelHeadNut = new Picture("GreySquirrel1Nut.png", 0);
            Picture greySquirrelTail = new Picture("GreySquirrel2.png", 0);
            arraySquirrels[1] = new Squirrel(greySquirrelHeadNut, greySquirrelTail, 2, 2, 3, 2, "grey", "headNut");

            //create the flower/flowers and set it'/their location
            arrayFlowers = new Flower[1];
            Picture flowerP =  new Picture("Flower.png", 0);
            arrayFlowers[0] = new Flower(flowerP, 2, 1, "single");
        }
        else if (selectedLevel == "Level 2"){
            nutHeads = 2;
    
            arraySquirrels = new Squirrel[2];
            Picture squirrelFlower = new Picture("SquirrelFlower.png", 0);
    
            Picture brownSquirrelHeadNut = new Picture("BrownSquirrel1Nut.png", 0);
            Picture brownSquirrelTail = new Picture("BrownSquirrel2.png", 0);
            arraySquirrels[0] = new Squirrel(brownSquirrelHeadNut, brownSquirrelTail, squirrelFlower, 2, 0, 3, 0, 2, 1, "brown", "headNut");
    
            Picture blackSquirrelHeadNut = new Picture("BlackSquirrel1Nut.png", 180);
            Picture blackSquirrelTail = new Picture("BlackSquirrel2.png", 180);
            arraySquirrels[1] = new Squirrel(blackSquirrelHeadNut, blackSquirrelTail, squirrelFlower, 2, 3, 1, 3, 1, 2, "black", "headNut");
    
            arrayFlowers = new Flower[1];
            Picture flowerP =  new Picture("Flower.png", 0);
            arrayFlowers[0] = new Flower(flowerP, 3, 3, "single");
        }
        else if (selectedLevel == "Level 3"){
            nutHeads = 4;

            arraySquirrels = new Squirrel[4];
            Picture squirrelFlower = new Picture("SquirrelFlower.png", 0);

            Picture redSquirrelHeadNut = new Picture("RedSquirrel1Nut.png", 270);
            Picture redSquirrelTail = new Picture("RedSquirrel2.png", 270);
            arraySquirrels[0] = new Squirrel(redSquirrelHeadNut, redSquirrelTail, 2, 0, 2, 1, "red", "headNut");

            Picture greySquirrelHeadNut = new Picture("GreySquirrel1Nut.png", 180);
            Picture greySquirrelTail = new Picture("GreySquirrel2.png", 180);
            arraySquirrels[1] = new Squirrel(greySquirrelHeadNut, greySquirrelTail, 2, 3, 1, 3, "grey", "headNut");

            Picture brownSquirrelHeadNut = new Picture("BrownSquirrel1Nut.png", 180);
            Picture brownSquirrelTail = new Picture("BrownSquirrel2.png", 180);
            arraySquirrels[2] = new Squirrel(brownSquirrelHeadNut, brownSquirrelTail, squirrelFlower, 3, 2, 2, 2, 3, 1, "brown", "headNut");

            Picture blackSquirrelHeadNut = new Picture("BlackSquirrel1Nut.png", 180);
            Picture blackSquirrelTail = new Picture("BlackSquirrel2.png", 180);
            arraySquirrels[3] = new Squirrel(blackSquirrelHeadNut, blackSquirrelTail, squirrelFlower, 1, 2, 0, 2, 0, 1, "black", "headNut");

        }
        else if (selectedLevel == "Level 4"){
            nutHeads = 3;

            arraySquirrels = new Squirrel[3];
            Picture squirrelFlower = new Picture("SquirrelFlower.png", 0);

            Picture redSquirrelHeadNut = new Picture("RedSquirrel1Nut.png", 270);
            Picture redSquirrelTail = new Picture("RedSquirrel2.png", 270);
            arraySquirrels[0] = new Squirrel(redSquirrelHeadNut, redSquirrelTail, 2, 0, 2, 1, "red", "headNut");

            Picture brownSquirrelHeadNut = new Picture("BrownSquirrel1Nut.png", 0);
            Picture brownSquirrelTail = new Picture("BrownSquirrel2.png", 0);
            arraySquirrels[1] = new Squirrel(brownSquirrelHeadNut, brownSquirrelTail, squirrelFlower, 0, 0, 1, 0, 0, 1, "brown", "headNut");

            Picture blackSquirrelHeadNut = new Picture("BlackSquirrel1Nut.png", 270);
            Picture blackSquirrelTail = new Picture("BlackSquirrel2.png", 270);
            arraySquirrels[2] = new Squirrel(blackSquirrelHeadNut, blackSquirrelTail, squirrelFlower, 3, 1, 3, 2, 2, 2, "black", "headNut");

            arrayFlowers = new Flower[1];
            Picture flowerP =  new Picture("Flower.png", 0);
            arrayFlowers[0] = new Flower(flowerP, 3, 3, "single");
        }
        else if (selectedLevel == "Level 5"){
            nutHeads = 3;

            arraySquirrels = new Squirrel[3];
            Picture squirrelFlower = new Picture("SquirrelFlower.png", 0);

            Picture redSquirrelHeadNut = new Picture("RedSquirrel1Nut.png", 90);
            Picture redSquirrelTail = new Picture("RedSquirrel2.png", 90);
            arraySquirrels[0] = new Squirrel(redSquirrelHeadNut, redSquirrelTail, 0, 3, 0, 2, "red", "headNut");

            Picture brownSquirrelHeadNut = new Picture("BrownSquirrel1Nut.png", 90);
            Picture brownSquirrelTail = new Picture("BrownSquirrel2.png", 90);
            arraySquirrels[1] = new Squirrel(brownSquirrelHeadNut, brownSquirrelTail, squirrelFlower, 1, 3, 1, 2, 2, 3, "brown", "headNut");

            Picture blackSquirrelHeadNut = new Picture("BlackSquirrel1Nut.png", 90);
            Picture blackSquirrelTail = new Picture("BlackSquirrel2.png", 90);
            arraySquirrels[2] = new Squirrel(blackSquirrelHeadNut, blackSquirrelTail, squirrelFlower, 2, 2, 2, 1, 3, 1, "black", "headNut");

        }
        else if (selectedLevel == "Level 6"){
            nutHeads = 3;

            arraySquirrels = new Squirrel[3];
            Picture squirrelFlower = new Picture("SquirrelFlower.png", 0);

            Picture greySquirrelHeadNut = new Picture("GreySquirrel1Nut.png", 180);
            Picture greySquirrelTail = new Picture("GreySquirrel2.png", 180);
            arraySquirrels[0] = new Squirrel(greySquirrelHeadNut, greySquirrelTail, 1, 3, 0, 3, "grey", "headNut");

            Picture brownSquirrelHeadNut = new Picture("BrownSquirrel1Nut.png", 90);
            Picture brownSquirrelTail = new Picture("BrownSquirrel2.png", 90);
            arraySquirrels[1] = new Squirrel(brownSquirrelHeadNut, brownSquirrelTail, squirrelFlower, 1, 2, 1, 1, 2, 2, "brown", "headNut");

            Picture blackSquirrelHeadNut = new Picture("BlackSquirrel1Nut.png", 180);
            Picture blackSquirrelTail = new Picture("BlackSquirrel2.png", 180);
            arraySquirrels[2] = new Squirrel(blackSquirrelHeadNut, blackSquirrelTail, squirrelFlower, 3, 1, 2, 1, 2, 0, "black", "headNut");

            arrayFlowers = new Flower[1];
            Picture flowerP =  new Picture("Flower.png", 0);
            arrayFlowers[0] = new Flower(flowerP, 3, 3, "single");
        }
        addObstacles();     //add the squirrels/flowers/holes to the board
    }

    /**
     * Add the squirrels/flowers/holes to the board on their location on the matrix of squares
     */
    public void addObstacles(){

        //holes
        for( int i = 0 ; i < arrayHoles.length ; i++ ){
            matrixSquare[arrayHoles[i].getX()][arrayHoles[i].getY()].setAttributesSquare(arrayHoles[i].getHoleP(), arrayHoles[i].getType(), arrayHoles[i].isAvailable(), -1);        
        }

        //flowers
        if (arrayFlowers != null){
            for( int i = 0 ; i < arrayFlowers.length ; i++ ){
                matrixSquare[arrayFlowers[i].getX()][arrayFlowers[i].getY()].setAttributesSquare(arrayFlowers[i].getFlower(), arrayFlowers[i].getType(), arrayFlowers[i].isAvailable(), -1);
            }
        }

        //squirrels
        for( int i = 0 ; i < arraySquirrels.length ; i++ ){
            //squirrel flowers
            if(arraySquirrels[i].getFlower() != null){
                matrixSquare[arraySquirrels[i].getFlowerX()][arraySquirrels[i].getFlowerY()].setAttributesSquare(arraySquirrels[i].getFlower(), "squirrelFlower", false, i);
            }
            //head
            matrixSquare[arraySquirrels[i].getHeadX()][arraySquirrels[i].getHeadY()].setAttributesSquare(arraySquirrels[i].getHead(), arraySquirrels[i].getHeadType(), false, i);
            //tail
            matrixSquare[arraySquirrels[i].getTailX()][arraySquirrels[i].getTailY()].setAttributesSquare(arraySquirrels[i].getTail(),"tail",false,i); 
        }
    }

    /**
     * Set the icons of the board buttons to empty squares
     * and make them available for movement.
     */
    public void ResetBoard(){
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {       
                matrixSquare[i][j].getButton().setIcon(emptySquare);
                matrixSquare[i][j].setAvailable(true);
            }
        }
    }

    /**
     * Change the head of a squirrel from a nutHead to a simple head
     * and decrement the the nutHead counter.
     */
    public void changeSquirrelHead(){
        if(clickedSquirrel.getType() == "red")
            clickedSquirrel.setHead(new Picture("RedSquirrel1.png", clickedSquirrel.getHead().getRotation()));

        if(clickedSquirrel.getType() == "grey")
            clickedSquirrel.setHead(new Picture("GreySquirrel1.png", clickedSquirrel.getHead().getRotation()));

        if(clickedSquirrel.getType() == "black")
            clickedSquirrel.setHead(new Picture("BlackSquirrel1.png", clickedSquirrel.getHead().getRotation()));

        if(clickedSquirrel.getType() == "brown")
            clickedSquirrel.setHead(new Picture("BrownSquirrel1.png", clickedSquirrel.getHead().getRotation()));

        clickedSquirrel.setHeadType("head");
        nutHeads--;
    }

    /**
     * Check when a nutHead is above a hole and change the picture
     * of the hole to a holeNut.
     */
    public void checkHole(){
        for(int i = 0; i < arrayHoles.length; i++){
            if (clickedSquirrel.getHeadType() == "headNut" && 
                arrayHoles[i].getX() == clickedSquirrel.getHeadX() && arrayHoles[i].getY() == clickedSquirrel.getHeadY() &&
                arrayHoles[i].getType() == "hole"){
                    changeSquirrelHead();
                    arrayHoles[i].setHoleP(holeNut);
                    arrayHoles[i].setType("holeNut");
            }
        }
    }

    /**
     * Check if the movement of the squirrel the player chooses is valid.
     * @param clickedSquirrel the choosen squirrel.
     * @param e the direction up/down/left/right.
     * @param rotation the rotation of the pictures in degrees of the respective squirrel.
     * @return true if valid/false if not.
     */
    public boolean validMove(Squirrel clickedSquirrel, ActionEvent e){

        int rotation = clickedSquirrel.getHead().getRotation();

        if (rotation == 0){
            if (e.getSource() == arrowUp){
                if (clickedSquirrel.getType() == "black" || clickedSquirrel.getType() == "brown"){
                    if ((clickedSquirrel.getHeadX() - 1) >= 0 && 
                        matrixSquare[clickedSquirrel.getHeadX()-1][clickedSquirrel.getHeadY()].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()-1][clickedSquirrel.getFlowerY()].isAvailable() == true){
                            moveThreeTileSquirrel("up");
                        return true;
                    }
                }
                else{
                    if ((clickedSquirrel.getHeadX() - 1) >=0 && 
                        matrixSquare[clickedSquirrel.getHeadX()-1][clickedSquirrel.getHeadY()].isAvailable() == true){
                            setPreviousSquaresAvailable();
                            clickedSquirrel.moveSquirrel("up");
                        return true;
                    }
                }
            }

            if(e.getSource() == arrowDown){
                if (clickedSquirrel.getType() == "black" || clickedSquirrel.getType() == "brown"){
                    if ((clickedSquirrel.getTailX() + 1) < 4 &&
                        matrixSquare[clickedSquirrel.getTailX()+1][clickedSquirrel.getTailY()].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()+1][clickedSquirrel.getFlowerY()].isAvailable() == true){
                            moveThreeTileSquirrel("down");
                        return true;
                    }
                }
                else{
                    if ((clickedSquirrel.getTailX() + 1) < 4 &&
                        matrixSquare[clickedSquirrel.getTailX()+1][clickedSquirrel.getTailY()].isAvailable() == true){
                            setPreviousSquaresAvailable();
                            clickedSquirrel.moveSquirrel("down");
                        return true;
                    }
                }
            }

            if(e.getSource() == arrowLeft){
                if ((clickedSquirrel.getHeadY() - 1 >= 0) &&
                    matrixSquare[clickedSquirrel.getHeadX()][clickedSquirrel.getHeadY()-1].isAvailable() == true &&
                    matrixSquare[clickedSquirrel.getTailX()][clickedSquirrel.getTailY()-1].isAvailable() == true){
                        setPreviousSquaresAvailable();
                        clickedSquirrel.moveSquirrel("left");
                 
                        if (clickedSquirrel.getType() == "black" || clickedSquirrel.getType() == "brown"){
                            matrixSquare[clickedSquirrel.getFlowerX()][clickedSquirrel.getFlowerY()].setAvailable(true);
                            clickedSquirrel.moveFlower("left");
                            return true;
                        }
                    return true;
                }
            }

            if(e.getSource() == arrowRight){
                if (clickedSquirrel.getType() == "black"){
                    if ((clickedSquirrel.getFlowerY() + 1) < 4 &&
                        matrixSquare[clickedSquirrel.getHeadX()][clickedSquirrel.getHeadY()+1].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()][clickedSquirrel.getFlowerY()+1].isAvailable() == true){
                            moveThreeTileSquirrel("right");
                        return true;
                    }   
                }
                else if (clickedSquirrel.getType() == "brown"){
                    if ((clickedSquirrel.getFlowerY() + 1) < 4 &&
                        matrixSquare[clickedSquirrel.getTailX()][clickedSquirrel.getTailY()+1].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()][clickedSquirrel.getFlowerY()+1].isAvailable() == true){
                            moveThreeTileSquirrel("right");
                        return true;
                    }
                }
                else{
                    if ((clickedSquirrel.getHeadY() + 1) < 4 &&
                        matrixSquare[clickedSquirrel.getHeadX()][clickedSquirrel.getHeadY()+1].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getTailX()][clickedSquirrel.getTailY()+1].isAvailable() == true){
                            setPreviousSquaresAvailable();
                            clickedSquirrel.moveSquirrel("right");
                        return true;           
                    }
                }
            }

        }

        if (rotation == 90){
            if (e.getSource() == arrowUp){
                if ((clickedSquirrel.getHeadX() - 1) >= 0 &&
                    matrixSquare[clickedSquirrel.getHeadX()-1][clickedSquirrel.getHeadY()].isAvailable() == true &&
                    matrixSquare[clickedSquirrel.getTailX()-1][clickedSquirrel.getHeadY()].isAvailable() == true){
                        setPreviousSquaresAvailable();
                        clickedSquirrel.moveSquirrel("up");
                
                    if (clickedSquirrel.getType() == "black" || clickedSquirrel.getType() == "brown"){
                            matrixSquare[clickedSquirrel.getFlowerX()][clickedSquirrel.getFlowerY()].setAvailable(true);
                            clickedSquirrel.moveFlower("up");
                        return true;
                    }
                    return true;
                }
            }

            if (e.getSource() == arrowDown){
                if (clickedSquirrel.getType() == "black"){
                    if ((clickedSquirrel.getFlowerX() + 1) < 4 &&
                        matrixSquare[clickedSquirrel.getHeadX()+1][clickedSquirrel.getHeadY()].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()+1][clickedSquirrel.getFlowerY()].isAvailable() == true){
                            moveThreeTileSquirrel("down");
                        return true;
                    }
                }
                else if (clickedSquirrel.getType() == "brown"){
                    if ((clickedSquirrel.getFlowerX() + 1) < 4 &&
                        matrixSquare[clickedSquirrel.getTailX()+1][clickedSquirrel.getTailY()].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()+1][clickedSquirrel.getFlowerY()].isAvailable() == true){
                            moveThreeTileSquirrel("down");
                    return true;
                    }
                }
                else {
                    if ((clickedSquirrel.getHeadX() + 1) < 4 &&
                        matrixSquare[clickedSquirrel.getHeadX()+1][clickedSquirrel.getHeadY()].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getTailX()+1][clickedSquirrel.getTailY()].isAvailable() == true){
                            setPreviousSquaresAvailable();
                            clickedSquirrel.moveSquirrel("down");
                        return true;
                    }
                }
            }

            if (e.getSource() == arrowLeft){
                if (clickedSquirrel.getType() == "black" || clickedSquirrel.getType() == "brown"){
                    if ((clickedSquirrel.getTailY() - 1) >= 0 &&
                        matrixSquare[clickedSquirrel.getTailX()][clickedSquirrel.getTailY()-1].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()][clickedSquirrel.getFlowerY()-1].isAvailable() == true){
                            moveThreeTileSquirrel("left");
                        return true;
                    }
                }
                else{
                    if ((clickedSquirrel.getTailY() - 1) >= 0 &&
                        matrixSquare[clickedSquirrel.getTailX()][clickedSquirrel.getTailY()-1].isAvailable() == true){
                            setPreviousSquaresAvailable();
                            clickedSquirrel.moveSquirrel("left");
                        return true;
                    }
                }
            }

            if (e.getSource() == arrowRight){
                if (clickedSquirrel.getType() == "black" || clickedSquirrel.getType() == "brown"){
                    if ((clickedSquirrel.getHeadY() + 1) < 4 &&
                        matrixSquare[clickedSquirrel.getHeadX()][clickedSquirrel.getHeadY()+1].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()][clickedSquirrel.getFlowerY()+1].isAvailable() == true){
                            moveThreeTileSquirrel("right");
                        return true;
                    }
                }
                else{
                    if ((clickedSquirrel.getHeadY() + 1) < 4 &&
                        matrixSquare[clickedSquirrel.getHeadX()][clickedSquirrel.getHeadY()+1].isAvailable() == true){
                            setPreviousSquaresAvailable();
                            clickedSquirrel.moveSquirrel("right");
                        return true;   
                    }
                }
            }

        }

        if (rotation == 180){
            if(e.getSource() == arrowUp){
                if (clickedSquirrel.getType() == "black" || clickedSquirrel.getType() == "brown"){
                    if ((clickedSquirrel.getTailX() - 1) >= 0 &&
                        matrixSquare[clickedSquirrel.getTailX()-1][clickedSquirrel.getTailY()].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()-1][clickedSquirrel.getFlowerY()].isAvailable() == true){
                            moveThreeTileSquirrel("up");
                        return true;
                    }
                }
                else{
                    if ((clickedSquirrel.getTailX() - 1) >= 0 &&
                        matrixSquare[clickedSquirrel.getTailX()-1][clickedSquirrel.getTailY()].isAvailable() == true){
                            setPreviousSquaresAvailable();
                            clickedSquirrel.moveSquirrel("up");
                        return true;
                    }
                }
            }

            if (e.getSource() == arrowDown){
                if (clickedSquirrel.getType() == "black" || clickedSquirrel.getType() == "brown"){
                    if ((clickedSquirrel.getHeadX() + 1) < 4 &&
                        matrixSquare[clickedSquirrel.getHeadX()+1][clickedSquirrel.getHeadY()].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()+1][clickedSquirrel.getFlowerY()].isAvailable() == true){
                            moveThreeTileSquirrel("down");
                        return true;
                    }
                }
                else{
                    if ((clickedSquirrel.getHeadX() + 1) < 4 &&
                        matrixSquare[clickedSquirrel.getHeadX()+1][clickedSquirrel.getHeadY()].isAvailable() == true){
                            setPreviousSquaresAvailable();
                            clickedSquirrel.moveSquirrel("down");
                        return true;
                    }
                }
            }

            if (e.getSource() == arrowLeft){
                if (clickedSquirrel.getType() == "black"){
                    if ((clickedSquirrel.getFlowerY() - 1) >= 0 &&
                        matrixSquare[clickedSquirrel.getHeadX()][clickedSquirrel.getHeadY()-1].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()][clickedSquirrel.getFlowerY()-1].isAvailable() == true){
                            moveThreeTileSquirrel("left");
                        return true;
                    }   
                }
                else if (clickedSquirrel.getType() == "brown"){
                    if ((clickedSquirrel.getFlowerY() - 1) >= 0 &&
                        matrixSquare[clickedSquirrel.getTailX()][clickedSquirrel.getTailY()-1].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()][clickedSquirrel.getFlowerY()-1].isAvailable() == true){
                            moveThreeTileSquirrel("left");
                        return true;
                    }
                }
                else{
                    if ((clickedSquirrel.getHeadY() - 1) >= 0 &&
                        matrixSquare[clickedSquirrel.getHeadX()][clickedSquirrel.getHeadY()-1].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getTailX()][clickedSquirrel.getTailY()-1].isAvailable() == true){
                            setPreviousSquaresAvailable();
                            clickedSquirrel.moveSquirrel("left");
                        return true;
                    }
                }
            }

            if (e.getSource() == arrowRight){
                if ((clickedSquirrel.getHeadY() + 1) < 4 &&
                    matrixSquare[clickedSquirrel.getHeadX()][clickedSquirrel.getHeadY()+1].isAvailable() == true &&
                    matrixSquare[clickedSquirrel.getTailX()][clickedSquirrel.getTailY()+1].isAvailable() == true){
                        setPreviousSquaresAvailable();
                        clickedSquirrel.moveSquirrel("right");
           
                        if (clickedSquirrel.getType() == "black" || clickedSquirrel.getType() == "brown"){
                            matrixSquare[clickedSquirrel.getFlowerX()][clickedSquirrel.getFlowerY()].setAvailable(true);
                            clickedSquirrel.moveFlower("right");
                            return true;                
                        }
                    return true;
                }
            }
        }

        if (rotation == 270){
            if (e.getSource() == arrowUp){
                if (clickedSquirrel.getType() == "black"){
                    if ((clickedSquirrel.getFlowerX() - 1) >= 0 &&
                        matrixSquare[clickedSquirrel.getHeadX()-1][clickedSquirrel.getHeadY()].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()-1][clickedSquirrel.getFlowerY()].isAvailable() == true){
                            moveThreeTileSquirrel("up");
                        return true;
                    }
                }
                else if (clickedSquirrel.getType() == "brown"){
                    if ((clickedSquirrel.getFlowerX() - 1) >= 0 &&
                        matrixSquare[clickedSquirrel.getTailX()-1][clickedSquirrel.getTailY()].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()-1][clickedSquirrel.getFlowerY()].isAvailable() == true){
                            moveThreeTileSquirrel("up");
                        return true;
                    }
                }
                else {
                    if ((clickedSquirrel.getHeadX() - 1) >= 0 &&
                        matrixSquare[clickedSquirrel.getHeadX()-1][clickedSquirrel.getHeadY()].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getTailX()-1][clickedSquirrel.getTailY()].isAvailable() == true){
                            setPreviousSquaresAvailable();
                            clickedSquirrel.moveSquirrel("up");
                        return true;
                    }
                }
            }

            if (e.getSource() == arrowDown){
                if ((clickedSquirrel.getHeadX() + 1) < 4 &&
                    matrixSquare[clickedSquirrel.getHeadX()+1][clickedSquirrel.getHeadY()].isAvailable() == true &&
                    matrixSquare[clickedSquirrel.getTailX()+1][clickedSquirrel.getTailY()].isAvailable() == true){
                        setPreviousSquaresAvailable();
                        clickedSquirrel.moveSquirrel("down");
                 
                        if (clickedSquirrel.getType() == "black" || clickedSquirrel.getType() == "brown"){
                            matrixSquare[clickedSquirrel.getFlowerX()][clickedSquirrel.getFlowerY()].setAvailable(true);
                            clickedSquirrel.moveFlower("down");
                            return true;
                        }
                    return true;
                }
            }

            if (e.getSource() == arrowLeft){
                if (clickedSquirrel.getType() == "black" || clickedSquirrel.getType() == "brown"){
                    if ((clickedSquirrel.getHeadY() - 1) >= 0 &&
                        matrixSquare[clickedSquirrel.getHeadX()][clickedSquirrel.getHeadY()-1].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()][clickedSquirrel.getFlowerY()-1].isAvailable() == true){
                            moveThreeTileSquirrel("left");
                        return true;
                    }
                }
                else{
                    if ((clickedSquirrel.getHeadY() - 1) >= 0 &&
                        matrixSquare[clickedSquirrel.getHeadX()][clickedSquirrel.getHeadY()-1].isAvailable() == true){
                            setPreviousSquaresAvailable();
                            clickedSquirrel.moveSquirrel("left");
                        return true;
                    }
                }
            }

            if (e.getSource() == arrowRight){
                if (clickedSquirrel.getType() == "black" || clickedSquirrel.getType() == "brown"){
                    if ((clickedSquirrel.getTailY() + 1) < 4 &&
                        matrixSquare[clickedSquirrel.getTailX()][clickedSquirrel.getTailY()+1].isAvailable() == true &&
                        matrixSquare[clickedSquirrel.getFlowerX()][clickedSquirrel.getFlowerY()+1].isAvailable() == true){
                            moveThreeTileSquirrel("right");
                        return true;                   
                    }
                }
                else{
                    if ((clickedSquirrel.getTailY() + 1) < 4 &&
                        matrixSquare[clickedSquirrel.getTailX()][clickedSquirrel.getTailY()+1].isAvailable() == true){
                            setPreviousSquaresAvailable();
                            clickedSquirrel.moveSquirrel("right");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * After every move, the previous squares that were a squirrel
     * have to be set available, so that another squirrel can move
     * to those coordinates.
     */
    public void setPreviousSquaresAvailable(){
        matrixSquare[clickedSquirrel.getHeadX()][clickedSquirrel.getHeadY()].setAvailable(true);
        matrixSquare[clickedSquirrel.getTailX()][clickedSquirrel.getTailY()].setAvailable(true);
    }

    /**
     * Check when a level is won and display an appropriate message.
     */
    public void checkWin(){
        if(nutHeads == 0){
            frame.setTitle("YOU WIN!");
        }
    }

    /**
     * Change the 3 tile squirrel coordinates in relation with the choosen direction
     * @param direction a string that represents the direction (up/down/left/right).
     */
    public void moveThreeTileSquirrel(String direction){
        setPreviousSquaresAvailable();
        matrixSquare[clickedSquirrel.getFlowerX()][clickedSquirrel.getFlowerY()].setAvailable(true);
        clickedSquirrel.moveSquirrel(direction);
        clickedSquirrel.moveFlower(direction);
    }

    /**
     * Complete the movement of a squirrel.
     * 1. Change the prev squares where the squirrel was with the respective board tile.
     * 2. Check for headNuts over normal holes.
     * 3. Add the obstacoles to the board.
     * 4. Increment move counter and set new value on button.
     */
    public void doFullMovementCycle(){
        ResetBoard();
        checkHole();
        addObstacles();
        counter++;
        moveCounter.setText("Moves: " + counter);
    }

    /**
     * Check what button is pressed and perform different actions.
     */
    public void actionPerformed(ActionEvent e){
        JButton button = (JButton)e.getSource();

        //check what button on the 4x4 is pressed and associate it with a squirrel if .getType is head or headNut
        for (int i =0; i<4; i++){
            for (int j =0; j<4; j++){
                if (button == matrixSquare[i][j].getButton() && (matrixSquare[i][j].getType() == "headNut" || matrixSquare[i][j].getType() == "head" )){
                    clickedSquirrel =  arraySquirrels[matrixSquare[i][j].getIndexSquirrel()];
                }
            }
        }

        //directions
        if (clickedSquirrel.getHead() != null){
            if (e.getSource() == arrowUp && validMove(clickedSquirrel, e) == true){
                doFullMovementCycle();
            }

            if (e.getSource() == arrowDown && validMove(clickedSquirrel, e) == true){
                doFullMovementCycle();
            }

            if (e.getSource() == arrowRight && validMove(clickedSquirrel, e) == true){
                doFullMovementCycle();
            }

            if (e.getSource() == arrowLeft && validMove(clickedSquirrel, e) == true){
                doFullMovementCycle();
            }
        }

        //levels
        if (e.getSource() == selectionButtons[0]){
            selectLevel("Level 1");
        }

        if (e.getSource() == selectionButtons[1]){
            selectLevel("Level 2");
        }

        if (e.getSource() == selectionButtons[2]){
            selectLevel("Level 3");
        }

        if (e.getSource() == selectionButtons[3]){
            selectLevel("Level 4");
        }

        if (e.getSource() == selectionButtons[4]){
            selectLevel("Level 5");
        }

        if (e.getSource() == selectionButtons[5]){
            selectLevel("Level 6");
        }
        checkWin();     //check for a win after every button press
    }
}