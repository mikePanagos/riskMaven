package mojo.risk;

import java.util.*;

/**
 * @author Michael Panagos
 */
public class Dice {
    private int number;

    public Dice() {
        diceroll();
    }

    public void diceroll() {
        Random rand = new Random();
        this.number = rand.nextInt(6)+1; // return random int
    }

    public int getVal(){
        return this.number;
    }
}
