/**
 * This class reprsents a Squirrel piece on the board game "Cache Noisette!".
 */
public class Squirrel {
    Picture head;
    Picture tail;
    Picture flower;

    int headX;
    int headY;
    int tailX;
    int tailY;
    int flowerX;
    int flowerY;

    String type;
    String headType;

    public Squirrel() {
        this(null, null, null, -1, -1, -1, -1, -1, -1, "", "");
    }

    /**
     * Constructor that creates a 2 tile Squirrel with the given parameters.
     * @param head the picture of the head of the Squirrel.
     * @param tail the picture of the tail of the Squirrel.
     * @param headX the x coordinates of the head of the Squirrel.
     * @param headY the y coordinates of the head of the Squirrel.
     * @param tailX the x coordinates of the tail of the Squirrel.
     * @param tailY the y coordinates of the tail of the Squirrel.
     * @param type a way to differentiate a Squirrel from one another (red/grey/brown/black).
     * @param headType a way to differentiate a nutHead from a simple head.
     */
    public Squirrel(Picture head, Picture tail, int headX, int headY, int tailX, int tailY, String type, String headType) {
        this.head = head;
        this.tail = tail;
        this.headX = headX;
        this.headY = headY;
        this.tailX = tailX;
        this.tailY = tailY;
        this.type = type;
        this.headType = headType;
    }

    /**
     * Constructor that creates a 3 tile Squirrel with the given parameters.
     * @param head the picture of the head of the Squirrel.
     * @param tail the picture of the tail of the Squirrel.
     * @param headX the x coordinates of the head of the Squirrel.
     * @param headY the y coordinates of the head of the Squirrel.
     * @param tailX the x coordinates of the tail of the Squirrel.
     * @param tailY the y coordinates of the tail of the Squirrel.
     * @param flowerX the x coordinates of the flower of the Squirrel.
     * @param flowerY the y coordinates of the flower of the Squirrel.
     * @param type a way to differentiate a Squirrel from one another (red/grey/brown/black).
     * @param headType a way to differentiate a nutHead from a simple head.
     */
    public Squirrel(Picture head, Picture tail, Picture flower, int headX, int headY, int tailX, int tailY, int flowerX, int flowerY, String type, String headType) {
        this.head = head;
        this.tail = tail;
        this.flower = flower;
        this.headX = headX;
        this.headY = headY;
        this.tailX = tailX;
        this.tailY = tailY;
        this.flowerX = flowerX;
        this.flowerY = flowerY;
        this.type = type;
        this.headType = headType;
    }

    /**
     * Get the current x coordinates of the head of the Squirrel.
     * @return the x coordonates of the head of the specific Squirrel.
     */
    public int getHeadX() {
        return headX;
    }

    /**
     * Get the current y coordinates of the head of the Squirrel.
     * @return the y coordonates of the head of the specific Squirrel.
     */
    public int getHeadY() {
        return headY;
    }

    /**
     * Get the current x coordinates of the tail of the Squirrel.
     * @return the x coordonates of the tail of the specific Squirrel.
     */
    public int getTailX() {
        return tailX;
    }

    /**
     * Get the current y coordinates of the tail of the Squirrel.
     * @return the y coordonates of the tail of the specific Squirrel.
     */
    public int getTailY() {
        return tailY;
    }

    /**
     * Set the x coodinates of the head of the specific Squirrel.
     * @param headX the x coordinates of the head of the specific Squirrel.
     */
    public void setHeadX(int headX) {
        this.headX = headX;
    }

    /**
     * Set the y coodinates of the head of the specific Squirrel.
     * @param headY the y coordinates of the head of the specific Squirrel.
     */
    public void setHeadY(int headY) {
        this.headY = headY;
    }

    /**
     * Set the x coodinates of the tail of the specific Squirrel.
     * @param tailX the x coordinates of the tail of the specific Squirrel.
     */
    public void setTailX(int tailX) {
        this.tailX = tailX;
    }

    /**
     * Set the y coodinates of the tail of the specific Squirrel.
     * @param tailY the y coordinates of the tail of the specific Squirrel.
     */
    public void setTailY(int tailY) {
        this.tailY = tailY;
    }

    /**
     * Get the set picture of the head of the specific Squirrel.
     * @return the picture of the head of the specific Squirrel.
     */
    public Picture getHead() {
        return head;
    }

    /**
     * Set a picture of the head to the specific Squirrel.
     * @param head the picture of the head of a specific Squirrel.
     */
    public void setHead(Picture head) {
        this.head = head;
    }

    /**
     * Get the set picture of the tail of the specific Squirrel.
     * @return the picture of the tail of the specific Squirrel.
     */
    public Picture getTail() {
        return tail;
    }

    /**
     * Set a picture of the head to the specific Squirrel.
     * @param tail the picture of the head of a specific Squirrel.
     */
    public void setTail(Picture tail) {
        this.tail = tail;
    }

    /**
     * Change the coordinates of the 2 tile Squirrel.
     * @param direction a string that determins the direction in which a Squirrel moves.
     * A Squirrel can only move one tile at a time.
     */
    public void moveSquirrel(String direction){
        if(direction == "up"){
            this.headX = getHeadX() - 1;
            this.headY = getHeadY();
            this.tailX = getTailX() - 1;
            this.tailY = getTailY();
        }

        if(direction == "down"){
            this.headX = getHeadX() + 1;
            this.headY = getHeadY();
            this.tailX = getTailX() + 1;
            this.tailY = getTailY();
        }

        if(direction == "left"){
            this.headX = getHeadX();
            this.headY = getHeadY() - 1;
            this.tailX = getTailX();
            this.tailY = getTailY() - 1;
        }

        if(direction == "right"){
            this.headX = getHeadX();
            this.headY = getHeadY() + 1;
            this.tailX = getTailX();
            this.tailY = getTailY() + 1;
        }
    }

    /**
     * Change the coordinates of the flower of a Squirrel
     * @param direction a string that determins the direction in which a flower moves.
     * A flower can only move one tile at a time.
     */
    public void moveFlower(String direction){
        if (direction == "up"){
            this.flowerX = getFlowerX() - 1;
            this.flowerY = getFlowerY();
        }
        if (direction == "down"){
            this.flowerX = getFlowerX() + 1;
            this.flowerY = getFlowerY();
        }
        if (direction == "left"){
            this.flowerX = getFlowerX();
            this.flowerY = getFlowerY() - 1;
        }
        if (direction == "right"){
            this.flowerX = getFlowerX();
            this.flowerY = getFlowerY() + 1;
        }
    }

    /**
     * Get the type of the specific Squirrel.
     * @return the type of the specific Squirrel.
     */
    public String getType() {
        return type;
    }

    /**
     * Set a type for the specific Squirrel.
     * @param type a way to differentiate a Squirrel from one another.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the set picture of the flower of the specific Squirrel.
     * @return the picture of the flower of the specific Squirrel.
     */
    public Picture getFlower() {
        return flower;
    }

    /**
     * Set a picture of the flower to the specific Squirrel.
     * @param flower the picture of the flower of a specific Squirrel.
     */
    public void setFlower(Picture flower) {
        this.flower = flower;
    }

    /**
     * Get the current x coordinates of the flower of the Squirrel.
     * @return the x coordonates of the flower of the specific Squirrel.
     */
    public int getFlowerX() {
        return flowerX;
    }

    /**
     * Set the x coodinates of the flower of the specific Squirrel.
     * @param flowerX the x coordinates of the flower of the specific Squirrel.
     */
    public void setFlowerX(int flowerX) {
        this.flowerX = flowerX;
    }

    /**
     * Get the current y coordinates of the flower of the Squirrel.
     * @return the y coordonates of the flower of the specific Squirrel.
     */
    public int getFlowerY() {
        return flowerY;
    }

    /**
     * Set the y coodinates of the flower of the specific Squirrel.
     * @param flowerY the y coordinates of the flower of the specific Squirrel.
     */
    public void setFlowerY(int flowerY) {
        this.flowerY = flowerY;
    }

    /**
     * Get the type of the head of a specific Squirrel.
     * @return the type of the head of a specific Squirrel.
     */
    public String getHeadType() {
        return headType;
    }

    /**
     * Set a type for the head of a specific Squirrel.
     * @param type a way to differentiate the head of a Squirrel from one another.
     */
    public void setHeadType(String headType) {
        this.headType = headType;
    }
}
