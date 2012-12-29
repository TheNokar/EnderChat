package net.gauragangur.EnderChat.Events;

import net.gauragangur.EnderChat.EnderChat;
import net.gauragangur.EnderChat.Methods;

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
		String methods = Methods.Format(msg, event.getMessage(), pname, pname);
		event.setFormat(methods);
	}
	
}
