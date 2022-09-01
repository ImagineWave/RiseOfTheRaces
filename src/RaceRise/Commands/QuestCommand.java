package RaceRise.Commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import RaceRise.Main;

public class QuestCommand implements CommandExecutor{
	private Main plugin;
	public QuestCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		File messages = new File(plugin.getDataFolder() + File.separator + "MessagesRu.yml");
		FileConfiguration msgs = YamlConfiguration.loadConfiguration(messages);
		String noPermission = msgs.getString("NoPermission");
		Player p = (Player) sender;
		if(!p.hasPermission("race.quest")) {
			p.sendMessage(noPermission);
			return true;
		}
		String race = getRace(p);
		switch (race) {
		case("Human"):{
			p.sendMessage("§2Для повышения уровня вам стоит заняться скотоводством");
			break;
		}
		case("Elf"):{
			p.sendMessage("§2Занимайтесь §aозеленением§2 территории");
			break;
		}
		case("Dwarf"):{
			p.sendMessage("§2Копайте. Просто копайте");
			break;
		}
		case("Orc"):{
			p.sendMessage("§2Орк! Видишь того моба? §cПОЙДИ И УБЕЙ ЕГО");
			break;
		}
		case("Naga"):{
			p.sendMessage("§2Ходит слух, что если убить здоровую рыбину, то это поможет");
			break;
		}
		case("Sky"):{
			p.sendMessage("§2Ходит слух, что если убить древнюю крылатую тварь, то это поможет");
			break;
		}
		}
		return true;
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
