package mojo.proxy;
import mojo.risk.*;

public interface PaymentInterface {
    String getPriceForCredits();
    int  buyCredits(int option,Player p);
}