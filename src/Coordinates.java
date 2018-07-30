public class Coordinates {
    private int x;
    private int y;

    public Coordinates() {
        x = 0;
        y = 0;
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        // x: 1 to 26 ( x0 = 1)
        // y: A to Z (y0 = A)
        int xCoord = getX();
        int yCoord = 65 + getY();
        // "C7" // x = 7, y = 2
        return String.format("%c%d", yCoord, xCoord);
    }

//    public static void main(String[] args) {
//        Coordinates c = new Coordinates(7, 2);
//        System.out.println(c);
//    }
}
