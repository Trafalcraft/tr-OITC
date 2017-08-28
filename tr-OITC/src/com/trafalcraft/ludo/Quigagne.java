package com.trafalcraft.ludo;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Quigagne {
	public static void Quigagne(){
		Scorboard.DeleteScorBoard();
		
		for(Player OnlinePlayers : Bukkit.getOnlinePlayers()){
			OnlinePlayers.getInventory().clear();
		}
		
		List<String>  win = new LinkedList<String>();
		
		for(int i = 0; i != Main.getNbrJoueur(); i++){
			if(win.size() == 0){
				win.add(Main.getNomname(i));
			}else if(Main.getPlayerList(Main.nomIndexOf(win.get(0))).getPoint() == Main.getPlayerList(i).getPoint()){
				win.add(Main.getNomname(i));
			}else if(Main.getPlayerList(Main.nomIndexOf(win.get(0))).getPoint() < Main.getPlayerList(i).getPoint()){
				win.clear();
				win.add(Main.getNomname(i));
			}			
		}
		
		if(win.size() == 1){
			Bukkit.getServer().broadcastMessage("§3§lOITC> §b" + win.get(0) + " a gagné!!!!");
		}else if(win.size() > 1 ){
			Bukkit.getServer().broadcastMessage("§3§lOITC> §b" + "les gagnants sont :" );
			for(int b = 0; b != win.size(); b++){
				Bukkit.getServer().broadcastMessage("§b" + "-" + win.get(b));
			}
		}
		
		int CAB3 = 5;
		Timerfinal.startCountdown(CAB3, win);
		
		
		
		
	}
}
