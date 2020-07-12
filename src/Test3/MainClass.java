package Test3;

import org.junit.Assert;
import org.junit.Test;

public class MainClass
{
    private String class_string = "Hellow world!";

    @Test
    public void testGetClassString()
    {

        if (getClassString().contains("Hello") | getClassString().contains("hello")) {

            System.out.println("Test passed");

        } else {
            Assert.fail("String from getClassString has no Hello or hello: " + getClassString());
        }

    }




    public String getClassString()
    {
        return class_string;

    }

}
