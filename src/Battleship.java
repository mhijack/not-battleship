import java.util.HashSet;
import java.util.Set;

public class Battleship extends Boat implements Attacker {
    public Battleship(int team, Coordinates location, int direction) {
        super(team, location, direction, 4, 3, 1);
    }

    @Override
    public String getID() {
        return "B" + this.getTeam();
    }

    @Override
    public String getActions() {
        String userAction = "Choose any of the following actions for the Battleship:\n";
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

    /**
     *  A Battleship will attack twice any Boat that is within its vision and in the direction it is facing.
     *  Call the Boat’s takeHit method using the Battleship’s attack strength
     **/
    @Override
    public String attack(World world) {
        Coordinates nextLoc = world.getAdjacentLocation(this.getLocation(), this.getIntDirection());
        String result = "Fire cannons! ";
        if (nextLoc == null)
            result = "There are no boats in range currently.";
        Boat boat = world.getOccupant(nextLoc);
        for (int i = 0; i < 2; i++) {
            result += boat.takeHit(this.getStrength()) + "\n";
        }

        return result;
    }
}
