package com.trafalcraft.ludo;


import java.util.LinkedList;
import java.util.List;
import com.trafalcraft.ludo.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class tpdebut {
	static JavaPlugin plugin = Main.getPlugin();
	
	
	public static void tpdebut(){
		
		List <Integer> rd = new LinkedList<Integer>();
		rd.add(0);
		
		for(int i = 0; i != Main.playerListSize(); i++){
			
			int randowm = 0;
			while(rd.contains(randowm) || randowm == 1* ((Main.getPlugin().getConfig().getInt("nbrspawn"))+1)){
				int att = plugin.getConfig().getInt("nbrspawn");
				randowm = (byte)(Math.random() * (att + 1));
			}
			
			double x4 = plugin.getConfig().getDouble("spawn." + randowm + ".x");
			double y4 = plugin.getConfig().getDouble("spawn." + randowm + ".y");
			double z4 = plugin.getConfig().getDouble("spawn." + randowm + ".z");
			float v4 = Main.getPlugin().getConfig().getInt("spawn." + randowm + ".v");
			float w4 = Main.getPlugin().getConfig().getInt("spawn." + randowm + ".w");
			
			World world = Bukkit.getWorld("world");
			Main.getPlayerList(i).getJoueur().teleport(new Location(world, x4, y4, z4, v4, w4));
			rd.add(randowm);
			
			Main.getPlayerList(i).getJoueur().getInventory().clear();
			Main.getPlayerList(i).getJoueur().getInventory().addItem(new ItemStack(Material.BOW, 1));
			Main.getPlayerList(i).getJoueur().getInventory().addItem(new ItemStack(Material.ARROW, 1));
			Main.getPlayerList(i).getJoueur().getInventory().addItem(new ItemStack(Material.STONE_AXE, 1));
			Main.getPlayerList(i).getJoueur().updateInventory();
			
			Main.getPlayerList(i).getJoueur().setHealth(20);
		}
	}
}
