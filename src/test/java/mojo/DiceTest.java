package mojo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.risk.*;


import mojo.*;
//"successfully added log.txt to s3 bucket"

public class DiceTest{


    @Test public void DiceTesting(){

        Dice d=new Dice();

        assertTrue(" basic dice test ", (d.getVal()==1||d.getVal()==2||d.getVal()==3||d.getVal()==4||d.getVal()==5||d.getVal()==6));
    }
    @Test public void DiceTest2(){
       
        Dice d=new Dice();
        d.diceroll();

        assertTrue(" basic dice test ", (d.getVal()==1||d.getVal()==2||d.getVal()==3||d.getVal()==4||d.getVal()==5||d.getVal()==6));
    }
}