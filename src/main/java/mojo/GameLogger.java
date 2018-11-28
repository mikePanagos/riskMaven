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
	PrintStream log; // File used to write log to. Example: ~\Risk\Log\log_01 *in windows
	
	public GameLogger() {
		/*
		 * Create new file to hold the game log.
		 * If not able to create print exception to console.
		 */
		try {
			log = new PrintStream("log.txt", "UTF-8");
			System.out.println("GameLogger successfully initiated.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	};
	
	public void updateLog(String move) {
		log.println(move);
		return;
	};
	
	public String getLog() {
		ClassLoader cl = getClass().getClassLoader();

		File file = new File("./log.txt");
		System.out.println(file.getPath());
		System.out.println(file.getAbsolutePath());
		String str = "";
		String tok="";
		
		try {
			BufferedReader logfile = new BufferedReader(new FileReader(file));
			// Read in log file and append it to a string;
			do {
				
				tok= logfile.readLine();
				if(tok!=null)
				{
					str +=tok;
				}
			} while (tok!=null);
			
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
