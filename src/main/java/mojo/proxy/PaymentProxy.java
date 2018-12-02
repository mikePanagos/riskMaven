package mojo.proxy;
import com.amazonaws.services.kms.model.RetireGrantRequest;

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



    /**
     *  @return a string of prices 
     */
    public String getPriceForCredits(){
        String str="";

        for (int i = 0; i < price.length; i++) {
            str += "option "+(i+1)+". $"+price[i][1]+" for "+price[i][0]+" credits\n";
        }
        str+="undo cost 50\n";
        return str;
    }

    /**
     * 
     * @param val sets val to price its a array of price and amount
     */
    private void setPriceForCredits(int [][]val){
        this.price=val;
    }

    /**
     * 
     *  @param option is the option you want to buy
     * @param p is the player that will buy
     * @return units player get  
     */
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
        p.setCredit(p.getCredit()-cost);
        p.setCredit(p.getCredit()+units);
        }
        
        return units;
    }

    /**
     * 
     * @param undo the turen number that we are going to undo to 
     * @return true or false based on if it passed or failed
     */
    public boolean  buyUndo(Player p,int undo){
        Setup s=Setup.getInstances();
        Undo u=Undo.init();
        if(p.getCredit()>50){
            return u.undo(s.getPlayers(),undo);
        }else{
            return false;
        }
        

    }

    /**
     * 
     * @param give the player that is giving credits 
     * @param get the player that will get the credits
     * @param amount the amount  give will give
     * @return
     */
    public boolean giveCreditsTo(Player give,Player get,int amount){
        if((give.getCredit()-amount)<0){
            return false;
        }else{

            give.setCredit(give.getCredit()-amount);
            get.setCredit(amount+get.getCredit());
            return true;

        }
    }
}
