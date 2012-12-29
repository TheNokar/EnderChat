package net.gauragangur.EnderChat.Commands;

import net.gauragangur.EnderChat.EnderChat;
import net.gauragangur.EnderChat.Memory;
import net.gauragangur.EnderChat.Methods;

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
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§4You got to be player to use this command");
			return false;
		}
		
		//Get the target
		Player name = plugin.getServer().getPlayer(args[0]);
		
		//Tells the player that it has to be more than 1 args to send private message
		if(args.length <= 1) {
			sender.sendMessage("§aUsage §2/tell <playername> <msg>");
			return false;
		}
						
		//Check if the player is online
		if(name == null) {
			sender.sendMessage(ChatColor.DARK_RED + args[0] + ChatColor.RED +" is not online");
			return false;
		}
		
		//Main command
		if(args.length >= 2 && !(name == null)) {
			
			//Send error if player tries to send him self a messages
			if(name == sender) {
				sender.sendMessage("§4You can't send your self a message");
				return false;
			}
			
			//The format
			String msgFormat = plugin.getConfig().getString("private-message.receive-msg");
			String format = Methods.Format(msgFormat, Methods.getMessage(args, 1),(Player) sender, name);
			name.sendMessage(format);
			sender.sendMessage(format);
			Memory.reaplyCommand.put(name.getName(), sender.getName());
		return true;
		}
		return false;
	}
}
