package RaceRise.Commands ;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import RaceRise.Main;

public class raceSet implements CommandExecutor{
	private Main plugin;
	public raceSet(Main plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		File messages = new File(plugin.getDataFolder() + File.separator + "MessagesRu.yml");
		FileConfiguration msgs = YamlConfiguration.loadConfiguration(messages);
		String noPermission = msgs.getString("NoPermission");
		String humanName = msgs.getString("humanName");
		String elfName = msgs.getString("elfName");
		String dwarfName = msgs.getString("dwarfName");
		String orcName = msgs.getString("orcName");
		String nagaName = msgs.getString("nagaName");
		String skyName = msgs.getString("skyName");
		
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		Player p = (Player) sender;
		if(!p.hasPermission("race.set")) {
			p.sendMessage(noPermission);
		}
		Player t = (Bukkit.getPlayerExact(args[0]));
		String r = args[1];
		if(!(r.equals("human")||r.equals("elf")||r.equals("dwarf")||r.equals("orc")||r.equals("naga")||r.equals("sky"))) {
			p.sendMessage("/setrace <Player> human/elf/dwarf/orc/naga/sky");
			return false;
		}
		switch (r) {
			case("human"):{
				pls.set("Player."+ t.getName()+".Race", "Human");
				p.sendMessage("§aВы установили игроку §6 "+t.getName()+" §aрасу "+humanName);
				break;
			}
			case("elf"):{
				pls.set("Player."+ t.getName()+".Race", "Elf");
				p.sendMessage("§aВы установили игроку §6 "+t.getName()+" §aрасу "+elfName);
				break;
			}
			case("dwarf"):{
				pls.set("Player."+ t.getName()+".Race", "Dwarf");
				p.sendMessage("§aВы установили игроку §6 "+t.getName()+" §aрасу "+dwarfName);
				break;
			}
			case("orc"):{
				pls.set("Player."+ t.getName()+".Race", "Orc");
				p.sendMessage("§aВы установили игроку §6 "+t.getName()+" §aрасу "+orcName);
				break;
			}
			case("naga"):{
				pls.set("Player."+ t.getName()+".Race", "Naga");
				p.sendMessage("§aВы установили игроку §6 "+t.getName()+" §aрасу "+nagaName);
				break;
			}
			case("sky"):{
				pls.set("Player."+ t.getName()+".Race", "Sky");
				p.sendMessage("§aВы установили игроку §6 "+t.getName()+" §aрасу "+skyName);
				break;
			}
		}
		try {
			pls.save(players);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
