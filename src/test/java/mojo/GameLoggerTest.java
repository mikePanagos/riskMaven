


package mojo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import mojo.risk.*;


import mojo.*;
//"successfully added log.txt to s3 bucket"

public class GameLoggerTest{


    @Test public void GameLoggerTesting(){

        GameLogger gl=new GameLogger();

        gl.updateLog("player attacked another player and won");

        System.out.println("\n\n\n\n\n\n\n\n\n"+ gl.getLog()+"\n\n\n\n\n\n\n\n\n\n\n\n");

        assertTrue(" basic GameLogger test ", gl.getLog().equals("player attacked another player and won"));
        gl.stopLogging();
    }
}