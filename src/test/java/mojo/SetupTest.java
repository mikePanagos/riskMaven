package mojo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

import risk.*;

public class SetupTest {
    @Test public void setup() {
        Setup s= new Setup();
        s.setup(2);
        List<Player> playerList= new ArrayList<>(s.getPlayers());
        
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(0).getId()==1));
    }

    @Test public void setup1() {
        Setup s= new Setup();
        s.setup(6);
        List<Player> playerList= new ArrayList<>(s.getPlayers());
        
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(5).getId()==6));
    }
    @Test public void setup2() {
        Setup s= new Setup();
        s.setup(6);
        List<Player> playerList= new ArrayList<>(s.getPlayers());
        
        assertTrue("someLibraryMethod should return 'true'", (playerList.get(5).getArmiesCount()==20));
    }
    @Test public void setup3() {
        Setup s= new Setup();
        s.setup(6);
        List<Territory> ter= new ArrayList<>(s.getTerritories());
        
        assertTrue("someLibraryMethod should return 'true'", (ter.get(4).getName().equals("Greenland")));
    }
    @Test public void setup4() {
        Setup s= new Setup();
        s.setup(6);
        List<Territory> ter= new ArrayList<>(s.getTerritories());
        
        assertTrue("someLibraryMethod should return 'true'", (ter.get(4).getNeighboringTerritories().get(0).getName().equals("Iceland")));
    }
}