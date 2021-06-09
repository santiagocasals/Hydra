package me.acru;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import me.acru.modes.Modes;
import me.acru.modes.webhook.Request;
import me.acru.modes.webhook.Webhook;
import me.acru.player.PlayerEvents;
import me.acru.security.types.SHA256;
import me.acru.utils.FileUtils;
import me.acru.utils.StringUtils;

public class Main extends JavaPlugin implements Listener {
	
	public static Main instance;
	
	public void onEnable() {
		//Set instance
		instance = this;
		
		//Check config
		try {
			FileUtils.checkConfig();
		} catch (Exception e) {
			StringUtils.errorLogger(e.toString());
		}
		
		//Read mode events
		this.getServer().getPluginManager().registerEvents(new Webhook(), this);
		
		//Read configurated mode
		Modes.avaiableModes();
		
		StringUtils.successLogger("Enabled Hydra version " + StringUtils.getVersion() + "");
	}


}
