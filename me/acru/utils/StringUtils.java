package me.acru.utils;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import me.acru.Main;

public class StringUtils {
	
	public static Player player;
	public static PlayerMoveEvent pme;
	
	public static void successLogger(String arg) {
		System.out.println("[Hydra] [SUCCESS] " + arg);
	}
	
	public static void warnLogger(String arg) {
		System.out.println("[Hydra] [WARN] " + arg);
	}
	
	public static void errorLogger(String arg) {
		System.out.println("[Hydra] [ERROR] " + arg);
	}
	
	public static void infoLogger(String arg) {
		System.out.println("[Hydra] [INFO] " + arg);
	}
	
	public static String getVersion() {
		PluginDescriptionFile pdf = Main.instance.getDescription();
		return pdf.getVersion();
	}
	
	public static String getPermissions() {
		return "hydra.admin";
	}
	
	public static String getRandomUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

}
