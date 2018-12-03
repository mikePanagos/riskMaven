/**
 * 
 */
package mojo;

import mojo.bot.RiskyBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import mojo.WebConnection;

import java.util.Scanner;

/**
 * @author TeamMojo
 *
 */
public class Risk {

	/**
	 * @param args Risk arguments
	 */
	public static void main(String[] args) {
	    // Check to see if the game is in replay mode.
        System.out.println("What would you like to do?");
        System.out.println("1. Replay");
        System.out.println("2. New Game");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

		if (choice == 1) {
            // Do replay here.
            System.out.println("Initializing replay!");
            WebConnection webConnection = new WebConnection();
            System.out.println(webConnection.replay());
        }
        else if (choice == 2) {
            ApiContextInitializer.init();

            // Instantiate Telegram Bots API
            TelegramBotsApi botsApi = new TelegramBotsApi();

            // Register our bot
            try {
                botsApi.registerBot(new RiskyBot());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Invalid option. Run the program again.");
        }
	}

}
