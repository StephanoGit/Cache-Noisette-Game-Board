import javax.swing.JButton;

/**
 * This class represents a Square on the 4x4 board game "Cache Noisette!".
 */
public class Square {

    private JButton button;     //each square is a button

    private String type;        //a way to differentiate a Square from one another
    private boolean available;

    private int indexSquirrel;  //if a square has a part of the squirrel on it, find out which one is it

    public Square(){
        this(null,"",true,-1);
    }

    /**
     * Constructor that creates a Square with the given parameters.
     * @param picture The picture of the square.
     * @param type The type of the Square .
     * @param available The availability of the Square.
     * @param indexSquirrel if a square has a part of the squirrel on it, find out which one is it.
     */
    public Square(Picture picture, String type, boolean available, int indexSquirrel) {
        this.button = new JButton(picture);
        this.type = type;
        this.available = available;
        this.indexSquirrel = indexSquirrel;
    }

    /**
     * Set attributes to a specific Square.
     * @param picture The picture of the square.
     * @param type The type of the Square .
     * @param available The availability of the Square.
     * @param indexSquirrel if a square has a part of the squirrel on it, find out which one is it.
     */
    public void setAttributesSquare(Picture picture, String type, boolean available, int indexSquirrel){
        this.button.setIcon(picture);
        this.setType(type);
        this.setAvailable(available);
        this.indexSquirrel = indexSquirrel;
    }

    /**
     * Get the appropriate button of the Square.
     * @return the button of a specific Square.
     */
    public JButton getButton() {
        return button;
    }

    /**
     * Set a specific button as a Square on the board.
     * @param button the button set for the specific Square.
     */
    public void setButton(JButton button) {
        this.button = button;
    }

    /**
     * Get the type of the specific Square.
     * @return the type of the specific Square.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of a specific Square.
     * @param type a way to differentiate a Square from one another.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the availability of a specific Square.
     * @return true if available, false otherwise.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Set the availability of a specific Square.
     * @param available determins whether the Square is available or not.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Get the index of the Squirrel that is positioned on the specific Square.
     * In some cases there might not be one.
     * @return a number in the array of Squirrels
     */
    public int getIndexSquirrel() {
        return indexSquirrel;
    }

    /**
     * Set the index of the Squirrel that is positioned on the specific Square.
     * In some cases there might not be one.
     * @param indexSquirrel a number in the array of Squirrels
     */
    public void setIndexSquirrel(int indexSquirrel) {
        this.indexSquirrel = indexSquirrel;
    }

}
