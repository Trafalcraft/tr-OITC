package com.trafalcraft.ludo;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class Tpingame {
	public static void Tpingame(String psedo){
		
		int randowm = 0;
		int att = Main.getPlugin().getConfig().getInt("nbrspawn");
		
		while(randowm < 1 || randowm == 1* (att+1)){
			randowm = (byte)(Math.random() * (att + 1));
		}
		
		double x5 = Main.getPlugin().getConfig().getDouble("spawn." + randowm + ".x");
		double y5 = Main.getPlugin().getConfig().getDouble("spawn." + randowm + ".y");
		double z5 = Main.getPlugin().getConfig().getDouble("spawn." + randowm + ".z");
		float v5 = Main.getPlugin().getConfig().getInt("spawn." + randowm + ".v");
		float w5 = Main.getPlugin().getConfig().getInt("spawn." + randowm + ".w");
		
		World world = Bukkit.getWorld("world");
		
		Main.getPlayerList(Main.nomIndexOf(psedo)).getJoueur().teleport(new Location (world,x5,y5,z5,v5,w5));
	}
	
	public static  Location  Tptmort(){
		
		int randowm2 = 0;
		int att = Main.getPlugin().getConfig().getInt("nbrspawn");
		
		while(randowm2 < 1 || randowm2 == 1* (att+1)){
			randowm2 = (byte)(Math.random() * (att + 1));
		}
		
		double x6 = Main.getPlugin().getConfig().getDouble("spawn." + randowm2 + ".x");
		double y6 = Main.getPlugin().getConfig().getDouble("spawn." + randowm2 + ".y");
		double z6 = Main.getPlugin().getConfig().getDouble("spawn." + randowm2 + ".z");
		float v5 = Main.getPlugin().getConfig().getInt("spawn." + randowm2 + ".v");
		float w5 = Main.getPlugin().getConfig().getInt("spawn." + randowm2 + ".w");
		
		World world2 = Bukkit.getWorld("world");
		
		Location loc = new Location(world2, x6, y6, z6 ,v5 ,w5);		
		
		return loc;
		
	}
	
}
