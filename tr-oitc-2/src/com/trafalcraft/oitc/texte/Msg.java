package com.trafalcraft.oitc.texte;

public enum Msg {
	
	Prefix("§3§lOITC> §b"),
	No_Permission("vous n'avez pas le droit d'utiliser cette commande"),
	Commande_Imcomplete("la commande est imcomplète"),
	Argument_Incorect("argument #arg incorect"),
	Arene_Inexistante("cette arène n'existe pas"),
	No_Configure_Ingame("vous ne pouvez faire cela sur une arène en cours"),
	arene_creee("arène crée"),
	arene_existante("cette arène existe déja"),
	lobby_config("le lobby a bien été enregistré"),
	spawn_config("le spawn numéro #num a bien été enregistré"),
	lobby_event_config("le lobby d'event a bien étè enregistré"),
	spawn_delete("le spawn numéro #num a bien été détruit"),
	entrer_nombre("vous devez entrer un nombre"),
	config_temps("temps maximum enregistré"),
	config_maxplayer("nombre de joueur maximum enregistré"),
	config_maxpoint("nombre de point nécessaire pour gagner enregistré"),
	config_status("status enregistré"),
	status_off_mal_congig("impossible de set le status à on, l'arène est mal configurée"),
	status_deja_on("le status est déja à on"),
	compte_a_rebour_pas_commencee("le temps avant le lancement de la partie n'a pas encore commencé"),
	dans_aucune_arene("vous n'êtes dans aucune arène"),
	arene_reload("l'arène a bien été reload"),
	no_reload_avec_joueur("l'arène contient des joueurs en attentes elle ne peut donc pas être reload"),
	aucune_arene_config("il n'y a aucune arène configurée"),
	trop_arene("il y a trop d'arènes configurées pour que le pannel d'administration les affiche"),
	join("#name a rejoint la partie"),
	party_full("la partie est déja pleine"),
	party_deja_en_cour("la partie est déja en cours"),
	deja_dans_une_arene("vous etes déja dans une arène"),
	mort_chute("#name est mort(e) de chute"),
	mort_bruler("#name est mort(e) brulé(e)"),
	mort_lave("#name est mort(e) dans la lave"),
	mort_noyer("#name est mort(e) noyé(e)"),
	mort_poison("#name est mort(e) empoisonné(e)"),
	autre_mort("#name est mort(e)"),
	kill("#killer a tué #victime"),
	serie_5("#name est en serie de 5 éliminations"),
	serie_10("#name est en serie de 10 éliminations"),
	serie_15("#name est en serie de 15 éliminations"),
	serie_30("#name est en serie de 30 éliminations"),
	stop_Lobby_Timer("il n'y a plus assez de joueur pour commencer la partie"),
	start_lobby_timer("la partie commence dans #temps seconde(s)"),
	seconde("seconde(s)"),
	start_game("la partie commence !!!"),
	fin_game_1m("la partie se termine dans une minute !!!"),
	fin_game("partie terminée!!!"),
	win("#name a gagné la partie félicitation!!!"),
	win_title(" a gagné"),
	egalite("égalité"),
	plusieurs_gagnants("les gagnants sont :"),
	headshot("#killer a tué #victime en head shot !!!");
	
	
	 private String value;
	 
		private Msg(String value) {
			this.value = value;
	    }
		
	    public String toString(){
	    	return value;
	    }
	    
	    public void replaceby(String value){
			this.value = value;
	    }
	    
	    public void replaceBy(String s){
	    	this.value = s;
	    }
}
