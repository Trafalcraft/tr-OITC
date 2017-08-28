package com.trafalcraft.ludo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.trafalcraft.ludo.util.BungeeCoord;

public class Main extends JavaPlugin implements Listener {
	private static Main plugin;
	private ArrayList <player> playerList = new ArrayList<player>();
	private List <String> nom = new LinkedList<String>();
	private int nbrJoueur = 0;
	private int enJeu = 0;
	public EventClass ec;
	public static BungeeCoord	bungeeHandler;

	public void onEnable() {
		Bukkit.getServer().setWhitelist(false);
		this.ec = new EventClass(this);
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(ec,this);
		getConfig().options().copyDefaults(false);
		plugin = this;
		
		Bukkit.getServer().getPluginManager().registerEvents(bungeeHandler = new BungeeCoord(), plugin);
	    this.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
	}

	public static void main(String[] args) {
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		
		/*if (command.getName().equalsIgnoreCase("join")) {
			
			if(Main.getEnJeu() == 0){
				
				if(nbrJoueur != plugin.getConfig().getInt("joueurmax.max")){
			
					World world = Bukkit.getWorld("world");
					
					if (nom.contains(p.getName())) {
						
						p.sendMessage("§3§lOITC> §b" + "tu es d�j� dans la partie");
						
					}else{
						
						if(Securiteconfig.Securite() == true){
							player player = new player(p, 0);
							playerList.add(player);
							nom.add(p.getName());
							nbrJoueur++;
							Bukkit.getServer().broadcastMessage("§3§lOITC> §b" + p.getName() + " a rejoint la partie (" + nbrJoueur + "/" + plugin.getConfig().getInt("joueurmax.max") + ")");
						
							int x1 = plugin.getConfig().getInt("lobby.x");
							int y1 = plugin.getConfig().getInt("lobby.y");
							int z1 = plugin.getConfig().getInt("lobby.z");
							p.teleport(new Location(world, x1, y1, z1  ));
							
							p.getInventory().clear();
							
							if(Main.getNbrJoueur() >= 2){
								this.demare();
							}
							
						}else{
							p.sendMessage("§3§lOITC> §b" + "la partie est mal configur�e");
						}
					}
				}else{
					p.sendMessage("§3§lOITC> §b" + "la partie est pleine");
				}
			}else{
				p.sendMessage("§3§lOITC> §b" + "la partie est d�j� lanc�e");
			}
		}*/
		
		if (command.getName().equalsIgnoreCase("leave")) {
			
			BungeeCoord.sendPlayerToHub(p);
			
			}
		
		if (command.getName().equalsIgnoreCase("setlobby")) {
			if(p.isOp() == true){
				if(Main.getEnJeu() == 0){
					plugin.getConfig().createSection("lobby");
					plugin.getConfig().createSection("lobby.x");
					plugin.getConfig().createSection("lobby.y");
					plugin.getConfig().createSection("lobby.z");
					plugin.getConfig().createSection("lobby.v");
					plugin.getConfig().createSection("lobby.w");
				
					double x2 = p.getLocation().getBlockX();
					double y2 = p.getLocation().getBlockY();
					double z2 = p.getLocation().getBlockZ();
					float v2 = p.getLocation().getPitch();
					float w2 = p.getLocation().getYaw();
					
				
					plugin.getConfig().set("lobby.x", x2);
					plugin.getConfig().set("lobby.y", y2);
					plugin.getConfig().set("lobby.z", z2);
					plugin.getConfig().set("lobby.v", v2);
					plugin.getConfig().set("lobby.w", w2);
					plugin.saveConfig();
				
					p.sendMessage("§3§lOITC> §b" + "lobby enregistré");
				}else{
					p.sendMessage("§3§lOITC> §b" + "impossible de configurer la partie lorsqu'elle est en cours");
				}
			}else{
				p.sendMessage("§3§lOITC> §b" + "tu n'as pas l'autorisation d'effectuer cette commande");
			}
		}
		
		if (command.getName().equalsIgnoreCase("fstart")) {
			if(p.isOp() == true){
				if(nbrJoueur >= 2){
				
					Bukkit.getScheduler().cancelTask(timer1.task);
					Bukkit.getServer().broadcastMessage("§3§lOITC> §b" + p.getName() + " a forcé le lancement de la partie");
				
					int CAB = 6;
					timer1.startCountdown(CAB);
					
				}
			}else{
				p.sendMessage("§3§lOITC> §b" + "tu n'as pas l'autorisation d'effectuer cette commande");
			}
			
		}
		
		if (command.getName().equalsIgnoreCase("setspawn")) {
			
			if(p.isOp() == true){
				if(Main.getEnJeu() == 0){
					
					plugin.getConfig().set("nbrspawn", plugin.getConfig().getInt("nbrspawn") + 1);
					plugin.saveConfig();
					
					plugin.getConfig().createSection("spawn." + plugin.getConfig().getInt("nbrspawn"));
					plugin.saveConfig();
			
					double x3 = p.getLocation().getBlockX();
					double y3 = p.getLocation().getBlockY();
					double z3 = p.getLocation().getBlockZ();
					float v3 = p.getLocation().getPitch();
					float w3 = p.getLocation().getYaw();
			
					plugin.getConfig().createSection("spawn." + plugin.getConfig().getInt("nbrspawn") + ".x");
					plugin.getConfig().createSection("spawn." + plugin.getConfig().getInt("nbrspawn") + ".y");
					plugin.getConfig().createSection("spawn." + plugin.getConfig().getInt("nbrspawn") + ".z");
					plugin.getConfig().createSection("spawn." + plugin.getConfig().getInt("nbrspawn") + ".v");
					plugin.getConfig().createSection("spawn." + plugin.getConfig().getInt("nbrspawn") + ".w");
					plugin.saveConfig();
			
					plugin.getConfig().set("spawn." + plugin.getConfig().getInt("nbrspawn") + ".x", x3);
					plugin.getConfig().set("spawn." + plugin.getConfig().getInt("nbrspawn") + ".y", y3);
					plugin.getConfig().set("spawn." + plugin.getConfig().getInt("nbrspawn") + ".z", z3);
					plugin.getConfig().set("spawn." + plugin.getConfig().getInt("nbrspawn") + ".v", v3);
					plugin.getConfig().set("spawn." + plugin.getConfig().getInt("nbrspawn") + ".w", w3);
					plugin.saveConfig();
			
					p.sendMessage("§3§lOITC> §b" + "spawn " + plugin.getConfig().getInt("nbrspawn") + " créé");
				
				}else{
					p.sendMessage("§3lOITC> §b" + "impossible de configurer la partie lorsqu'elle est en cours");
				}
			}else{
				p.sendMessage("§3§lOITC> §b" + "tu n'as pas l'autorisation d'effectuer cette commande");
			}
		}
		
		if (command.getName().equalsIgnoreCase("setmaxjoueur")) {
			
			if(p.isOp() == true){
				if(Main.getEnJeu() == 0){
					try{
						plugin.getConfig().createSection("joueurmax");
						plugin.getConfig().createSection("joueurmax.max");
					
						Integer max = Integer.valueOf(args[0]);
					
						plugin.getConfig().set("joueurmax.max",max);
						plugin.saveConfig();
					
						p.sendMessage("§3§lOITC> §b" + "nombre maximum de joueurs enregistrés");
						
					}catch(NumberFormatException e){
						
						p.sendMessage("§3§lOITC> §b" + "entrez un nombre");
					}
					
				}else{
					p.sendMessage("§3§lOITC> §b" + "impossible de configurer la partie lorsqu'elle est en cours");
				}
			}else{
				p.sendMessage("§3§lOITC> §b" + "tu n'as pas l'autorisation d'effectuer cette commande");
			}
		}
		
		if (command.getName().equalsIgnoreCase("deletespawn")) {
			
			if(p.isOp() == true){
				if(Main.getEnJeu() == 0){
					
					plugin.getConfig().set("nbrspawn", plugin.getConfig().getInt("nbrspawn") - 1);
					plugin.saveConfig();
					
					p.sendMessage("§3§lOITC> §b" + "spawn " + (plugin.getConfig().getInt("nbrspawn")+1) + " détruit");
					
				}else{
					p.sendMessage("§3§lOITC> §b" + "impossible de configurer la partie lorsqu'elle est en cours");
				}
			}else{
				p.sendMessage("§3§lOITC> §b" + "tu n'as pas l'autorisation d'effectuer cette commande");
			}
		}
		
		if (command.getName().equalsIgnoreCase("setmaxpoint")) {
			
			if(p.isOp() == true){
				if(Main.getEnJeu() == 0){
					
					try{
						
						plugin.getConfig().createSection("maxpoint");
						plugin.getConfig().createSection("maxpoint.max");
						
						Integer max2 = Integer.valueOf(args[0]);
						
						plugin.getConfig().set("maxpoint.max",max2);
						plugin.saveConfig();
						
						p.sendMessage("§3§lOITC> §b" + "nombre maximum de points enregistrés");
						
					}catch(NumberFormatException e){
						
						p.sendMessage("§3§lOITC> §b" + "entrez un nombre");
						
					}
				}else{
					p.sendMessage("§3§lOITC> §b" + "impossible de configurer la partie lorsqu'elle est en cours");
				}
			}else{
				p.sendMessage("§3§lOITC> §b" + "tu n'as pas l'autorisation d'effectuer cette commande");
			}
		}
		
		if (command.getName().equalsIgnoreCase("settemps")) {
			
			if(p.isOp() == true){
				if(Main.getEnJeu() == 0){
					
					try{
						
						plugin.getConfig().createSection("temps");
						plugin.getConfig().createSection("temps.max");
						
						Integer max3 = Integer.valueOf(args[0]);
						
						plugin.getConfig().set("temps.max",max3);
						plugin.saveConfig();
						
						p.sendMessage("§3§lOITC> §b" + "temps maximum de la partie enregistré");
						
					}catch(NumberFormatException e){
						
						p.sendMessage("§3§lOITC> §b" + "entrez un nombre");
						
					}
			
				}else{
					p.sendMessage("§3§lOITC> §b" + "impossible de configurer la partie lorsqu'elle est en cours");
				}
			}else{
				p.sendMessage("§3§lOITC> §b" + "tu n'as pas l'autorisation d'effectuer cette commande");
			}
			
		}
		
		if (command.getName().equalsIgnoreCase("setdegatchute")) {
			
			if(p.isOp() == true){
				if(Main.getEnJeu() == 0){
					if(args[0].contains("true") || args[0].contains("false") ){
						
						plugin.getConfig().createSection("degatchute");
						plugin.getConfig().createSection("degatchute.active");
						
						Boolean active1 = Boolean.valueOf(args[0]);
						
						plugin.getConfig().set("degatchute.active",active1);
						plugin.saveConfig();
						
						p.sendMessage("§3§lOITC> §b" + "activation des dégàts de chutes enregistrée");
						
					}else{
						p.sendMessage("§3§lOITC> §b" + "argument invalide entrez true ou false");
					}
				}else{
					p.sendMessage("§3§lOITC> §b" + "impossible de configurer la partie lorsqu'elle est en cours");
				}
			}else{
				p.sendMessage("§3§lOITC> §b" + "tu n'as pas l'autorisation d'effectuer cette commande");
			}
		}
		
		if (command.getName().equalsIgnoreCase("setdegatnoyade")) {
			
			if(p.isOp() == true){
				if(Main.getEnJeu() == 0){
					if(args[0].contains("true") || args[0].contains("false") ){
						
						plugin.getConfig().createSection("degatnoyade");
						plugin.getConfig().createSection("degatnoyade.active");
						
						Boolean active2 = Boolean.valueOf(args[0]);
						
						plugin.getConfig().set("degatnoyade.active",active2);
						plugin.saveConfig();
						
						p.sendMessage("§3§lOITC> §b" + "activation des dégàts de noyades enregistrée");
						
					}else{
						p.sendMessage("§3§lOITC> §b" + "argument invalide entrez true ou false");
					}
				}else{
					p.sendMessage("§3§lOITC> §b" + "impossible de configurer la partie lorsqu'elle est en cours");
				}
			}else{
				p.sendMessage("§3§lOITC> §b" + "tu n'as pas l'autorisation d'effectuer cette commande");
			}
		}
		
		if (command.getName().equalsIgnoreCase("setdegatdufeu")) {
			
			if(p.isOp() == true){
				if(Main.getEnJeu() == 0){
					if(args[0].contains("true") || args[0].contains("false") ){
						
						plugin.getConfig().createSection("degatfeu");
						plugin.getConfig().createSection("degatfeu.active");
						
						Boolean active3 = Boolean.valueOf(args[0]);
						
						plugin.getConfig().set("degatfeu.active",active3);
						plugin.saveConfig();
						
						p.sendMessage("§3§lOITC> §b" + "activation des dégàts du feu enregistrée");
						
					}else{
						p.sendMessage("§3§lOITC> §b" + "argument invalide entrez true ou false");
					}
				}else{
					p.sendMessage("§3§lOITC> §b" + "impossible de configurer la partie lorsqu'elle est en cours");
				}
			}else{
				p.sendMessage("§3§lOITC> §b" + "tu n'as pas l'autorisation d'effectuer cette commande");
			}
		}
		return false;
	}
	
	public static void demare(){
		int CAB = 31;
		timer1.startCountdown(CAB);
	}
	
	public static void stop(){
		Bukkit.getScheduler().cancelTask(timer1.task);
		
		Bukkit.getServer().broadcastMessage("§3§lOITC> §b" + "il n'y a plus assez de joueurs pour lancer la partie");
	}
	
	
	public static Main getPlugin(){
		return plugin;
	}
	public static void addPlayer(player player){
		plugin.playerList.add(player);
	}
	public static void removePlayer(Player player){
		plugin.playerList.remove(player);
	}
	public static boolean containsPlayer(Player player){
		if(plugin.playerList.contains(player)){
			return true;
		}
		return false;
	}
	public static player getPlayerList(int i){
			return plugin.playerList.get(i);

	}
	public static int playerListSize(){
		return 	plugin.playerList.size();
	}
	public static String getNomname(int i){
		return plugin.nom.get(i);
	}
	public static int getNbrJoueur(){
		return plugin.nbrJoueur;
	}
	public static int nomIndexOf(String i){
		return plugin.nom.indexOf(i);
	}
	public static boolean containsNom(Entity nom){
		if(plugin.nom.contains(nom.getName())){
			return true;
		}
		return false;
		
	}
	public static int getEnJeu(){
		return plugin.enJeu;
	}
	public static void setEnJeu(int i){
		plugin.enJeu = i ;
		
	}
	public static void addNom(String j){
		plugin.nom.add(j);
	}
	public static void setNbrJoueur(int p){
		plugin.nbrJoueur = p;
	}
	public static void removeNom(int j){
		plugin.nom.remove(j);
	}
	public static void removePlayerIndex(int player){
		plugin.playerList.remove(player);
	}
}
