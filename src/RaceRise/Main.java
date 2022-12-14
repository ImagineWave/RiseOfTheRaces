package RaceRise;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import RaceRise.commands.QuestCommand;
import RaceRise.commands.SetLevel;
import RaceRise.commands.openRacePickGUI;
import RaceRise.commands.raceSet;
import RaceRise.listeners.BedrockBreak;
import RaceRise.listeners.CobbleGenerator;
import RaceRise.listeners.GUIListener;
import RaceRise.listeners.HpSetup;
import RaceRise.listeners.NagaOneShot;
import RaceRise.listeners.OrcBlocker;
import RaceRise.listeners.Quests;
import RaceRise.listeners.Runnebles;

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
			msgs.set("NoPermission", "??c?? ?????? ?????? ????????");
			msgs.set("RacePickCooldown", "??c???? ???? ???????????? ?????????????? ???????? ??????");
			msgs.set("RacePick", "??a???? ?????????????? ?????????????? ????????");
			msgs.set("GUIname", "??b???????????????? ????????");
			msgs.set("humanName", "??e??????????????");
			msgs.set("elfName", "??a????????");
			msgs.set("dwarfName", "??9????????");
			msgs.set("orcName", "??c??????");
			msgs.set("nagaName", "??b????????");
			msgs.set("skyName", "??f????????????????");
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
	