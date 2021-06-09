package me.acru.player;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.acru.utils.FileUtils;
import me.acru.utils.StringUtils;

public class PlayerEvents implements Listener {
	
	public static GameMode gm;
	public static boolean isFreezed = false;
	static ArrayList<Player> frozen = new ArrayList<Player>();


	public static boolean checkPlayerPermissions(PlayerJoinEvent e) throws Exception {
		
		Player p = e.getPlayer();
		
		if (p.hasPermission(StringUtils.getPermissions()) || p.isOp()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isChangedAddress(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		gm = p.getGameMode();
		
		StringUtils.player = p;
		String getPlayerString = FileUtils.fData.getString("storage.webhook." + p.getUniqueId() + ".address");
		
		if (!p.getAddress().getHostString().equalsIgnoreCase(getPlayerString)) {
			p.sendMessage(p.getAddress().getHostString() + " no es igual a " + FileUtils.fData.getString("storage.webhook." + p.getUniqueId() + ".address"));
			return true;
		} else {
			return false;
		}
	}
	
	
	public static void freezePlayer(PlayerMoveEvent e) {
		
		while (isFreezed) {
			Player p = e.getPlayer();
			
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5000, 1));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5000, 20));
			p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 5000, 1),true);
			
		    if (frozen.contains(p)) {
		      e.setTo(e.getFrom());
		      p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 1));
		      p.closeInventory();
		    } else {
		    	Player t = Bukkit.getServer().getPlayer(p.getName());
		    	frozen.remove(t);
		        for (PotionEffect effect : p.getActivePotionEffects())
		            p.removePotionEffect(effect.getType());
		    }
		}	
		
	}
	

}
