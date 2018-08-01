import java.lang.Math;

public class Submarine extends ScoutBoat implements Attacker {
    private int numOfTorpedoes;

    public Submarine(int team, Coordinates location, int direction, int numOfTorpedoes) {
        super(team, location, direction, 3, 2);
        this.numOfTorpedoes = numOfTorpedoes;
    }

    @Override
    public String getID() {
        return "S" + this.getTeam();
    }

    @Override
    public String getActions() {
        String userAction = "Choose any of the following actions for the Submarine:\n";
        userAction += "1. Move\n";
        userAction += "2. Turn left\n";
        userAction += "3. Turn right\n";
        userAction += "4. Submerge\n";
        userAction += "5. Fire torpedoes\n";
        return userAction;
    }

    public int getNumOfTorpedoes() {
        return this.numOfTorpedoes;
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
                action += this.submerge(world);
            else if (choice == 5)
                action += this.attack(world);
            else
                action += "";
            result += action + "\n";
        }
        return result;
    }

    /**
     * Select a random position at least 2 spaces away from current location.
     * Keeping choosing until find a space unoccupied.
     * */
    public String submerge(World world) {
        // Do: Pick a random position 2 spaces away; Check if occupied; If not, move there; If occupied: choose again
        Coordinates randomLoc;
        Coordinates location = this.getLocation();
        do {
            /**
             *  Generating random integer [min, max]:
             *
             *  Min + (int)(Math.random() * ((Max - Min) + 1))
             *
             **/
            int maxX = world.getWidth() - 1;
            int maxY = world.getHeight() - 1;

            int lowX = location.getX() - 2;
            if (lowX < 0) lowX = 0;

            int highX = location.getX() + 2;
            if (highX > maxX) highX = maxX;

            int lowY = location.getY() - 2;
            if (lowY < 0) lowY = 0;

            int highY = location.getY() + 2;
            if (highY > maxY) highY = maxY;

            int randomX;
            int randomY;
            do {
                randomX = (int) (Math.random() * maxX) + 1;
            } while (randomX >= lowX && randomX <= highX);
            do {
                randomY = (int) (Math.random() * maxY) + 1;
            } while (randomY >= lowY && randomY <= highY);

            randomLoc = new Coordinates(randomX, randomY);
        } while (world.isLocationOccupied(randomLoc));

        world.setOccupant(this, randomLoc); // set submarine to unoccupied loc on world map
        world.setOccupant(null, location); // set old location as null on world map
        this.setLocation(randomLoc); // set submarine's location to new random location
        return String.format("%s moves from %s to %s", this.toString(), location, randomLoc);
    }

    /**
     *  If numOfTorpedoes > 0 && an enemy appears in direction submarine is facing && within vision:
     *      Call takeHit of that ship with an attack strength equal to
     *      a random integer between 1 and the current health of the boat being attacked.
     **/
    @Override
    public String attack(World world) {
        if (getNumOfTorpedoes() > 0) {
            Coordinates nextLoc = world.getAdjacentLocation(this.getLocation(), this.getIntDirection());

            for (int i = 0; i < this.getVision(); i++) {
                if (world.isLocationValid(nextLoc) && world.isLocationOccupied(nextLoc)) {
                    Boat boat = world.getOccupant(nextLoc);
                    int attack = 1 + (int) (Math.random() * boat.getHealth());
                    this.numOfTorpedoes -= 1;
                    return "Fire torpedoes! " + boat.takeHit(attack);
                }
                nextLoc = world.getAdjacentLocation(nextLoc, this.getIntDirection());
            }
            return "There are no boats in range currently.";
        }
        return this.toString() + " has no torpedoes remaining.";
    }
}
