import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sergiojimenezfemenia on 2/8/17.
 */
public class BoardTest {

    @Test
    public void initBoardTest() {

        Integer pits = 4;
        Integer dimension = 10;
        Board board = new Board(dimension);
        board.initBoard(pits);
        board.printBoard();

        Assert.assertNotNull(board);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionTest() {
        Board board = new Board(0);
    }

}
