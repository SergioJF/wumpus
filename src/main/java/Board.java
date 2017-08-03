import java.util.HashMap;

/**
 * Created by sergiojimenezfemenia on 2/8/17.
 */
public class Board {

    private static final Integer WUMPUS = 1;

    private HashMap<Coordinate, CellPerceptions> cells;
    private Integer dimension;
    private Integer pits;

    public void setCellEmpty(Coordinate coordinate) {
        this.cells.put(coordinate, CellPerceptions.EMPTY);
    }

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public Board(Integer dimension) throws IllegalArgumentException {
        try {
            if(dimension == null || dimension < 1) {
                throw new IllegalArgumentException();
            }

            this.dimension = dimension;
            this.cells = new HashMap<Coordinate, CellPerceptions>();
            for(int i = 1; i <= dimension; i++){
                for(int j = 1; j <= dimension; j++){

                    Coordinate aux = new Coordinate(i, j);
                    cells.put(aux, CellPerceptions.EMPTY);

                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Incorrect dimensions");
            throw new IllegalArgumentException();
        }
    }

    public void initBoard(Integer pits) {
        if(!this.cells.isEmpty()) {

            Coordinate initialCoord = new Coordinate(1, 1);
            this.cells.put(initialCoord, CellPerceptions.START);

            // Inicializamos de forma aleatoria los pozos a lo largo del tablero
            while(pits > 0){

                Double x = Math.random() * getDimension() + 1;
                Double y = Math.random() * getDimension() + 1;

                Coordinate coordinate = new Coordinate(x.intValue(), y.intValue());

                if (!initialCoord.equals(coordinate)) {

                    if (this.cells.get(coordinate) == CellPerceptions.EMPTY) {
                        this.cells.put(coordinate, CellPerceptions.PIT);
                        pits--;
                    }
                }
            }

            // Inicializamos la posición del wumpu en el tablero
            Boolean wimpus = false;
            while(!wimpus){

                Double x = Math.random() * getDimension() + 1;
                Double y = Math.random() * getDimension() + 1;

                Coordinate coordinate = new Coordinate(x.intValue(), y.intValue());

                if (!initialCoord.equals(coordinate)) {

                    if (this.cells.get(coordinate) == CellPerceptions.EMPTY) {
                        this.cells.put(coordinate, CellPerceptions.WUMPUS);
                        wimpus = true;
                    }
                }
            }

            // Inicializamos la posición del oro en el tablero
            Boolean gold = false;
            while(!gold){

                Double x = Math.random() * getDimension() + 1;
                Double y = Math.random() * getDimension() + 1;

                Coordinate coordinate = new Coordinate(x.intValue(), y.intValue());

                if (!initialCoord.equals(coordinate)) {

                    if (this.cells.get(coordinate) == CellPerceptions.EMPTY) {
                        this.cells.put(coordinate, CellPerceptions.GOLD);
                        gold = true;
                    }
                }
            }


        }
    }

    public void printBoard() {
        for (int i = 1; i <= dimension; i++) {
            for (int j = 1; j <= dimension; j++) {
                Coordinate aux = new Coordinate(i, j);
                System.out.print("(" +i + ","+ j + ")" + cells.get(aux) + " ");
            }
            System.out.println();
        }
    }

    public CellPerceptions getCell(Coordinate coordinate) {
        return this.cells.get(coordinate);
    }

    public Boolean isInside(Coordinate coordinate) {
        Boolean inside = false;

        if (coordinate == null) {
            return inside;
        }

        if(coordinate.getX() < 1 || coordinate.getX() > this.getDimension() || coordinate.getY() < 1 || coordinate.getY() > this.getDimension()) {
            inside = false;
        } else {
            inside = true;
        }
        return inside;
    }
}



