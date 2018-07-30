import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class World {
    /*
    public static final int NORTH = 0;
    public static final int NORTHEAST = 1;
    public static final int EAST = 2;
    public static final int SOUTHEAST = 3;
    public static final int SOUTH = 4;
    public static final int SOUTHWEST = 5;
    public static final int WEST = 6;
    public static final int NORTHWEST = 7;
    */

    public Set<String> DIRECTIONS = new HashSet<>(Arrays.asList("N", "NE", "E", "S", "SE", "W", "NW"));
    private Boat[][] map; // bottom right corner: (width, length)

    public World(int width, int length) {
        // width & length must be between 4 - 10;
        if (width < 4) {
            width = 4;
        } else if (width > 10) {
            width = 10;
        }
        if (length < 4) {
            length = 4;
        } else if (length > 10) {
            length = 10;
        }
        map = new Boat[width][length]; // (x, y)
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
        return map[x][y];
    }

    public boolean isLocationValid(Coordinates c) {
        int x = c.getX();
        int y = c.getY();
        if (x < 0 || x > map.length || y < 0 || y > map[0].length) {
            return false;
        }
        return true;
    }

    public boolean isLocationOccupied(Coordinates c) {
        // return true if location is null; false otherwise;
        return map[c.getX()][c.getY()] == null;
    }

    public boolean setOccupant(Boat boat, Coordinates c) {
        if (isLocationValid(c)) {
            if (!isLocationOccupied(c)) {
                map[c.getX()][c.getY()] = boat;
                return true;
            }
        }
        return false;
    }

    public Coordinates getAdjacentLocation(Coordinates c, int direction) {
        if (!isLocationValid(c) || direction < 0 || direction > 7) {
            return null;
        }
        int x = c.getX();
        int y = c.getY();
        switch (direction) {
            case 0:
                y -= 1;
                break;
            case 1:
                x += 1;
                y -= 1;
                break;
            case 2:
                x += 1;
                break;
            case 3:
                x += 1;
                y += 1;
                break;
            case 4:
                y += 1;
                break;
            case 5:
                x -= 1;
                y += 1;
                break;
            case 6:
                x -= 1;
                break;
            case 7:
                x -= 1;
                y -= 1;
                break;
            default:
                break;
        }
        return new Coordinates(x, y);
    }

    public String drawTeamMap(Boat[] teamBoats, int view)
    {
        /*** NOTE: For this implementation, we will provide a general strategy and ask you to complete
         certain tasks. The basic procedure of this method is as follows:

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
        int yStart, yEnd, xStart, xEnd;
        Coordinates boatLocation;

        /*** TODO: Write a for-loop that repeats for each row in the map ***/
        for (int row = 0; row < teamView.length; row++) {
            /*** TODO: Write a for-loop that repeats for each column in the map ***/
            for (int col = 0; col < teamView[row].length; col++) {
                /*** TODO: Set each spot in the teamView array to the non-visible String (i.e., "###") ***/
                teamView[row][col] = "###";
            }
        }

        if (view != 1)
        {
            /*** TODO: Write a for-loop to repeat over all of the current team's boats, storing the current
             boat in a Boat variable, b
             ***/
            for (int row = 0; row < teamView.length; row++) {
                /*** TODO: Write a conditional statement checking if the current boat has more than 0 health ***/
                {
                    /*** TODO: Set yStart, yEnd, xStart and xEnd such that the for-loops below will iterate
                     over each visible space for the current boat, and will not produce an
                     ArrayIndexOutOfBoundsException (i.e., if a boat has vision 2, check 2 spaces
                     to the left and 2 to the right, but make sure not to index beyond the map
                     boundaries)
                     ***/
                    for (int y = yStart; y <= yEnd; y++)
                    {
                        for (int x = xStart; x <= xEnd; x++)
                        {
                            /*** TODO: Write a conditional to check if the game map has nothing stored in the
                             current space (in which case, we'll store the water String)
                             ***/
                            teamView[y][x] = "~~~";
                            else if (((Boat)map[y][x]).getHealth() <= 0)
                            teamView[y][x] = " ~ ";
                        else
                        {
                            /*** TODO: Write a conditional and the corresponding branches that add the boat
                             String with the appropriate information (location or direction) for
                             each boat that can be seen by the current boat
                             ***/
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