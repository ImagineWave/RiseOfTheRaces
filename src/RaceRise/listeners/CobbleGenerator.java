package RaceRise.listeners;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFormEvent;

import RaceRise.Main;

public class CobbleGenerator implements Listener{
	private Main plugin;
	public CobbleGenerator(Main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onCobbleGeneration(BlockFormEvent e) {
		if(!e.getBlock().getType().equals(Material.LAVA)) return;
		if(e.getBlock().getY()<100) return;
		Block b = e.getBlock();
		if(checkBlockLoc(e.getBlock().getLocation())) {
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
		            @Override
		            public void run() {
		            	Material ore = getRandomOre();
		            	b.setType(ore);
		            }
		        }, 1L);
			return;
		}
		return;
	}
	@EventHandler
	public void playerBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (e.getBlock().getY()<100) return;
		if (!raceCheck(p)) return;
		if (cobbleGenCheck(b)&&(raceCheck(p)&&e.getBlock().getY()>=100)) {
			blockLocToConfig(e.getBlock().getLocation());
			return;
		}
		if(!raceCheck(p)) {
			blockLocDelFromConfig(e.getBlock().getLocation());
		}
		return;
	}
	public boolean checkBlockLoc(Location loc) {
		File cobble = new File(plugin.getDataFolder() + File.separator + "CobbleGens.yml");
		FileConfiguration c = YamlConfiguration.loadConfiguration(cobble);
		List<String> blocks = c.getStringList("Blocks");
		String locSt = loc.toString();
		if(blocks.contains(locSt)) {
			return true;
		}
		return false;
	}
	public void blockLocToConfig(Location loc) {
		File cobble = new File(plugin.getDataFolder() + File.separator + "CobbleGens.yml");
		FileConfiguration c = YamlConfiguration.loadConfiguration(cobble);
		List<String> blocks = c.getStringList("Blocks");
		if (blocks.contains(loc.toString())) return;
		blocks.add(loc.toString());
		c.set("Blocks", blocks);
		try {
			c.save(cobble);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void blockLocDelFromConfig(Location loc) {
		File cobble = new File(plugin.getDataFolder() + File.separator + "CobbleGens.yml");
		FileConfiguration c = YamlConfiguration.loadConfiguration(cobble);
		List<String> blocks = c.getStringList("Blocks");
		if (!blocks.contains(loc.toString())) return;
		blocks.remove(loc.toString());
		c.set("Blocks", blocks);
		try {
			c.save(cobble);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean cobbleGenCheck(Block center) {
		if(!center.getType().equals(Material.COBBLESTONE)) return false;
		Boolean water = false;
		Boolean lava = false;
		for(int x = -1; x<2; x++) {
			for(int y = -1; y<2; y++) {
				for(int z = -1; z<2; z++) {
					if (center.getRelative(x,y,z).getType().equals(Material.WATER)) {
						water = true;
					}
					if (center.getRelative(x,y,z).getType().equals(Material.LAVA)) {
						lava = true;
					}
				}
			}
		}
		if(water&&lava) {
			return true;
		}
		return false;
	}
	
	public boolean raceCheck(Player p) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		String race = pls.getString("Player."+ p.getName()+".Race");
		int lvl = pls.getInt("Player."+ p.getName()+".RaceLvl.Sky");
		if ((race.equalsIgnoreCase("sky"))&&(lvl>0)) return true;
		return false;
	}
	public Material getRandomOre() {
		Material result = Material.COBBLESTONE;
		int random = getRandomArbitrary();
		if(random==3580) {
			result = Material.NETHERITE_BLOCK;
			return result;
		}
		if(random<655000) {
			return result;
		}
		if((655000<random)&&(random<795000)) {
			result = Material.COAL_ORE;
			return result;
		}
		if((795000<random)&&(random<870000)) { 
			result = Material.IRON_ORE;
			return result;
		}
		if((870000<random)&&(random<895000)) { 
			result = Material.REDSTONE_ORE;
			return result;
		}
		if((895000<random)&&(random<911000)) { 
			result = Material.GOLD_ORE;
			return result;
		}
		if((911000<random)&&(random<923000)) {
			result = Material.LAPIS_ORE;
			return result;
		}
		if((911000<random)&&(random<931000)) {
			result = Material.EMERALD_ORE;
			return result;
		}
		if((931000<random)&&(random<935000)) {
			result = Material.DIAMOND_ORE;
			return result;
		}
		return result;
	}
	public int getRandomArbitrary() {
		int min = 1; 
		int max = 935001;
		int result = (int) (Math.random() * (max - min) + min);
		return result;
	}
}
