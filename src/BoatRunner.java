public class BoatRunner extends Boat{
    private int id;

    public BoatRunner(int team, Coordinates location, int direction, int health, int strength, int vision, int id) {
        super(team, location, direction, health, strength, vision);
        this.id = id;
    }

    public String getID() {
        return String.format("B%d", this.id);
    }

    public String act(int[] choices, World world){
        return "Not implemented in Part A";
    }


    public String getActions(){
        return "Not implemented in Part A";
    }

    public static void main(String[] args) {
        Coordinates c = new Coordinates(2, 3);

        Coordinates d = new Coordinates();

        if (d.getX() == 0 && d.getY() == 0) System.out.println("Coordinates default to 0");
        if (c.getX() == 2 && c.getY() == 3) System.out.println("Coordinates correctly returns x and y.");

        int width = 10;
        int height = 5;
        World w = new World(width, height); //should default to minimum of x = 4

        if (w.getWidth() == width)
            System.out.println("World width is correct");

        if (w.getHeight() == height)
            System.out.println("World height is correct");

        System.out.printf("height is %s, width is %s\n", w.getHeight(), w.getWidth());

        if (!w.isLocationValid(new Coordinates(9,5)))
            // should return false because it's not valid
            System.out.println("Catches an incorrect location");

        BoatRunner b0 = new BoatRunner(1, new Coordinates(0,0), 0, 9, 5, 1, 0);

        if (w.setOccupant(b0, new Coordinates(0,0)))
            System.out.println("Set a boat at (0,0)");

        if (w.isLocationOccupied(new Coordinates(0, 0)))
            System.out.println("0, 0 is occupied");

        Coordinates e = w.getAdjacentLocation(new Coordinates(0,0), World.SOUTHEAST);

        BoatRunner[] barray = new BoatRunner[3];

        BoatRunner b1 = new BoatRunner(1, new Coordinates(3,3), 0, 5, 5, 2, 1);
        BoatRunner b2 = new BoatRunner(1, new Coordinates(2,2), 0, 2, 5, 2, 2);
        BoatRunner b3 = new BoatRunner(1, new Coordinates(1,1), 0, 2, 5, 2, 3);

        barray[0] = b1;

        barray[1] = b2;

        barray[2] = b3;

        for (int i = 0; i < barray.length; i++) {
            w.setOccupant(barray[i], barray[i].getLocation());
        }

//        System.out.println(barray[2].getHealth());
//        barray[2].turn(-1);
//        barray[2].turn(-1);
//        barray[2].turn(-1);
//        barray[2].move(w);
//        barray[2].takeHit(1);
//        barray[2].takeHit(1);
//        barray[2].takeHit(1);
//        barray[2].takeHit(1);
//         barray[2].move(w);
//        System.out.println(barray[2].getHealth());


        String map1;

        /*
        Test boat.move()

        b1.move(w);
        b2.turn(-1);
        System.out.println(b2.move(w));
        */

        if (w.isLocationOccupied(new Coordinates(1, 1)))
            System.out.println("1, 1 is occupied");

        /*
        System.out.println(b1.turn(-1)); // face NW
        System.out.println(b1.turn(-1)); // face W
        System.out.println(b1.turn(-1)); // face SW
        System.out.println(b1.turn(-1)); // face S
        System.out.println(b1.turn(-1)); // face SE
        System.out.println(b1.turn(-1)); // face E
        System.out.println(b1.turn(-1)); // face NE
        System.out.println(b1.turn(-1)); // face N
        System.out.println(b1.turn(-1)); // face NW
        */

//        System.out.println(a.move(w));
        System.out.println(b0.turn(1));
        System.out.println(b0.turn(1));
        System.out.println(b0.move(w));
        System.out.println(b0.turn(1));
        System.out.println(b0.turn(1));
        System.out.println(b0.move(w));
        System.out.println(b0.takeHit(8));
        System.out.println(b0.takeHit(10));

        System.out.println(b3.move(w));

        System.out.println(b2.move(w));
        System.out.println(b2.move(w));
        System.out.println(b2.move(w));
        System.out.println(b2.turn(1));
        System.out.println(b2.turn(1));
        System.out.println(b2.move(w));
        System.out.println(b2.takeHit(1));

        map1 = w.drawTeamMap(barray, 3);

        /**
        System.out.println((char)barray[0].getDirection());
        System.out.println(barray[0].turn(-1));
        System.out.println(barray[0].turn(-1));
        System.out.println(barray[0].turn(-1));
        System.out.println(barray[0].turn(1));
        System.out.println(barray[0].turn(1));
        System.out.println(barray[0].turn(-1));
        System.out.println(barray[0].turn(-1));
        System.out.println((char)barray[0].getDirection());
        **/

        System.out.println(map1);
    }
}