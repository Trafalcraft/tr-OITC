package com.trafalcraft.oitc.Controler;

import java.util.Map;

import com.google.common.collect.Maps;
import com.trafalcraft.oitc.data.Arene;

public class ArenaControle {
	private final static Map<String, Arene> activeMap = Maps.newHashMap();
	
	public static void addArene(String name){
		if(!activeMap.containsKey(name)){
			Arene arene = new Arene(name);
			activeMap.put(name, arene);
		}
	}
	
	public static boolean contains(String aname){
		if(activeMap.containsKey(aname)){
			return true;
		}
		return false;
	}
	
	public static void removeMap(String name){
		if(activeMap.containsKey(name)){
			activeMap.remove(name);
		}
	}
	
	public static Arene getArena(String name){
		return activeMap.get(name);
	}
}
