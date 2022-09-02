package RaceRise.listeners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import RaceRise.Main;

public class Quests implements Listener{
	private Main plugin;
	public Quests(Main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void HumanQuestProgress(EntityDeathEvent e) {
		ArrayList<EntityType> animal = new ArrayList<EntityType>();
		animal.add(EntityType.PIG);
		animal.add(EntityType.COW);
		animal.add(EntityType.HORSE);
		animal.add(EntityType.CHICKEN);
		animal.add(EntityType.SHEEP);
		if(!animal.contains(e.getEntity().getType())) return;
		if (!(e.getEntity() instanceof Damageable)) return;
		if (e.getEntity().getKiller()== null) return;
		Player p = e.getEntity().getKiller();
		String race = getRace(p);
		if(!race.equalsIgnoreCase("Human")) return;
		questHumanAnimalAdd(p,7);
		return;
	}
	@EventHandler
	public void skyQuestProgress(EntityDeathEvent e) {
		if (!e.getEntityType().equals(EntityType.ENDER_DRAGON)) return;
		Player p = e.getEntity().getKiller();
		String race = getRace(p);
		if(!race.equalsIgnoreCase("sky")) return;
		questSkyDragonsKilled(p,1001);
		return;
	}
	@EventHandler
	public void elfQuestProgress(BlockPlaceEvent e) {
		ArrayList<Material> sapling = new ArrayList<Material>();
		sapling.add(Material.OAK_SAPLING);
		sapling.add(Material.ACACIA_SAPLING);
		sapling.add(Material.BIRCH_SAPLING);
		sapling.add(Material.DARK_OAK_SAPLING);
		sapling.add(Material.JUNGLE_SAPLING);
		sapling.add(Material.SPRUCE_SAPLING);
		if (!sapling.contains(e.getBlock().getType())) return;
		Player p = e.getPlayer();
		String race = getRace(p);
		if(!race.equalsIgnoreCase("elf")) return;
		questElfSapplingAdd(p,2);
		return;
	}
	@EventHandler
	public void elfQuestRegress(BlockBreakEvent e) {
		ArrayList<Material> sapling = new ArrayList<Material>();
		sapling.add(Material.OAK_SAPLING);
		sapling.add(Material.ACACIA_SAPLING);
		sapling.add(Material.BIRCH_SAPLING);
		sapling.add(Material.DARK_OAK_SAPLING);
		sapling.add(Material.JUNGLE_SAPLING);
		sapling.add(Material.SPRUCE_SAPLING);
		if (!sapling.contains(e.getBlock().getType())) return;
		Player p = e.getPlayer();
		String race = getRace(p);
		if(!race.equalsIgnoreCase("elf")) return;
		questElfSapplingAdd(p,-2);
		return;
	}
	@EventHandler
	public void dwarfQuestProgress(BlockBreakEvent e) {
		Player p = e.getPlayer();
		String race = getRace(p);
		if(!race.equalsIgnoreCase("dwarf")) return;
		Material type = e.getBlock().getType();
		if (type.equals(Material.STONE))return;
		switch (type) {
			case COAL_ORE:{
				questDwarfBlockBreak(p,2);
				break;	
			}
			case IRON_ORE:{
				questDwarfBlockBreak(p,3);
				break;
			}
			case GOLD_ORE:{
				questDwarfBlockBreak(p,5);
				break;
			}
			case REDSTONE_ORE:{
				questDwarfBlockBreak(p,2);
				break;
			}
			case LAPIS_ORE:{
				questDwarfBlockBreak(p,5);
				break;
			}
			case EMERALD_ORE:{
				questDwarfBlockBreak(p,10);
				break;
			}
			case DIAMOND_ORE:{
				questDwarfBlockBreak(p,10);
				break;
			}
		}
		return;
	}
	@EventHandler
	public void dwarfQuestRegress(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		String race = getRace(p);
		if(!race.equalsIgnoreCase("dwarf")) return;
		Material type = e.getBlock().getType();
		switch (type) {
			case STONE:{
				questDwarfBlockBreak(p,-1);
				break;
			}
			case COAL_ORE:{
				questDwarfBlockBreak(p,-2);
				break;	
			}
			case IRON_ORE:{
				questDwarfBlockBreak(p,-3);
				break;
			}
			case GOLD_ORE:{
				questDwarfBlockBreak(p,-5);
				break;
			}
			case REDSTONE_ORE:{
				questDwarfBlockBreak(p,-2);
				break;
			}
			case LAPIS_ORE:{
				questDwarfBlockBreak(p,-5);
				break;
			}
			case EMERALD_ORE:{
				questDwarfBlockBreak(p,-10);
				break;
			}
			case DIAMOND_ORE:{
				questDwarfBlockBreak(p,-10);
				break;
			}
		}
		return;
	}
	@EventHandler
	public void orcQuestProgress(EntityDeathEvent e) {
		if(e.getEntity().getKiller()== null) return;
		Player p = e.getEntity().getKiller();
		String race = getRace(p);
		if(!race.equalsIgnoreCase("Orc")) return;
		questOrcEntityKilled(p,2);
		return;
	}
	@EventHandler
	public void nagaQuestProgress(EntityDeathEvent e) {
		if (!e.getEntityType().equals(EntityType.ELDER_GUARDIAN)) return;
		Player p = e.getEntity().getKiller();
		String race = getRace(p);
		if(!race.equalsIgnoreCase("naga")) return;
		questNagaGuardiansKilled(p,335);
		return;
	}
	public String getRace(Player p) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		String race = "none";
		race = pls.getString("Player."+ p.getName()+".Race");
		return race;
	}
	public void questHumanAnimalAdd(Player p, int value) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		int oldValue = pls.getInt("Player."+ p.getName()+".Quest.Human.Animals");
		int newValue = oldValue + value;
		p.sendMessage("§aПрогресс опыта §6"+oldValue+"/1000 §e---> §6"+newValue+"/1000");
		int lvlup = 1000;
		if (newValue>lvlup) {
			int lvl = pls.getInt("Player."+ p.getName()+".RaceLvl.Human");
			pls.set("Player."+ p.getName()+".RaceLvl.Human", lvl + 1);
			p.sendMessage("§6Новый уровень!");
			newValue = 0;
		}
		pls.set("Player."+ p.getName()+".Quest.Human.Animals", newValue);
		try {
			pls.save(players);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void questElfSapplingAdd(Player p, int value) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		int oldValue = pls.getInt("Player."+ p.getName()+".Quest.Elf.Sapling");
		int newValue = oldValue + value;
		p.sendMessage("§aПрогресс опыта §6"+oldValue+"/1000 §e---> §6"+newValue+"/1000");
		int lvlup = 1000;
		if (newValue>lvlup) {
			int lvl = pls.getInt("Player."+ p.getName()+".RaceLvl.Elf");
			pls.set("Player."+ p.getName()+".RaceLvl.Elf", lvl + 1);
			p.sendMessage("§6Новый уровень!");
			newValue = 0;
		}
		pls.set("Player."+ p.getName()+".Quest.Elf.Sapling", newValue);
		try {
			pls.save(players);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void questDwarfBlockBreak(Player p, int value) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		int oldValue = pls.getInt("Player."+ p.getName()+".Quest.Dwarf.Blocks");
		int newValue = oldValue + value;
		if(value>1) {
		p.sendMessage("§aПрогресс опыта §6"+oldValue+"/3000 §e---> §6"+newValue+"/3000");
		}
		int lvlup = 3000;
		if (newValue>lvlup) {
			int lvl = pls.getInt("Player."+ p.getName()+".RaceLvl.Dwarf");
			pls.set("Player."+ p.getName()+".RaceLvl.Dwarf", lvl + 1);
			p.sendMessage("§6Новый уровень!");
			newValue = 0;
		}
		pls.set("Player."+ p.getName()+".Quest.Dwarf.Blocks", newValue);
		try {
			pls.save(players);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void questOrcEntityKilled(Player p, int value) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		int oldValue = pls.getInt("Player."+ p.getName()+".Quest.Orc.KilledMobs");
		int newValue = oldValue + value;
		p.sendMessage("§aПрогресс опыта §6"+oldValue+"/1000 §e---> §6"+newValue+"/1000");
		int lvlup = 1000;
		if (newValue>lvlup) {
			int lvl = pls.getInt("Player."+ p.getName()+".RaceLvl.Orc");
			pls.set("Player."+ p.getName()+".RaceLvl.Orc", lvl + 1);
			p.sendMessage("§6Новый уровень!");
			newValue = 0;
		}
		pls.set("Player."+ p.getName()+".Quest.Orc.KilledMobs", newValue);
		try {
			pls.save(players);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void questNagaGuardiansKilled(Player p, int value) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		int oldValue = pls.getInt("Player."+ p.getName()+".Quest.Naga.KilledGuardians");
		int newValue = oldValue + value;
		p.sendMessage("§aПрогресс опыта §6"+oldValue+"/1000 §e---> §6"+newValue+"/1000");
		int lvlup = 1000;
		if (newValue>lvlup) {
			int lvl = pls.getInt("Player."+ p.getName()+".RaceLvl.Naga");
			pls.set("Player."+ p.getName()+".RaceLvl.Naga", lvl + 1);
			p.sendMessage("§6Новый уровень!");
			newValue = 0;
		}
		pls.set("Player."+ p.getName()+".Quest.Naga.KilledGuardians", newValue);
		try {
			pls.save(players);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void questSkyDragonsKilled(Player p, int value) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		int oldValue = pls.getInt("Player."+ p.getName()+".Quest.Sky.KilledDragons");
		int newValue = oldValue + value;
		p.sendMessage("§aПрогресс опыта §6"+oldValue+"/1000 §e---> §6"+newValue+"/1000");
		int lvlup = 1000;
		if (newValue>lvlup) {
			int lvl = pls.getInt("Player."+ p.getName()+".RaceLvl.Sky");
			pls.set("Player."+ p.getName()+".RaceLvl.Sky", lvl + 1);
			p.sendMessage("§6Новый уровень!");
			newValue = 0;
		}
		pls.set("Player."+ p.getName()+".Quest.Sky.KilledDragons", newValue);
		try {
			pls.save(players);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
