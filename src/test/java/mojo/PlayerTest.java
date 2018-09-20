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
     * test that when you added a armies to a player 
     */
    @Test public void playerTest2() {
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
        Setup s= new Setup();
        s.setup(6);
        List<Territory> ter= new ArrayList<>(s.getTerritories());
        List<Player> playerList = new ArrayList<>(s.getPlayers());
        playerList.get(1).addTerritory(ter.get(0));
   
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).getTerritories().get(0).getName().equals("Alaska")));
    }
   
     /**
     *  testing when you add territroys to multiple players
     */
    @Test public void playerTest5() {
        Setup s= new Setup();
        s.setup(6);
        List<Territory> ter=s.getTerritories();
        List<Player> playerList =s.getPlayers();
        playerList.get(1).addTerritory(ter.get(0));
        playerList.get(2).addTerritory(ter.get(1));
   
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).getTerritories().get(0).getName().equals("Alaska")));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(2).getTerritories().get(0).getName().equals("Alberta")));
    }
     /**
     *  testing when you add territroys to multiple players
     */
    @Test public void playerTest6() {
        Setup s= new Setup();
        s.setup(6);
        List<Territory> ter=s.getTerritories();
        List<Player> playerList =s.getPlayers();
        playerList.get(1).addTerritory(ter.get(0));
        playerList.get(2).addTerritory(ter.get(1));
   
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).getTerritories().get(0).getName().equals("Alaska")));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(2).getTerritories().get(0).getName().equals("Alberta")));
    }
}