package mojo;

import org.glassfish.grizzly.utils.ArrayUtils;
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
        p.addCard(new Card("Al","cc"));
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
        Setup s=Setup.getInstances(2);
        GameEngine ge=GameEngine.getInit();

        Player p=new Player(123333,5,5);
        System.out.println(ge.handOutCard(p));
        assertTrue(" handOutCardTest ",p.getCardCount()>0);

    }
    @Test public void shuffleCardsTest(){
        Setup s=Setup.getInstances(2);
        GameEngine ge=GameEngine.getInit();
        List<Card>cList=s.getDeck();
        String firstTerName=cList.get(0).getTerritoryName();
        String secondTerName=cList.get(1).getTerritoryName();

        ge.shuffleCards();
        Player p=new Player(1111111,3,3);
        ge.handOutCard(p);
        ge.handOutCard(p);
        ge.handOutCard(p);
        assertTrue(" shuffleCardsTest ",(firstTerName!=p.getCards().get(0).getTerritoryName()&&secondTerName!=p.getCards().get(1).getTerritoryName()));

    }
    @Test public void verifyCommandAttackTest(){
        Setup s=Setup.getInstances(2);
        GameEngine ge=GameEngine.getInit();
        Player p=new Player(11111,3,4);
        Territory t=new Territory("a","coun",11111,66);
        Territory nextToT=new Territory("aa","coun",444,5);
        List<Territory> tlist=new ArrayList<>();
        tlist.add(nextToT);
        t.setNeighboringTerritories(tlist);
        p.addTerritory(t);
        p.setItsMyTurn(true);
        String commands[]={"a","aa","3"};
        assertTrue(" verifyCommandAttackTest ",GameEngine.verifyCommand("attack",commands, p));

    }
    @Test public void verifyCommandTradeTest(){
        Setup s=Setup.getInstances(2);
        GameEngine ge=GameEngine.getInit();
        Player p=new Player(11111,3,4);
        Territory t=new Territory("a","coun",11111,66);
        Territory nextToT=new Territory("aa","coun",444,5);
        List<Territory> tlist=new ArrayList<>();
        tlist.add(nextToT);
        t.setNeighboringTerritories(tlist);
        p.addTerritory(t);
        p.setItsMyTurn(true);
        String commands[]={"a","aa","3"};
        assertTrue(" verifyCommandTradeTest ",GameEngine.verifyCommand("trade",commands, p));

    }
    @Test public void verifyCommandFortifyTest(){
        Setup s=Setup.getInstances(2);
        GameEngine ge=GameEngine.getInit();
        Player p=new Player(11111,3,4);
        Territory t=new Territory("a","coun",11111,66);
        Territory nextToT=new Territory("aa","coun",11111,5);
        List<Territory> tlist=new ArrayList<>();
        tlist.add(nextToT);
        t.setNeighboringTerritories(tlist);
        p.addTerritory(t);
        p.addTerritory(nextToT);

        p.setItsMyTurn(true);
        String commands[]={"a","aa","3"};
        assertTrue(" verifyCommandFortifyTest ",GameEngine.verifyCommand("fortify",commands, p));

    }
}