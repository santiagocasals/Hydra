package me.acru.modes.webhook;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import me.acru.player.PlayerEvents;
import me.acru.utils.StringUtils;

public class Webhook implements Listener {
	
	public static String url;
	public static boolean active = false;
	
	public static PlayerMoveEvent createVariable(PlayerMoveEvent e) {
		return StringUtils.pme = e;
	}

	@EventHandler
	public static void webhookModule(PlayerJoinEvent e) throws Exception {
		Player player = e.getPlayer();
		
		if (active) {
			if(PlayerEvents.checkPlayerPermissions(e)) {
				if(PlayerEvents.isChangedAddress(e)) {
					PlayerEvents.isFreezed = true;
				} else {
					player.sendMessage("Haz ingresado desde tu misma sesión (IP) anterior.");
				}
			} else {
				player.sendMessage("Caca 3");
			}
			
		}
	}

}
