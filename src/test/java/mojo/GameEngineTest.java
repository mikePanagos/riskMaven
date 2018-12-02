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

    @Test public void allocateUnitsTest(){

        GameEngine ge=GameEngine.getInit();


        Player p=new Player(123333,5,5);
        Territory t=new Territory("t","c",123333,6);
        p.addTerritory(t);
        String [] com={"5","5","t"};
        ge.allocateUnits(com, p);
     


        // System.out.println(+"does this work");

        assertTrue(" allocateUnitsTest ",t.getNumOfUnits()>4);
    }
    @Test public void tradeTest(){

        GameEngine ge=GameEngine.getInit();

        Player p=new Player(123333,5,5);
        p.addCard(new Card("Al","c"));
        p.addCard(new Card("Al","cc"));
        p.addCard(new Card("Al","c"));


        // System.out.println(+"does this work");

        assertTrue(" tradeTest ",ge.trade(p).equals("You got 3 more units. You now have 8 in total.\n" ));
    }


    @Test public void fortifyTest(){

        GameEngine ge=GameEngine.getInit();

        Player p=new Player(123333,5,5);
       Territory t=new Territory("ale","N",1111,5);
       Territory t2=new Territory("al","H",1111,5);

        assertTrue(" fortifyTest ",ge.fortify(t,t2,4).equals("Fortifying Action:\nNow al has 9 and ale has 1" ));
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
        assertTrue(" shuffleCardsTest ",(firstTerName!=p.getCards().get(0).getTerritoryName()&&secondTerName!=p.getCards().get(1).getTerritoryName()||(firstTerName!=p.getCards().get(0).getTerritoryName()||secondTerName!=p.getCards().get(1).getTerritoryName())));

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
        String commands[]={" ","a","aa","3"};
        assertTrue(" verifyCommandAttackTest ",GameEngine.verifyCommand("attack",commands, p));

    }
    @Test public void verifyCommandDefaultTest(){
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
        String commands[]={" ","a","aa","3"};
        assertTrue(" verifyCommandDefaultTest ",GameEngine.verifyCommand("",commands, p));

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
        String commands[]={" ","a","aa","3"};
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
        String commands[]={" ","a","aa","3"};
        assertTrue(" verifyCommandFortifyTest ",GameEngine.verifyCommand("fortify",commands, p));

    }
    @Test public void verifyCommandSurrenderTest(){
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
        String commands[]={"","a","aa","3"};
        assertTrue(" verifyCommandSurrenderTest ",GameEngine.verifyCommand("fortify",commands, p));

    }
    @Test public void verifyCommandFortifyTestFalse(){
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
        String commands[]={" ","a","a","3"};
        assertTrue(" verifyCommandFortifyTestFalse ",!GameEngine.verifyCommand("fortify",commands, p));

    }
    @Test public void verifyCommandFortifyTestFalse2(){
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
        String commands[]={" ","a","ffff","3"};
        assertTrue(" verifyCommandFortifyTestFalse ",!GameEngine.verifyCommand("fortify",commands, p));

    }
    @Test public void verifyCommandFortifyTestFalse3(){
        Setup s=Setup.getInstances(2);
        GameEngine ge=GameEngine.getInit();
        Player p=new Player(11111,3,4);
        Territory t=new Territory("a","coun",11111,1);
        Territory nextToT=new Territory("aa","coun",11111,5);
        List<Territory> tlist=new ArrayList<>();
        tlist.add(nextToT);
        t.setNeighboringTerritories(tlist);
        p.addTerritory(t);
        p.addTerritory(nextToT);

        p.setItsMyTurn(true);
        String commands[]={" ","a","aa","3"};
        assertTrue(" verifyCommandFortifyTestFalse ",!GameEngine.verifyCommand("fortify",commands, p));

    }
    @Test public void verifyCommandSurrenderTestFalse(){
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
        String commands[]={"","a","gggg","3"};
        assertTrue(" verifyCommandSurrenderTestFalse ",!GameEngine.verifyCommand("fortify",commands, p));

    }
    @Test public void verifyCommandAttackTestFalse1(){
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
        String commands[]={" ","a","awwwa","3"};
        assertTrue(" verifyCommandAttackTestFalse1 ",!GameEngine.verifyCommand("attack",commands, p));
    }
    @Test public void verifyCommandAttackTestfalse2(){
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
        String commands[]={" ","awww","aa","3"};
        assertTrue(" verifyCommandAttackTestfalse2 ",!GameEngine.verifyCommand("attack",commands, p));
    }
    @Test public void verifyCommandAttackTestfalse3(){
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
        String commands[]={" ","awww","aa","43"};
        assertTrue(" verifyCommandAttackTestfalse3 ",!GameEngine.verifyCommand("attack",commands, p));
    }
    @Test public void surrenderTest(){
        Setup s=Setup.getInstances(2);
        GameEngine ge=GameEngine.getInit();
        
        Player p=new Player(11111,3,4);

        p.addTerritory(new Territory("aaa","a",11111,4));
        int num=p.getTerritoryCount();
        try{
        ge.surrender(p);}
        catch(Exception e){
            System.err.println(e);
        }
        p.getTerritoryList().get(0).setOwner(0);
        System.out.println(num+" hello "+p.getTerritoryList().get(0).getOwner()+"          "+p.getTerritoryList().get(0).getNumOfUnits()+"\n\n\n\n\n");
        assertTrue(" surrenderTest ",p.getTerritoryList().get(0).getNumOfUnits()==0);
    }
    @Test public void initialPhaseTest(){
        Setup s=Setup.getInstances(2);
        GameEngine ge=GameEngine.getInit();
        
        Player p=new Player(11111,3,0);
        p.addTerritory(new Territory("aaa","a",11111,4));
       

        assertTrue(" initialPhaseTest ",ge.initialPhase(p));
    }
    @Test public void initialPhaseTest2(){
        Setup s=Setup.getInstances(2);
        GameEngine ge=GameEngine.getInit();
        
        Player p=new Player(11111,3,0);
        p.receivedUnits=false;
        p.addContinent("Asia");
        p.addContinent("North America");
        p.addContinent("Europe");
        p.addContinent("Africa");
        p.addContinent("South America");
        p.addContinent("Australia");
        p.addTerritory(new Territory("aaa","a",11111,4));
       

        assertTrue(" initialPhaseTest ",ge.initialPhase(p));
    }
    @Test public void initialPhaseTest3(){
        Setup s=Setup.getInstances(2);
        GameEngine ge=GameEngine.getInit();
        
        Player p=new Player(11111,0,0);
        p.receivedUnits=true;
        p.setAttackedAtLeastOnces(true);
       

        p.addTerritory(new Territory("aaa","a",11111,4));
       

        assertTrue(" initialPhaseTest ",!ge.initialPhase(p));
        ge.endingPhase(p);
    }

  
   
}