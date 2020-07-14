package Test2;

import org.junit.Assert;
import org.junit.Test;

public class MainClass
{
    private int class_number = 20;

    @Test
    public void testGetClassNumber()
    {
        Assert.assertTrue("Returned value from getClassNumber is less or equal than 45 " ,getClassNumber() > 45);
    }




    public int getClassNumber()
    {
        return class_number;

    }

}

