package com.trafalcraft.ludo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.trafalcraft.ludo.util.BungeeCoord;

public class Timerfinal {
	private static JavaPlugin plugin = Main.getPlugin();
	private static int task3;
	
	
	private static int temps;
	private static int task4;
	private static ArrayList <Player> playerKickList = new ArrayList<Player>();

	public static void startCountdown(final int CAB3, final List<String> r) {
		
		temps = 5;
    	playerKickList.addAll(Bukkit.getOnlinePlayers());
		temps = Bukkit.getOnlinePlayers().size()+temps;
        task4 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            
            private int List;
            private int OnlinePlayers = Bukkit.getOnlinePlayers().size();

			public void run() {
                if(temps == 0){
                	Bukkit.getServer().setWhitelist(false);
                	Bukkit.shutdown();
                    Bukkit.getServer().getScheduler().cancelTask(task4);
                }
            	if(temps <= OnlinePlayers)
                    {
                       	if(playerKickList.get(List) != null && playerKickList.size()<=OnlinePlayers){
                       		BungeeCoord.sendPlayerToHub(playerKickList.get(List));

                       	}
                   		List = List+1;
                   		Bukkit.getLogger().warning(temps+"");
                       	                          	
                    	
                    	

                    }else{
                    	for(String listwinner : r){
                    		Player pl = Bukkit.getPlayer(listwinner);
                    		Firework f = (Firework) pl.getWorld().spawn(pl.getLocation(), Firework.class);
                    		FireworkMeta fm = f.getFireworkMeta();
                    		
                    		fm.addEffects(FireworkEffect.builder()
                    				.flicker(true)
                    				.trail(true)
                    				.with(Type.BALL_LARGE)
                    				.withColor(Color.AQUA)
                    				.withColor(Color.RED)
                    				.withColor(Color.AQUA)
                    				.withColor(Color.RED)
                    				.build()
                    				);
                    		
                    		fm.setPower(2);
                    		f.setFireworkMeta(fm);
                    	}
                    }
               
	            	temps = temps-1;
            }
            },0, 20);
        
/*		task3 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {

			@Override
			public void run() {
				int time3 = CAB3;

				if (time3 != 0) {
					time3--;
					startCountdown(time3);
				}
				
				if (time3 == 0) {
					Bukkit.getScheduler().cancelTask(task3);
					
				}

			}

		}, 20);*/

	}
}

