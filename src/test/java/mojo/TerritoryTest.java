package mojo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.risk.*;

public class TerritoryTest {

    @Test
    public void territorytesting() {
        Territory japan = new Territory("japan", "Asia", 0, 0);
        Territory mongola = new Territory("mongola", "Asia",  0, 0); 
        Territory kamchatka = new Territory("kamchatka", "Asia",  0, 0); 
        // test case for Territory it test adding a Territory then added Neighboring
        // Territory to make sure it still points to the right thing
        List<Territory> neighboringTerritories = new ArrayList<>();
        neighboringTerritories.add(mongola);
        neighboringTerritories.add(kamchatka);
        japan.setNeighboringTerritories(neighboringTerritories);
        kamchatka.setOwner(1);
        kamchatka.setNumOfUnits(50);
        String nOfJapan= japan.getStrNeighboringTerritories();
        System.out.println("test one "+nOfJapan);

        
        assertTrue("someLibraryMethod should return 'true'", ((kamchatka.getNumOfUnits() == 50)&&(kamchatka.getOwner()==1)));

    }

    @Test
    public void territorytesting2() {
        Territory japan = new Territory("japan", "Asia",  0, 0); 
        Territory mongola = new Territory("mongola", "Asia",  0, 0); 
        Territory kamchatka = new Territory("kamchatka", "Asia",  0, 0); 
        // test case for Territory it test adding a Territory then added Neighboring
        // Territory to make sure it still points to the right thing
        List<Territory> neighboringTerritories = new ArrayList<>();
        neighboringTerritories.add(mongola);
        neighboringTerritories.add(kamchatka);
        japan.setNeighboringTerritories(neighboringTerritories);
        kamchatka.setOwner(1);
        kamchatka.setNumOfUnits(50);
        String janString= japan.getStrNeighboringTerritories();
        System.out.println("test 2 "+janString);
        assertTrue("someLibraryMethod should return 'true'", (japan.getStrNeighboringTerritories().equals("0. mongola 1. kamchatka ")));

    }

}
