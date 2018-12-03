package mojo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.risk.*;
import mojo.undo.*;

public class UndoTest {


    @Test //test lost Territory then undo it to regain it  
    public void territorytesting() {
        Setup s= Setup.getInstances(4);
        Setup.SetupPLayers(2);
        Undo u = Undo.init(2);
        List<Territory> t=s.getTerritories();
        List<Player> p=s.getPlayers();

        p.get(0).addTerritory(t.get(0));
        System.out.println("undo test 1: "+p.get(0).printableTerritories());
        u.saveState(p);
        p.get(0).removeTerritory(t.get(0));
        System.out.println("undo test 1: "+p.get(0).printableTerritories());

        
        System.out.println("undo test 1: "+p.get(0).printableTerritories());
        assertTrue("territorytesting should return 'true'", u.undo(p, 1));


    }
    //test to remove one teritory form one player and add it to anthor then undo it
    @Test public void territorytesting2() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

        Setup s= Setup.getInstances(4);
        Setup.SetupPLayers(2);
        Undo u = Undo.init(3);
        List<Territory> t=s.getTerritories();
        List<Player> p=s.getPlayers();

        p.get(0).addTerritory(t.get(0));
        p.get(1).addTerritory(t.get(1));
        u.saveState(p);

        p.get(0).removeTerritory(t.get(0));
        p.get(1).addTerritory(t.get(0));
        System.out.println("undo test 2: "+p.get(1).printableTerritories());

        u.undo(p,1);
        System.out.println("undo test 2: "+p.get(0).printableTerritories());
        System.out.println("undo test 2: "+p.get(1).printableTerritories());

        assertTrue("territorytesting2 ", (p.get(0).printableTerritories().equals("0. Alaska army on 0. ")));


    }

}