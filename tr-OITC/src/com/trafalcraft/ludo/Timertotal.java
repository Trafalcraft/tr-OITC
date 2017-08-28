package com.trafalcraft.ludo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Timertotal {
	static JavaPlugin plugin = Main.getPlugin();
	public static int task2;

	public static void startCountdown(final int CAB2) {
		task2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {

			@Override
			public void run() {
				int time2 = CAB2;

				if (time2 != 0) {
					time2--;
					startCountdown(time2);
				}
				
				if(time2 == 60){
					Bukkit.getServer().broadcastMessage("§3§lOITC> §b" + "il reste une minute");
				}
				
				if (time2 == 0) {
					Bukkit.getServer().broadcastMessage("§3§lOITC> §b" + "la partie est finie");
					Bukkit.getScheduler().cancelTask(task2);
					Quigagne.Quigagne();
					Main.setEnJeu(0);
				}

			}

		}, 20);

	}
}
