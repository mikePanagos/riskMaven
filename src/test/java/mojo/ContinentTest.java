package mojo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

import mojo.Setup;

public class ContinentTest {

    @Test public void continentTest1(){
        Setup s=Setup.getInstances(2);
        Setup.SetupPLayers(2);
        List<Continent> c =s.getContinent();
        System.out.println(" ");
        System.out.println(c.get(0).getContinentName());



        assertTrue("testing continentTest",c.get(0).getContinentName().equals("North America"));
    }


}