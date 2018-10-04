package mojo;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.risk.*;

public class PlayerTest {
   
    /**
     * test that when you added a continent to a player ownership it indeeds does increase the count
     */
    @Test public void playerTest1() {
        Player p = new Player(1, 40, 0);
        p.addContinent("Africa");
        
        assertTrue("someLibraryMethod should return 'true'", (p.getContinentCount()==1));
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
        System.out.println("test 1 nothing print");
        for (int i = 0; i < list.size(); i++) {

            System.out.println(list.get(i).getTerritoryName()+" "+list.get(i).getType());
            
        }
        assertTrue("someLibraryMethod should return 'true'", (list.size()==0));
    }
    /**
 * this test shows that when a player trys to trade cards they have to be the same type or it wont work.
 * if there where all the same card type list size whould be 3
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
        System.out.println("test2 ");

        for (int i = 0; i < list.size(); i++) {

            System.out.println(list.get(i).getTerritoryName()+" "+list.get(i).getType());
            
        }
        assertTrue("someLibraryMethod should return 'true'", (list.size()!=0));
    }
    // /**
    //  * test that when you added a armies to a player 
    //  */
    @Test public void playerTest() {
        Player p = new Player(1, 40, 0);
        p.addContinent("Africa");
        
        assertTrue("someLibraryMethod should return 'true'", (p.getArmiesCount()==40));
    }
    /**
     * test that when you added a armies to a player 
     */
    @Test public void playerTest3() {
        Player p = new Player(1, 40, 0);
        p.setArmiesCount(p.getArmiesCount()-6);        
        assertTrue("someLibraryMethod should return 'true'", (p.getArmiesCount()==34));
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
   System.out.println("test 3 "+playerList.get(1).printableTerritories());
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).printableTerritories().equals("0. Alaska army on 0. ")));
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
        System.out.println("test 4 "+playerList.get(1).printableTerritories());
        playerList.get(2).addTerritory(ter.get(1));
   
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).printableTerritories().equals("0. Alaska army on 0. ")));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(2).printableTerritories().equals("0. Alberta army on 0. ")));
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
   
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).printableTerritories().equals("0. Alaska army on 0. ")));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(2).printableTerritories().equals("0. Alberta army on 0. ")));
    }
}