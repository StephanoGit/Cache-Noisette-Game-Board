/**
 * This class represents a flower square/piece on the board game "Cache Noisette!".
 */
public class Flower {

    private static boolean available = false;   //can't move another piece on a flower one

    private Picture flower;
    
    //coordinates on the board
    private int x;
    private int y;

    private String type;

    public Flower(){
        this(null,-1,-1,"");
    }

    /**
     * Constructor that creates a Flower with the given parameters.
     * @param flower The picture of the flower.
     * @param x The x coordinates from the left corner of the board.
     * @param y The y coordinates from the left corner of the board.
     * @param type a way to differentiate a flower from one another.
     */
    public Flower(Picture flower, int x, int y, String type) {
        this.flower = flower;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * Set a picture to the specific Flower.
     * @param flower the picture of the specific Flower.
     */
    public void setFlower(Picture flower) {
        this.flower = flower;
    }

    /**
     * Get the set picture of the specific Flower.
     * @return the picture of the specific Flower.
     */
    public Picture getFlower() {
        return flower;
    }

    /**
     * Get the current x coordinates of Flower.
     * @return the x coordonates of the specific Flower.
     */
    public int getX() {
        return x;
    }

    /**
     * Set the x coodinates of the specific Flower.
     * @param x the x coordinates of the specific Flower.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get the current y coordinates of Flower.
     * @return the y coordonates of the specific Flower.
     */
    public int getY() {
        return y;
    }

    /**
     * Set the y coodinates of the specific Flower.
     * @param y the y coordinates of the specific Flower.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get the type of the specific Flower.
     * @return the type of the specific Flower.
     */
    public String getType() {
        return type;
    }

    /**
     * Set a type for the specific Flower.
     * @param type a way to differentiate a flower from one another.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the availability of a specific Flower
     * @return true if available, false otherwise
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Set the availability of a specific Flower
     * @param available determins whether the square where a flower is placed is available or not
     */
    public void setAvailable(boolean available) {
        Flower.available = available;
    }
}

