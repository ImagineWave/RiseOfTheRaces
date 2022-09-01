package RaceRise;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import RaceRise.Commands.QuestCommand;
import RaceRise.Commands.SetLevel;
import RaceRise.Commands.openRacePickGUI;
import RaceRise.Commands.raceSet;
import RaceRise.Listeners.BedrockBreak;
import RaceRise.Listeners.CobbleGenerator;
import RaceRise.Listeners.GUIListener;
import RaceRise.Listeners.HpSetup;
import RaceRise.Listeners.NagaOneShot;
import RaceRise.Listeners.OrcBlocker;
import RaceRise.Listeners.Quests;
import RaceRise.Listeners.Runnebles;

public class Main extends JavaPlugin{
	private static Main instance;
	public RiseOfTheRaceAPI api;
	
	public static Main instance() {
		return instance;
	}
	public void onEnable() {
		
		
		Bukkit.getServer().getPluginManager().registerEvents(new GUIListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new HpSetup(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CobbleGenerator(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Quests(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new OrcBlocker(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new NagaOneShot(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new BedrockBreak(this), this);
		
		RiseOfTheRaceAPI api = new RiseOfTheRaceAPI(this);
		
		BukkitTask globalCheck = new Runnebles(this).runTaskTimer(this, 0, 100L); //TODO
		
		getCommand("race").setExecutor(new openRacePickGUI(this));
		getCommand("setrace").setExecutor(new raceSet(this));
		getCommand("racequest").setExecutor(new QuestCommand(this));
		getCommand("setracelvl").setExecutor(new SetLevel(this));
		
		////////////////////////////////////////
		File players = new File(getDataFolder() + File.separator + "Players.yml");
		if (!players.exists()) {
			FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
			pls.set("Player.Default.Race", "Human");
			pls.set("Player.Default.RacePickCooldown", "0");
			try {
				pls.save(players);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		File messages = new File(getDataFolder() + File.separator + "MessagesRu.yml");
		if (!messages.exists()) {
			FileConfiguration msgs = YamlConfiguration.loadConfiguration(messages);
			msgs.set("NoPermission", "§cУ вас нет прав");
			msgs.set("RacePickCooldown", "§cВы не можете сменить расу еще");
			msgs.set("RacePick", "§aВы успешно выбрали расу");
			msgs.set("GUIname", "§bВыберите расу");
			msgs.set("humanName", "§eЧеловек");
			msgs.set("elfName", "§aЭльф");
			msgs.set("dwarfName", "§9Гном");
			msgs.set("orcName", "§cОрк");
			msgs.set("nagaName", "§bНага");
			msgs.set("skyName", "§fПернатый");
			try {
				msgs.save(messages);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	
	}
	public void onDisable() {
		
	}	
}
	