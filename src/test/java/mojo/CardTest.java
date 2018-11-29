package mojo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.risk.*;


import mojo.*;
//"successfully added log.txt to s3 bucket"

public class CardTest{


    @Test public void ConstructorTest(){
        Card c= new Card();
        //test for blank constructor


       assertTrue(" ",c.getTerritoryName().equals(""));
    }
    @Test public void Constructor2Test(){
        Card c= new Card();
        c.setTerritoryName("Alaska");
        c.setType("Cavalry");
        Card newC=new Card(c);
        //test for constructor with a Card passed in


       assertTrue(" ",newC.getTerritoryName().equals("Alaska"));
    }


    @Test public void PrintCardTest(){
        Card c= new Card();
        c.setTerritoryName("Alaska");
        c.setType("Cavalry");
        Card newC=new Card(c);
        //test for constructor with a Card passed in


       assertTrue("print card test",newC.printCard().equals("Cavalry Alaska"));
    }


   
}