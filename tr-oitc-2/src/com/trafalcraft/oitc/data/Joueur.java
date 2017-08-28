package com.trafalcraft.oitc.data;

import org.bukkit.entity.Player;

public class Joueur {
	Player player;
	String arene;
	boolean inGame;	
	int nbrPoint;
	int nbrMort;
	int nbrKill;
	int serie;
	int ProchainSpawn;
	
	public Joueur(Player p, String c, String a){
		this.player = p;
		this.arene = a;
		this.inGame = false;
		this.nbrPoint = 0;
	}
	
	public void setProchainSpawn(int i){
		this.ProchainSpawn = i;
	}
	
	public int getProchainSpawn(){
		return this.ProchainSpawn;
	}
	
	public void setNbrMort(int i){
		this.nbrMort = i;
	}
	
	public int getNbrmort(){
		return this.nbrMort;
	}
	
	public void setNbrKill(int i){
		this.nbrKill = i;
	}
	
	public int getNbrKill(){
		return this.nbrKill;
	}
	
	public void setSerie(int i){
		this.serie = i;
	}
	
	public int getSerie(){
		return this.serie;
	}
	
	public void setPlayer(Player pl){
		this.player = pl;
	}
	
	public void setArene(String ar){
		this.arene = ar;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public String getArene(){
		return this.arene;
	}
	
	public boolean getInGame(){
		return this.inGame;
	}
	
	public void setInGame(boolean b){
		this.inGame = b;
	}
	
	public void setNbrPoint(int i){
		this.nbrPoint = i;
	}
	
	public int getNbrPoint(){
		return this.nbrPoint;
	}
}
