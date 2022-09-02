package RaceRise.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import RaceRise.Main;

public class GUIListener implements Listener{
	private Main plugin;
	public GUIListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void guiClickEvent(InventoryClickEvent e) {
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
		Player p = (Player) e.getWhoClicked();
		
		if (e.getView().getTitle().equalsIgnoreCase(guiName)) {
			if (e.getCurrentItem() == null) { //NULL CHECK
				e.setCancelled(true);
				return;
			}
			
			if (e.getCurrentItem().getType().equals(Material.YELLOW_BANNER)) {
				//Human
				racePick(1,p);
			}
			
			if (e.getCurrentItem().getType().equals(Material.LIME_BANNER)) {
				//Elf
				racePick(2,p);
			}
			
			if (e.getCurrentItem().getType().equals(Material.BLUE_BANNER)) {
				//Dwarf
				racePick(3,p);
			}
			
			if (e.getCurrentItem().getType().equals(Material.RED_BANNER)) {
				//Orc
				racePick(4,p);
			}
			
			if (e.getCurrentItem().getType().equals(Material.CYAN_BANNER)) {
				//Naga
				racePick(5,p);
			}
			
			if (e.getCurrentItem().getType().equals(Material.WHITE_BANNER)) {
				//Sky
				racePick(6,p);
			}
			
			if (e.getCurrentItem().getType().equals(Material.STRUCTURE_VOID)) {
				//Random
				racePick(getRandomArbitrary(),p);
			}
			
			if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
				//Close
				e.getView().close();
			}
			e.setCancelled(true);
		}
		return;
	}
	public void racePick(int value, Player p) {
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
		Long cooldownRacePick = pls.getLong("cooldownRacePick");
		switch (value) {
		case 1: {
			pls.set("Player."+ p.getName()+".Race", "Human");
			pls.set("Player."+ p.getName()+".Cooldown", System.currentTimeMillis());
			try {
				pls.save(players);
			} catch (IOException e) {
				e.printStackTrace();
			}
			p.sendMessage(racePickMsg +" "+humanName);
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent add race-human");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-elf");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-dwarf");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-orc");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-naga");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-sky");
			Bukkit.broadcastMessage("§6"+p.getName()+"§2 выбрал расу "+humanName);
			p.setHealth(0);
			break;
		}
		case 2:{
			pls.set("Player."+ p.getName()+".Race", "Elf");
			pls.set("Player."+ p.getName()+".Cooldown", System.currentTimeMillis());
			try {
				pls.save(players);
			} catch (IOException e) {
				e.printStackTrace();
			}
			p.sendMessage(racePickMsg +" "+elfName);
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-human");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent add race-elf");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-dwarf");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-orc");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-naga");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-sky");
			Bukkit.broadcastMessage("§6"+p.getName()+"§2 выбрал расу "+elfName);
			p.setHealth(0);
			break;
		}
		case 3:{
			pls.set("Player."+ p.getName()+".Race", "Dwarf");
			pls.set("Player."+ p.getName()+".Cooldown", System.currentTimeMillis());
			try {
				pls.save(players);
			} catch (IOException e) {
				e.printStackTrace();
			}
			p.sendMessage(racePickMsg +" "+dwarfName);
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-human");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-elf");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent add race-dwarf");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-orc");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-naga");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-sky");
			Bukkit.broadcastMessage("§6"+p.getName()+"§2 выбрал расу "+dwarfName);
			p.setHealth(0);
			break;
		}
		case 4:{
			pls.set("Player."+ p.getName()+".Race", "Orc");
			pls.set("Player."+ p.getName()+".Cooldown", System.currentTimeMillis());
			try {
				pls.save(players);
			} catch (IOException e) {
				e.printStackTrace();
			}
			p.sendMessage(racePickMsg +" "+orcName);
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-human");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-elf");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-dwarf");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent add race-orc");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-naga");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-sky");
			Bukkit.broadcastMessage("§6"+p.getName()+"§2 выбрал расу "+orcName);
			p.setHealth(0);
			break;
		}
		case 5:{
			pls.set("Player."+ p.getName()+".Race", "Naga");
			pls.set("Player."+ p.getName()+".Cooldown", System.currentTimeMillis());
			try {
				pls.save(players);
			} catch (IOException e) {
				e.printStackTrace();
			}
			p.sendMessage(racePickMsg +" "+nagaName);
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-human");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-elf");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-dwarf");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-orc");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent add race-naga");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-sky");
			Bukkit.broadcastMessage("§6"+p.getName()+"§2 выбрал расу "+nagaName);
			p.setHealth(0);
			break;
		}
		case 6:{
			pls.set("Player."+ p.getName()+".Race", "Sky");
			pls.set("Player."+ p.getName()+".Cooldown", System.currentTimeMillis());
			try {
				pls.save(players);
			} catch (IOException e) {
				e.printStackTrace();
			}
			p.sendMessage(racePickMsg +" "+skyName);
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-human");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-elf");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-dwarf");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-orc");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent remove race-naga");
			plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "lp user " + p.getName() + " parent add race-sky");
			Bukkit.broadcastMessage("§6"+p.getName()+"§2 выбрал расу "+skyName);
			p.setHealth(0);
			break;
		}
		}
		
	}
	public int getRandomArbitrary() {
		int min = 1; 
		int max = 7;
		int result = (int) (Math.random() * (max - min) + min);
		return result;
	}
	
}
