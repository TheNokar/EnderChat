package net.gauragangur.EnderChat;

import java.util.logging.Logger;

import net.gauragangur.EnderChat.Commands.PmCommand;
import net.gauragangur.EnderChat.Commands.ReplyCommand;
import net.gauragangur.EnderChat.Events.ChatEvent;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;


import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public class EnderChat extends JavaPlugin {
	
	public final Logger logger = Logger.getLogger("EnderChat");
	public final ChatEvent chate = new ChatEvent(this);
	public static Permission perms = null;
	public static Chat chat = null;
	
	
	public void onEnable() {	
		ConsoleCommandSender console = getServer().getConsoleSender();
		logger.info(this.getDescription().getName() + " version: " + this.getDescription().getVersion() + " has been enabled!");
		PluginManager pm = getServer().getPluginManager();

		//Setting up vault
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			console.sendMessage(ChatColor.RED + "[EnderChat] did not find vault, disabling the EnderChat!");
			getServer().getPluginManager().disablePlugin(this);
		} else {
			//Tell the console that vault is installed
			console.sendMessage(ChatColor.GREEN + "[EnderChat] found vault, EnderChat can now act normal!");
			//Set up the permissions
			this.setupChat();
			this.setupPermissions();
			//Regisering the chatEvent
			pm.registerEvents(chate, this);
			this.getCommand("tell").setExecutor(new PmCommand(this));
			this.getCommand("r").setExecutor(new ReplyCommand(this));

		}


		//Generate the config
		this.saveDefaultConfig();
		this.getConfig().options().copyDefaults(true);
		
		}
	public void onDisable() {
		logger.info(this.getDescription().getName() + " version: " + this.getDescription().getVersion() + " has been disabled!");
	}
	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null) {
			perms = permissionProvider.getProvider();
		}
		return (perms != null);
	}

	private boolean setupChat() {
		RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
		if (chatProvider != null) {
			chat = chatProvider.getProvider();
		}
		return (chat != null);
	}
}
