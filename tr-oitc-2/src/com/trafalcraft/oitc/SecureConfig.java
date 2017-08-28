package com.trafalcraft.oitc;

import com.trafalcraft.oitc.file.FileControler;

public class SecureConfig {

	public static boolean testConfig(String arene){
		if(FileControler.getArena(arene).contains("world") && FileControler.getArena(arene).getString("world") != null){
			if(FileControler.getArena(arene).contains("maxplayer") && FileControler.getArena(arene).getString("maxplayer") != null){
				if(FileControler.getArena(arene).contains("temps") && FileControler.getArena(arene).getString("temps") != null){
					if(FileControler.getArena(arene).contains("lobby.x") && FileControler.getArena(arene).getString("lobby.x") != null){
						if(FileControler.getArena(arene).contains("maxpoint") && FileControler.getArena(arene).getString("maxpoint") != null){
							if(FileControler.getArena(arene).contains("spawn.1.x") && FileControler.getArena(arene).getString("spawn.1.x") != null){
								if(FileControler.getArena(arene).getInt("nbrspawn") >= FileControler.getArena(arene).getInt("maxplayer")){
									if(FileControler.getArena(arene).contains("eventlobby.x") && FileControler.getArena(arene).getString("eventlobby.x") != null){
										return true;
									}else{
										return false;
									}
								}else{
									return false;
								}
							}else{
								return false;
							}
						}else{
							return false;
						}
					}else{
						return false;
					}
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
