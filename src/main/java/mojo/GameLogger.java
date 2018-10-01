package mojo;
import java.io.*;

/**
 * This module will create a game logging object. This object will track game moves and update a log.
 */

/**
 * @author oscartovar
 *
 */
public class GameLogger {
	PrintWriter log; // File used to write log to. Example: ~\Risk\Log\log_01 *in windows
	
	public GameLogger() {
		/*
		 * Create new file to hold the game log.
		 * If not able to create print exception to console.
		 */
		try {
			log = new PrintWriter("~/Risk/log", "UTF-8");
			System.out.println("GameLogger successfully initiated.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	};
	
	public String updateLog(String move) {
		log.println(move);
		return move;
	};
	
	public String getLog() {
		File file = new File("~/Risk/log");
		String str = "";
		
		try {
			BufferedReader logfile = new BufferedReader(new FileReader(file));
			// Read in log file and append it to a string;
			do {
				str = logfile.readLine();
			} while (str != null);
			
			logfile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	};
	
	public void stopLogging() {
		log.close();
	};
}
