package mojo.proxy;
import mojo.risk.*;

public interface PaymentInterface {
    String getPriceForCredits();
    int  buyCredits(int option,Player p);
    public boolean giveCreditsTo(Player give,Player get,int amount);
    public boolean  buyUndo(Player p,int undo);
}