/**
 * 
 */
package mojo;
import mojo.*;
import mojo.bot.RiskyBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author TeamMojo
 *
 */
public class Risk {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

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
