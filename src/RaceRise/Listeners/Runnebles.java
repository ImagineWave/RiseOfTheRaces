package RaceRise.Listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import RaceRise.Main;

public class Runnebles extends BukkitRunnable{
	private Main plugin;
	public Runnebles(Main plugin) {
		this.plugin = plugin;
	}
	@Override
	public void run() {
		Double HumanMaxHp = 20d;
		
		Double ElfMaxHp = 22d;
		Double ElfMinHp = 12d;
		
		Double DwarfMaxHp = 20d;
		Double DwarfMinHp = 8d;
		
		Double OrcMaxHp = 26d;
		Double OrcMinHp = 16d;
		
		Double NagaMaxHp = 20d;
		Double NagaMinHp = 20d;
		
		Double SkyMaxHp = 20d;
		Double SkyMinHp = 4d;
		
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration f = YamlConfiguration.loadConfiguration(players);
		for(Player pls : Bukkit.getServer().getOnlinePlayers()) {
			String race = f.getString("Player."+ pls.getName()+".Race");
			switch (race) {
			
			case("Human"):{
				if(pls.getGameMode().equals(GameMode.SURVIVAL)) pls.setAllowFlight(false);
				pls.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 120, 1));
				if(f.getInt("Player."+ pls.getName()+".RaceLvl."+race)>0) {
					pls.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 120, 3));
					pls.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 0));
				}
				break;
			}
			case("Elf"):{
				if(pls.getGameMode().equals(GameMode.SURVIVAL)) pls.setAllowFlight(false);
				if((pls.getLocation().getBlockY()>25)&&(!pls.getLocation().getWorld().getName().equals("world_nether"))) {
					if(pls.getMaxHealth()==ElfMinHp) {
						pls.setMaxHealth(ElfMaxHp);
						Double hp = hpToPlayer(pls);
						pls.setHealth(hp);
					}
					pls.setMaxHealth(ElfMaxHp);
					pls.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 120, 1));
					pls.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 1));
					if(f.getInt("Player."+ pls.getName()+".RaceLvl."+race)>0) {
						pls.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 120, 1));
					}
					break;
				}
				pls.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 120, 0));
				if(pls.getMaxHealth()!=ElfMinHp) {
					hpToCFG(pls);
				}
				pls.setMaxHealth(ElfMinHp);
				break;
			}
			
			
			case("Dwarf"):{
				if(pls.getGameMode().equals(GameMode.SURVIVAL)) pls.setAllowFlight(false);
				if((pls.getLocation().getBlockY()<45)||(pls.getLocation().getWorld().getName().equals("world_nether"))) {
					if(pls.getMaxHealth()==DwarfMinHp) {
						pls.setMaxHealth(DwarfMaxHp);
						Double hp = hpToPlayer(pls);
						pls.setHealth(hp);
					}
					pls.setMaxHealth(DwarfMaxHp);
					pls.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 120, 1));
					pls.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 400, 0));
					if(f.getInt("Player."+ pls.getName()+".RaceLvl."+race)>0) {
						pls.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 120, 3));
					}
					break;
				}
				if(pls.getMaxHealth()!=DwarfMinHp) {
					hpToCFG(pls);
				}
				pls.setMaxHealth(DwarfMinHp);
				break;
			}
			
			
			case("Orc"):{
				if(pls.getGameMode().equals(GameMode.SURVIVAL)) pls.setAllowFlight(false);
				if(pls.getLocation().getWorld().getName().equals("world_nether")) {
					if(pls.getMaxHealth()==OrcMinHp) {
						pls.setMaxHealth(OrcMaxHp);
						Double hp = hpToPlayer(pls);
						pls.setHealth(hp);
					}
					pls.setMaxHealth(OrcMaxHp);
					break;
				}
				if(pls.getMaxHealth()!=OrcMinHp) {
					hpToCFG(pls);
				}
				pls.setMaxHealth(OrcMinHp);
				break;
			}
			case("Naga"):{
				if(pls.getGameMode().equals(GameMode.SURVIVAL)) pls.setAllowFlight(false);
				if(pls.getLocation().getBlock().getType().equals(Material.WATER)) {
				pls.setMaxHealth(NagaMaxHp);
				pls.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 120, 0));
				pls.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 120, 0));
				pls.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0));
				break;
				}
				break;
			}
			
			
			case("Sky"):{
				pls.setAllowFlight(true);
				if (pls.getLocation().getWorld().getName().equals("world_the_end")) {
					pls.setAllowFlight(false);
					pls.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 120, 0));
					pls.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 240, 1));
				}
				if((pls.getLocation().getBlockY()>100)&&(!pls.getLocation().getWorld().getName().equals("world_nether"))) {
					if(pls.getMaxHealth()==SkyMinHp) {
						Double hp = hpToPlayer(pls);
						pls.setMaxHealth(SkyMaxHp);
						pls.setHealth(hp);
						break;
					}
					pls.setMaxHealth(SkyMaxHp);
					break;
				}
				if(pls.getMaxHealth()!=SkyMinHp) {
					hpToCFG(pls);
				}
				pls.setMaxHealth(SkyMinHp);
				break;
			}
			}
		}
		
		
	}

	public void hpToCFG(Player p) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration f = YamlConfiguration.loadConfiguration(players);
		Double hp = p.getHealth();
		if(hp<1) hp = 20D;
		f.set("Player."+ p.getName()+".LastHealth",hp);
		try {
			f.save(players);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public double hpToPlayer(Player p) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration f = YamlConfiguration.loadConfiguration(players);
		Double hp = f.getDouble("Player."+ p.getName()+".LastHealth");
		return hp;
	}

}
