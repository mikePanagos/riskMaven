package mojo.undo;

import java.util.ArrayList;
import java.util.List;

import mojo.risk.*;

public class StateOfGame { 
    private int turn;
    private List<Player> pListPrev = new ArrayList<>();

    public StateOfGame(){
        setTurn(0);
        setPlayerListPrev(null);
    }

    public StateOfGame(int turn,List<Player> pList){
        setTurn(turn);
        setPlayerListPrev(pList);
    }


    public void setTurn(int t){
        this.turn=t;
    }

    /**
     * 
     * @param pList sets the list equal to the list being pasted in
     */
    public void setPlayerListPrev(List<Player> pList){
        // this.pListPrev=pList;
        // System.out.println(pList.size()+" prev player list size is ");

        for (int i = 0; i < pList.size(); i++) {
            this.pListPrev.add(pList.get(i));
            
        }
        // System.out.println(this.pListPrev.size()+" prev player list size is ");

    }

    public int getTurn(){
        return this.turn;
    }

    public List<Player> getPlayerListPrev(){
        return this.pListPrev;
    }
    

}