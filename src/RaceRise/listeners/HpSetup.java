package RaceRise.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import RaceRise.Main;

public class HpSetup implements Listener{
	private Main plugin;
	public HpSetup(Main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void hpSetup(PlayerRespawnEvent e) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		Player p = e.getPlayer();
		String race = pls.getString("Player."+ p.getName()+".Race");
		
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
		switch (race) {
		
		
		case("Human"):{
			p.setMaxHealth(HumanMaxHp);
			break;
		}
		case("Elf"):{
			p.setMaxHealth(ElfMinHp);
			break;
		}
		case("Dwarf"):{
			p.setMaxHealth(DwarfMinHp);
			break;
		}
		case("Orc"):{
			p.setMaxHealth(OrcMinHp);
			break;
		}
		case("Naga"):{
			p.setMaxHealth(NagaMinHp);
			break;
		}
		case("Sky"):{
			p.setMaxHealth(SkyMinHp);
			p.setAllowFlight(true);
			break;
		}
		}
	}
	@EventHandler
	public void orcBuff(EntityDeathEvent e) {
		if(e.getEntity()==null) return;
		if(!(e.getEntity().getKiller() instanceof Player)) return;
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		Player p = e.getEntity().getKiller();
		String race = pls.getString("Player."+ p.getName()+".Race");
		if(race.equalsIgnoreCase("Orc")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120, 1));
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 1));
		}
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		Player p = e.getPlayer();
		String race = pls.getString("Player."+ p.getName()+".Race");
		if(race.equalsIgnoreCase("Sky")) {
			p.setAllowFlight(true);
		}
	}
	@EventHandler
	public void onFirstJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		Double hp = pls.getDouble("Player."+ p.getName()+".LastHealth");
		if(hp==0) {
			pls.set("Player."+ p.getName()+".LastHealth", 20);
			pls.set("Player."+ p.getName()+".Race", "Human");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent add race-human");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-elf");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-dwarf");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-orc");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-naga");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-sky");
			try {
				pls.save(players);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
