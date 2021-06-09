package me.acru.modes;

import org.bukkit.Bukkit;
import me.acru.Main;
import me.acru.modes.normal.Normal;
import me.acru.modes.web.Web;
import me.acru.modes.webhook.Webhook;
import me.acru.utils.StringUtils;

public class Modes {
	
	public static String readMode() {
		return Main.instance.getConfig().getString("mode.active");
	}
	
	public static void avaiableModes() {
		String mode = readMode();
		if(mode.equalsIgnoreCase("normal")) {
			StringUtils.successLogger("The NORMAL mode has been selected successfully.");
			Normal.active = true;
		} else if (mode.equalsIgnoreCase("webhook")) {
			StringUtils.successLogger("The WEBHOOK mode has been selected successfully.");
			Webhook.active = true;
		} else if (mode.equalsIgnoreCase("web")) {
			StringUtils.successLogger("The WEB mode has been selected successfully.");
			Web.active = true;
		} else {
			StringUtils.errorLogger("Your selected mode has not been founded. Please check if exists in the list.");
			StringUtils.errorLogger("Avaiable modes: NORMAL, WEBHOOK, WEB. Closing plugin, please fix the error.");
			Bukkit.getPluginManager().disablePlugin(Main.instance);
		}
	}

}
