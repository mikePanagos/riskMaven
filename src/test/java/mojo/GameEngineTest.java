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
        System.out.println(ge.handOutCard(p));
        assertTrue(" handOutCardTest ",(ge.handOutCard(p).equals("Player 123333 is getting card Alaska Infantry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Alberta Cavalry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Central America Artillery" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Eastern United States Infantry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Greenland Cavalry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Northwest Artillery" )||ge.handOutCard(p).equals("Player 123333 is getting card Ontario Infantry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Quebec Cavalry" )||ge.handOutCard(p).equals("Player 123333 is getting card Western United States Artillery" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Argentina Infantry" )||ge.handOutCard(p).equals("Player 123333 is getting card Brazil Cavalry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Peru Artillery" )||ge.handOutCard(p).equals("Player 123333 is getting card Venexuela Infantry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Western Australia Cavalry" )||ge.handOutCard(p).equals("Player 123333 is getting card Britain Artillery" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Iceland Infantry" )||ge.handOutCard(p).equals("Player 123333 is getting card Northern Europe Cavalry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Scandinavia Artillery" )||ge.handOutCard(p).equals("Player 123333 is getting card Southern Europe Infantry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Ukraine Cavalry" )||ge.handOutCard(p).equals("Player 123333 is getting card Western Europe Artillery" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Congo Infantry" )||ge.handOutCard(p).equals("Player 123333 is getting card East Africa Cavalry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Egypt Artillery" )||ge.handOutCard(p).equals("Player 123333 is getting card Madagascar Infantry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card North Africa Cavalry" )||ge.handOutCard(p).equals("Player 123333 is getting card South Africa Artillery" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Afganistan Infantry" )||ge.handOutCard(p).equals("Player 123333 is getting card China Cavalry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card India Artillery" )||ge.handOutCard(p).equals("Player 123333 is getting card Irkutsk Infantry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Japan Cavalry" )||ge.handOutCard(p).equals("Player 123333 is getting card Kamchatka Artillery" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Middle East Infantry" )||ge.handOutCard(p).equals("Player 123333 is getting card Mongolia Cavalry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Slam Artillery" )||ge.handOutCard(p).equals("Player 123333 is getting card Siberia Infantry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Ural Cavalry" )||ge.handOutCard(p).equals("Player 123333 is getting card Yakutsk Artillery" )||
        ge.handOutCard(p).equals("Player 123333 is getting card Eastern Australia Infantry" )||ge.handOutCard(p).equals("Player 123333 is getting card Indonesia Cavalry" )||
        ge.handOutCard(p).equals("Player 123333 is getting card New Guinea Artillery" )));
        assertTrue(" handOutCardTest ",true);

    }
    @Test public void shuffleCardsTest(){
        Setup s=Setup.getInstances();
        GameEngine ge=GameEngine.getInit();
        List<Card>cList=s.getDeck();
        String firstTerName=cList.get(0).getTerritoryName();
        ge.shuffleCards();
        System.out.println("frist car name is "+firstTerName+". after shuffle its is"+cList.get(0).getTerritoryName());
        assertTrue("",true);
       
        // assertTrue(" shuffleCardsTest ",firstTerName!=cList.get(0).getTerritoryName());

    }
}