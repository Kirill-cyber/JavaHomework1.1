import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainClass {

    @Test
    public void testGetLocalNumber()
    {
        Assert.assertEquals("Incorrect value has returned from getLocalNumber", 14, this.getLocalNumber());
    }




    public int getLocalNumber()
    {
        return 14;

    }
}
