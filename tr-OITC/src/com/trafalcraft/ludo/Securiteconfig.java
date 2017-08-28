package com.trafalcraft.ludo;

public class Securiteconfig {
	public static boolean Securite(){
		if(Main.getPlugin().getConfig().contains("lobby.x")){
			if(Main.getPlugin().getConfig().contains("joueurmax.max")){
				if(Main.getPlugin().getConfig().contains("nbrspawn")){
					if(Main.getPlugin().getConfig().contains("spawn.1")){
						if(Main.getPlugin().getConfig().contains("maxpoint.max")){
							if(Main.getPlugin().getConfig().contains("degatchute.active")){
								if(Main.getPlugin().getConfig().contains("degatfeu.active")){
									if(Main.getPlugin().getConfig().contains("degatnoyade.active")){
										if(Main.getPlugin().getConfig().contains("temps.max")){
											if(Main.getPlugin().getConfig().getInt("joueurmax.max") <= Main.getPlugin().getConfig().getInt("nbrspawn")){
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
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
