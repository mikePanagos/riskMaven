package mojo;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.risk.*;
import mojo.*;

public class PlayerTest {
   
    /**
     * test that when you added a continent to a player ownership it indeeds does increase the count
     */
    @Test public void playerTest1() {
        Player p = new Player(1, 40, 0);
        p.addContinent("Africa");
        
        assertTrue("playerTest1 should return 'true'", (p.getContinentCount()==1));
    }
/**
 * this test shows that when a player trys to trade cards they have to be the same type or it wont work.
 * if there where all the same card type list size whould be 3
 */
    @Test public void playerTest2() {
        Player p = new Player(1, 40, 0);
        Card card1=new Card("Alaska", "Infantry");
        Card card5=new Card("Greenland", "Cavalry");
        Card card7=new Card("Ontario", "Infantry");
        p.addCard(card1);
        p.addCard(card5);
        p.addCard(card7);

        List<Card> list=p.getCards();
        System.out.println(" ");

        System.out.println("test 1 nothing print");
        for (int i = 0; i < list.size(); i++) {

            System.out.println(list.get(i).getTerritoryName()+" "+list.get(i).getType());
            
        }
        assertTrue("playerTest2 should return 'true'", (list.size()!=0));
    }
    /**
 * this test shows that when a player tries to trade cards they have to be the same type or it wont work.
 * if there where all the same card type list size would be 3
 */
    @Test public void playerCardTest2() {
        Player p = new Player(1, 40, 0);
        Card card1=new Card("Alaska", "Infantry");
        Card card5=new Card("Greenland", "Infantry");
        Card card7=new Card("Ontario", "Infantry");
        p.addCard(card1);
        p.addCard(card5);
        p.addCard(card7);

        List<Card> list=p.getCards();
        System.out.println(" ");

        System.out.println("test2 ");

        for (int i = 0; i < list.size(); i++) {

            System.out.println(list.get(i).getTerritoryName()+" "+list.get(i).getType());
            
        }
        assertTrue("playerCardTest2 should return 'true'", (list.size()!=0));
    }
    @Test public void printCardsTest() {
        Player p = new Player(1, 40, 0);
        Card card1=new Card("Alaska", "Infantry");
        Card card2=new Card("Greenland", "Infantry");
        Card card3=new Card("Ontario", "Infantry");
        p.addCard(card1);
        p.addCard(card2);
        p.addCard(card3);

        List<Card> list=p.getCards();
        System.out.println(" ");

        assertTrue("printCardsTest should return 'true'", (p.printCards().equals("[Alaska Infantry]\n[Greenland Infantry]\n[Ontario Infantry]\n")));
    }
    // /**
    //  * test that when you added a armies to a player 
    //  */
    @Test public void playerTest() {
        Player p = new Player(1, 40, 0);
        p.addContinent("Africa");
        
        assertTrue("playerTest should return 'true'", (p.getArmiesCount()==40));
    }
    /**
     * test that when you added a armies to a player 
     */
    @Test public void playerTest3() {
        Player p = new Player(1, 40, 0);
        p.setArmiesCount(p.getArmiesCount()-6);        
        assertTrue("playerTest3 should return 'true'", (p.getArmiesCount()==34));
    }
     /**
     * test that when you add a territory to a player 
     */
    @Test public void playerTest4() {
        Setup s= Setup.getInstances(3);
        Setup.SetupPLayers(6);
        List<Territory> ter= s.getTerritories();
        List<Player> playerList = s.getPlayers();
        playerList.get(1).addTerritory(ter.get(0));
        System.out.println(" ");

   System.out.println("test 3 "+playerList.get(1).printableTerritories());
        assertTrue("playerTest4 should return 'true'", (playerList.get(1).printableTerritories().equals("0. Alaska army on 0. ")));
    }
   
     /**
     *  testing when you add territroys to multiple players
     */
    @Test public void playerTest5() {
        // s=null;
        Setup s= Setup.getInstances(1);
        Setup.SetupPLayers(6);
        List<Territory> ter=s.getTerritories();
        List<Player> playerList =s.getPlayers();
        playerList.get(1).addTerritory(ter.get(0));
        System.out.println(" ");

        System.out.println("test 4 "+playerList.get(1).printableTerritories());
        playerList.get(2).addTerritory(ter.get(1));
   
        assertTrue("playerTest5 should return 'true'", (playerList.get(1).printableTerritories().equals("0. Alaska army on 0. ")));
        assertTrue("playerTest5 should return 'true'", (playerList.get(2).printableTerritories().equals("0. Alberta army on 0. ")));
    }
     /**
     *  testing when you add territroys to multiple players
     */
    @Test public void playerTest6() {
        Setup s= Setup.getInstances(2);
        Setup.SetupPLayers(6);
        List<Territory> ter=s.getTerritories();
        List<Player> playerList =s.getPlayers();
        playerList.get(1).addTerritory(ter.get(0));
        playerList.get(2).addTerritory(ter.get(1));//0. Alberta army on 0.
   
        assertTrue("playerTest6 should return 'true'", (playerList.get(1).printableTerritories().equals("0. Alaska army on 0. ")));
        assertTrue("playerTest6 should return 'true'", (playerList.get(2).printableTerritories().equals("0. Alberta army on 0. ")));
    }

    @Test public void setPointCountTest() {
        Setup s= Setup.getInstances(2);
        Setup.SetupPLayers(6);
        List<Player> playerList =s.getPlayers();
        playerList.get(2).setPointCount(10000);
   
        assertTrue("setPointCountTest should return 'true'", playerList.get(2).getPointCount()==10000);
       
    }

    @Test public void setPointCountTotalTest() {
        Setup s= Setup.getInstances(2);
        Setup.SetupPLayers(6);
        List<Player> playerList =s.getPlayers();
        playerList.get(2).setPointCount();
   
        assertTrue("setPointCountTotalTest", playerList.get(2).getPointCount()==0);
       
    }
    @Test public void removeTerritoryByNameTest(){
        Setup s= Setup.getInstances(2);
        List<Player> playerList =new ArrayList<>();
        playerList.add(new Player(1111111,23,3));
        playerList.add(new Player(444,3,3));
        s.makePlayerList(playerList);
        
        //1 means it was successful
        assertTrue("removeTerritoryByNameTest ", playerList.get(0).removeTerritoryByName("Alaska")==1);
    }

    @Test public void setTerritoryCountTest(){
        Setup s= Setup.getInstances(2);
        List<Player> playerList =new ArrayList<>();
        playerList.add(new Player(1111111,23,3));
        playerList.add(new Player(444,3,3));
        s.makePlayerList(playerList);
        playerList.get(0).setTerritoryCount();
        
        //1 means it was successful
        assertTrue("setTerritoryCountTest ", playerList.get(0).getTerritoryCount()==21);
    }


    @Test public void getPrintableListOfTerritoryThatCanAttackTest(){
        Setup s= Setup.getInstances(2);
        List<Player> playerList =new ArrayList<>();
       
        playerList.add(new Player(1111111,23,3));
        playerList.add(new Player(444,3,3));
        s.makePlayerList(playerList);
        List<Territory> ter=s.getTerritories();
        System.out.println("\n\n");
        playerList.get(0).getTerritory(0).setNumOfUnits(5);
        System.out.println("\n\n");
    
        assertTrue("getPrintableListOfTerritoryThatCanAttackTest ", playerList.get(0).getPrintableListOfTerritoryThatCanAttack().equals("0. Alaska "));
    }


    @Test public void getTerritoryThatCanAttackTest(){
        Setup s= Setup.getInstances(2);
        List<Player> playerList =new ArrayList<>();
       
        playerList.add(new Player(1111111,23,3));
        playerList.add(new Player(444,3,3));
        s.makePlayerList(playerList);
        List<Territory> ter=s.getTerritories();
        System.out.println("\n\n");
        playerList.get(0).getTerritory(0).setNumOfUnits(5);
        System.out.println("\n\n");
    
        assertTrue("getTerritoryThatCanAttackTest", playerList.get(0).getTerritoryThatCanAttack(0).getName().equals("Alaska"));
    }
    @Test public void getAttackableTerritoryCountTest(){
        Setup s= Setup.getInstances(2);
        List<Player> playerList =new ArrayList<>();
       
        playerList.add(new Player(1111111,23,3));
        playerList.add(new Player(444,3,3));
        s.makePlayerList(playerList);
        List<Territory> ter=s.getTerritories();
        System.out.println("\n\n");
        playerList.get(0).getTerritory(0).setNumOfUnits(5);
        System.out.println("\n\n");
    
        assertTrue("getAttackableTerritoryCountTest", playerList.get(0).getAttackableTerritoryCount()==1);
    }

    @Test public void removeContinentTest(){
        Player p=new Player(1111111,23,3);
        p.addContinent("north");
        p.getContinents();
        p.removeContinent("north");
  
    
        assertTrue("removeContinentTest", p.getContinentCount()==0);
    }

    @Test public void removeCardTest(){
        Player p=new Player(1111111,23,3);
        Card c=new Card("Cav","Alaska");
        p.addCard(c);
        p.removeCard(c);
      
    
        assertTrue("removeContinentTest", p.getContinentCount()==0);
    }
// rollDices
@Test public void rollDicesTest(){
    Player p=new Player(1111111,23,3);
    Card c=new Card("Cav","Alaska");
    p.rollDices(2);
    p.rollDices(2);
  

    assertTrue("rollDicesTest", p.getDice().get(0)==1||p.getDice().get(0)==2||p.getDice().get(0)==3||p.getDice().get(0)==4||p.getDice().get(0)==5||p.getDice().get(0)==6);
    
}


// setPrevTerritoryCount

@Test public void setPrevTerritoryCountTest(){
    Player p=new Player(1111111,23,3);
    p.setPrevTerritoryCount(5);
    

    assertTrue("setPrevTerritoryCountTest", p.getPrevTerritoryCount()==5);
    
}

@Test public void getAttackedAtLeastOncesTest(){
    Player p=new Player(1111111,23,3);
    p.setAttackedAtLeastOnces(false);
    

    assertTrue("getAttackedAtLeastOncesTest", p.getAttackedAtLeastOnces()==false);
    
}

// getItsMyTurn
@Test public void getItsMyTurnTest(){
    Player p=new Player(1111111,23,3);
    assertTrue("getItsMyTurnTest", p.getItsMyTurn()==false);
    
}
@Test public void setSelectedMoveTest(){
    Player p=new Player(1111111,23,3);
    p.setSelectedMove("attack");
    assertTrue("setSelectedMoveTest", p.getSelectedMove().equals("attack"));
    
}

// printTerritoriesVerbose
@Test public void printTerritoriesVerboseTest(){
    Player p=new Player(1111111,23,3);
  
    Territory japan = new Territory("japan", "Asia", 1111111, 0);
    Territory mongola = new Territory("mongola", "Asia",  1111111, 0); 
    p.addTerritory(japan);
    p.addTerritory(mongola);
    assertTrue("printTerritoriesVerboseTest", p.printTerritoriesVerbose().equals("Territory Name: japan\n"+"Number of units allocated: 0\nTerritory Name: mongola\n"+"Number of units allocated: 0\n"));
    
}

// printFortifyTerritories
@Test public void printFortifyTerritoriesTest(){
    Player p=new Player(1111111,23,3);
  
    Territory japan = new Territory("japan", "Asia", 1111111, 6);
    Territory mongola = new Territory("mongola", "Asia",  1111111, 0); 
    p.addTerritory(japan);
    p.addTerritory(mongola);
    System.out.println(p.printFortifyTerritories());
    System.out.println("");
    System.out.println("Territory Name: japan\nUnits: 6\n--------------------------------------\nCan fortify the following territories:\n--------------------------------------\nNone \n");
    assertTrue("",true);
    assertTrue("printFortifyTerritoriesTest", p.printFortifyTerritories().equals("Territory Name: japan\nUnits: 6\n--------------------------------------\nCan fortify the following territories:\n--------------------------------------\nNone \n"));
    
}

@Test public void printFortifyTerritoriesTest2(){
    Player p=new Player(1111111,23,3);
  
    Territory japan = new Territory("japan", "Asia", 1111111, 6);
    Territory mongola = new Territory("mongola", "Asia",  1111111, 0); 
    List<Territory> t=new ArrayList<>();
    t.add(mongola);
    japan.setNeighboringTerritories(t);
    List<Territory> t2=new ArrayList<>();
    t2.add(japan);
    mongola.setNeighboringTerritories(t2);
    p.addTerritory(japan);
    p.addTerritory(mongola);
    System.out.println("\n\n"+mongola.getSurroundingAllies().size()+"===number");
    System.out.println(p.printFortifyTerritories());
    System.out.println("");
    String shouldBe="Territory Name: japan\nUnits: 6\n--------------------------------------\nCan fortify the following territories:\n--------------------------------------\nTerritory Name: mongola\nUnits: 0\n";

    assertTrue("printFortifyTerritoriesTest", p.printFortifyTerritories().equals(shouldBe));
    
}
}