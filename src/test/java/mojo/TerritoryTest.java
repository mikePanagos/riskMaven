package mojo;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.risk.*;

public class TerritoryTest {

   @Test public void territorytesting()
    {
        Territory japan =new Territory("japan","Asia"," ",0);
        Territory mongola = new Territory("mongola","Asia"," " ,0);
        Territory kamchatka = new Territory("kamchatka","Asia"," " ,0);
        //test case for Territory it test adding a Territory then added Neighboring Territory to make sure it still points to the right thing
        List<Territory> neighboringTerritories = new ArrayList<>();
        neighboringTerritories.add(mongola);
        neighboringTerritories.add(kamchatka);
        japan.setNeighboringTerritories(neighboringTerritories);
        kamchatka.setOwner("mike");
        kamchatka.setNumOfUnits(50);
       List<Territory> list=new ArrayList<>(japan.getNeighboringTerritories());

       for(int i=0;i<list.size();i++)
       {
           if(list.get(i).getName().equals("kamchatka")){
            assertTrue("someLibraryMethod should return 'true'",(list.get(i).getNumOfUnits()==50));
           
           }else{
        
           }
           
       }
       

       
   
    
}
  

}
