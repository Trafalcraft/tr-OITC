package com.trafalcraft.ludo;

import org.bukkit.entity.Player;

public class player {
	int nbrPoint;
	Player joueur;

	public player(){
		System.out.println("§3§lOITC> §b" + "joueur crée");
		nbrPoint = 0;
		joueur = null;
	}
	
	public player(Player jr, int point){
		System.out.println("§3§lOITC> §b" + "joueur avec parametre crée");
		joueur = jr;
		nbrPoint = point;
	}
	
	public Player getJoueur(){
		return joueur;
	}
	public int getPoint(){
		return nbrPoint;
	}
	
	public void setPlayer(Player play){
		joueur = play;
	}
	public void setPoint(int pt){
		nbrPoint = pt;
	}
}
