import java.lang.Math;

public abstract class ScoutBoat extends Boat {
    public ScoutBoat(int team, Coordinates location, int direction, int health, int vision) {
        super(team, location, direction, health, 1, vision);
    }

    /**
     * ScoutBoat has 25% chance of avoiding attack
     *
     * @params attack represents strength of incoming attack
     *
     * @returns a String representation of result of attack
     **/
    @Override
    public String takeHit(int attack) {
        double hitChance = 0.25;
        double hitResult = Math.random();

        if (hitResult > hitChance)
            return this.getID() + " has avoided the attack!";
        String result = super.takeHit(attack);
        return result;
    }
}
