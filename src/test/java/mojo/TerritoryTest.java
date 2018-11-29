package mojo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.risk.*;

public class TerritoryTest {

    @Test
    public void getStrNeighboringTerritoriesTest() {
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
        System.out.println(" ");

        System.out.println("test one "+nOfJapan);

        
        assertTrue("territorytesting should return 'true'", ((kamchatka.getNumOfUnits() == 50)&&(kamchatka.getOwner()==1)));

    }

    @Test
    public void territorytesting2Test() {
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
        System.out.println(" ");

        System.out.println("test 2 "+janString);
        assertTrue("territorytesting2Test should return 'true'", (japan.getStrNeighboringTerritories().equals("0. mongola 1. kamchatka ")));

    }

    @Test public void territoryCon2Test() {
        Territory t= new Territory(); 
        assertTrue("territoryCon2Test should return 'true'",t.getName().equals("Territory") );

    }
    @Test public void getcontinentNameTest() {
        Territory t= new Territory(); 
        assertTrue("getcontinentNameTest should return 'true'",t.getcontinentName().equals("Continent") );



    }
    @Test public void getprintableListOfOwnedNeighboringTerritoriesTest() {
        Territory japan = new Territory("japan", "Asia",  1111111, 0); 
        Territory mongola = new Territory("mongola", "Asia", 1111111, 0); 
        Territory kamchatka = new Territory("kamchatka", "Asia", 5555, 0); 
        // test case for Territory it test adding a Territory then added Neighboring
        // Territory to make sure it still points to the right thing
        List<Territory> neighboringTerritories = new ArrayList<>();
        neighboringTerritories.add(mongola);
        neighboringTerritories.add(kamchatka);
        japan.setNeighboringTerritories(neighboringTerritories);

        assertTrue("getprintableListOfOwnedNeighboringTerritoriesTest should return 'true'",japan.getprintableListOfOwnedNeighboringTerritories(1111111).equals("0. mongola ") );

    }

    @Test public void checkNeighborsTest() {
        Territory japan = new Territory("japan", "Asia",  1111111, 0); 
        Territory mongola = new Territory("mongola", "Asia", 1111111, 0); 
        Territory kamchatka = new Territory("kamchatka", "Asia", 1111111, 0); 
        // test case for Territory it test adding a Territory then added Neighboring
        // Territory to make sure it still points to the right thing
        List<Territory> neighboringTerritories = new ArrayList<>();
        neighboringTerritories.add(mongola);
        neighboringTerritories.add(kamchatka);
        japan.setNeighboringTerritories(neighboringTerritories);

        assertTrue("checkNeighborsTest should return 'true'",japan.checkNeighbors(1111111)==true );

    }
    @Test public void checkNeighborsTestCase2() {
        Territory japan = new Territory("japan", "Asia",  1111111, 0); 
        Territory mongola = new Territory("mongola", "Asia", 333, 0); 
        Territory kamchatka = new Territory("kamchatka", "Asia", 33, 0); 
        // test case for Territory it test adding a Territory then added Neighboring
        // Territory to make sure it still points to the right thing
        List<Territory> neighboringTerritories = new ArrayList<>();
        neighboringTerritories.add(mongola);
        neighboringTerritories.add(kamchatka);
        japan.setNeighboringTerritories(neighboringTerritories);

        assertTrue("checkNeighborsTest should return 'true'",japan.checkNeighbors(1111111)==true );

    }

    // getOwnedNeighboringTerritory
    @Test public void getOwnedNeighboringTerritoryTest() {
        Territory japan = new Territory("japan", "Asia",  1111111, 0); 
        Territory mongola = new Territory("mongola", "Asia", 1111111, 0); 
        Territory kamchatka = new Territory("kamchatka", "Asia", 1111111, 0); 
        // test case for Territory it test adding a Territory then added Neighboring
        // Territory to make sure it still points to the right thing
        List<Territory> neighboringTerritories = new ArrayList<>();
        neighboringTerritories.add(mongola);
        neighboringTerritories.add(kamchatka);
        japan.setNeighboringTerritories(neighboringTerritories);

        assertTrue("getOwnedNeighboringTerritoryTest should return 'true'",japan.getOwnedNeighboringTerritory(1111111,0).getName().equals("mongola") );

    }

    @Test public void getAttackableNeighboringTerritoryTest() {
        Territory japan = new Territory("japan", "Asia",  1111111, 0); 
        Territory mongola = new Territory("mongola", "Asia", 1111111, 0); 
        Territory kamchatka = new Territory("kamchatka", "Asia", 5555, 0); 
        // test case for Territory it test adding a Territory then added Neighboring
        // Territory to make sure it still points to the right thing
        List<Territory> neighboringTerritories = new ArrayList<>();
        neighboringTerritories.add(mongola);
        neighboringTerritories.add(kamchatka);
        japan.setNeighboringTerritories(neighboringTerritories);

        assertTrue("getAttackableNeighboringTerritoryTest should return 'true'",japan.getAttackableNeighboringTerritory(1111111,0).getName().equals("kamchatka") );

    }

    // printableListOfAttackableNeighboringTerritories


    @Test public void printableListOfAttackableNeighboringTerritoriesTest() {
        Territory japan = new Territory("japan", "Asia",  1111111, 0); 
        Territory mongola = new Territory("mongola", "Asia", 1111111, 4); 
        Territory kamchatka = new Territory("kamchatka", "Asia", 555, 1); 
        // test case for Territory it test adding a Territory then added Neighboring
        // Territory to make sure it still points to the right thing
        List<Territory> neighboringTerritories = new ArrayList<>();
        neighboringTerritories.add(mongola);
        neighboringTerritories.add(kamchatka);
        japan.setNeighboringTerritories(neighboringTerritories);
        System.out.println("this is a test\n\n\n\n"+(japan.printableListOfAttackableNeighboringTerritories(1111111)));
        assertTrue("",true);
        assertTrue("printableListOfAttackableNeighboringTerritoriesTest should return 'true'",japan.printableListOfAttackableNeighboringTerritories(1111111).equals("0. kamchatka ") );

    }

    // getNumOfNeighborsNotOwned


    @Test public void getNumOfNeighborsNotOwnedTest() {
        Territory japan = new Territory("japan", "Asia",  1111111, 0); 
        Territory mongola = new Territory("mongola", "Asia", 1111111, 4); 
        Territory kamchatka = new Territory("kamchatka", "Asia", 555, 1); 
        // test case for Territory it test adding a Territory then added Neighboring
        // Territory to make sure it still points to the right thing
        List<Territory> neighboringTerritories = new ArrayList<>();
        neighboringTerritories.add(mongola);
        neighboringTerritories.add(kamchatka);
        japan.setNeighboringTerritories(neighboringTerritories);
        System.out.println("this is a test\n\n\n\n"+(japan.printableListOfAttackableNeighboringTerritories(1111111)));
        assertTrue("",true);
        assertTrue("getNumOfNeighborsNotOwnedTest should return 'true'",japan.getNumOfNeighborsNotOwned()==1 );


    }

    // getSurroundingEnemies
    @Test public void getSurroundingEnemiesTest() {
        Territory japan = new Territory("japan", "Asia",  1111111, 0); 
        Territory mongola = new Territory("mongola", "Asia", 1111111, 4); 
        Territory kamchatka = new Territory("kamchatka", "Asia", 555, 1); 
        // test case for Territory it test adding a Territory then added Neighboring
        // Territory to make sure it still points to the right thing
        List<Territory> neighboringTerritories = new ArrayList<>();
        neighboringTerritories.add(mongola);
        neighboringTerritories.add(kamchatka);
        japan.setNeighboringTerritories(neighboringTerritories);
        System.out.println("this is a test\n\n\n\n"+(japan.printableListOfAttackableNeighboringTerritories(1111111)));
        assertTrue("",true);
        assertTrue("getSurroundingEnemiesTest should return 'true'",japan.getSurroundingEnemies().get(0).getName().equals("kamchatka") );

    }

    @Test public void setAndGetNumTest() {
        Territory japan = new Territory("japan", "Asia",  1111111, 0); 
        Territory mongola = new Territory("mongola", "Asia", 1111111, 4); 
        Territory kamchatka = new Territory("kamchatka", "Asia", 555, 1); 
        // test case for Territory it test adding a Territory then added Neighboring
        // Territory to make sure it still points to the right thing
        List<Territory> neighboringTerritories = new ArrayList<>();
        neighboringTerritories.add(mongola);
        neighboringTerritories.add(kamchatka);
        japan.setNeighboringTerritories(neighboringTerritories);
       japan.setOwner(00);
        assertTrue("setAndGetNumTest ",japan.getOwner()==00 );

    }
}
