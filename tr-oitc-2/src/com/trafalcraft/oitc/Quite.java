package com.trafalcraft.oitc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.inventivetalent.bossbar.BossBarAPI;

import com.trafalcraft.oitc.Controler.ArenaControle;
import com.trafalcraft.oitc.Controler.PlayerControle;

public class Quite {
	public static void leave(Player p){
		
		if(PlayerControle.contains(p.getName())){
			
			ClearPotion.clearEffect(p);
			BossBarAPI.removeAllBars(p);
			p.setLevel(0);
			p.setDisplayName(p.getName());
			
			String arene = PlayerControle.getJoueur(p.getName()).getArene();
			
			ArenaControle.getArena(arene).removePlayer(Bukkit.getPlayer(p.getName()));
			PlayerControle.removePlayer(p.getName());
			
			if(ArenaControle.getArena(arene).getStatus().equalsIgnoreCase("in-game")){
			
				if(ArenaControle.getArena(arene).getPlayerList().size() == 1){
					for(Player pl : ArenaControle.getArena(arene).getPlayerList()){

	       				PlayerControle.getJoueur(pl.getName()).setInGame(false);
	       			}
					
					ArenaControle.getArena(arene).stopGameTimer();
					ArenaControle.getArena(arene).WhoWin();
					ArenaControle.getArena(arene).setStatus("end");
				}
			}
		}
	}
}
