/**
 * 
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
     * 
     * @param head
     * @param tail
     * @param headX
     * @param headY
     * @param tailX
     * @param tailY
     * @param type
     * @param headType
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
     * 
     * @param head
     * @param tail
     * @param flower
     * @param headX
     * @param headY
     * @param tailX
     * @param tailY
     * @param flowerX
     * @param flowerY
     * @param type
     * @param headType
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
     * 
     * @return
     */
    public int getHeadX() {
        return headX;
    }

    /**
     * 
     * @return
     */
    public int getHeadY() {
        return headY;
    }

    /**
     * 
     * @return
     */
    public int getTailX() {
        return tailX;
    }

    /**
     * 
     * @return
     */
    public int getTailY() {
        return tailY;
    }

    /**
     * 
     * @param headX
     */
    public void setHeadX(int headX) {
        this.headX = headX;
    }

    /**
     * 
     * @param headY
     */
    public void setHeadY(int headY) {
        this.headY = headY;
    }

    /**
     * 
     * @param tailX
     */
    public void setTailX(int tailX) {
        this.tailX = tailX;
    }

    /**
     * 
     * @param tailY
     */
    public void setTailY(int tailY) {
        this.tailY = tailY;
    }

    /**
     * 
     * @return
     */
    public Picture getHead() {
        return head;
    }

    /**
     * 
     * @param head
     */
    public void setHead(Picture head) {
        this.head = head;
    }

    /**
     * 
     * @return
     */
    public Picture getTail() {
        return tail;
    }

    /**
     * 
     * @param tail
     */
    public void setTail(Picture tail) {
        this.tail = tail;
    }

    /**
     * 
     * @param headX
     * @param headY
     * @param tailX
     * @param tailY
     */
    public void setNewCoordonates(int headX, int headY, int tailX, int tailY) {
        this.headX = headX;
        this.headY = headY;
        this.tailX = tailX;
        this.tailY = tailY;
    }

    /**
     * 
     * @param direction
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
     * 
     * @param direction
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
    public Picture getFlower() {
        return flower;
    }

    /**
     * 
     * @param flower
     */
    public void setFlower(Picture flower) {
        this.flower = flower;
    }

    /**
     * 
     * @return
     */
    public int getFlowerX() {
        return flowerX;
    }

    /**
     * 
     * @param flowerX
     */
    public void setFlowerX(int flowerX) {
        this.flowerX = flowerX;
    }

    /**
     * 
     * @return
     */
    public int getFlowerY() {
        return flowerY;
    }

    /**
     * 
     * @param flowerY
     */
    public void setFlowerY(int flowerY) {
        this.flowerY = flowerY;
    }

    /**
     * 
     * @return
     */
    public String getHeadType() {
        return headType;
    }

    /**
     * 
     * @param headType
     */
    public void setHeadType(String headType) {
        this.headType = headType;
    }
}
