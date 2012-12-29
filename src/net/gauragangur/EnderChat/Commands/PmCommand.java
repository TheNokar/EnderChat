package net.gauragangur.EnderChat.Commands;

import net.gauragangur.EnderChat.EnderChat;
import net.gauragangur.EnderChat.Events.ChatEvent;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PmCommand implements CommandExecutor {

	EnderChat plugin;
	public PmCommand(EnderChat enderchat) {
		plugin = enderchat;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§4You got to be player to use this command");
			return false;
		}
		if(args.length <= 1) {
			sender.sendMessage("§aUsage §2/tell <playername> <msg>");
			return false;
		}
				
		Player name = plugin.getServer().getPlayer(args[0]);
		
		if(name == null) {
			sender.sendMessage(ChatColor.DARK_RED + args[0] + ChatColor.RED +" is not online");
			return false;
		}
		if(args.length >= 2 && !(name == null)) {
		if(name == sender) {
			sender.sendMessage("§4You can't send your self a message");
			return false;
		}
		String msgFormat = plugin.getConfig().getString("private-message.receive-msg");
		msgFormat = msgFormat
					.replace("{sender}", sender.getName())
					.replace("{name}", name.getName())
					.replace("{msg}", getMessage(args, 1))
		            .replace("{prefix}", ChatEvent.getPrefix((Player) sender))
		            .replace("{prefix-receiver}", ChatEvent.getPrefix(name))
		            .replaceAll("&((?i)[0-9a-fk-or])", "\u00A7$1");
		name.sendMessage(msgFormat);
		sender.sendMessage(msgFormat);
		return true;
		}
		return false;
	}
	private String getMessage(String[] args, int start) {
		if (args.length > start) {
			StringBuilder reason = new StringBuilder();
			reason.append(args[start]);
			for (int i = start+1; i < args.length; ++i) {
				reason.append(" ").append(args[i]);
			}
			return reason.toString();
		} else {
			String reason = plugin.getConfig().getString("EnderChat.Nothing");
			if (reason == null) {
				// TODO: i18n
				reason = "Engin ástæda";
			}
			return reason;
		}
	}
}
