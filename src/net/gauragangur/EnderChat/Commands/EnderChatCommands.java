package net.gauragangur.EnderChat.Commands;

import net.gauragangur.EnderChat.EnderChat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EnderChatCommands implements CommandExecutor {
	EnderChat plugin;
	
	public EnderChatCommands(EnderChat enderchat) {
		plugin = enderchat;
	}
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(args.length == 0) {
		}
		
		return false;
	}
}
