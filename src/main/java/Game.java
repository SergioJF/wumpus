import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by sergiojimenezfemenia on 2/8/17.
 */
public class Game {

    private Board board;
    private Hunter hunter;
    private Boolean gameOver;
    private Integer dimensions;

    public Game(String name, Integer arrows, Integer dimension, Integer pits) {
        this.dimensions = dimension;
        this.board = new Board(dimension);
        this.board.initBoard(pits);
        this.hunter = new Hunter(name, arrows, new Coordinate(1, 1));
        this.gameOver = false;
    }

    public void play() {
        Scanner scan = new Scanner(System.in);
        HashMap<Orientation, CellPerceptions> perceptions = new HashMap<Orientation, CellPerceptions>();
        System.out.println();
        System.out.println("HUNT THE WUMPUS");
        System.out.println("Welcome " + this.getHunter().getName());
        System.out.println("Game start!!");

        while(!gameOver) {
            System.out.println();
            System.out.println("--------------------------------------------------------------");
            System.out.println("Menu: ");
            System.out.println("1: Move to the " + this.hunter.getOrientacion());
            System.out.println("2: Turn left" );
            System.out.println("3: Turn rigth");
            System.out.println("4: Shoot a arrow. You have "+ this.hunter.getArrows()+" arrows");
            System.out.println("0: Exit");
            System.out.println();
            System.out.println();
            this.hunterPerception(perceptions);
            printPerceptions(perceptions);


            if(this.getHunter().getGold()) {
                System.out.println("You already have the gold try to escape !!!");
            }
            System.out.print("Which option do you want to take? Option: ");
            int option = scan.nextInt();

            switch (option) {
                case -1: // CHEAT
                    board.printBoard();
                    break;
                case 0:
                    System.out.println("Going out ... ");
                    gameOver = true;
                    break;
                case 1:
                    if(perceptions.get(this.getHunter().getOrientacion()) == CellPerceptions.WALL) {
                        System.out.println("There is a wall you can not cross it !!");
                    } else {
                        this.getHunter().move();
                        gameOver = moved(this.getHunter().getPosition());

                    }
                    break;
                case 2:
                    this.getHunter().turnLeft();
                    System.out.println("The hunter has now turned left points " + this.getHunter().getOrientacion());
                    break;
                case 3:
                    this.getHunter().turnRigth();
                    System.out.println("The hunter has now turned rigth points " + this.getHunter().getOrientacion());
                    break;
                case 4:
                    if(this.getHunter().getArrows() > 0) {
                        this.shootArrow();
                    } else {
                        System.out.println("You can not shoot, you do not have arrows left!!4");
                    }

                    break;
                default:
                    throw new AssertionError("Unknown option: " + option);
            }

        }

        System.out.println("*********  GAME OVER **********");
    }

    private Boolean moved(Coordinate coordinate) {
        Boolean finish = false;

        if(board.getCell(coordinate) == CellPerceptions.WUMPUS) {
            System.out.println(CellPerceptions.WUMPUS.getPerception());
            finish = true;
        }

        if (board.getCell(coordinate) == CellPerceptions.PIT) {
            System.out.println(CellPerceptions.PIT.getPerception());
            finish = true;
        }

        if (board.getCell(coordinate) == CellPerceptions.GOLD) {
            System.out.println(CellPerceptions.GOLD.getPerception());
            this.getHunter().setGold(true);
            finish = false;
        }

        if(board.getCell(coordinate) == CellPerceptions.START && this.getHunter().getGold()) {
            System.out.println("This is the way out and I'm healthy and safe !!!!!");
            finish = true;
        }
        return finish;
    }

    private void shootArrow() {
        System.out.println("The hunter is preparing the bow and points " + this.getHunter().getOrientacion());
        this.getHunter().shoot();

        Coordinate arrowTracking = new Coordinate(this.getHunter().getPosition());
        isDead(arrowTracking, false);

    }

    private void isDead(Coordinate coordinate, Boolean dead) {


        if(board.isInside(coordinate) && !dead) {
            if(board.getCell(coordinate) == CellPerceptions.WUMPUS) {
                board.setCellEmpty(coordinate);
                System.out.println(CellPerceptions.SCREAM.getPerception());
                dead = true;
            } else {
                switch (this.getHunter().getOrientacion()) {
                    case NORTH:
                        coordinate.downX();
                        break;
                    case WEST:
                        coordinate.downY();
                        break;
                    case SOUTH:
                        coordinate.upX();
                        break;
                    case EAST:
                        coordinate.upY();
                        break;
                }

            }
            if(!board.isInside(coordinate)) {
                System.out.println(CellPerceptions.FAIL.getPerception());
            }

            isDead(coordinate, dead);
        }

    }

    public void printPerceptions(HashMap<Orientation, CellPerceptions> perceptions) {
        System.out.println("*******************************************************");
        System.out.println("The hunter is perceiving something . . . ");
        perceptions.forEach((k, v) -> {
            System.out.print("  In the " + k.toString() + " I perceive a ... " + v.getPerception());
            System.out.println();
        });
        System.out.println("*******************************************************");
    }

    public void hunterPerception(HashMap<Orientation, CellPerceptions> perceptions) {



        Coordinate north = new Coordinate(this.hunter.getPosition().getX()-1,  this.hunter.getPosition().getY());
        Coordinate south = new Coordinate(this.hunter.getPosition().getX()+1,  this.hunter.getPosition().getY());
        Coordinate west =  new Coordinate(this.hunter.getPosition().getX(),  this.hunter.getPosition().getY()-1);
        Coordinate east =  new Coordinate(this.hunter.getPosition().getX(),  this.hunter.getPosition().getY()+1);


        // Lógica para cargar un hashmap donde podrmos saber inidcando la orientación que percepción tienen el cazador en la casilla que se encuentra
        if(north.getX() == 0 ) {
            perceptions.put(Orientation.NORTH, CellPerceptions.WALL);
        }
        else {
            setCellPerception(north, Orientation.NORTH, perceptions);
        }

        if(south.getX() > this.dimensions) {
            perceptions.put(Orientation.SOUTH, CellPerceptions.WALL);
        }
        else {
            setCellPerception(south, Orientation.SOUTH, perceptions);
        }

        if(west.getY() == 0) {
            perceptions.put(Orientation.WEST, CellPerceptions.WALL);
        }
        else {
            setCellPerception(west, Orientation.WEST, perceptions);
        }

        if(east.getY() > this.dimensions) {
            perceptions.put(Orientation.EAST, CellPerceptions.WALL);
        }
        else {
            setCellPerception(east, Orientation.EAST, perceptions);
        }

    }

    private void setCellPerception(Coordinate coordinate, Orientation orientation, HashMap<Orientation, CellPerceptions> perceptions) {
        if (board.getCell(coordinate) == CellPerceptions.WUMPUS) {
            perceptions.put(orientation, CellPerceptions.STINK);
        }
        else if (board.getCell(coordinate) == CellPerceptions.PIT) {
            perceptions.put(orientation, CellPerceptions.BREEZE);
        }
        else if (board.getCell(coordinate) == CellPerceptions.EMPTY) {
            perceptions.put(orientation, CellPerceptions.EMPTY);
        }
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Hunter getHunter() {
        return hunter;
    }

    public void setHunter(Hunter hunter) {
        this.hunter = hunter;
    }
}
