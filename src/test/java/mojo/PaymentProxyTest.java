package mojo;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.risk.*;
import mojo.proxy.*;

public class PaymentProxyTest {     
    /**
     * for testing the credit assign and string return 
     * 
     *  */
    @Test public void getCreditTest() {
        int [][] price ={{5,1},{10,2},{25,5},{75,7},{500,10},{1000,100}};
        PaymentInterface pf = new PaymentProxy(price);


        assertTrue("someLibraryMethod should return 'true'", (pf.getPriceForCredits().equals("option 1. $1 for 5 credits\noption 2. $2 for 10 credits\noption 3. $5 for 25 credits\noption 4. $7 for 75 credits\noption 5. $10 for 500 credits\noption 6. $100 for 1000 credits\n")));
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
      
        System.out.println(pf.buyCredits( 5 , playerList.get(0) ) );
        
        assertTrue("someLibraryMethod should return 'true'", ( (pf.buyCredits( 5 , playerList.get(0) ) ) ==100));
        // assertTrue("condition",true);
    }

}
   