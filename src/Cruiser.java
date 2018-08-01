public class Cruiser extends ScoutBoat {
    public Cruiser(int team, Coordinates location, int direction) {
        super(team, location, direction, 3, 3);
    }

    public String getID() {
        return "C" + this.getTeam();
    }

    public String getActions() {
        String userAction = "Choose any of the following actions for the Cruiser:\n";
        userAction += "1. Move\n";
        userAction += "2. Turn left\n";
        userAction += "3. Turn right\n";
        return userAction;
    }

    @Override
    // Cruiser can move twice
    public String act(int[] choices, World world) {
        String result = "";
        for (int choice: choices) {
            for (int i = 0; i < 2; i++) {
                String action = "";
                if (choice == 1)
                    action += super.move(world);
                else if (choice == 2)
                    action += super.turn(-1);
                else if (choice == 3)
                    action += super.turn(1);
                else
                    action += "";
                result += action + "\n";
            }
        }
        return result;
    }

/*    public static void main(String[] args) {
        World world = new World(6, 8);
        Cruiser c = new Cruiser(1, new Coordinates(2, 2), 0);

        int[] actions = {1, 2, 3, 1};
        System.out.println(c.act(actions, world));
    }*/
}
