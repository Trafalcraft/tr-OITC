package com.trafalcraft.ludo.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.trafalcraft.ludo.Main;

public class BungeeCoord implements Listener{

	
	public static void sendPlayerToHub(Player p) {
		final ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF("jeux");
		if (Bukkit.getOnlinePlayers().size() > 0) {
			p.sendPluginMessage(Main.getPlugin(), "BungeeCord", out.toByteArray());
		}
	}
	
	public static void sendOtherPlayerToHub(String name, Player p) {
		final ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
//		out.writeUTF(name);
		out.writeUTF("jeux");
		if (Bukkit.getOnlinePlayers().size() > 0) {
			p.sendPluginMessage(Main.getPlugin(), "BungeeCord", out.toByteArray());
		}
	}
	
}
