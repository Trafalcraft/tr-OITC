package com.trafalcraft.ludo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class timer1 {
	static JavaPlugin plugin = Main.getPlugin();
	public static int task;

	public static void startCountdown(final int CAB) {
		task = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {

			@Override
			public void run() {
				int time = CAB;

				if (time != 0) {
					time--;
					startCountdown(time);
				}
				if (time == 30) {
					Bukkit.getServer().broadcastMessage("§3§lOITC> §b" + "la partie commence dans 30 secondes");
				}
				if (time == 20) {
					Bukkit.getServer().broadcastMessage("§3§lOITC> §b" + "la partie commence dans 20 secondes");
				}
				if (time <= 10 && time != 0) {
					Bukkit.getServer()
							.broadcastMessage("§3§lOITC> §b" + "la partie commence dans " + time + " seconde(s)");
				}

				if (time == 0) {
					Bukkit.getServer().broadcastMessage("§3§lOITC> §b" + "la partie commence");
					Bukkit.getScheduler().cancelTask(task);
					
					tpdebut.tpdebut();
					
					Main.setEnJeu(1);
					
					int CAB2 = (Main.getPlugin().getConfig().getInt("temps.max")) * 60;
					Timertotal.startCountdown(CAB2);
					
					Scorboard.sendScoreBoard();
					Bukkit.getServer().setWhitelist(true);
				}

			}

		}, 20);

	}

}
