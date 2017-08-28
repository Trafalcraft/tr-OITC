package com.trafalcraft.ludo;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class EventClass implements Listener {

	public Main plugin;
	public EventClass(Main main) {
		this.plugin = main;
	}

	@EventHandler
	public void ontest(EntityDamageByEntityEvent e){
		if(e.getCause() == DamageCause.PROJECTILE){
			if(Main.containsNom((Entity) ((Arrow)e.getDamager()).getShooter()) == true ){
				if(Main.containsNom(e.getEntity()) == true){
					if(Main.getEnJeu() == 1){
						if(e.getDamager() != e.getEntity()){
						
							e.setCancelled(true);
							((Arrow)e.getDamager()).remove();
						
							Location nl = new Location(((Player)e.getEntity()).getWorld(), ((Player)e.getEntity()).getLocation().getX(), ((Player)e.getEntity()).getLocation().getY() , ((Player)e.getEntity()).getLocation().getZ());
							Location nl2 = new Location(((Player)e.getEntity()).getWorld(), ((Player)e.getEntity()).getLocation().getX(), ((Player)e.getEntity()).getLocation().getY()+1 , ((Player)e.getEntity()).getLocation().getZ());
						
							for(Player allp:Bukkit.getOnlinePlayers()){
								allp.playEffect(nl, Effect.STEP_SOUND, 152);
								allp.playEffect(nl2, Effect.STEP_SOUND, 152);
							}
						
							int pt = Main.getPlayerList(Main.nomIndexOf(((CommandSender) ((Arrow)e.getDamager()).getShooter()).getName())).getPoint();
							Main.getPlayerList(Main.nomIndexOf(((CommandSender) ((Arrow)e.getDamager()).getShooter()).getName())).setPoint(pt + 1);
						
							Scorboard.plusdepoint(((Player) ((Arrow)e.getDamager()).getShooter()).getName());
						
							((Player)e.getEntity()).setHealth(20);
							Tpingame.Tpingame(e.getEntity().getName());
						
							Bukkit.getServer().broadcastMessage("§3§lOITC> §b" + ((CommandSender) ((Arrow)e.getDamager()).getShooter()).getName() + " a tué " + e.getEntity().getName());
				        
							if(Main.getPlugin().getConfig().getInt("maxpoint.max") == pt + 1){
								Quigagne.Quigagne();
								Main.setEnJeu(0);
								Bukkit.getScheduler().cancelTask(Timertotal.task2);
							
							}else{
								((Player)e.getEntity()).getInventory().remove(Material.BOW);
								((Player)e.getEntity()).getInventory().remove(Material.ARROW);
								((Player)e.getEntity()).getInventory().remove(Material.STONE_AXE);
								((Player)e.getEntity()).getInventory().addItem(new ItemStack(Material.BOW, 1));
								((Player)e.getEntity()).getInventory().addItem(new ItemStack(Material.ARROW, 1));
								((Player)e.getEntity()).getInventory().addItem(new ItemStack(Material.STONE_AXE, 1));
								((Player)e.getEntity()).updateInventory();
							
								((Player) ((Arrow)e.getDamager()).getShooter()).getInventory().remove(Material.BOW);
								((Player) ((Arrow)e.getDamager()).getShooter()).getInventory().remove(Material.STONE_AXE);
								((Player) ((Arrow)e.getDamager()).getShooter()).getInventory().addItem(new ItemStack(Material.BOW, 1));
								((Player) ((Arrow)e.getDamager()).getShooter()).getInventory().addItem(new ItemStack(Material.ARROW, 1));
								((Player) ((Arrow)e.getDamager()).getShooter()).getInventory().addItem(new ItemStack(Material.STONE_AXE, 1));
								((Player) ((Arrow)e.getDamager()).getShooter()).updateInventory();
							}
						}
					}else{
						e.setCancelled(true);
					}
				}else{
					e.setDamage(1000);
				}
			}else{
				e.setCancelled(true);
			}
		}else if(e.getCause() == DamageCause.ENTITY_ATTACK){
			if(Main.containsNom(e.getDamager()) == true){
				if(Main.containsNom(e.getEntity()) == true){
					if(Main.getEnJeu() == 1){
						if(e.getFinalDamage() >= ((Player)e.getEntity()).getHealth() ){
						
							e.setCancelled(true);
							
							Location nl = new Location(((Player)e.getEntity()).getWorld(), ((Player)e.getEntity()).getLocation().getX(), ((Player)e.getEntity()).getLocation().getY() , ((Player)e.getEntity()).getLocation().getZ());
					        Location nl2 = new Location(((Player)e.getEntity()).getWorld(), ((Player)e.getEntity()).getLocation().getX(), ((Player)e.getEntity()).getLocation().getY()+1 , ((Player)e.getEntity()).getLocation().getZ());
							
					        for(Player allp:Bukkit.getOnlinePlayers()){
				                allp.playEffect(nl, Effect.STEP_SOUND, 152);
				                allp.playEffect(nl2, Effect.STEP_SOUND, 152);
				            }
						
							int pt2 = Main.getPlayerList(Main.nomIndexOf(e.getDamager().getName())).getPoint();
							Main.getPlayerList(Main.nomIndexOf(e.getDamager().getName())).setPoint(pt2 + 1);
							
							Scorboard.plusdepoint(((Player)e.getDamager()).getName());
						
							((Player)e.getEntity()).setHealth(20);
							Tpingame.Tpingame(e.getEntity().getName());
							
							Bukkit.getServer().broadcastMessage("§3§lOITC> §b" + e.getDamager().getName() + " a tué " + e.getEntity().getName());
							
							if(Main.getPlugin().getConfig().getInt("maxpoint.max") == pt2 + 1){
								Quigagne.Quigagne();
								Main.setEnJeu(0);
								Bukkit.getScheduler().cancelTask(Timertotal.task2);
							
							}else{
								((Player)e.getEntity()).getInventory().remove(Material.BOW);
								((Player)e.getEntity()).getInventory().remove(Material.ARROW);
								((Player)e.getEntity()).getInventory().remove(Material.STONE_AXE);
								((Player)e.getEntity()).getInventory().addItem(new ItemStack(Material.BOW, 1));
								((Player)e.getEntity()).getInventory().addItem(new ItemStack(Material.ARROW, 1));
								((Player)e.getEntity()).getInventory().addItem(new ItemStack(Material.STONE_AXE, 1));
								((Player)e.getEntity()).updateInventory();
								
								((Player)e.getDamager()).getInventory().remove(Material.BOW);
								((Player)e.getDamager()).getInventory().remove(Material.STONE_AXE);
								((Player)e.getDamager()).getInventory().addItem(new ItemStack(Material.BOW, 1));
								((Player)e.getDamager()).getInventory().addItem(new ItemStack(Material.ARROW, 1));
								((Player)e.getDamager()).getInventory().addItem(new ItemStack(Material.STONE_AXE, 1));
								((Player)e.getDamager()).updateInventory();
							}
							
						}
					}else{
						e.setCancelled(true);
					}
				}else{
					e.setDamage(1000);
				}
			}else{
				e.setCancelled(true);
			}
		}else{
			e.setCancelled(true);
		}
	}


	@EventHandler
	public void onFaim(FoodLevelChangeEvent e){
		e.setCancelled(true);
		((Player) e.getEntity()).setFoodLevel(20);
	}
	
	@EventHandler
	public void onMob(EntitySpawnEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onblock(BlockBreakEvent e){
		if(e.getPlayer().isOp() == true){
			if(Main.getEnJeu() == 0){
				return;
			}else{
				e.setCancelled(true);
			}
		}else{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onRamasse(PlayerPickupItemEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onAutredegat (EntityDamageEvent e){
		if(e.getEntity().getType() == EntityType.PLAYER){
			if(Main.getEnJeu() == 1){
				if(e.getCause() == DamageCause.FALL){
					if(Main.getPlugin().getConfig().getBoolean("degatchute.active") == false){
						e.setCancelled(true);
					}else{
						Location nl = new Location(((Player)e.getEntity()).getWorld(), ((Player)e.getEntity()).getLocation().getX(), ((Player)e.getEntity()).getLocation().getY() , ((Player)e.getEntity()).getLocation().getZ());
				        Location nl2 = new Location(((Player)e.getEntity()).getWorld(), ((Player)e.getEntity()).getLocation().getX(), ((Player)e.getEntity()).getLocation().getY()+1 , ((Player)e.getEntity()).getLocation().getZ());
						
				        for(Player allp:Bukkit.getOnlinePlayers()){
			                allp.playEffect(nl, Effect.STEP_SOUND, 152);
			                allp.playEffect(nl2, Effect.STEP_SOUND, 152);
			            }
					}
				}else if(e.getCause() == DamageCause.FIRE){
					if(Main.getPlugin().getConfig().getBoolean("degatfeu.active") == false){
						e.setCancelled(true);
					}
				}else if(e.getCause() == DamageCause.FIRE_TICK){
						if(Main.getPlugin().getConfig().getBoolean("degatfeu.active") == false){
							e.setCancelled(true);
						}
				}else if(e.getCause() == DamageCause.LAVA){
					if(Main.getPlugin().getConfig().getBoolean("degatfeu.active") == false){
						e.setCancelled(true);
					}
				}else if(e.getCause() == DamageCause.DROWNING){
					if(Main.getPlugin().getConfig().getBoolean("degatnoyade.active") == false){
						e.setCancelled(true);
					}
				}
			}else{
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onTchiant(PlayerRespawnEvent e){
		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().addItem(new ItemStack (Material.STONE_AXE));
		e.getPlayer().getInventory().addItem(new ItemStack (Material.BOW));
		e.getPlayer().getInventory().addItem(new ItemStack (Material.ARROW));
		e.getPlayer().updateInventory();
		
		Tpingame.Tptmort();
		
		e.setRespawnLocation(Tpingame.Tptmort());;
	}
	
	@EventHandler
	public void onDiscret(PlayerDeathEvent e){
		
			e.setDeathMessage("");
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		
		World world = Bukkit.getWorld("world");
					
		if(Securiteconfig.Securite() == true){
			player player = new player(e.getPlayer(), 0);
			Main.addPlayer(player);
			Main.addNom(e.getPlayer().getName());
			Main.setNbrJoueur((Main.getNbrJoueur()) + 1);
			
			e.setJoinMessage("§3§lOITC> §b" + e.getPlayer().getName() + " a rejoint la partie (" + Main.getNbrJoueur() + "/" + plugin.getConfig().getInt("joueurmax.max") + ")");
					
			int x1 = plugin.getConfig().getInt("lobby.x");
			int y1 = plugin.getConfig().getInt("lobby.y");
			int z1 = plugin.getConfig().getInt("lobby.z");
			float v1 = plugin.getConfig().getInt("lobby.v");
			float w1 = plugin.getConfig().getInt("lobby.w");
			
			e.getPlayer().teleport(new Location(world, x1, y1, z1, v1, w1));
						
			e.getPlayer().getInventory().clear();
						
			if(Main.getNbrJoueur() == 2){
				Main.demare();
			}else if(Main.getNbrJoueur() == Main.getPlugin().getConfig().getInt("joueurmax.max")){
				Bukkit.getServer().setWhitelist(true);
			}
						
		}else{
			e.getPlayer().sendMessage("§3§lOITC> §b" + "la partie est mal configurée");
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e){
	
		if(Main.containsNom(e.getPlayer())){
			
			int a1 = Main.nomIndexOf(e.getPlayer().getName());
			
			Main.removeNom(a1);
			Main.removePlayerIndex(a1);
			Main.setNbrJoueur((Main.getNbrJoueur()) - 1);
			
			e.setQuitMessage("§3§lOITC> §b" + e.getPlayer().getName() + " a quitté la partie (" + Main.getNbrJoueur() + "/" + plugin.getConfig().getInt("joueurmax.max") + ")");
						
			if(Main.getEnJeu() == 1 && Main.getNbrJoueur() == 1){
				Quigagne.Quigagne();
				
			}else if(Main.getEnJeu() == 0 && Main.getNbrJoueur() == 1){
				Main.stop();
				
			}else if(Main.getEnJeu() == 0 && Main.getNbrJoueur() == (Main.getPlugin().getConfig().getInt("joueurmax.max")) - 1){
				Bukkit.getServer().setWhitelist(false);
			}
		}
	}
}
