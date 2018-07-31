public class BoatRunner extends Boat{

    public BoatRunner(int team, Coordinates location, int direction, int health, int strength, int vision) {
        super(team, location, direction, health, strength, vision);
    }

    public String getID() {
        return "BA";
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

        BoatRunner a = new BoatRunner(1, new Coordinates(0,0), 0, 9, 5, 1);

//        if (w.setOccupant(a, new Coordinates(0,0)))
//
//            System.out.println("Set a boat at (0,0)");

        Coordinates e = w.getAdjacentLocation(new Coordinates(0,0), w.SOUTHEAST);

        BoatRunner[] barray = new BoatRunner[3];

        BoatRunner b1 = new BoatRunner(1, new Coordinates(3,3), 0, 2, 5, 2);
        BoatRunner b2 = new BoatRunner(1, new Coordinates(2,2), 0, 2, 5, 2);
        BoatRunner b3 = new BoatRunner(1, new Coordinates(1,1), 0, 2, 5, 2);

        barray[0] = b1;

        barray[1] = b2;

        barray[2] = b3;

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

        b1.move(w);

        String map1 = w.drawTeamMap(barray, 2);

        /*
        System.out.println((char)barray[0].getDirection());
        System.out.println(barray[0].turn(-1));
        System.out.println(barray[0].turn(-1));
        System.out.println(barray[0].turn(-1));
        System.out.println(barray[0].turn(1));
        System.out.println(barray[0].turn(1));
        System.out.println(barray[0].turn(-1));
        System.out.println(barray[0].turn(-1));
        System.out.println((char)barray[0].getDirection());
        */
        System.out.println(map1);
    }
}