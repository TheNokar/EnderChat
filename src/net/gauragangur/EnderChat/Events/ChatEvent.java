package net.gauragangur.EnderChat.Events;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
				.replaceAll("\u00A7c", "");
        Matcher matcher = Pattern.compile("\\{color.[a-zA-Z]+\\}").matcher(format);
		if(pname.hasPermission("enderchat.color")) {
			format = format.replaceAll("&((?i)[0-9a-fk-or])", "\u00A7$1");
		}
				
		event.setFormat(format);
	}
	
    public static String setColor(String string) {
    	return ChatColor.translateAlternateColorCodes('&', string);
    }
    public static String getPrefix(Player player) {
    	return setColor(EnderChat.chat.getPlayerPrefix(player));
    }
    public String getSuffix(Player player) {
    	return setColor(EnderChat.chat.getPlayerSuffix(player));
    }
    public String getGroup(Player player) {
    	return setColor(EnderChat.chat.getPrimaryGroup(player));
    }
}
