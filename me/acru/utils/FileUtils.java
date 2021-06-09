package me.acru.utils;

import java.io.File;
import java.io.FileWriter;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import me.acru.Main;
import me.acru.language.LanguageManager;
import me.acru.modes.web.Web;
import me.acru.modes.webhook.Webhook;
import me.acru.notifications.NotificationsManager;
import me.acru.security.EncryptionManager;

public class FileUtils {
	
	public static File storage = new File(Main.instance.getDataFolder()+File.separator+"storage.yml");
	public static File config = new File(Main.instance.getDataFolder()+File.separator+"config.yml");
	public static File lang = new File(Main.instance.getDataFolder()+File.separator+"lang.yml");
	
	public static FileConfiguration fData = YamlConfiguration.loadConfiguration(new File(Main.instance.getDataFolder(), "storage.yml"));
	public static FileConfiguration fLang = YamlConfiguration.loadConfiguration(new File(Main.instance.getDataFolder(), "lang.yml"));

	
	public static void checkConfig() throws Exception {
		
       
        if (!config.exists()) {
        	StringUtils.infoLogger("File 'config.yml' has been not detected, creating one.");
        	createConfigFile();
        }
        
        if (!storage.exists()) {
        	StringUtils.infoLogger("File 'storage.yml' has been not detected, creating one.");
        	createStorageFolder();
        } 
        
        if (!lang.exists()) {
        	StringUtils.infoLogger("File 'lang.yml' has been not detected, creating one.");
        	createLanguageFolder();
        }
        
        StringUtils.successLogger("All config files has been loaded.");
        getAllVars();
        
	}
	
	public static void createStorageFolder() throws Exception {
		FileConfiguration data = YamlConfiguration.loadConfiguration(new File(Main.instance.getDataFolder(), "storage.yml"));
        data.options().copyDefaults(true);
        data.save(storage);
	}
	
	public static void createLanguageFolder() throws Exception {
		FileWriter fw = new FileWriter(Main.instance.getDataFolder()+File.separator+"storage.yml");
		FileConfiguration data = YamlConfiguration.loadConfiguration(new File(Main.instance.getDataFolder(), "lang.yml"));
        data.options().copyDefaults(true);
        fw.write("# Hydra by Acru - https://www.spigotmc.org/members/acru.1247961/\\n# If you liked the plugin, please give it 5 stars in the resource.\\n#\\n# Remember that I am offering support for any doubt, query or bug.\\n# Consult through discord (https://discord.gg/kis8jsKl) or the discussion of the post.\\n\\n# Select the mode of your preference, you can see the functionality of each mode in the resource.\\nmode:\\n  #The active modes at the moment are: normal, webhook, web.\\n  active: \"webhook\"\\n\\n  # In case you are using webhook mode, enter its URL to establish the connection.\\n  webhook: \"https://discord.com/api/webhooks/ID/TOKEN\"\\n\\n  # In case you are using web mode, enter the web URL (also including the path where the system files are located).\\n  web: \"http://localhost/system/api/?key=%key%&player=%player%&password=%password%\"\\n\\n  #Not everyone should access the API data, so a key system was designed in which you must have the password for the JSON data to be displayed.\\n  key: \"key\"\\n\\n# The encryption used here will be in charge of transforming the flat password into an indecipherable one.\\nencryption:\\n  # The currently available modes are: MD5, SHA256, CUSTOM, NONE (not recommended).\\n  active: \"md5\"\\n\\n  #In case you have chosen CUSTOM, you must enter a password or key, this will be used to encrypt the passwords (try to keep it private since this method is not 100% effective).\\n  key: \"password\"\\n\\n# You can choose between different options to have the notifications to your preference.\\nnotifications:\\n  # If you decide not to activate the notifications, the other functionalities will not work (no notification will arrive.)\\n  active: true\\n\\n    # In the \"success\" section, all the notifications of the sessions that have been successful (such as registrations or logins) arrive.\\n  success: true\\n\\n  # In the \"warning\" section all the notifications of alerts arrive as the entry of the wrong password or from another IP address.\\n  warning: true\\n\\n  # In the \"urgent\" section, all the urgent notifications arrive as a ban of a user after several unsuccessful attempts or the threat of a vulnerability.\\n  urgent: true\\n\\n#You can modify the actions that are performed in different plugin events.\\nactions:\\n  #If the option is activated, any player who is not registered will be penalized.\\n  punish: true\\n\\n  #This will be the command to execute if the previous option is activated. Placeholder: %player%\\n  execute: \"ban %player% Security issue.\"\\n".replace("\n", System.getProperty("line.separator")));
        data.save(lang);
	}
	
	public static void createConfigFile() throws Exception {
		FileWriter fw = new FileWriter(Main.instance.getDataFolder()+File.separator+"config.yml");
		FileConfiguration data = YamlConfiguration.loadConfiguration(new File(Main.instance.getDataFolder(), "config.yml"));
        data.options().copyDefaults(true);
        fw.write("# Hydra by Acru - https://www.spigotmc.org/members/acru.1247961/\\n# If you liked the plugin, please give it 5 stars in the resource.\\n#\\n# Remember that I am offering support for any doubt, query or bug.\\n# Consult through discord (https://discord.gg/kis8jsKl) or the discussion of the post.\\n\\n# Select the mode of your preference, you can see the functionality of each mode in the resource.\\nmode:\\n  #The active modes at the moment are: normal, webhook, web.\\n  active: \"webhook\"\\n\\n  # In case you are using webhook mode, enter its URL to establish the connection.\\n  webhook: \"https://discord.com/api/webhooks/ID/TOKEN\"\\n\\n  # In case you are using web mode, enter the web URL (also including the path where the system files are located).\\n  web: \"http://localhost/system/api/?key=%key%&player=%player%&password=%password%\"\\n\\n  #Not everyone should access the API data, so a key system was designed in which you must have the password for the JSON data to be displayed.\\n  key: \"key\"\\n\\n# The encryption used here will be in charge of transforming the flat password into an indecipherable one.\\nencryption:\\n  # The currently available modes are: MD5, SHA256, CUSTOM, NONE (not recommended).\\n  active: \"md5\"\\n\\n  #In case you have chosen CUSTOM, you must enter a password or key, this will be used to encrypt the passwords (try to keep it private since this method is not 100% effective).\\n  key: \"password\"\\n\\n# You can choose between different options to have the notifications to your preference.\\nnotifications:\\n  # If you decide not to activate the notifications, the other functionalities will not work (no notification will arrive.)\\n  active: true\\n\\n    # In the \"success\" section, all the notifications of the sessions that have been successful (such as registrations or logins) arrive.\\n  success: true\\n\\n  # In the \"warning\" section all the notifications of alerts arrive as the entry of the wrong password or from another IP address.\\n  warning: true\\n\\n  # In the \"urgent\" section, all the urgent notifications arrive as a ban of a user after several unsuccessful attempts or the threat of a vulnerability.\\n  urgent: true\\n\\n#You can modify the actions that are performed in different plugin events.\\nactions:\\n  #If the option is activated, any player who is not registered will be penalized.\\n  punish: true\\n\\n  #This will be the command to execute if the previous option is activated. Placeholder: %player%\\n  execute: \"ban %player% Security issue.\"\\n".replace("\n", System.getProperty("line.separator")));
        fw.close();
        data.save(config);
	}
	
	
	
	public static void getAllVars() {
		//Language
		LanguageManager.language = Main.instance.getConfig().getString("language");	
		//Webhook variable
		Webhook.url = Main.instance.getConfig().getString("mode.webhook");
		//Web variable
		Web.url = Main.instance.getConfig().getString("mode.web");
		// Web key
		Web.key = Main.instance.getConfig().getString("mode.key");	
		//Active encryption
		EncryptionManager.activeEncryption = Main.instance.getConfig().getString("encryption.active");
		//Custom key
		EncryptionManager.customKey = Main.instance.getConfig().getString("encryption.key");
		//Active notifications
		NotificationsManager.activeNotifications = Main.instance.getConfig().getBoolean("notifications.active");
		//Success notifications
		NotificationsManager.successBoolean = Main.instance.getConfig().getBoolean("notifications.active");
		//Warning notifications
		NotificationsManager.warningBoolean = Main.instance.getConfig().getBoolean("notifications.warning");
		//Urgent notifications
		NotificationsManager.urgentBoolean = Main.instance.getConfig().getBoolean("notifications.urgent");
		//LicenseManager.license = Main.instance.getConfig().getString("license");
	}
	
	
	
	
}
