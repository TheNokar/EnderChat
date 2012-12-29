package net.gauragangur.EnderChat.Events;

import net.gauragangur.EnderChat.EnderChat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

	EnderChat plugin;
	
	public ChatEvent(EnderChat enderchat) {
		plugin = enderchat;
	}
	@EventHandler
	public void playerChat(AsyncPlayerChatEvent event){
		Player pname = event.getPlayer();
		
		//Getting name
		String name = pname.getName();

		pname = plugin.getServer().getPlayer(name);
		name = pname.getName();
		String msg = plugin.getConfig().getString("msg-format");
		pname = plugin.getServer().getPlayer(name);
				
		//Format string
		String format = msg;
		format = format.replace("{name}", name)
				.replace("{msg}", event.getMessage())
				.replace("{prefix}", getPrefix(pname))
				.replace("{suffix}", getSuffix(pname))
				.replace("{world}", pname.getWorld().getName())
				.replace("{group}", getGroup(pname))
				.replace("&", "§");
		event.setFormat(format);
	}
	
    public String setColor(String string) {
    	return ChatColor.translateAlternateColorCodes('&', string);
    }
    public String getPrefix(Player player) {
    	return setColor(EnderChat.chat.getPlayerPrefix(player));
    }
    public String getSuffix(Player player) {
    	return setColor(EnderChat.chat.getPlayerSuffix(player));
    }
    public String getGroup(Player player) {
    	return setColor(EnderChat.chat.getPrimaryGroup(player));
    }
}
