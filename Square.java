import javax.swing.JButton;

/**
 * This class represents a Square on the 4x4 board game "Cache Noisette!".
 */
public class Square {

    private JButton button;     //each square is a button

    private String type; 
    private boolean available;

    private int indexSquirrel;  //if a square has a part of the squirrel on it, find out which one is it

    public Square(){
        this(null,"",true,-1);
    }

    /**
     * Constructor that creates a Square with the given parameters.
     * @param picture The picture of the square
     * @param type The type of the Square 
     * @param available The availability of the Square
     * @param indexSquirrel if a square has a part of the squirrel on it, find out which one is it
     */
    public Square(Picture picture, String type, boolean available, int indexSquirrel) {
        this.button = new JButton(picture);
        this.type = type;
        this.available = available;
        this.indexSquirrel = indexSquirrel;
    }

    /**
     * Set attributes to a specific Square.
     * @param picture The picture of the square
     * @param type The type of the Square 
     * @param available The availability of the Square
     * @param indexSquirrel if a square has a part of the squirrel on it, find out which one is it
     */
    public void setAttributesSquare(Picture picture, String type, boolean available, int indexSquirrel){
        this.button.setIcon(picture);
        this.setType(type);
        this.setAvailable(available);
        this.indexSquirrel = indexSquirrel;
    }

    /**
     * 
     * @return
     */
    public JButton getButton() {
        return button;
    }

    /**
     * 
     * @param button
     */
    public void setButton(JButton button) {
        this.button = button;
    }

    /**
     * 
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * 
     * @param available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * 
     * @return
     */
    public int getIndexSquirrel() {
        return indexSquirrel;
    }

    /**
     * 
     * @param indexSquirrel
     */
    public void setIndexSquirrel(int indexSquirrel) {
        this.indexSquirrel = indexSquirrel;
    }

}
