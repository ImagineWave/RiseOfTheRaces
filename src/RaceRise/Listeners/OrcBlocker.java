package RaceRise.Listeners;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import RaceRise.Main;

public class OrcBlocker implements Listener{
	private Main plugin;
	public OrcBlocker(Main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void orcTorg(PlayerInteractEntityEvent e) {
		Entity ent = e.getRightClicked();
		Player p  = e.getPlayer();
		if(ent == null) return;
		if(!ent.getType().equals(EntityType.VILLAGER)) return;
		if(!getRace(p).equalsIgnoreCase("orc")) return;
		if(getRaceLvl(p)>0) {
			return;
		}
		p.sendMessage("§cМы, орки, не торгуем с грязными отродиями");
		e.setCancelled(true);
	}
	public String getRace(Player p) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		String race = pls.getString("Player."+ p.getName()+".Race");
		return race;
	}
	public int getRaceLvl(Player p) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		String race = getRace(p);
		int result = pls.getInt("Player."+ p.getName()+".RaceLvl."+race);
		return result;
	}
}
