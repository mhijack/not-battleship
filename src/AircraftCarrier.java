import java.util.ArrayList;
import java.util.List;

public class AircraftCarrier extends Boat implements Attacker {
    private boolean hasPlanes;

    public AircraftCarrier(int team, Coordinates location, int direction) {
        super(team, location, direction, 5, 1, 1);
        this.hasPlanes = true;
    }

    @Override
    public String getID() {
        return "A" + this.getTeam();
    }

    @Override
    public String getActions() {
        String userAction = "Choose any of the following actions for the AircraftCarrier:\n";
        userAction += "1. Move\n";
        userAction += "2. Turn left\n";
        userAction += "3. Turn right\n";
        userAction += "4. Launch planes\n";
        return userAction;
    }

    @Override
    public String act(int[] choices, World world) {
        String result = "";

        for (int choice: choices) {
            String action = "";
            if (choice == 1)
                action += super.move(world);
            else if (choice == 2)
                action += super.turn(-1);
            else if (choice == 3)
                action += super.turn(1);
            else if (choice == 4)
                action += this.attack(world);
            else
                action += "";
            result += action + "\n";
        }
        return result;
    }

    /**
     *  Launches plane and attack all ships in vision
     **/
    @Override
    public String attack(World world) {
        if (hasPlanes) {
            int yStart, yEnd, xStart, xEnd; // vision boundary
            int vision = this.getVision();
            Coordinates location = this.getLocation();

            yStart = location.getY() - vision;
            if (yStart < 0)
                yStart = 0;

            yEnd = location.getY() + vision;
            if (yEnd > world.getHeight() - 1)
                yEnd = world.getHeight() - 1;

            xStart = location.getX() - vision;
            if (xStart < 0)
                xStart = 0;

            xEnd = location.getX() + vision;
            if (xEnd > world.getWidth() - 1)
                xEnd = world.getWidth() - 1;

            String result = "";
            double successRate = 1.0;
            List<Boat> boatArr = new ArrayList<>();

            if (this.hasPlanes) {
                for (int y = yStart; y <= yEnd; y++) {
                    for (int x = xStart; x <= xEnd; x++) {
                        System.out.printf("CHecking coordinates %d, %d\n", x, y);
                        Coordinates currentLoc = new Coordinates(x, y);
                        Boat boatInVision = world.getOccupant(currentLoc);

                        if (boatInVision != null && boatInVision != this) {
                            boatArr.add(boatInVision);
                        }
                    }
                }
                if (boatArr.size() == 0)
                    return "There are no boats in range currently.";
                else {
                    for (Boat boat: boatArr) {
                        if (Math.random() > successRate) {
                            this.hasPlanes = false;
                            return result + "The planes have been destroyed.";
                        }
                        result += boat.takeHit(this.getStrength()) + "\n";
                        successRate *= 0.8;
                    }
                    return result;
                }
            }
        }
        return this.toString() + " has no planes remaining.";
    }
}
