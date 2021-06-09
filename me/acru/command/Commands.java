package me.acru.command;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.acru.player.PlayerEvents;
import me.acru.utils.FileUtils;
import me.acru.utils.StringUtils;

public class Commands {
	
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	UUID playerUUID = StringUtils.player.getUniqueId();
    	String password = null;
    	String player = null;
		
        if (label.equalsIgnoreCase("hydra")) {
        	if (sender.hasPermission(StringUtils.getPermissions()) || sender.isOp()) {
        		if (args.length > 0) {
        			if (args[0].equalsIgnoreCase("login")) {
        				if (args.length == 3) { //Registro normal de un player propio
        					if (PlayerEvents.isFreezed) {
        						try {
        							password = args[1];
        							String code = args[2];
        							
        							if (Integer.valueOf(code) == ) {
					            			FileUtils.fData.set("storage.webhook." + playerUUID + ".address", StringUtils.player.getAddress().getHostName());
					        				FileUtils.fData.save(FileUtils.storage);
	        				
	            			String playerRegisterEvent = Main.instance.getConfig().getString("messages.player-success.player-register-event").replace("%pass%", password);
	            			System.out.print("[Fenix] A new register has been detected, password encrypted: " + encryptPassword);
	            			
	            			Events.whiles = false;
							Events.player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 0, 0),true);
							Events.player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 0, 0),true);
							Events.player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 0, 0),true);
							Events.player.setGameMode(Events.gm);
						
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerRegisterEvent));
            			} else {
                			String loginUsage = Main.instance.getConfig().getString("messages.player-success.player-correct-password");
            				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', loginUsage));
            			}
            			
            		} catch(Exception e) {
                		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', exceptionError));
                		System.out.print("Exception: " + e);
            		}
            		
            		} else {
            		if (args.length == 3) { //Force registro a usuario especifico
            			try {
	            			password = args[1];
	            			player = args[2];
	            			String encryptPassword = RegisterEvent.register(password);
	        				Main.instance.getConfig().set("data." + playerUUID + ".password", encryptPassword);
	        				Main.instance.getConfig().set("data." + playerUUID + ".address", "NONE");
	        				Main.instance.saveConfig();
	        				Main.instance.reloadConfig();
	            			String playerForceRegister = Main.instance.getConfig().getString("messages.player-success.player-force-register").replace("%pass%", password);
	            			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerForceRegister).replace("%player%", player));
            			} catch(Exception e) {
                    		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', exceptionError));
                    		System.out.print("Exception: " + e);
            			}
            		} else {
            			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', registerUse));
            		}
            		
            	}

            	
            } else {
            	if (args[0].equalsIgnoreCase("login")) {
            		if (args.length == 2) {
            			try {
	            			password = args[1];
	            			final String secretKey = HttpRequest.url + HttpRequest.name;
	            			String encryptPassword = RegisterEvent.register(password);
	            			String storagedPassword = Main.instance.getConfig().getString("data." + sender.getName() + ".password");
	
	            				if (tries != 0) {		
	            					if (encryptPassword.equalsIgnoreCase(storagedPassword)) {

	            						Events.whiles = false;
	            						Events.player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 0, 0),true);
	            						Events.player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 0, 0),true);
	            						Events.player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 0, 0),true);
	            						Events.player.setGameMode(Events.gm);
	            						
	                        			String loginUsage = Main.instance.getConfig().getString("messages.player-success.player-correct-password");
	                    				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', loginUsage));
	                    				
	                    				String encryptedIP = EncryptData.encrypt(Events.ip, secretKey);
	                    				Main.instance.getConfig().set("data." + playerUUID + ".address", encryptedIP);
	                    				Main.instance.saveConfig();
	                    				Main.instance.reloadConfig();
	                    				tries = Main.instance.getConfig().getInt("config.tries-to-ban");
	                    				Events.player.setGameMode(Events.gm);
	
			            			} else {
			            				tries--;
			                    		String loginError = Main.instance.getConfig().getString("messages.player-warning.player-failed-to-password").replace("%tries%", Integer.toString(tries));
			                			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', loginError));
			            			}
            				
	            		    } else {
	        					String commandToDispatch = Main.instance.getConfig().getString("config.command-if-player-failed-the-tries").replace("%player%", sender.getName());
	        					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), ChatColor.translateAlternateColorCodes('&', commandToDispatch));
	        					tries = Main.instance.getConfig().getInt("config.tries-to-ban");
	        					if(Utils.enabledWebhook) {
	        						String information = "The player has been banned because he tried " + tries + "times and failed.";
	        						HttpRequest.sendWebhookRequest(Events.player.getName(), Events.ip, information);
	        					}
	            		    }
            				
	            		} catch (Exception e) {
	            				System.out.print("[Fenix] A exception has been generated: " +e);
	            			}

            			
            	} else {
            		String loginUsage = Main.instance.getConfig().getString("messages.commands.login").replace("\\n", "\n");
            		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', loginUsage));
            	}
            }
           }
        } else {
        	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', fenix));
        }
     } else {
    	 //no permissions
			String noPermissions = Main.instance.getConfig().getString("messages.player-warning.player-dont-have-permissions").replace("\\n", "\n");
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermissions));
     }
    } else {
    	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', exceptionError));
    }
  }
		return false;
    }


}
