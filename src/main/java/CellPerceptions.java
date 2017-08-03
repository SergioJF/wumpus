/**
 * Created by sergiojimenezfemenia on 2/8/17.
 */
public enum CellPerceptions {

    WUMPUS("I'm dead for a wumpus"),
    STINK("Stinks of dirty wumpus"),
    SCREAM("I killed the wumpus I heard his scream"),
    BREEZE("There is a breeze there must be a pit near"),
    PIT("I fall by a pit"),
    GOLD("How much shine will be gold. I'll take it!!"),
    WALL("There is a wall"),
    START("This is the entrance I will have to leave"),
    FAIL("I do not hear any scream I will have missed the shot"),
    EMPTY("I do not perceive anything");

    private String perception;

    CellPerceptions(String perception) {
        this.perception = perception;
    }

    public String getPerception() {
        return perception;
    }
}
