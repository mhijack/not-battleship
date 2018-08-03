public class World {
    public static final int NORTH = 0;
    public static final int NORTHEAST = 1;
    public static final int EAST = 2;
    public static final int SOUTHEAST = 3;
    public static final int SOUTH = 4;
    public static final int SOUTHWEST = 5;
    public static final int WEST = 6;
    public static final int NORTHWEST = 7;

    public String[] DIRECTIONS = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
    private Boat[][] map; // bottom right corner: (width, length)

    public World(int width, int height) {
        // width & length must be between 4 - 10;
        if (width < 4)          width = 4;
        else if (width > 10)    width = 10;
        if (height < 4)         height = 4;
        else if (height > 10)   height = 10;

        map = new Boat[height][width]; // (y, x)
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                map[row][col] = null;
            }
        }
    }

    public int getWidth() {
        return map[0].length;
    }

    public int getHeight() {
        return map.length;
    }

    public Boat getOccupant(Coordinates c) {
        int x = c.getX();
        int y = c.getY();
        return map[y][x];
    }

    public boolean isLocationValid(Coordinates c) {
        int x = c.getX();
        int y = c.getY();
        if (x < 0 || x >= this.getWidth() || y < 0 || y >= this.getHeight())
            return false;
        return true;
    }

    public boolean isLocationOccupied(Coordinates c) {
        // return true if location is null; false otherwise;
        return !(map[c.getY()][c.getX()] == null);
    }

    public boolean setOccupant(Boat boat, Coordinates c) {
        if (isLocationValid(c)) {
            if (boat == null || !isLocationOccupied(c)) {
                map[c.getY()][c.getX()] = boat;
                return true;
            }
        }
        return false;
    }

    public String[] getDIRECTIONS() {
        return this.DIRECTIONS;
    }

    public Coordinates getAdjacentLocation(Coordinates c, int direction) {
//        if (!isLocationValid(c)) return null;
        int x = c.getX();
        int y = c.getY();
        switch (direction) {
            case NORTH:
                y -= 1;
                break;
            case NORTHEAST:
                x += 1;
                y -= 1;
                break;
            case EAST:
                x += 1;
                break;
            case SOUTHEAST:
                x += 1;
                y += 1;
                break;
            case SOUTH:
                y += 1;
                break;
            case SOUTHWEST:
                x -= 1;
                y += 1;
                break;
            case WEST:
                x -= 1;
                break;
            case NORTHWEST:
                x -= 1;
                y -= 1;
                break;
            default:
                // invalid direction, return null;
                break;
        }
        Coordinates adjacentLoc = new Coordinates(x, y);
        if (isLocationValid(adjacentLoc)) return adjacentLoc;
        return null;
    }

    public String drawTeamMap(Boat[] teamBoats, int view)
    {
        /***
         1) Create a two-dimensional map with all spaces marked as invisible
         2) If the view is a player view (i.e., not the in-between view):
         a) Loop through all of the current team's boats
         b) For each boat that is still alive, get the endpoints for that boat's vision
         c) Use the game map to determine whether to draw the water, or another boat for
         each position within that boat's vision
         d) Write the proper information (direction or health) along with the boat's
         standard output
         3) Adding the row and column headers, use your temporary map to build the map String
         that is returned to the function caller
         ***/
        String drawMap = "";
        String[][] teamView = new String[this.getHeight()][this.getWidth()];
        int yStart, yEnd, xStart, xEnd; // vision boundary
        Coordinates boatLocation;

        /*** TODO: Write a for-loop that repeats for each row in the map ***/
        for (int row = 0; row < teamView.length; row++) {
            /*** TODO: Write a for-loop that repeats for each column in the map ***/
            for (int col = 0; col < teamView[row].length; col++) {
                /*** TODO: Set each spot in the teamView array to the non-visible String (i.e., "###") ***/
                teamView[row][col] = "###";
            }
        }

        // str += (char) (i + 'A') + " ";

        if (view != 1) {
            /*** TODO: Write a for-loop to repeat over all of the current team's boats, storing the current
             boat in a Boat variable, b
             ***/
            for (int i = 0; i < teamBoats.length; i++) {
                Boat b = teamBoats[i];

                /*** TODO: Write a conditional statement checking if the current boat has more than 0 health ***/
                if (b != null && b.getHealth() > 0) {
                    /*** TODO: Set yStart, yEnd, xStart and xEnd such that the for-loops below will iterate
                     over each visible space for the current boat, and will not produce an
                     ArrayIndexOutOfBoundsException (i.e., if a boat has vision 2, check 2 spaces
                     to the left and 2 to the right, but make sure not to index beyond the map
                     boundaries)
                     ***/

                    int vision = b.getVision();
                    boatLocation = b.getLocation();

                    yStart = boatLocation.getY() - vision;
                    if (yStart < 0)
                        yStart = 0;

                    yEnd = boatLocation.getY() + vision;
                    if (yEnd > this.getHeight() - 1)
                        yEnd = this.getHeight() - 1;

                    xStart = boatLocation.getX() - vision;
                    if (xStart < 0)
                        xStart = 0;

                    xEnd = boatLocation.getX() + vision;
                    if (xEnd > this.getWidth() - 1)
                        xEnd = this.getWidth() - 1;

                    for (int y = yStart; y <= yEnd; y++) {
                        for (int x = xStart; x <= xEnd; x++) {
                            /*** TODO: Write a conditional to check if the game map has nothing stored in the
                             current space (in which case, we'll store the water String)
                             ***/
                            if (map[y][x] == null) {
                                teamView[y][x] = "~~~";
                            } else if ((map[y][x]).getHealth() <= 0) {
                                teamView[y][x] = " ~ ";
                            } else {
                                /*** TODO: Write a conditional and the corresponding branches that add the boat
                                 String with the appropriate information (location or direction) for
                                 each boat that can be seen by the current boat
                                 ***/
                                String boatName = String.format("%s", map[y][x].toString());
                                String info;
                                if (view == 2)      info = String.format("%c", map[y][x].getDirection());
                                else                info = String.format("%d", map[y][x].getHealth());
                                teamView[y][x] = info + boatName;
                            }
                        }
                    }
                }
            }
        }

        /*** NOTE: The code below stores the map as a complete String. You should make sure that you understand
         its functionality, but you do not need to make changes if you follow the instruction above
         ***/
        for (int y = -1; y < this.getHeight(); y++)
        {
            drawMap += (char)(y + 65) + " ";
            for (int x = 0; x < this.getWidth(); x++)
            {
                if (y == -1)
                {
                    drawMap += " " + (x + 1) + ((x < 9) ? " " : "");
                }
                else
                {
                    drawMap += teamView[y][x];
                }
            }
            drawMap += "\n";
        }

        return drawMap;
    }
}