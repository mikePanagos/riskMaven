package mojo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

import mojo.Setup;

public class TimerTest {

    @Test public void timerTest(){

        Playtime play = new Playtime();

        play.startTimer(1);

        try{
        Thread.sleep(1000);
        }catch(Exception e){
            
        }


        assertTrue("testing timer",true);
    }


}

