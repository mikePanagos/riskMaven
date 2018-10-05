package mojo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

import mojo.risk.*;

public class SetupTest {
    /**
     * test player id //
     */
    @Test
    public void setupPlayer2() {
        Setup s = Setup.getInstances(2);
        Setup.SetupPLayers(2);
        List<Player> playerList = new ArrayList<>(s.getPlayers());

        assertTrue("someLibraryMethod should return 'true'", (playerList.get(0).getId() == 1));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).getId() == 2));

        // for (int i = 0; i < playerList.size(); i++) {
        //     System.out.println("player name is " + playerList.get(i).getId());
        // }
    }

    /**
     * test player id
     */
    @Test
    public void setupPlayer3() {
        Setup s = Setup.getInstances(2);
        Setup.SetupPLayers(3);

        List<Player> playerList = new ArrayList<>(s.getPlayers());
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(0).getId() == 1));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).getId() == 2));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(2).getId() == 3));
    }

    /**
     * test player id
     */
    @Test
    public void setupPlayer4() {
        Setup s = Setup.getInstances(2);
        Setup.SetupPLayers(4);
        List<Player> playerList = new ArrayList<>(s.getPlayers());
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(0).getId() == 1));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).getId() == 2));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(2).getId() == 3));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(3).getId() == 4));
    }

    /**
     * test player id
     */
    @Test
    public void setupPlayer5() {
        Setup s = Setup.getInstances(2);
        Setup.SetupPLayers(5);
        List<Player> playerList = new ArrayList<>(s.getPlayers());
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(0).getId() == 1));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).getId() == 2));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(2).getId() == 3));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(3).getId() == 4));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(4).getId() == 5));
    }

    /**
     * test player id when there are 6 players
     */
    @Test
    public void setupPlayer6() {
        Setup s = Setup.getInstances(2);
        Setup.SetupPLayers(6);
        List<Player> playerList = new ArrayList<>(s.getPlayers());
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(0).getId() == 1));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).getId() == 2));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(2).getId() == 3));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(3).getId() == 4));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(4).getId() == 5));
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(5).getId() == 6));
    }

    /**
     * test the amount of armys handed out if there are 2 players
     */
    @Test
    public void setuparmycount2() {
        Setup s = Setup.getInstances(2);
        Setup.SetupPLayers(2);
        List<Player> playerList = new ArrayList<>(s.getPlayers());

        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).getArmiesCount() == 40));
    }

    /**
     * test the amount of armys handed out if there are 3 players
     */
    @Test
    public void setuparmycount3() {
        Setup s = Setup.getInstances(2);
        Setup.SetupPLayers(3);
        List<Player> playerList = new ArrayList<>(s.getPlayers());

        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).getArmiesCount() == 35));
    }

    /**
     * test the amount of armys handed out if there are 4 players
     */
    @Test
    public void setuparmycount4() {
        Setup s = Setup.getInstances(2);
        Setup.SetupPLayers(4);
        List<Player> playerList = new ArrayList<>(s.getPlayers());

        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).getArmiesCount() == 30));
    }

    /**
     * test the amount of armys handed out if there are 5 players
     */
    @Test
    public void setuparmycount5() {
        Setup s = Setup.getInstances(2);
        Setup.SetupPLayers(5);
        List<Player> playerList = new ArrayList<>(s.getPlayers());

        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).getArmiesCount() == 25));
    }

    /**
     * test the amount of armys handed out if there are 6 players
     */
    @Test
    public void setuparmycount6() {
        Setup s = Setup.getInstances(2);
        Setup.SetupPLayers(6);
        List<Player> playerList = new ArrayList<>(s.getPlayers());

        assertTrue("someLibraryMethod should return 'true'", (playerList.get(1).getArmiesCount() == 20));
    }

    /**
     * test that the territories are created and shows that there is at least in the
     * order of being added
     */
    @Test
    public void setup3() {
        Setup s = Setup.getInstances(2);
        Setup.SetupPLayers(6);
        List<Territory> ter = new ArrayList<>(s.getTerritories());

        assertTrue("someLibraryMethod should return 'true'", (ter.get(4).getName().equals("Greenland")));
    }

    /**
    * test that the Neighboringterritoiers list exits and is corret
    */
    @Test public void setup4() {
            Setup s = Setup.getInstances(2);
    Setup.SetupPLayers(6);
    List<Territory> ter= s.getTerritories();
    System.out.println(" ");

    System.out.println("test 11 "+ter.get(4).getStrNeighboringTerritories());

    assertTrue("someLibraryMethod should return 'true'",(ter.get(4).getStrNeighboringTerritories().equals("0. Iceland 1. Northwest 2. Ontario 3. Quebec ")));
    }
}