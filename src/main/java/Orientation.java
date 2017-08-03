/**
 * Created by sergiojimenezfemenia on 2/8/17.
 */
public enum Orientation {
    GOAL,
    NORTH,
    SOUTH,
    EAST,
    WEST;

    Orientation left(){
        switch (this) {
            case NORTH:
                return WEST;
            case WEST:
                return SOUTH;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            default:
                throw new AssertionError("Unknown orientation: " + this);
        }
    }

    Orientation right(){
        switch (this) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                throw new AssertionError("Unknown orientation: " + this);
        }
    }
}
