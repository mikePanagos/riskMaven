import java.util.Timer;
import java.util.TimerTask;

public class Playtime {
	
    Timer timer;

    public Playtime(int seconds) {
        timer = new Timer();
        timer.schedule(new Tasks(), seconds*1000);
	}

    class Tasks extends TimerTask {
        public void run() {
            System.out.println("Time's up!");
            timer.cancel(); //Terminate the timer thread
        }
    }
}

//Use to call for 30 seconds: "Playtime play = new Playtime(30);"