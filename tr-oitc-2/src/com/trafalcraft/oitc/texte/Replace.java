package com.trafalcraft.oitc.texte;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import com.trafalcraft.oitc.Main;

public class Replace {

	public static void replaceTexte(){
		
		File d = new File(Main.getPlugin().getDataFolder().getPath(), "texte.yml");
		
		YamlConfiguration file = YamlConfiguration.loadConfiguration(d);
		
		if(file.contains("Prefix") && file.get("Prefix") != null){
			Msg.Prefix.replaceBy(file.getString("Prefix"));
		}
		if(file.contains("No_Permission") && file.get("No_Permission") != null){
			Msg.Prefix.replaceBy(file.getString("No_Permission"));
		}
		if(file.contains("Commande_Imcomplete") && file.get("Commande_Imcomplete") != null){
			Msg.Prefix.replaceBy(file.getString("Commande_Imcomplete"));
		}
		if(file.contains("Argument_Incorect") && file.get("Argument_Incorect") != null){
			Msg.Prefix.replaceBy(file.getString("Argument_Incorect"));
		}
		if(file.contains("Arene_Inexistante") && file.get("Arene_Inexistante") != null){
			Msg.Prefix.replaceBy(file.getString("Arene_Inexistante"));
		}
		if(file.contains("No_Configure_Ingame") && file.get("No_Configure_Ingame") != null){
			Msg.Prefix.replaceBy(file.getString("No_Configure_Ingame"));
		}
		if(file.contains("arene_creee") && file.get("arene_creee") != null){
			Msg.Prefix.replaceBy(file.getString("arene_creee"));
		}
		if(file.contains("arene_existante") && file.get("arene_existante") != null){
			Msg.Prefix.replaceBy(file.getString("arene_existante"));
		}
		if(file.contains("lobby_config") && file.get("lobby_config") != null){
			Msg.Prefix.replaceBy(file.getString("lobby_config"));
		}
		if(file.contains("spawn_config") && file.get("spawn_config") != null){
			Msg.Prefix.replaceBy(file.getString("spawn_config"));
		}
		if(file.contains("lobby_event_config") && file.get("lobby_event_config") != null){
			Msg.Prefix.replaceBy(file.getString("lobby_event_config"));
		}
		if(file.contains("spawn_delete") && file.get("spawn_delete") != null){
			Msg.Prefix.replaceBy(file.getString("spawn_delete"));
		}
		if(file.contains("entrer_nombre") && file.get("entrer_nombre") != null){
			Msg.Prefix.replaceBy(file.getString("entrer_nombre"));
		}
		if(file.contains("config_temps") && file.get("config_temps") != null){
			Msg.Prefix.replaceBy(file.getString("config_temps"));
		}
		if(file.contains("config_maxplayer") && file.get("config_maxplayer") != null){
			Msg.Prefix.replaceBy(file.getString("config_maxplayer"));
		}
		if(file.contains("config_maxpoint") && file.get("config_maxpoint") != null){
			Msg.Prefix.replaceBy(file.getString("config_maxpoint"));
		}
		if(file.contains("config_status") && file.get("config_status") != null){
			Msg.Prefix.replaceBy(file.getString("config_status"));
		}
		if(file.contains("status_off_mal_congig") && file.get("status_off_mal_congig") != null){
			Msg.Prefix.replaceBy(file.getString("status_off_mal_congig"));
		}
		if(file.contains("status_deja_on") && file.get("status_deja_on") != null){
			Msg.Prefix.replaceBy(file.getString("status_deja_on"));
		}
		if(file.contains("compte_a_rebour_pas_commencee") && file.get("compte_a_rebour_pas_commencee") != null){
			Msg.Prefix.replaceBy(file.getString("compte_a_rebour_pas_commencee"));
		}
		if(file.contains("dans_aucune_arene") && file.get("dans_aucune_arene") != null){
			Msg.Prefix.replaceBy(file.getString("dans_aucune_arene"));
		}
		if(file.contains("arene_reload") && file.get("arene_reload") != null){
			Msg.Prefix.replaceBy(file.getString("arene_reload"));
		}
		if(file.contains("no_reload_avec_joueur") && file.get("no_reload_avec_joueur") != null){
			Msg.Prefix.replaceBy(file.getString("no_reload_avec_joueur"));
		}
		if(file.contains("aucune_arene_config") && file.get("aucune_arene_config") != null){
			Msg.Prefix.replaceBy(file.getString("aucune_arene_config"));
		}
		if(file.contains("trop_arene") && file.get("trop_arene") != null){
			Msg.Prefix.replaceBy(file.getString("trop_arene"));
		}
		if(file.contains("join") && file.get("join") != null){
			Msg.Prefix.replaceBy(file.getString("join"));
		}
		if(file.contains("party_full") && file.get("party_full") != null){
			Msg.Prefix.replaceBy(file.getString("party_full"));
		}
		if(file.contains("party_deja_en_cour") && file.get("party_deja_en_cour") != null){
			Msg.Prefix.replaceBy(file.getString("party_deja_en_cour"));
		}
		if(file.contains("deja_dans_une_arene") && file.get("deja_dans_une_arene") != null){
			Msg.Prefix.replaceBy(file.getString("deja_dans_une_arene"));
		}
		if(file.contains("mort_chute") && file.get("mort_chute") != null){
			Msg.Prefix.replaceBy(file.getString("mort_chute"));
		}
		if(file.contains("mort_bruler") && file.get("mort_bruler") != null){
			Msg.Prefix.replaceBy(file.getString("mort_bruler"));
		}
		if(file.contains("mort_lave") && file.get("mort_lave") != null){
			Msg.Prefix.replaceBy(file.getString("mort_lave"));
		}
		if(file.contains("mort_noyer") && file.get("mort_noyer") != null){
			Msg.Prefix.replaceBy(file.getString("mort_noyer"));
		}
		if(file.contains("mort_poison") && file.get("mort_poison") != null){
			Msg.Prefix.replaceBy(file.getString("mort_poison"));
		}
		if(file.contains("autre_mort") && file.get("autre_mort") != null){
			Msg.Prefix.replaceBy(file.getString("autre_mort"));
		}
		if(file.contains("kill") && file.get("kill") != null){
			Msg.Prefix.replaceBy(file.getString("kill"));
		}
		if(file.contains("serie_5") && file.get("serie_5") != null){
			Msg.Prefix.replaceBy(file.getString("serie_5"));
		}
		if(file.contains("serie_10") && file.get("serie_10") != null){
			Msg.Prefix.replaceBy(file.getString("serie_10"));
		}
		if(file.contains("serie_15") && file.get("serie_15") != null){
			Msg.Prefix.replaceBy(file.getString("serie_15"));
		}
		if(file.contains("serie_30") && file.get("serie_30") != null){
			Msg.Prefix.replaceBy(file.getString("serie_30"));
		}
		if(file.contains("stop_Lobby_Timer") && file.get("stop_Lobby_Timer") != null){
			Msg.Prefix.replaceBy(file.getString("stop_Lobby_Timer"));
		}
		if(file.contains("start_lobby_timer") && file.get("start_lobby_timer") != null){
			Msg.Prefix.replaceBy(file.getString("start_lobby_timer"));
		}
		if(file.contains("seconde") && file.get("seconde") != null){
			Msg.Prefix.replaceBy(file.getString("seconde"));
		}
		if(file.contains("start_game") && file.get("start_game") != null){
			Msg.Prefix.replaceBy(file.getString("start_game"));
		}
		if(file.contains("fin_game_1m") && file.get("fin_game_1m") != null){
			Msg.Prefix.replaceBy(file.getString("fin_game_1m"));
		}
		if(file.contains("fin_game") && file.get("fin_game") != null){
			Msg.Prefix.replaceBy(file.getString("fin_game"));
		}
		if(file.contains("win") && file.get("win") != null){
			Msg.Prefix.replaceBy(file.getString("win"));
		}
		if(file.contains("win_title") && file.get("win_title") != null){
			Msg.Prefix.replaceBy(file.getString("win_title"));
		}
		if(file.contains("egalite") && file.get("egalite") != null){
			Msg.Prefix.replaceBy(file.getString("egalite"));
		}
		if(file.contains("plusieurs_gagnants") && file.get("plusieurs_gagnants") != null){
			Msg.Prefix.replaceBy(file.getString("plusieurs_gagnants"));
		}
		if(file.contains("headshot") && file.get("headshot") != null){
			Msg.headshot.replaceBy(file.getString("headshot"));
		}
	
	}
}
