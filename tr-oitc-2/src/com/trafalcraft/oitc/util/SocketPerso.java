package com.trafalcraft.oitc.util;

import com.trafalcraft.client.api.SocketPlugin;
import com.trafalcraft.oitc.Controler.ArenaControle;
import com.trafalcraft.oitc.Controler.PlayerControle;
import com.trafalcraft.oitc.file.FileControler;

public class SocketPerso implements SocketPlugin {

	@Override
	public boolean checkJoin(String arg0, String arg1) {
		if(ArenaControle.contains(arg0)){
			if(ArenaControle.getArena(arg0).getStatus().equalsIgnoreCase("lobby")){
					if(ArenaControle.getArena(arg0).getPlayerList().size() < FileControler.getArena(arg0).getInt("maxplayer")){
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
	}

	@Override
	public void playerJoin(String arg0, String arg1, String arg2) {
		PlayerControle.addPlayer(arg0, arg1);
	}

}
