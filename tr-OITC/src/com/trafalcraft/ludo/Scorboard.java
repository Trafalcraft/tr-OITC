package com.trafalcraft.ludo;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Scorboard {
	
	static ScoreboardManager manager = Bukkit.getScoreboardManager();
	static Scoreboard Board = manager.getNewScoreboard();
	static Objective o = Board.registerNewObjective("Nom", "Kill");
	
	public static void sendScoreBoard(){
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName("§3§l" + "OITC");
		
		for(Player OnlinePlayers : Bukkit.getOnlinePlayers()){
			o.getScore(OnlinePlayers).setScore(0);
		}
		
		
		for(Player OnlinePlayers : Bukkit.getOnlinePlayers()){
			OnlinePlayers.setScoreboard(Board);
		}
	}
	
	public static void DeleteScorBoard(){
		Board.clearSlot(DisplaySlot.SIDEBAR);
	}
	
	public static void plusdepoint(String nom){
		o.getScore(nom).setScore((o.getScore(nom).getScore()) + 1);
	}
}
