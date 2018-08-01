import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        /**
         *  Testing Battleship:
         *
         *
         */
        World world = new World(10, 10);

        List<Boat> barr = new ArrayList<>();
        Boat[] arr = new Boat[barr.size()];

        Battleship b = new Battleship(1, new Coordinates(6, 4), 6);
        Cruiser c = new Cruiser(1, new Coordinates(0, 4), 2);
        Submarine s = new Submarine(1, new Coordinates(4, 4), 6, 2);
        AircraftCarrier a = new AircraftCarrier(1, new Coordinates(5, 4), 6);
        Destroyer d = new Destroyer(1, new Coordinates(8, 5), 6);

        barr.add(b);
        barr.add(c);
        barr.add(s);
        barr.add(a);
        barr.add(d);

        for (Boat boat: barr) {
            world.setOccupant(boat, boat.getLocation());
        }

        /* // Submarine attacks - Passed
        System.out.println(s.attack(world));
        System.out.println(s.attack(world));
        System.out.println(s.attack(world));
        */

        /* // AircraftCarrier attacks - Passed
        System.out.println(a.attack(world));
        System.out.println(a.attack(world));
        System.out.println(a.attack(world));
         */

        /* // Destroyer attacks - Passed
        System.out.println(d.attack(world));
        System.out.println(d.attack(world));
        System.out.println(d.attack(world));
         */

        /* // Battleship attacks - Passed
        System.out.println(b.attack(world));
        System.out.println(b.attack(world));
        System.out.println(b.attack(world));
         */
        System.out.println(b.attack(world));


        String dirMap = world.drawTeamMap(barr.toArray(arr), 2);
        String healthMap = world.drawTeamMap(barr.toArray(arr), 3);






        // For Visualizing Test
        System.out.println(dirMap);
        System.out.println(healthMap);

        System.out.println("Battleships at location " + b.getLocation());
        System.out.println("Cruiser at location " + c.getLocation());
        System.out.println("Submarine at location " + s.getLocation());
    }
}
