/**
 * 
 */
package mojo;

import mojo.bot.RiskyBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import mojo.WebConnection;

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
		if (args.length > 1 && args[1].equals("replay")) {
            // Do replay here.
            System.out.println("Initializing replay!");
            WebConnection webConnection = new WebConnection();
            System.out.println(webConnection.replay());
        }
        else  {
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
	}

}
