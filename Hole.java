/**
 * This class reprsents a Hole on the board game "Cache Noisette!".
 */
public class Hole {

    private static boolean available = true;    //you are always able to move over a hole

    private Picture holeP;

    private int x;
    private int y;
    private String type;

    public Hole() {
        this(null, -1, -1, "");
    }

    /**
     * Constructor that creates a Hole with the given parameters.
     * @param holeP The picture of the hole
     * @param x The x coordinates from the left corner of the board.
     * @param y The y coordinates from the left corner of the board.
     * @param type a way to differentiate a hole from a nutHole
     */
    public Hole(Picture holeP, int x, int y, String type) {
        this.holeP = holeP;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * Get the availability of a specific Hole
     * @return true if available, false otherwise
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Set the availability of a specific Hole
     * @param available determins whether the square where a Hole is placed is available or not
     */
    public void setAvailable(boolean available) {
        Hole.available = available;
    }

    /**
     * Get the set picture of the specific Hole.
     * @return the picture of the specific Hole.
     */
    public Picture getHoleP() {
        return holeP;
    }

    /**
     * Set a picture to the specific Hole.
     * @param holeP the picture of the specific Hole.
     */
    public void setHoleP(Picture holeP) {
        this.holeP = holeP;
    }

    /**
     * Get the current x coordinates of Hole.
     * @return the x coordonates of the specific Hole.
     */
    public int getX() {
        return x;
    }

    /**
     * Set the x coodinates of the specific Hole.
     * @param x the x coordinates of the specific Hole.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get the current y coordinates of Hole.
     * @return the y coordonates of the specific Hole.
     */
    public int getY() {
        return y;
    }

    /**
     * Set the y coodinates of the specific Hole.
     * @param y the y coordinates of the specific Hole.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get the type of the specific Hole.
     * @return the type of the specific Hole.
     */
    public String getType() {
        return type;
    }

    /**
     * Set a type for the specific Hole.
     * @param type a way to differentiate a Hole from one another.
     */
    public void setType(String type) {
        this.type = type;
    }
}
