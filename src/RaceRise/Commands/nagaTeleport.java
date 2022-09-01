package RaceRise.Commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import RaceRise.Main;

public class nagaTeleport implements CommandExecutor{
	private Main plugin;
	public nagaTeleport(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		
		File messages = new File(plugin.getDataFolder() + File.separator + "MessagesRu.yml");
		FileConfiguration msgs = YamlConfiguration.loadConfiguration(messages);
		String guiName = msgs.getString("GUIname");
		String racePickMsg = msgs.getString("RacePick");
		String humanName = msgs.getString("humanName");
		String elfName = msgs.getString("elfName");
		String dwarfName = msgs.getString("dwarfName");
		String orcName = msgs.getString("orcName");
		String nagaName = msgs.getString("nagaName");
		String skyName = msgs.getString("skyName");
		
		Player p = (Player) sender;
		String race = getRace(p);
		int lvl = pls.getInt("Player."+ p.getName()+".RaceLvl.Naga");
		if(!race.equalsIgnoreCase("naga")) {
			p.sendMessage("§cВы не "+nagaName);
			return true;
		}
		if(lvl<1) {
			p.sendMessage("§cВы не достигли нужного уровная расы "+nagaName);
			return true;
		}
		return false;
	}
	public String getRace(Player p) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		String race = pls.getString("Player."+ p.getName()+".Race");
		return race;
	}
}
