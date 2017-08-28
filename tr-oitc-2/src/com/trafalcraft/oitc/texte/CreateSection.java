package com.trafalcraft.oitc.texte;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import com.trafalcraft.oitc.Main;

public class CreateSection {

	public static void create(){
		
		File d = new File(Main.getPlugin().getDataFolder(), "texte.yml");
		
		YamlConfiguration file = YamlConfiguration.loadConfiguration(d);
		
		file.set("Prefix", "§3§lOITC> §b");
		file.set("No_Permission", "vous n'avez pas le droit d'utiliser cette commande");
		file.set("Commande_Imcomplete", "la commande est imcomplète");
		file.set("Argument_Incorect", "argument #arg incorect");
		file.set("Arene_Inexistante", "cette arène n'existe pas");
		file.set("No_Configure_Ingame", "vous ne pouvez faire cela sur une arène en cours");
		file.set("arene_creee", "arène crée");
		file.set("arene_existante", "cette arène existe déja");
		file.set("lobby_config", "le lobby a bien été enregistré");
		file.set("spawn_config", "le spawn numéro #num a bien été enregistré");
		file.set("lobby_event_config", "le lobby d'event a bien étè enregistré");
		file.set("spawn_delete", "le spawn numéro #num a bien été détruit");
		file.set("entrer_nombre", "vous devez entrer un nombre");
		file.set("config_temps", "temps maximum enregistré");
		file.set("config_maxplayer", "nombre de joueur maximum enregistré");
		file.set("config_maxpoint", "nombre de point nécessaire pour gagner enregistré");
		file.set("config_status", "status enregistré");
		file.set("status_off_mal_congig", "impossible de set le status à on, l'arène est mal configurée");
		file.set("status_deja_on", "le status est déja à on");
		file.set("compte_a_rebour_pas_commencee", "le temps avant le lancement de la partie n'a pas encore commencé");
		file.set("dans_aucune_arene", "vous n'êtes dans aucune arène");
		file.set("arene_reload", "l'arène a bien été reload");
		file.set("no_reload_avec_joueur", "l'arène contient des joueurs en attentes elle ne peut donc pas être reload");
		file.set("aucune_arene_config", "il n'y a aucune arène configurée");
		file.set("trop_arene", "il y a trop d'arènes configurées pour que le pannel d'administration les affiche");
		file.set("join", "#name a rejoint la partie");
		file.set("party_full", "la partie est déja pleine");
		file.set("party_deja_en_cour", "la partie est déja en cours");
		file.set("deja_dans_une_arene", "vous etes déja dans une arène");
		file.set("mort_chute", "#name est mort(e) de chute");
		file.set("mort_bruler", "#name est mort(e) brulé(e)");
		file.set("mort_lave", "#name est mort(e) dans la lave");
		file.set("mort_noyer", "#name est mort(e) noyé(e)");
		file.set("mort_poison", "#name est mort(e) empoisonné(e)");
		file.set("autre_mort", "#name est mort(e)");
		file.set("kill", "#killer a tué #victime");
		file.set("serie_5", "#name est en serie de 5 éliminations");
		file.set("serie_10", "#name est en serie de 10 éliminations");
		file.set("serie_15", "#name est en serie de 15 éliminations");
		file.set("serie_30", "#name est en serie de 30 éliminations");
		file.set("stop_Lobby_Timer", "il n'y a plus assez de joueur pour commencer la partie");
		file.set("start_lobby_timer", "la partie commence dans #temps seconde(s)");
		file.set("seconde", "seconde(s)");
		file.set("start_game", "la partie commence !!!");
		file.set("fin_game_1m", "la partie se termine dans une minute !!!");
		file.set("fin_game", "partie terminée!!!");
		file.set("win", "#name a gagné la partie félicitation!!!");
		file.set("win_title", " a gagné");
		file.set("egalite", "égalité");
		file.set("plusieurs_gagnants", "les gagnants sont :");
		file.set("headshot" ,"#killer a tué #victime en head shot !!!");
		
		try{
			
			file.save(d);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
