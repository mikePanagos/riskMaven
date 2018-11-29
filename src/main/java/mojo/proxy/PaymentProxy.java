package mojo.proxy;
import mojo.Setup;
import mojo.risk.*;
import mojo.undo.Undo;

public class PaymentProxy implements PaymentInterface {
    // 1. Create a "wrapper" for a remote,
    // or expensive, or sensitive target
    private int [][] price ={{5,1},{10,2},{25,5},{75,7},{100,10},{1000,100}};//credits ,price
  


    public PaymentProxy(int[][]val){
        setPriceForCredits(val);
    }

    public String getPriceForCredits(){
        String str="";

        for (int i = 0; i < price.length; i++) {
            str += "option "+(i+1)+". $"+price[i][1]+" for "+price[i][0]+" credits\n";
        }

        return str;
    }
    private void setPriceForCredits(int [][]val){
        this.price=val;
    }


    
    public int  buyCredits(int option,Player p){
        int cost=0;
        int units=0;
        for (int i = 0; i < price.length; i++) {
            if(i==option-1){
                cost=price[i][1];
                units=price[i][0];
            }
        }
        System.out.println(units+" units");
        System.out.println(cost+" cost");



        if(cost>p.getCredit()){
            System.out.println("insufficient funds");
        }else{
        p.setMoney(p.getMoney()-cost);
        p.setCredit(p.getCredit()+units);
        }
        
        return units;
    }
//  add transfer from player to player
    public boolean  buyUndo(int undo){
        Setup s=Setup.getInstances();
        Undo u=Undo.init();
        return u.undo(s.getPlayers(),undo);

    }

    /
    public boolean giveCreditsTo(Player give,Player get,int amount){
        if((give.getCredit()-amount)>0){
            return false;
        }else{

            give.setCredit(give.getCredit()-amount);
            get.setCredit(amount+get.getCredit());
            return true;

        }
    }
}
