/**
 * Created by sergiojimenezfemenia on 2/8/17.
 */
public class Hunter {
    private String name;
    private Integer arrows;
    private Orientation orientacion;
    private Coordinate position;
    private Boolean gold;


    public Hunter(String name, Integer arrows, Coordinate coordinate) {
        this.name = name;
        this.gold = false;
        this.arrows = arrows;
        this.orientacion = Orientation.NORTH;
        this.position = coordinate;
    }

    public void shoot() {
        if(this.arrows > 0 ) {
            arrows--;
            System.out.println("I fired and I have "+arrows+" arrows left. Have I hit him? ...");
        } else {
            System.out.println("I do not have arrows left");
        }
    }

    public void move() {
        switch (orientacion) {
            case NORTH:
                position.downX();
                break;
            case WEST:
                position.downY();
                break;
            case SOUTH:
                position.upX();
                break;
            case EAST:
                position.upY();
                break;
            default:
                throw new AssertionError("Unknown orientation: " + this);

        }
    }

    public void turnLeft() {
        setOrientacion(this.orientacion.left());
    }

    public void turnRigth() {
        setOrientacion(this.orientacion.right());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArrows() {
        return arrows;
    }

    public void setArrows(Integer arrows) {
        this.arrows = arrows;
    }

    public Orientation getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(Orientation orientacion) {
        this.orientacion = orientacion;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public Boolean getGold() {
        return gold;
    }

    public void setGold(Boolean gold) {
        this.gold = gold;
    }
}
