package mojo;

import java.util.Timer;
import java.util.TimerTask;

public class Playtime {
    Timer time = new Timer();
    int count=0;

    public Playtime() {
    	
    }
    
    public  void startTimer(int seconds) {
//         count++;
//         if(count>1){
//             time.
//         }
        
        time.schedule(new TimerTask(){
            @Override
            public void run() {
                System.out.println("Time's up!");
                time.cancel(); //Terminate the timer thread
            }
        }, seconds*1000);
    }
    
    
}

//Use to call for 30 seconds: "Playtime play = new Playtime(30);"