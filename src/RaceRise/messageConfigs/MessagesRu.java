package RaceRise.messageConfigs;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import RaceRise.Main;

public class MessagesRu {
	private Main plugin;
	public MessagesRu(Main plugin) {
		this.plugin = plugin;
	}
	
	public void init() {
		File players = new File(plugin.getDataFolder() + File.separator + "MessagesRu.yml");
		if (!players.exists()) {
			FileConfiguration h = YamlConfiguration.loadConfiguration(players);
			h.set("NoPermission", "§cУ вас нет прав");
			h.set("RacePickCooldown", "§cВы не можете сменить расу еще");
			h.set("RacePick", "§aВы успешно выбрали расу");
			h.set("GUIname", "§bВыберите расу");
			h.set("humanName", "§eЧеловек");
			h.set("elfName", "§aЭльф");
			h.set("dwarfName", "§9Гном");
			h.set("orcName", "§cОрк");
			h.set("nagaName", "§bНага");
			h.set("skyName", "§fПернатый");
			try {
				h.save(players);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
