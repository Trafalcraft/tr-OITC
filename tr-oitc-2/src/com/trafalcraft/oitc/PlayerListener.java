package com.trafalcraft.oitc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.trafalcraft.oitc.Controler.ArenaControle;
import com.trafalcraft.oitc.Controler.PlayerControle;
import com.trafalcraft.oitc.file.FileControler;
import com.trafalcraft.oitc.texte.Msg;

public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerRespawnEvent(PlayerRespawnEvent e){
		
		if(PlayerControle.contains(e.getPlayer().getName())){
			String arene = PlayerControle.getJoueur(e.getPlayer().getName()).getArene();
			
			e.setRespawnLocation(new Location(Bukkit.getWorld(FileControler.getArena(arene).getString("world")), FileControler.getArena(arene).getDouble("spawn." + PlayerControle.getJoueur(e.getPlayer().getName()).getProchainSpawn() + ".x"), FileControler.getArena(arene).getDouble("spawn." + PlayerControle.getJoueur(e.getPlayer().getName()).getProchainSpawn() + ".y"), FileControler.getArena(arene).getDouble("spawn." + PlayerControle.getJoueur(e.getPlayer().getName()).getProchainSpawn() + ".z"), ((float)FileControler.getArena(arene).getDouble("spawn." + PlayerControle.getJoueur(e.getPlayer().getName()).getProchainSpawn() + ".yaw")), ((float)FileControler.getArena(arene).getDouble("spawn." + PlayerControle.getJoueur(e.getPlayer().getName()).getProchainSpawn() + ".pitch"))));
		}
	}
	
	@EventHandler
	public void onPlayerSendMessageEvent(AsyncPlayerChatEvent e){
		if(PlayerControle.contains(e.getPlayer().getName())){
			String Message = e.getMessage();
			e.setCancelled(true);
	
			Message = (Msg.Prefix.toString() + "§6" + e.getPlayer().getDisplayName() + ">> " + Message);
			Bukkit.getServer().broadcastMessage(Message);
		}
	}
	
	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent e){
		
		if(e.getCause() != DamageCause.ENTITY_ATTACK && e.getCause() != DamageCause.PROJECTILE){
			
			if(e.getEntity().getType() == EntityType.PLAYER){
				
				if(PlayerControle.contains(((Player)e.getEntity()).getName())){
					
					String arene = PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getArene();
					
					if(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getInGame() == true){
						
						if((((Player)e.getEntity()).getHealth()) - (e.getFinalDamage()) <= 0){
							
							e.setCancelled(true);
							((Player)e.getEntity()).setHealth(20);
							((Player)e.getEntity()).setFoodLevel(20);
							((Player)e.getEntity()).setSaturation(0);
							ClearPotion.clearEffect(((Player)e.getEntity()));
							Tp.tpInGame((Player)e.getEntity());
							PlayerControle.getJoueur(((Player)e.getEntity()).getName()).setNbrMort(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getNbrmort() + 1);
							PlayerControle.getJoueur(((Player)e.getEntity()).getName()).setSerie(0);
							
							if(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getNbrPoint() != 0){
								
								PlayerControle.getJoueur(((Player)e.getEntity()).getName()).setNbrPoint(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getNbrPoint() - 1);
								ArenaControle.getArena(arene).getScoreBoard().updateScore((Player)e.getEntity());
								((Player)e.getEntity()).setLevel(((Player)e.getEntity()).getLevel() - 1);
							}
							
							if(e.getCause() == DamageCause.FALL){
								
								for(Player p : ArenaControle.getArena(arene).getPlayerList()){
									p.sendMessage(Msg.Prefix.toString() + Msg.mort_chute.toString().replace("#name", ((Player)e.getEntity()).getName()));
								}
							}else if(e.getCause() == DamageCause.FIRE || e.getCause() == DamageCause.FIRE_TICK){
								
								for(Player p : ArenaControle.getArena(arene).getPlayerList()){
									p.sendMessage(Msg.Prefix.toString() + Msg.mort_bruler.toString().replace("#name", ((Player)e.getEntity()).getName()));
								}
							}else if(e.getCause() == DamageCause.LAVA){
								
								for(Player p : ArenaControle.getArena(arene).getPlayerList()){
									p.sendMessage(Msg.Prefix.toString() + Msg.mort_lave.toString().replace("#name", ((Player)e.getEntity()).getName()));
								}
							}else if(e.getCause() == DamageCause.DROWNING){
								
								for(Player p : ArenaControle.getArena(arene).getPlayerList()){
									p.sendMessage(Msg.Prefix.toString() + Msg.mort_noyer.toString().replace("#name", ((Player)e.getEntity()).getName()));
								}
							}else if(e.getCause() == DamageCause.POISON){
								
								for(Player p : ArenaControle.getArena(arene).getPlayerList()){
									p.sendMessage(Msg.Prefix.toString() + Msg.mort_poison.toString().replace("#name", ((Player)e.getEntity()).getName()));
								}
							}else if(e.getCause() != DamageCause.ENTITY_ATTACK && e.getCause() != DamageCause.PROJECTILE){
								
								for(Player p : ArenaControle.getArena(arene).getPlayerList()){
									p.sendMessage(Msg.Prefix.toString() + Msg.autre_mort.toString().replace("#name", ((Player)e.getEntity()).getName()));
								}
							}
						}
					}else{
						e.setCancelled(true);
					}
				}
			}
		}

	}
	
	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e){
		
		if(e.getEntity().getType() == EntityType.PLAYER){
			
			if(PlayerControle.contains(((Player)e.getEntity()).getName())){
				
				if(e.getDamager().getType() == EntityType.PLAYER){
					
					if(PlayerControle.contains(((Player)e.getDamager()).getName())){
						
						String arene = PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getArene();
						
						if(arene.equalsIgnoreCase(PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getArene())){
							
							if(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getInGame() == true){
								
								if(PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getInGame() == true){
									
									if((((Player)e.getEntity()).getHealth()) - (e.getFinalDamage()) <= 0){
										
										e.setCancelled(true);
										((Player)e.getEntity()).setHealth(20);
										((Player)e.getEntity()).setFoodLevel(20);
										((Player)e.getEntity()).setSaturation(0);
										ClearPotion.clearEffect(((Player)e.getEntity()));
										Tp.tpInGame((Player)e.getEntity());
										
										PlayerControle.getJoueur(((Player)e.getEntity()).getName()).setNbrMort(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getNbrmort() + 1);
										PlayerControle.getJoueur(((Player)e.getEntity()).getName()).setSerie(0);
										PlayerControle.getJoueur(((Player)e.getDamager()).getName()).setNbrKill(PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getNbrKill() + 1);
										PlayerControle.getJoueur(((Player)e.getDamager()).getName()).setSerie(PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getSerie() + 1);
										
										PlayerControle.getJoueur(((Player)e.getDamager()).getName()).setNbrPoint(PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getNbrPoint() + 1);
										ArenaControle.getArena(arene).getScoreBoard().updateScore((Player)e.getDamager());
										((Player)e.getDamager()).getInventory().addItem(new ItemStack(Material.ARROW));
										((Player)e.getDamager()).updateInventory();
										
										((Player)e.getDamager()).setLevel(PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getNbrPoint());
										
										for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
											pl.sendMessage(Msg.Prefix.toString() + Msg.kill.toString().replace("#killer", ((Player)e.getDamager()).getName()).replace("#victime", ((Player)e.getEntity()).getName()));
											
										}
										
										if(PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getSerie() == 5){
											for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
												pl.sendMessage(Msg.Prefix.toString() + "§4" + Msg.serie_5.toString().replace("#name", ((Player)e.getDamager()).getName()));
												pl.playSound(pl.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 100.0F, 100.0F);
											}
										}else if(PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getSerie() == 10){
											for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
												pl.sendMessage(Msg.Prefix.toString() + "§4" + Msg.serie_10.toString().replace("#name", ((Player)e.getDamager()).getName()));
												pl.playSound(pl.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 100.0F, 100.0F);
											}
										}else if(PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getSerie() == 15){
											for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
												pl.sendMessage(Msg.Prefix.toString() + "§4" + Msg.serie_15.toString().replace("#name", ((Player)e.getDamager()).getName()));
												pl.playSound(pl.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 100.0F, 100.0F);
											}
										}else if(PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getSerie() == 30){
											for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
												pl.sendMessage(Msg.Prefix.toString() + "§4" + Msg.serie_30.toString().replace("#name", ((Player)e.getDamager()).getName()));
												pl.playSound(pl.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 100.0F, 100.0F);
											}
										
										}
										
										if(PlayerControle.getJoueur(((Player)e.getDamager()).getName()).getNbrPoint() == FileControler.getArena(arene).getInt("maxpoint")){
											for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
				                   				PlayerControle.getJoueur(pl.getName()).setInGame(false);
				                   			}
											ArenaControle.getArena(arene).stopGameTimer();
											ArenaControle.getArena(arene).setStatus("end");
											ArenaControle.getArena(arene).WhoWin();
										}
									}
								}else{
									e.setCancelled(true);
								}
							}else{
								e.setCancelled(true);
							}
						}else{
							e.setCancelled(true);
						}
					}else{
						e.setCancelled(true);
					}
				}else if(e.getDamager().getType() == EntityType.ARROW || e.getDamager().getType() == EntityType.SPECTRAL_ARROW || e.getDamager().getType() == EntityType.TIPPED_ARROW){
					
					if(PlayerControle.contains(((Player)((Arrow)e.getDamager()).getShooter()).getName())){
						
						String arene = PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getArene();
						
						if(arene.equalsIgnoreCase(PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getArene())){
							
							if(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getInGame() == true){
							
								if(PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getInGame() == true){
									;
									e.setCancelled(true);
									e.getDamager().remove();
									((Player)e.getEntity()).setHealth(20);
									((Player)e.getEntity()).setFoodLevel(20);
									((Player)e.getEntity()).setSaturation(0);
									ClearPotion.clearEffect(((Player)e.getEntity()));
									Tp.tpInGame((Player)e.getEntity());
									
									PlayerControle.getJoueur(((Player)e.getEntity()).getName()).setNbrMort(PlayerControle.getJoueur(((Player)e.getEntity()).getName()).getNbrmort() + 1);
									PlayerControle.getJoueur(((Player)e.getEntity()).getName()).setSerie(0);
									PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).setNbrKill(PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getNbrKill() + 1);
									PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).setSerie(PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getSerie() + 1);
									
									PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).setNbrPoint(PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getNbrPoint() + 1);
									ArenaControle.getArena(arene).getScoreBoard().updateScore((Player)((Arrow)e.getDamager()).getShooter());
									((Player)((Arrow)e.getDamager()).getShooter()).getInventory().addItem(new ItemStack(Material.ARROW));
									((Player)((Arrow)e.getDamager()).getShooter()).updateInventory();
									
									((Player)((Arrow)e.getDamager()).getShooter()).setLevel(PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getNbrPoint());
									
									if(e.getDamager().getLocation().getY() >= (e.getEntity().getLocation().getY() + 1.53)){
										for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
											pl.sendMessage(Msg.Prefix.toString() + "§1" +Msg.headshot.toString().replace("#killer", ((Player)((Arrow)e.getDamager()).getShooter()).getName()).replace("#victime", ((Player)e.getEntity()).getName()));
										}
									}else{
										for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
											pl.sendMessage(Msg.Prefix.toString() + Msg.kill.toString().replace("#killer", ((Player)((Arrow)e.getDamager()).getShooter()).getName()).replace("#victime", ((Player)e.getEntity()).getName()));
										}
									}
									
									if(PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getSerie() == 5){
										for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
											pl.sendMessage(Msg.Prefix.toString() + "§4" + Msg.serie_5.toString().replace("#name",((Player)((Arrow)e.getDamager()).getShooter()).getName()));
											pl.playSound(pl.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 100.0F, 100.0F);
										}
									}else if(PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getSerie() == 10){
										for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
											pl.sendMessage(Msg.Prefix.toString() + "§4" + Msg.serie_10.toString().replace("#name",((Player)((Arrow)e.getDamager()).getShooter()).getName()));
											pl.playSound(pl.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 100.0F, 100.0F);
										}
									}else if(PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getSerie() == 15){
										for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
											pl.sendMessage(Msg.Prefix.toString() + "§4" + Msg.serie_15.toString().replace("#name",((Player)((Arrow)e.getDamager()).getShooter()).getName()));
											pl.playSound(pl.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 100.0F, 100.0F);
										}
									}else if(PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getSerie() == 30){
										for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
											pl.sendMessage(Msg.Prefix.toString() + "§4" + Msg.serie_30.toString().replace("#name",((Player)((Arrow)e.getDamager()).getShooter()).getName()));
											pl.playSound(pl.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 100.0F, 100.0F);
										}
									
									}
									
									if(PlayerControle.getJoueur(((Player)((Arrow)e.getDamager()).getShooter()).getName()).getNbrPoint() == FileControler.getArena(arene).getInt("maxpoint")){
										for(Player pl : ArenaControle.getArena(arene).getPlayerList()){
			                   				PlayerControle.getJoueur(pl.getName()).setInGame(false);
			                   			}
										ArenaControle.getArena(arene).stopGameTimer();
										ArenaControle.getArena(arene).setStatus("end");
										ArenaControle.getArena(arene).WhoWin();
									}
									
								}else{
									
									e.setCancelled(true);
									e.getDamager().remove();
								}
							}else{
								e.setCancelled(true);
								e.getDamager().remove();
							}
						}else{
							e.setCancelled(true);
							e.getDamager().remove();
						}
					}else{
						e.setCancelled(true);
						e.getDamager().remove();
					}
				}
			}
		}else{
			if(e.getEntity().getType() == EntityType.ARMOR_STAND){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void OnBreackBlockEvent(BlockBreakEvent e){

		if(PlayerControle.contains(e.getPlayer().getName())){
			e.setCancelled(true);
		}

	}
	
	
	@EventHandler
	public void onMob(EntitySpawnEvent e){
		e.setCancelled(true);
	}
	
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		
			
		if(PlayerControle.contains(e.getPlayer().getName())){
				
			e.setCancelled(true);
				
		}
			
	}
	
	
	@EventHandler
	public void OnblockPlaceEvent(BlockPlaceEvent e){
		
		if(PlayerControle.contains(e.getPlayer().getName())){
			e.setCancelled(true);
		}
		
	}
	
	
	@EventHandler
	public void onPlayerpickEvent(PlayerPickupItemEvent e){
		
		if(PlayerControle.contains(e.getPlayer().getName())){
			e.setCancelled(true);
		}
		
	}
	
	@EventHandler
	public void onQuite(PlayerQuitEvent e){
		Quite.leave(e.getPlayer());
	}
	
	@EventHandler
	public void onItemBreackEvent(PlayerItemDamageEvent e){
		if(PlayerControle.contains(e.getPlayer().getName())){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerFoodEvent(FoodLevelChangeEvent e){
		if(e.getEntity().getType() == EntityType.PLAYER){
			if(PlayerControle.contains(((Player)e.getEntity()).getName())){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onplayerdeathEvent(PlayerDeathEvent e){
		e.setDeathMessage("");
	}
	
}
