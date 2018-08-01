import java.util.HashSet;
import java.util.Set;
import java.lang.Math;

public class Destroyer extends Boat implements Attacker {
    public Destroyer(int team, Coordinates location, int direction) {
        super(team, location, direction, 2, 2, 1);
    }

    @Override
    public String getID() {
        return "D" + this.getTeam();
    }

    @Override
    public String getActions() {
        String userAction = "Choose any of the following actions for the Submarine:\n";
        userAction += "1. Move\n";
        userAction += "2. Turn left\n";
        userAction += "3. Turn right\n";
        userAction += "4. Attack\n";
        return userAction;
    }

    @Override
    public String act(int[] choices, World world) {
        String result = "";

        for (int choice : choices) {
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

    @Override
    public String attack(World world) {
        Coordinates nextLoc = world.getAdjacentLocation(this.getLocation(), this.getIntDirection());
        String result = "";
        Set<Boat> boatsInRange = new HashSet<>();

        for (int i = 0; i < this.getVision(); i++) {
            if (world.isLocationValid(nextLoc) && world.isLocationOccupied(nextLoc)) {
                Boat boat = world.getOccupant(nextLoc);
                boatsInRange.add(boat);
                result += boat.takeHit(this.getStrength());
            }
            nextLoc = world.getAdjacentLocation(nextLoc, this.getIntDirection());
        }
        if (boatsInRange.size() == 0) result = "There are no boats in range currently.";
        return result;
    }

    @Override
    public String takeHit(int attack) {
        if (Math.random() < 0.5)
            return this.toString() + " avoids the attack!";
        else
            return super.takeHit(attack);
    }
}
