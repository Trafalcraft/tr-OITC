package com.trafalcraft.oitc;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.trafalcraft.oitc.Controler.ArenaControle;
import com.trafalcraft.oitc.Controler.PlayerControle;
import com.trafalcraft.oitc.file.FileControler;

public class Tp {
	
	public static void tpStart(String arene){
		ArrayList<Location> spawn = new ArrayList<Location>();
		
		for(int i = 0; i < FileControler.getArena(arene).getInt("nbrspawn") ;i++){
			
			Location loc = new Location(Bukkit.getWorld(FileControler.getArena(arene).getString("world")), FileControler.getArena(arene).getDouble("spawn." + (i+1) + ".x"), FileControler.getArena(arene).getDouble("spawn." + (i+1) + ".y"), FileControler.getArena(arene).getDouble("spawn." + (i+1) + ".z"), (float)FileControler.getArena(arene).getDouble("spawn." + (i+1) + ".yaw"), (float)FileControler.getArena(arene).getDouble("spawn." + (i+1) + ".pitch"));
			spawn.add(loc);
		}
		
		for(Player p : ArenaControle.getArena(arene).getPlayerList()){
			
			int randowm = (int) (Math.random() * spawn.size()); 
			
			PlayerControle.getJoueur(p.getName()).setProchainSpawn(randowm + 1);
			
			Location loc = spawn.get(randowm);
			
			p.teleport(loc);
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {

	               @Override
	               public void run() {
	            	   
	            	p.setHealth(0);   
	                p.spigot().respawn();
	                p.teleport(loc);
	            	spawn.remove(randowm);
	       			
	       			p.getInventory().clear();
	       			p.getInventory().setItem(0, new ItemStack(Material.BOW));
	       			p.getInventory().setItem(1, new ItemStack(Material.STONE_SWORD));
	       			p.getInventory().setItem(2, new ItemStack(Material.ARROW));
	       			p.updateInventory();
	               
	               }
	           },3);
			
		}
	}
	
	public static void tpInGame(Player p){
		ArrayList<Location> spawn = new ArrayList<Location>();
		
		for(int i = 0; i < FileControler.getArena(PlayerControle.getJoueur(p.getName()).getArene()).getInt("nbrspawn") ;i++){
			Location loc = new Location(Bukkit.getWorld(FileControler.getArena(PlayerControle.getJoueur(p.getName()).getArene()).getString("world")), FileControler.getArena(PlayerControle.getJoueur(p.getName()).getArene()).getDouble("spawn." + (i+1) + ".x"), FileControler.getArena(PlayerControle.getJoueur(p.getName()).getArene()).getDouble("spawn." + (i+1) + ".y"), FileControler.getArena(PlayerControle.getJoueur(p.getName()).getArene()).getDouble("spawn." + (i+1) + ".z"), (float)FileControler.getArena(PlayerControle.getJoueur(p.getName()).getArene()).getDouble("spawn." + (i+1) + ".yaw"), (float)FileControler.getArena(PlayerControle.getJoueur(p.getName()).getArene()).getDouble("spawn." + (i+1) + ".pitch"));
			spawn.add(loc);
		}
		
		int randowm = (int) (Math.random() * (spawn.size())); 
		p.teleport(spawn.get(randowm));
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {

            @Override
            public void run() {
            	
            	p.setHealth(0);
         	    p.spigot().respawn();
         	    p.teleport(spawn.get(randowm));
            	p.getInventory().clear();
        		p.getInventory().setItem(0, new ItemStack(Material.BOW));
        		p.getInventory().setItem(1, new ItemStack(Material.STONE_SWORD));
        		p.getInventory().setItem(2, new ItemStack(Material.ARROW));
        		p.updateInventory();
            	
            }
        },3);
	}
	
	
}

