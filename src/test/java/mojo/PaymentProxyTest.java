package mojo;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.risk.*;
import mojo.proxy.*;
import mojo.undo.*;

public class PaymentProxyTest {     
    /**
     * for testing the credit assign and string return 
     * 
     *  */
    @Test public void getCreditTest() {
        int [][] price ={{5,1},{10,2},{25,5},{75,7},{500,10},{1000,100}};
        PaymentInterface pf = new PaymentProxy(price);


        assertTrue("someLibraryMethod should return 'true'", (pf.getPriceForCredits().equals("option 1. $1 for 5 credits\noption 2. $2 for 10 credits\noption 3. $5 for 25 credits\noption 4. $7 for 75 credits\noption 5. $10 for 500 credits\noption 6. $100 for 1000 credits\nundo cost 50\n")));
    }
    /**
     * for testing that it sets the credits and deducts money from user
     */
    @Test public void SetCreditTest() {
        Setup s = Setup.getInstances(2);
        Setup.SetupPLayers(2);
        List<Player> playerList = new ArrayList<>(s.getPlayers());
        

        int [][] price ={{5,1},{10,2},{25,5},{75,7},{100,10},{1000,100}};
        PaymentInterface pf = new PaymentProxy(price);
        playerList.get(0).setCredit(5);
      
        System.out.println(pf.buyCredits( 5 , playerList.get(0) ) );
        playerList.get(0).setCredit(500);

        System.out.println(pf.buyCredits( 5 , playerList.get(0) ) );
        
        assertTrue("someLibraryMethod should return 'true'", ( (pf.buyCredits( 5 , playerList.get(0) ) ) ==100));
        // assertTrue("condition",true);
    }

    @Test public void giveCredits() {
        Setup s = Setup.getInstances(2);
        Setup.SetupPLayers(2);
        List<Player> playerList = new ArrayList<>(s.getPlayers());

        int [][] price ={{5,1},{10,2},{25,5},{75,7},{100,10},{1000,100}};
        PaymentInterface pf = new PaymentProxy(price);
      
        System.out.println(pf.buyCredits( 5 , playerList.get(0) ) );
        
        assertTrue("someLibraryMethod should return 'true'", ( (pf.buyCredits( 5 , playerList.get(0) ) ) ==100));
        // assertTrue("condition",true);
    }

    @Test public void buyCreditsTest() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

        int [][] price ={{5,1},{10,2},{25,5},{75,7},{100,10},{1000,100}};
        PaymentInterface pf = new PaymentProxy(price);
      
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
        


        System.out.println("undo test 2: "+p.get(0).printableTerritories());
        System.out.println("undo test 2: "+p.get(1).printableTerritories());
        p.get(0).setCredit(500);

        assertTrue("buyCreditsTest should return 'true'", pf.buyUndo(p.get(0),1));
        p.get(0).setCredit(0);
        assertTrue("buyCreditsTest should return 'true'", !pf.buyUndo(p.get(0),1));




    }
    @Test public void giveCreditsToTest() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");

        Player p1=new Player(12444,4,4);
        Player p2=new Player(11111,4,4);
        int [][] price ={{5,1},{10,2},{25,5},{75,7},{100,10},{1000,100}};
        PaymentInterface pf = new PaymentProxy(price);
        p1.setCredit(0);


        assertTrue("giveCreditsToTest should return 'true'", !pf.giveCreditsTo(p1, p2, 5));
        p1.setCredit(500);
        assertTrue("giveCreditsToTest should return 'true'", pf.giveCreditsTo(p1, p2, 5));


    }
}
   