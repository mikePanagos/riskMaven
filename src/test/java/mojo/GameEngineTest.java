package mojo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.risk.*;


import mojo.*;
//"successfully added log.txt to s3 bucket"

public class GameEngineTest{


    @Test public void giveunitsTest(){

        GameEngine ge=GameEngine.getInit();

        Territory terr=new Territory();

        

        assertTrue(" giveunitsTest ",ge.giveunits(terr, 5)==5 );
    }

    @Test public void tradeTest(){

        GameEngine ge=GameEngine.getInit();

        Player p=new Player(123333,5,5);
        p.addCard(new Card("Al","c"));
        p.addCard(new Card("Al","c"));
        p.addCard(new Card("Al","c"));


        // System.out.println(+"does this work");

        assertTrue(" tradeTest ",ge.trade(p).equals("You got 3 more units. You now have 8 in total." ));
    }

    @Test public void fortifyTest(){

        GameEngine ge=GameEngine.getInit();

        Player p=new Player(123333,5,5);
       Territory t=new Territory("ale","N",1111,5);
       Territory t2=new Territory("al","H",1111,5);

        assertTrue(" fortifyTest ",ge.fortify(t,t2,4).equals("Now al has 9 and ale has 1" ));
    }
    
    @Test public void handOutCardTest(){

        GameEngine ge=GameEngine.getInit();

        Player p=new Player(123333,5,5);
       Territory t=new Territory("ale","N",1111,5);
       Territory t2=new Territory("al","H",1111,5);
        // System.out.println(ge.handOutCard(p));
        assertTrue(" handOutCardTest ",ge.handOutCard(p).equals("Player 123333 is getting card Alaska Infantry" ));
        assertTrue(" handOutCardTest ",true);

    }
}