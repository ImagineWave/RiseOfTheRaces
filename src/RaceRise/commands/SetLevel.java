package RaceRise.commands;

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

public class SetLevel implements CommandExecutor{
	private Main plugin;
	public SetLevel(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		File messages = new File(plugin.getDataFolder() + File.separator + "MessagesRu.yml");
		FileConfiguration msgs = YamlConfiguration.loadConfiguration(messages);
		String noPermission = msgs.getString("NoPermission");
		Player p = (Player) sender;
		if(!p.hasPermission("race.setlvl")) {
			p.sendMessage(noPermission);
			return true;
		}
		Player t = (Bukkit.getPlayerExact(args[0]));
		String r = args[1];
		if(!(r.equals("human")||r.equals("elf")||r.equals("dwarf")||r.equals("orc")||r.equals("naga")||r.equals("sky"))) {
			p.sendMessage("/setrace <Player> human/elf/dwarf/orc/naga/sky");
			return false;
		}
		int lvl = Integer.parseInt(args[2]);
		setLvl(p,t,r,lvl);
		return true;
	}
	public void setLvl(Player p, Player t, String race, int value) {
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
		switch (race) {
		case("human"):{
			pls.set("Player."+ t.getName()+".RaceLvl.Human", value);
			p.sendMessage("§aВы установили игроку §6 "+t.getName()+" §aуровень расы "+humanName+" §d"+value);
			break;
		}
		case("elf"):{
			pls.set("Player."+ t.getName()+".RaceLvl.Elf", value);
			p.sendMessage("§aВы установили игроку §6 "+t.getName()+" §aуровень расы "+elfName+" §d"+value);
			break;
		}
		case("dwarf"):{
			pls.set("Player."+ t.getName()+".RaceLvl.Dwarf", value);
			p.sendMessage("§aВы установили игроку §6 "+t.getName()+" §aуровень расы "+dwarfName+" §d"+value);
			break;
		}
		case("orc"):{
			pls.set("Player."+ t.getName()+".RaceLvl.Orc", value);
			p.sendMessage("§aВы установили игроку §6 "+t.getName()+" §aуровень расы "+orcName+" §d"+value);
			break;
		}
		case("naga"):{
			pls.set("Player."+ t.getName()+".RaceLvl.Naga", value);
			p.sendMessage("§aВы установили игроку §6 "+t.getName()+" §aуровень расы "+nagaName+" §d"+value);
			break;
		}
		case("sky"):{
			pls.set("Player."+ t.getName()+".RaceLvl.Sky", value);
			p.sendMessage("§aВы установили игроку §6 "+t.getName()+" §aуровень расы "+skyName+" §d"+value);
			break;
		}
	}
	try {
		pls.save(players);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
}
