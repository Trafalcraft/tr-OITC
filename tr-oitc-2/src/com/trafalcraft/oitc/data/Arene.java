package com.trafalcraft.oitc.data;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.inventivetalent.bossbar.BossBarAPI;
import org.inventivetalent.bossbar.BossBarAPI.Property;
import org.inventivetalent.bossbar.BossBarAPI.Style;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Sound;

import com.trafalcraft.oitc.ClearPotion;
import com.trafalcraft.oitc.Main;
import com.trafalcraft.oitc.Quite;
import com.trafalcraft.oitc.Tp;
import com.trafalcraft.oitc.Controler.PlayerControle;
import com.trafalcraft.oitc.file.FileControler;
import com.trafalcraft.oitc.texte.Msg;
import com.trafalcraft.oitc.util.BungeeCord;

import net.md_5.bungee.api.chat.TextComponent;

public class Arene extends BossBarAPI{
	private String name;
	private ArrayList <Player> playerList = new ArrayList<Player>();
	private String status;
	private int temps;
	private int taskLobby;
	private int taskGame;
	private int taskFinish;
	ArrayList<Player> winner = new ArrayList<Player>();
	private ScoreBoard score;
	private boolean event;
	
	public Arene(String nom){
		
		this.name = nom;
		this.status = "lobby";

	}
	
	public int getTaskLobby(){
		return this.taskLobby;
	}
	
	public int getTaskGame(){
		return this.taskGame;
	}
	
	public int getTaskFinish(){
		return this.taskFinish;
	}
	
	public void setTemps(int i){
		this.temps = i;
	}
	
	public int getTemps(){
		return this.temps;
	}
	
	public void setEvent(boolean e){
		this.event = e;
	}
	
	public boolean getEvent(){
		return this.event;
	}
	
	public ScoreBoard getScoreBoard(){
		return this.score;
	}
	
	public void setName(String nom){
		this.name = nom;
	}
	
	public String getName(){
		return name;
	}
	
	public void addPlayer(Player p){
		playerList.add(p);
		
		if(getPlayerList().size() == 2){
			startLobbyTimer();
			
			for(Player pl : getPlayerList()){
				BossBarAPI.removeAllBars(pl);
				BossBarAPI.addBar(pl, new TextComponent("§b" + "oitc"), Color.BLUE, Style.PROGRESS, 1.0f, 620, 1, Property.PLAY_MUSIC);
			
				pl.setLevel(30);
			}
		}
	}
	
	public void removePlayer(Player p){
		playerList.remove(p);
		
		if(getPlayerList().size() == 1){
			if(getTaskLobby() != 0){
				stopLobbyTimer();
				
				for(Player pl : getPlayerList()){
					pl.sendMessage(Msg.Prefix.toString() + Msg.stop_Lobby_Timer.toString());
					
					BossBarAPI.removeAllBars(pl);
					BossBarAPI.addBar(pl, new TextComponent("§b" + "oitc"), Color.BLUE, Style.PROGRESS, 1.0f);
					
					pl.setLevel(0);
				}
			}
		}
	}
	
	public int PlayerIndexOf(Player p){
		return playerList.indexOf(p);
	}
	
	public void setStatus(String s){
		this.status = s;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public ArrayList<Player> getPlayerList(){
		return this.playerList;
	}
	
	public void startLobbyTimer(){
		this.temps = 31;
		this.taskLobby = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            
			public void run() {
						for(Player pl : getPlayerList()){
							pl.setLevel(temps);
						}
                   		if(temps == 30||temps == 20||temps == 10||(temps <= 5&&temps>0)){
                   			for(Player pl : getPlayerList()){
                   				pl.sendMessage(Msg.Prefix.toString() + Msg.start_lobby_timer.toString().replace("#temps", temps + ""));
                   				pl.playSound(pl.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
                   				pl.sendTitle("§b" + temps + "", "§b" + Msg.seconde.toString());
                   				
                   			}
                   		}else if(temps <= 0){
                   			for(Player pl : getPlayerList()){
                   				pl.sendMessage(Msg.Prefix.toString() + Msg.start_game.toString());  
                   				pl.playSound(pl.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100.0F, 1.0F);
                   				
                   				BossBarAPI.removeAllBars(pl);
                   				BossBarAPI.addBar(pl, new TextComponent("§b" + "oitc"), Color.BLUE, Style.PROGRESS, 1.0f, (FileControler.getArena(getName()).getInt("temps")) * 20 * 60, 1, Property.PLAY_MUSIC);
            					
                   				
                   				PlayerControle.getJoueur(pl.getName()).setInGame(true);
               					pl.setHealth(20);
               					pl.setSaturation(0);
               					pl.setFoodLevel(20);
               					ClearPotion.clearEffect(pl);
                   			}
                   			
                   			stopLobbyTimer();
               				startGameTimer();
               				status = "in-game";
               				Tp.tpStart(getName());
               				taskLobby = 0;
               				score = new ScoreBoard(getName());
               				
               				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {

            	                @Override
            	                public void run() {
            	                   for(Player p : playerList){
            	                	   p.sendTitle("", "");
            	                   }
            	                }
            	            },20);
               				
                   		}
            	temps = temps-1;
            }
         }, 0, 20);
	}
	
	public void stopLobbyTimer(){
		Bukkit.getServer().getScheduler().cancelTask(this.taskLobby);
	}
	
	public void startGameTimer(){
		this.temps = (FileControler.getArena(name).getInt("temps"))*60;
		this.taskGame = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            
			public void run() {
						if(temps == 30||temps == 20||temps == 10||(temps <= 5&&temps>0)){
							for(Player pl : getPlayerList()){
								pl.playSound(pl.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
							}
						}
                   		if(temps == 60){
                   			for(Player pl : getPlayerList()){
                   				pl.sendMessage(Msg.Prefix.toString() + Msg.fin_game_1m.toString());
                   				pl.playSound(pl.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0F, 1.0F);
                   			}
                   		}else if(temps <= 0){
                   			for(Player pl : getPlayerList()){
                   				pl.sendMessage(Msg.Prefix.toString() + Msg.fin_game.toString());
                   				PlayerControle.getJoueur(pl.getName()).setInGame(false);
                   				pl.playSound(pl.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100.0F, 1.0F);
                   			}
                   			stopGameTimer();
                   			setStatus("end");
               				WhoWin();
               				taskGame = 0;
                   		}
            	temps = temps-1;
            }
         }, 0, 20);
	}
	
	public void stopGameTimer(){
		Bukkit.getServer().getScheduler().cancelTask(taskGame);
	}
	
	public void WhoWin(){
		
		for(Player p : getPlayerList()){
			if(winner.size() == 0){
				winner.add(p);
			}else if(PlayerControle.getJoueur(p.getName()).getNbrPoint() > PlayerControle.getJoueur(winner.get(0).getName()).getNbrPoint()){
				winner.clear();
				winner.add(p);
			}
		}
		
		if(winner.size() == 1){
			for(Player p : getPlayerList()){
				p.sendMessage(Msg.Prefix.toString() + Msg.win.toString().replace("#name", winner.get(0).getName()));
				
				p.sendTitle("§b" + winner.get(0).getName() + "" , "§b" + Msg.win_title.toString());
				
				BossBarAPI.removeAllBars(p);
				BossBarAPI.addBar(p, new TextComponent("§b" + winner.get(0).getName() + Msg.win_title.toString()), Color.BLUE, Style.PROGRESS, 1.0f, Property.PLAY_MUSIC);
   				
			}
		}else if(winner.size() > 1){
			for(Player p : getPlayerList()){
				
				p.sendTitle("§b" + Msg.egalite.toString() , "");
				

   				BossBarAPI.removeAllBars(p);
				BossBarAPI.addBar(p, new TextComponent("§b" + Msg.egalite.toString()), Color.BLUE, Style.PROGRESS, 1.0f, Property.PLAY_MUSIC);
   				
				
				p.sendMessage(Msg.Prefix.toString() + Msg.plusieurs_gagnants.toString());
				
				for(Player pl : winner){
					p.sendMessage(Msg.Prefix.toString() + "-" + pl.getName());
				}
			}
		}else{
			finGame();
		}
		
		startFinishTimer();
	}
	
	public void startFinishTimer(){
		this.temps = 5;
		this.taskFinish = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            
			public void run() {
            	
                   		if(temps != 0){
                   				for(Player listwinner : winner){
                   	
                   					Firework f = (Firework) listwinner.getWorld().spawn(listwinner.getLocation(), Firework.class);
                   					FireworkMeta fm = f.getFireworkMeta();
                    		
                   					fm.addEffects(FireworkEffect.builder()
                   							.flicker(true)
                   							.trail(true)
                   							.with(Type.BALL_LARGE)
                   							.withColor(org.bukkit.Color.AQUA)
                   							.withColor(org.bukkit.Color.RED)
                   							.withColor(org.bukkit.Color.AQUA)
                   							.withColor(org.bukkit.Color.RED)
                   							.build()
                   							);
                    		
                   					fm.setPower(2);
                   					f.setFireworkMeta(fm);
                   				}
                   		}else{
                   		
                   			if(event == true){
	                   			for(Player p : getPlayerList()){
	                   				p.teleport(new Location(Bukkit.getWorld(FileControler.getArena(getName()).getString("world")), FileControler.getArena(getName()).getDouble("eventlobby.x"), FileControler.getArena(getName()).getDouble("eventlobby.y"), FileControler.getArena(getName()).getDouble("eventlobby.z"), (float)FileControler.getArena(getName()).getDouble("eventlobby.yaw"), (float)FileControler.getArena(getName()).getDouble("eventlobby.pitch")));
	                   			}
                   			}
                   			
                   			StopFinishTimer();
                   			finGame();
                   			taskFinish = 0;
                   		}
            	temps = temps-1;
            }
         }, 0, 20);
	}
	
	public void StopFinishTimer(){
		Bukkit.getServer().getScheduler().cancelTask(taskFinish);
	}
	
	public void finGame(){
		
		if(event == false){
			for(int i = 0; i < getPlayerList().size(); i = i){
				BungeeCord.sendPlayerToHub(getPlayerList().get(i));
				Quite.leave(getPlayerList().get(0));
			}
		}else{
			for(int i = 0; i < getPlayerList().size(); i = i){
				Quite.leave(getPlayerList().get(0));
			}
		}
		
		this.taskLobby = 0;
		this.taskGame = 0;
		this.taskFinish = 0;
		this.playerList.clear();
		this.status = "lobby";
		this.winner.clear();
		this.temps = 0;
		getScoreBoard().clearScoreBoard();
	}
}
