package mojo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.risk.*;

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
    @Test public void continentListTest(){
        Setup s=Setup.getInstances(2);
        Setup.SetupPLayers(2);
        List<Continent> c =s.getContinent();
        System.out.println("\n\n\n\n\n\n");
        List<Territory>t=new ArrayList<>();
        t=c.get(0).getContinentListOfTerritories();
        String m="";
        for (Territory var : t) {
            m+=var.getName()+"\n";
            
        }
        System.out.println(m);
        System.out.println("\n\n\n\n\n\n");




        assertTrue("testing continentTest",m.equals("Alaska\n"+
        "Alberta\n"+
        "Central_America\n"+
        "Eastern_United_States\n"+
        "Greenland\n"+
        "Northwest\n"+
        "Ontario\n"+
        "Quebec\n"+
        "Western_United_States\n"));
    }


}