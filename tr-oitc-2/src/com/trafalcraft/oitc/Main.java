package com.trafalcraft.oitc;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.inventivetalent.bossbar.BossBarAPI;
import org.inventivetalent.bossbar.BossBarAPI.Color;
import org.inventivetalent.bossbar.BossBarAPI.Property;
import org.inventivetalent.bossbar.BossBarAPI.Style;

import com.trafalcraft.client.api.SocketApi;
import com.trafalcraft.oitc.Controler.ArenaControle;
import com.trafalcraft.oitc.Controler.PlayerControle;
import com.trafalcraft.oitc.file.FileControler;
import com.trafalcraft.oitc.pannel.CreateAreneList;
import com.trafalcraft.oitc.texte.CreateSection;
import com.trafalcraft.oitc.texte.Msg;
import com.trafalcraft.oitc.texte.Replace;
import com.trafalcraft.oitc.util.BungeeCord;
import com.trafalcraft.oitc.util.SocketPerso;

import net.md_5.bungee.api.chat.TextComponent;

public class Main extends JavaPlugin implements Listener{
	private static JavaPlugin plugin;
	
	public void onEnable(){
		
		plugin = this;
		getPlugin().getConfig().options().copyDefaults(true);
		
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(),this);
		Bukkit.getServer().getPluginManager().registerEvents(new com.trafalcraft.oitc.pannel.InventoryClickListener(),this);
		SocketApi.registerPlugin("oitc", new SocketPerso());
		
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeCord());
		
		File d = new File(getPlugin().getDataFolder().getPath()+"//arene");
		
		if(!(d.exists())){
			d.mkdir();
			d.mkdirs();
		}
		
		File t = new File(getPlugin().getDataFolder().getPath() , "texte.yml");
		
		if(!(t.exists())){
			try {
				t.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			CreateSection.create();
		}else{
			Replace.replaceTexte();
		}
		
		FileControler.loadAllFile();
		
		for(String s : FileControler.getAllName()){
			if(FileControler.getArena(s).getString("status").equalsIgnoreCase("on")){
				if(SecureConfig.testConfig(s) == true){
					
					ArenaControle.addArene(s);
					Bukkit.getLogger().info("§3§lOITC> §b" + "arene " + s + " creer");
					
				}else{
					Bukkit.getLogger().warning("§3§lOITC> §b" + "l'arene " + s + " est mal configuree");
				}
			}
		}
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("oitc")){
			if(args.length != 0){
				if(args[0].equalsIgnoreCase("create")){
					if(p.isOp()){
						if(!(PlayerControle.contains(p.getName()))){
							if(args.length == 2){
								if(!(FileControler.contains(args[1]))){
									FileControler.addFile(getPlugin().getDataFolder(), args[1]);
									FileControler.getArena(args[1]).set("name", args[1]);
									FileControler.getArena(args[1]).set("world", p.getWorld().getName());
									FileControler.getArena(args[1]).set("status", "off");
									FileControler.getArena(args[1]).set("nbrspawn", 0);
									FileControler.saveArena(args[1]);
									p.sendMessage(Msg.Prefix.toString() + Msg.arene_creee.toString());
								}else{
									p.sendMessage(Msg.Prefix.toString() + Msg.arene_existante.toString());
								}
							}else{
								p.sendMessage(Msg.Prefix.toString() + Msg.Commande_Imcomplete.toString());
							}
						}else{
							p.sendMessage(Msg.Prefix.toString() + Msg.No_Configure_Ingame.toString());
						}
					}else{
						p.sendMessage(Msg.Prefix.toString() + Msg.No_Permission.toString());
					}
					
				}else if(FileControler.contains(args[0])){
					if(p.isOp()){
						if(!PlayerControle.contains(p.getName())){
							if(args.length == 3){
								if(args[1].equalsIgnoreCase("set")){
									if(args[2].equalsIgnoreCase("lobby")){
												
										FileControler.getArena(args[0]).set("lobby.x", p.getLocation().getX());
										FileControler.getArena(args[0]).set("lobby.y", p.getLocation().getY());
										FileControler.getArena(args[0]).set("lobby.z", p.getLocation().getZ());
										FileControler.getArena(args[0]).set("lobby.yaw", p.getLocation().getYaw());
										FileControler.getArena(args[0]).set("lobby.pitch", p.getLocation().getPitch());
										FileControler.saveArena(args[0]);
												
										p.sendMessage(Msg.Prefix.toString() + Msg.lobby_config.toString());
									}else if(args[2].equalsIgnoreCase("spawn")){
										
										FileControler.getArena(args[0]).set("nbrspawn", FileControler.getArena(args[0]).getInt("nbrspawn") + 1);
										
										FileControler.getArena(args[0]).set("spawn." + FileControler.getArena(args[0]).getInt("nbrspawn") + ".x", p.getLocation().getX());
										FileControler.getArena(args[0]).set("spawn." + FileControler.getArena(args[0]).getInt("nbrspawn") + ".y", p.getLocation().getY());
										FileControler.getArena(args[0]).set("spawn." + FileControler.getArena(args[0]).getInt("nbrspawn") + ".z", p.getLocation().getZ());
										FileControler.getArena(args[0]).set("spawn." + FileControler.getArena(args[0]).getInt("nbrspawn") + ".yaw", p.getLocation().getYaw());
										FileControler.getArena(args[0]).set("spawn." + FileControler.getArena(args[0]).getInt("nbrspawn") + ".pitch", p.getLocation().getPitch());
										FileControler.saveArena(args[0]);
										
										p.sendMessage(Msg.Prefix.toString() + Msg.spawn_config.toString().replace("#num", FileControler.getArena(args[0]).getString("nbrspawn")));
									
									}else if(args[2].equalsIgnoreCase("eventlobby")){
										
										FileControler.getArena(args[0]).set("eventlobby.x", p.getLocation().getX());
										FileControler.getArena(args[0]).set("eventlobby.y", p.getLocation().getY());
										FileControler.getArena(args[0]).set("eventlobby.z", p.getLocation().getZ());
										FileControler.getArena(args[0]).set("eventlobby.yaw", p.getLocation().getYaw());
										FileControler.getArena(args[0]).set("eventlobby.pitch", p.getLocation().getPitch());
										FileControler.saveArena(args[0]);
										
										p.sendMessage(Msg.Prefix.toString() + Msg.lobby_event_config.toString());
									}else if(args[2].equalsIgnoreCase("help")){
										p.sendMessage("§3§l" + "/oitc <nom d'arène> set lobby: §b enregistre le lieu du lobby");
										p.sendMessage("§3§l" + "/oitc <nom d'arène> set spawn: §b enregistre le lieu d'un spawn");
										p.sendMessage("§3§l" + "/oitc <nom d'arène> set eventlobby: §b enregistre le lieu du lobby général");
										p.sendMessage("§3§l" + "/oitc <nom d'arène> set time: §b configure le temps max");
										p.sendMessage("§3§l" + "/oitc <nom d'arène> set maxplayer: §b configure le nombre de joueur max");
										p.sendMessage("§3§l" + "/oitc <nom d'arène> set maxpoint: §b configure le nombre de point nessessaire pour gagner");
										p.sendMessage("§3§l" + "/oitc <nom d'arène> set status <on/off>: §b configure le status de l'aréne");
									}else{
										p.sendMessage(Msg.Prefix.toString() + Msg.Argument_Incorect.toString().replace("#arg", args[2]));
									}
								}else if(args[1].equalsIgnoreCase("delete")){
									if(args[2].equalsIgnoreCase("spawn")){
										if(FileControler.getArena(args[0]).getInt("nbrspawn") != 0){
											
											FileControler.getArena(args[0]).set("spawn." + FileControler.getArena(args[0]).getString("nbrspawn"), null);
											FileControler.getArena(args[0]).set("nbrspawn", FileControler.getArena(args[0]).getInt("nbrspawn") - 1);
											FileControler.saveArena(args[0]);
											
											p.sendMessage(Msg.Prefix.toString() + Msg.spawn_delete.toString().replace("#num", FileControler.getArena(args[0]).getString("nbrspawn")));
										}
									}else{
										p.sendMessage(Msg.Prefix.toString() + Msg.Argument_Incorect.toString().replace("#arg", args[1]));
									}
								}else if(args[1].equalsIgnoreCase("help")){
									p.sendMessage("§3§l" + "/oitc <nom d'arène> set: §b permet de configurer l'arène");
									p.sendMessage("§3§l" + "/oitc <nom d'arène> delete spawn: §b permet de detruire le dernier spawn");
								}else{
									p.sendMessage(Msg.Prefix.toString() + Msg.Argument_Incorect.toString().replace("#arg", args[1]));
								}
							}else if(args.length == 4){
								if(args[1].equalsIgnoreCase("set")){
									if(args[2].equalsIgnoreCase("time")){
										try{
													
											Integer temps = Integer.valueOf(args[3]);
											FileControler.getArena(args[0]).set("temps", temps);
											FileControler.saveArena(args[0]);
											p.sendMessage(Msg.Prefix.toString() + Msg.config_temps.toString());
												
										}catch(NumberFormatException e){
											p.sendMessage(Msg.Prefix.toString() + Msg.entrer_nombre.toString());
										}
									}else if(args[2].equalsIgnoreCase("maxplayer")){
										try{
													
											Integer max1 = Integer.valueOf(args[3]);
											FileControler.getArena(args[0]).set("maxplayer", max1);
											FileControler.saveArena(args[0]);
											p.sendMessage(Msg.Prefix.toString() + Msg.config_maxplayer.toString());
												
										}catch(NumberFormatException e){
											p.sendMessage(Msg.Prefix.toString() + Msg.entrer_nombre.toString());
										}
									}else if(args[2].equalsIgnoreCase("maxpoint")){
										try{
													
											Integer max1 = Integer.valueOf(args[3]);
											FileControler.getArena(args[0]).set("maxpoint", max1);
											FileControler.saveArena(args[0]);
											p.sendMessage(Msg.Prefix.toString() + Msg.config_maxpoint.toString());
												
										}catch(NumberFormatException e){
											p.sendMessage(Msg.Prefix.toString() + Msg.entrer_nombre.toString());
										}
									}else if(args[2].equalsIgnoreCase("status")){
										if(args[3].equalsIgnoreCase("on") || args[3].equalsIgnoreCase("off")){
												if(FileControler.getArena(args[0]).getString("status").equalsIgnoreCase("off")){
													FileControler.getArena(args[0]).set("status", args[3]);
													FileControler.saveArena(args[0]);
														
													if(SecureConfig.testConfig(args[0]) == true){
														FileControler.getArena(args[0]).set("status", args[3]);
														FileControler.saveArena(args[0]);
														
														ArenaControle.addArene(args[0]);
														Bukkit.getLogger().info("arene " + args[0] + " creer");
														
														p.sendMessage(Msg.Prefix.toString() + Msg.config_status.toString());
													}else{
														p.sendMessage(Msg.Prefix.toString() + Msg.status_off_mal_congig.toString());
													}
												}else{
													p.sendMessage(Msg.Prefix.toString() + Msg.status_deja_on.toString());
												}
										}else{
											p.sendMessage(Msg.Prefix.toString() + Msg.Argument_Incorect.toString().replace("#arg", args[3]));
										}
									}else{
										p.sendMessage(Msg.Prefix.toString() + Msg.Argument_Incorect.toString().replace("#arg", args[2]));
									}
								}else{	
									p.sendMessage(Msg.Prefix.toString() + Msg.Argument_Incorect.toString().replace("#arg", args[1]));
								}
							}else if(args.length == 2 && args[1].equalsIgnoreCase("set")){	
								p.sendMessage("§3§l" + "/oitc <nom d'arène> set lobby: §b enregistre le lieu du lobby");
								p.sendMessage("§3§l" + "/oitc <nom d'arène> set spawn: §b enregistre le lieu d'un spawn");
								p.sendMessage("§3§l" + "/oitc <nom d'arène> set eventlobby: §b enregistre le lieu du lobby général");
								p.sendMessage("§3§l" + "/oitc <nom d'arène> set time: §b configure le temps maximum");
								p.sendMessage("§3§l" + "/oitc <nom d'arène> set maxplayer: §b set le nombre max de joueur");
								p.sendMessage("§3§l" + "/oitc <nom d'arène> set maxpoint: §b configure le nombre de point nessessaire pour gagner");
								p.sendMessage("§3§l" + "/oitc <nom d'arène> set status <on/off>: §b set le status");
							}else{
								p.sendMessage("§3§l" + "/oitc <nom d'arène> set: §b permet de configurer l'arène");
								p.sendMessage("§3§l" + "/oitc <nom d'arène> delete spawn: §b permet de detruire le dernier spawn");
							}
						}else{
							p.sendMessage(Msg.Prefix.toString() + Msg.No_Configure_Ingame.toString());
						}
					}else{
						p.sendMessage(Msg.Prefix.toString() + Msg.No_Permission.toString());
					}
				}else if(args[0].equalsIgnoreCase("join")){
					if(args.length == 2){
						PlayerControle.addPlayer(p.getName(), args[1]);
					}else{
						p.sendMessage(Msg.Prefix.toString() + Msg.Commande_Imcomplete.toString());
					}
				}else if(args[0].equalsIgnoreCase("fstart")){
					if(p.isOp()){
						if(PlayerControle.contains(p.getName())){
							
							String arene = PlayerControle.getJoueur(p.getName()).getArene();
							
							if(ArenaControle.getArena(arene).getStatus().equalsIgnoreCase("lobby")){
								if(ArenaControle.getArena(arene).getTaskLobby() != 0){
									
									ArenaControle.getArena(arene).setTemps(5);
									
									for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
										BossBarAPI.removeAllBars(pl);
										BossBarAPI.addBar(pl, new TextComponent("§b" + "oitc"), Color.BLUE, Style.PROGRESS, (float)5/30, 100, 1, Property.PLAY_MUSIC);
									}
								
								}else{
									p.sendMessage(Msg.Prefix.toString() + Msg.compte_a_rebour_pas_commencee.toString());
								}
							}else{
								p.sendMessage(Msg.Prefix.toString() + Msg.No_Configure_Ingame.toString());
							}
						}else{
							p.sendMessage(Msg.Prefix.toString() + Msg.dans_aucune_arene.toString());
						}
					}else{
						p.sendMessage(Msg.Prefix.toString() + Msg.No_Permission.toString());
					}
				}else if(args[0].equalsIgnoreCase("start")){
					if(p.isOp()){
						if(args.length == 3 || args.length == 5){
							if(args[1].equalsIgnoreCase("event")){
								if(ArenaControle.contains(args[2])){
									if(args.length == 5){
										if(args[3].equalsIgnoreCase("admin")){
											if(args[4].equalsIgnoreCase("on")){
												
												ArenaControle.getArena(args[2]).setEvent(true);
												
												for(Player pl : Bukkit.getServer().getOnlinePlayers()){
													PlayerControle.addPlayer(pl.getName(), args[2]);
												}
												
											}else if(args[4].equalsIgnoreCase("off")){
												
												ArenaControle.getArena(args[2]).setEvent(true);
												
												for(Player pl : Bukkit.getServer().getOnlinePlayers()){
													if(!pl.isOp()){
														PlayerControle.addPlayer(pl.getName(), args[2]);
													}
												}
												
											}else{
												p.sendMessage(Msg.Prefix.toString() + Msg.Argument_Incorect.toString().replace("#arg", args[4]));
											}
										}else{
											p.sendMessage(Msg.Prefix.toString() + Msg.Argument_Incorect.toString().replace("#arg", args[3]));
										}
									}else{
										ArenaControle.getArena(args[2]).setEvent(true);
										
										for(Player pl : Bukkit.getServer().getOnlinePlayers()){
											PlayerControle.addPlayer(pl.getName(), args[2]);
										}
									}
								}else{
									p.sendMessage(Msg.Prefix.toString() + Msg.Arene_Inexistante.toString());
								}
							}else if(args[1].equalsIgnoreCase("help")){
								p.sendMessage("§3§l" + "/oitc start event <nom d'arène> admin <on/off>: §b lance un event");
							}else{
								p.sendMessage(Msg.Prefix.toString() + Msg.Argument_Incorect.toString().replace("#arg", args[1]));
							}
						}else{
							p.sendMessage(Msg.Prefix.toString() + Msg.Commande_Imcomplete.toString());
						}
					}else{
						p.sendMessage(Msg.Prefix.toString() + Msg.No_Permission.toString());
					}
				}else if(args[0].equalsIgnoreCase("reload")){
					if(p.isOp()){
						if(args.length == 2){
							if(ArenaControle.contains(args[1])){
								if(ArenaControle.getArena(args[1]).getStatus().equalsIgnoreCase("lobby")){
									if(ArenaControle.getArena(args[1]).getPlayerList().size() == 0){
										
										FileControler.saveArena(args[1]);
										ArenaControle.removeMap(args[1]);
										FileControler.removeFile(args[1]);
										FileControler.loadFile(args[1]);
										
										ArenaControle.addArene(args[1]);
										Bukkit.getLogger().info("arene " + args[1] + " creer");
										
										p.sendMessage(Msg.Prefix.toString() + Msg.arene_reload.toString());
										
										
									}else{
										p.sendMessage(Msg.Prefix.toString()  + Msg.no_reload_avec_joueur.toString());
									}
								}else{
									p.sendMessage(Msg.Prefix.toString() + Msg.No_Configure_Ingame.toString());
								}
							}else{
								p.sendMessage(Msg.Prefix.toString() + Msg.Arene_Inexistante.toString());
							}
						}else{
							p.sendMessage(Msg.Prefix.toString() + Msg.Commande_Imcomplete.toString());
						}
					}else{
						p.sendMessage(Msg.Prefix.toString() + Msg.No_Permission.toString());
					}
				}else if(args[0].equalsIgnoreCase("pannel")){
					if(p.isOp()){
						if(FileControler.getAllName().size() <= 54){
							if(FileControler.getAllName().size() != 0){
								
								CreateAreneList.loadInventoryArena(p);
								
							}else{
								p.sendMessage(Msg.Prefix.toString() + Msg.aucune_arene_config.toString());
							}
						}else{
							p.sendMessage(Msg.Prefix.toString() + Msg.trop_arene.toString());
						}
					}else{
						p.sendMessage(Msg.Prefix.toString() + Msg.No_Permission.toString());
					}
				}else if(args[0].equalsIgnoreCase("leave")){
					BungeeCord.sendPlayerToHub(p);
				}else if(args[0].equalsIgnoreCase("help")){
					p.sendMessage("§3§l" + "/oitc create <nom d'arène>: §b créer une nouvelle arène");
					p.sendMessage("§3§l" + "/oitc <nom d'arène> : §b permet de config une aréne");
					p.sendMessage("§3§l" + "/oitc join <nom d'arène>: §b permet de rejoindre une arène");
					p.sendMessage("§3§l" + "/oitc fstart : §b permet de forcer le demarage d'une arène");
					p.sendMessage("§3§l" + "/oitc start : §b permet de lancer un event");
					p.sendMessage("§3§l" + "/oitc reload : §b permet de reload un arène");
					p.sendMessage("§3§l" + "/oitc pannel : §b permet d'ouvrir le pannel d'administration");
				}else{
					p.sendMessage(Msg.Prefix.toString() + Msg.Argument_Incorect.toString().replace("#arg", args[0]));
				}
			}else{
				p.sendMessage(Msg.Prefix.toString() + Msg.Commande_Imcomplete.toString());
			}
		}
		return false;
	}
	
	public static JavaPlugin getPlugin(){
		return plugin;
	}
}
