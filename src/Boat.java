public abstract class Boat {
    private int team;
    private Coordinates location;
    private int direction;
    private int health;
    private int strength;
    private int vision;
    public String[] DIRECTIONS = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};

    public Boat(int team, Coordinates location, int direction, int health, int strength, int vision) {
        this.team = team;
        this.location = location;
        this.direction = direction;
        this.health = health;
        this.strength = strength;
        this.vision = vision;
    }

    public int getTeam() {
        return this.team;
    }

    public Coordinates getLocation() {
        return this.location;
    }

    public int getIntDirection() { return this.direction; };

    /** @return integer, UTF-8 representing the direction arrow **/
    public int getDirection() {
        int arrow;
        switch (this.getIntDirection()) {
            case 0:
                arrow = 8593;
                break;
            case 1:
                arrow = 8599;
                break;
            case 2:
                arrow = 8594;
                break;
            case 3:
                arrow = 8600;
                break;
            case 4:
                arrow = 8595;
                break;
            case 5:
                arrow = 8601;
                break;
            case 6:
                arrow = 8592;
                break;
            case 7:
                arrow = 8598;
                break;
            default:
                arrow = this.direction;
                break;
        }
        return arrow;
    }

    public int getHealth() {
        return this.health;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getVision() {
        return this.vision;
    }

    /**
     *   @params none
     *   @return return a String that each Boat will use to specify the type of boat being mapped.
     **/
    public abstract String getID();

    /**
     *   @params int[] user choices, world object
     *   @return a String reporting on the result of the action selected.
     **/
    public abstract String act(int[] choices, World world);

    /**
     *   @params none
     *   @return a String representing all of the options available to the Boat.
     **/
    public abstract String getActions();

    public String move(World world) {
        // Get new x & y and create a new Coordinates object
        // Check if valid with world.isLocationValid(c)
        // If valid:
            // If not occupied:
                // On the map: Set Boat's current direction as null; Set map's to boat's location; Update boat's location
            // If occupied: return "B1 cannot move to D3 as it is occupied."
        // If invalid location: return "B1 cannot move off the map."
        Coordinates currentLoc = this.getLocation();
        int x = currentLoc.getX();
        int y = currentLoc.getY();

        Coordinates newLoc = world.getAdjacentLocation(currentLoc, this.direction);
        if (newLoc!= null && world.isLocationValid(newLoc)) {
            if (!world.isLocationOccupied(newLoc)) {
                world.setOccupant(null, currentLoc);
                world.setOccupant(this, newLoc);
                this.setLocation(newLoc);
                return String.format("%s moves from %s to %s", this.toString(), currentLoc, newLoc);
            } else {
                return String.format("%s cannot move to %s as it is occupied", this.toString(), newLoc);
            }
        } else {
            return String.format("%s cannot move off the map.", this.toString());
        }
    }

    /**
     *   @params int -1 if left, +1 if right
     *   @return a String representing results of action
     **/
    public String turn(int leftOrRight) {
        int newDirection;
        String result = String.format("%s turned ", this.toString());

        if (leftOrRight == -1) {
            // turn left
            if (this.direction == 0) newDirection = 7;
            else                     newDirection = this.direction - 1;
            result += "left";
        } else {
            // turn right
            if (this.direction == 7) newDirection = 0;
            else                     newDirection = this.direction + 1;
            result += "right";
        }
        this.direction = newDirection;
        result += String.format(", now facing %s", DIRECTIONS[this.direction]);
        return result;
    }

    public String takeHit(int strengthOfAttack) {
        int newHealth = this.getHealth() - strengthOfAttack;
        if (newHealth <= 0) {
            this.health = 0;
            return String.format("%s has been sunk!", this.toString());
        } else {
            this.health = newHealth;
            return String.format("%s takes %d damage.", this.toString(), strengthOfAttack);
        }
    }

    /** assumes input Coordinates is valid
     *
     * @param c Coordinates (boat's new location)
     */
    public void setLocation(Coordinates c) {
        this.location = c;
    }

    @Override
    public String toString() {
        return this.getID();
    }
}
