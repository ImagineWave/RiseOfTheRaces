package RaceRise;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class RiseOfTheRaceAPI {
	private Main plugin;
	public RiseOfTheRaceAPI (Main plugin) {
		this.plugin = plugin;
	}
	public String getRace(Player p) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		String race = pls.getString("Player."+ p.getName()+".Race");
		return race;
	}
	public Integer getRaceLvl(Player p, String race) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		Integer raceLvl = pls.getInt("Player."+ p.getName()+".RaceLvl."+race);
		return raceLvl;
	}
}
