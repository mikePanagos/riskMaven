package mojo;

import java.util.Timer;
import java.util.TimerTask;

public class Playtime {
    Timer time = new Timer();
    boolean done;
    public  void startTimer(int seconds) {
        done = false;
        time.schedule(new TimerTask(){
            @Override
            public void run() {
                System.out.println("Time's up!");
                done = true;
                time.cancel(); //Terminate the timer thread
            }
        }, seconds*1000);
    }
}

//Use to call for 30 seconds: "Playtime play = new Playtime(30);"