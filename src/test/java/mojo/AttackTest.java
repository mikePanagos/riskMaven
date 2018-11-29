package mojo;

import org.glassfish.grizzly.utils.ArrayUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.risk.*;


import mojo.*;
//"successfully added log.txt to s3 bucket"

public class AttackTest{
@Test public void attackTest(){
    System.out.println("\n\n\n");
    List<Player> plist=new ArrayList<>();
 
    Setup s=Setup.getInstances(2);
    Setup.SetupPLayers(2);
    
    GameEngine ge=GameEngine.getInit();
    List<Player>player=s.getPlayers();
    System.out.println("TEST "+player.get(0).getId());
    player.get(0).addTerritory(new Territory("aaa","a",1,4));
    player.get(1).addTerritory(new Territory("aaaa","a",2,1));
    player.get(1).addTerritory(new Territory("aaaaa","a",2,1));
    ge.attack(player.get(0).getTerritoryThatCanAttack(0), player.get(1).getTerritoryThatCanAttack(0), 2);
    System.out.println(ge.attack(player.get(0).getTerritoryThatCanAttack(0), player.get(1).getTerritoryThatCanAttack(0), 3));
    System.out.println(ge.attack(player.get(0).getTerritoryThatCanAttack(0), player.get(1).getTerritoryThatCanAttack(0), 1));

    System.out.println();
    System.out.println("\n\n\n");
    assertTrue(" attackTest ",!(ge.attack(player.get(0).getTerritoryThatCanAttack(0), player.get(1).getTerritoryThatCanAttack(1), 3).equals(null)));
}
}