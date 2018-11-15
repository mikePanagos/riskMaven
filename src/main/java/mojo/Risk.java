/**
 * 
 */
package mojo;
import mojo.*;
import mojo.bot.RiskyBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author TeamMojo
 *
 */
public class Risk {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		RiskyBot bot = new RiskyBot();
//		bot.getUpdates();
//		bot.getInfo();
//		gui ui = new gui();
//		ui.start();

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
