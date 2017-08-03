import java.util.Scanner;

/**
 * Created by sergiojimenezfemenia on 3/8/17.
 */
public class HuntTheWumpus {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Preparing the game, enter the following parameters");
        System.out.print("Name of the hunter: ");
        String name =  scan.nextLine();
        System.out.print("Arrows: ");
        int arrows = scan.nextInt();
        System.out.print("Dimension of the board: ");
        int dimension = scan.nextInt();
        System.out.print("Number of pits: ");
        int pits = scan.nextInt();
        Game game = new Game(name, arrows, dimension, pits);
        game.play();
    }
}
