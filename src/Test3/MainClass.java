package Test3;

import org.junit.Assert;
import org.junit.Test;

public class MainClass
{
    private String class_string = "Hellow world!";

    @Test
    public void testGetClassString()
    {
            Assert.assertTrue("String from getClassString has no Hello or hello: " + getClassString(),getClassString().contains("Hello") || getClassString().contains("hello"));
    }

    public String getClassString()
    {
        return class_string;
    }

}
