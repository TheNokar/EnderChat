package net.gauragangur.EnderChat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Methods {
	public static String Format(String format, String msg, Player player, Player name) {
		format = format
				.replace("{sender}", player.getName())
				.replace("{name}", name.getName())
				.replace("{msg}", msg)
				.replace("{prefix}", getPrefix(player))
				.replace("{suffix}", getSuffix(player))
				.replace("{world}", player.getWorld().getName())
				.replace("{group}", getGroup(player))
				.replace("{prefix-receiver}", getPrefix(name))
				.replaceAll("&", "§");
		return format;
	}
	
    public static String setColor(String string) {
    	return ChatColor.translateAlternateColorCodes('&', string);
    }
    public static String getPrefix(Player player) {
    	return setColor(EnderChat.chat.getPlayerPrefix(player));
    }
    public static String getSuffix(Player player) {
    	return setColor(EnderChat.chat.getPlayerSuffix(player));
    }
    public static String getGroup(Player player) {
    	return setColor(EnderChat.chat.getPrimaryGroup(player));
    }
    public static String message(String message) {
    	return setColor(message);
    }
	
	//The message thing
	public static String getMessage(String[] args, int start) {
		if (args.length > start) {
			StringBuilder messages = new StringBuilder();
			messages.append(args[start]);
			for (int i = start+1; i < args.length; ++i) {
				messages.append(" ").append(args[i]);
			}
		}
			String messages = "test";
			return messages.toString();
	}
}
