package com.trafalcraft.oitc.pannel;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.trafalcraft.oitc.file.FileControler;

public class CreateTeleportationInventory {
	
	public static void loadInventoryteleportation(Player p, String arene){
		
		Inventory inv = Bukkit.createInventory(p, 36, arene + ";tpInventory;oitc");
		
		if(FileControler.getArena(arene).contains("spawn")){
			
			ItemStack item = new ItemStack(Material.ENDER_PEARL);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("spawn");
			
			List<String> lore = new ArrayList<String>();
			
			item.setItemMeta(i);
			
			inv.setItem(10, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("spawn");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(10, item);
		}
		
		if(FileControler.getArena(arene).contains("lobby.x")){
			
			ItemStack item = new ItemStack(Material.ENDER_PEARL);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("lobby");
			
			List<String> lore = new ArrayList<String>();
			lore.add(FileControler.getArena(arene).getInt("lobby.x") + "");
			lore.add(FileControler.getArena(arene).getInt("lobby.y") + "");
			lore.add(FileControler.getArena(arene).getInt("lobby.z") + "");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(13, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("lobby");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(13, item);
		}
		
		if(FileControler.getArena(arene).contains("eventlobby.x")){
			
			ItemStack item = new ItemStack(Material.ENDER_PEARL);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("lobby event");
			
			List<String> lore = new ArrayList<String>();
			lore.add(FileControler.getArena(arene).getInt("eventlobby.x") + "");
			lore.add(FileControler.getArena(arene).getInt("eventlobby.y") + "");
			lore.add(FileControler.getArena(arene).getInt("eventlobby.z") + "");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(16, item);
			
		}else{
			
			ItemStack item = new ItemStack(Material.BARRIER);		
			ItemMeta i = item.getItemMeta();
			i.setDisplayName("lobby event");
			
			List<String> lore = new ArrayList<String>();
			lore.add("information non configurée");
			i.setLore(lore);
			
			item.setItemMeta(i);
			
			inv.setItem(16, item);
		}
		
		ItemStack item = new ItemStack(Material.ARROW);		
		ItemMeta i = item.getItemMeta();
		i.setDisplayName("back");
		
		item.setItemMeta(i);
		
		inv.setItem(31, item);
		
		p.openInventory(inv);
		
	}
}
