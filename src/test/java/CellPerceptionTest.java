import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sergiojimenezfemenia on 2/8/17.
 */

public class CellPerceptionTest {

    @Test
    public void cellPerceptionTest() {

        String expectect = "There is a breeze there must be a pit near";
        String actual = CellPerceptions.BREEZE.getPerception();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expectect, actual);
    }


}
