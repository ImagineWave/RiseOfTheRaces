package RaceRise.listeners;

import java.io.File;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import RaceRise.Main;

public class BedrockBreak implements Listener{
	private Main plugin;
	public BedrockBreak(Main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void blockHit(PlayerInteractEvent e) {
		if(!e.getAction().equals(Action.LEFT_CLICK_BLOCK)) return;
		if(!e.getClickedBlock().getType().equals(Material.BEDROCK)) return;
		Player p = e.getPlayer();
		if (checkAllow(p)) {
			blockBreak(p, e.getClickedBlock());
		}
	}
	public void blockBreak(Player p, Block b) {
		ItemStack item = p.getInventory().getItemInMainHand();
		ItemStack bedrock = new ItemStack(Material.BEDROCK);
		Short durability = item.getDurability();
		durability = (short) (durability + 100);
		item.setDurability(durability);
		if(b.getLocation().getWorld().getName().equals("world_the_end")) return;
		b.setType(Material.AIR);
		if(getRandomArbitrary()==42) {
			p.sendMessage("§aВы получили бедрок");
			p.getInventory().addItem(bedrock);
		}
	}
	public boolean checkAllow (Player p) {
		String race = getRace(p);
		int lvl =  getRaceLvl(p);
		ItemStack item = p.getInventory().getItemInMainHand();
		ItemMeta meta = item.getItemMeta();
		short durability = item.getDurability();
		
		if(!race.equalsIgnoreCase("Dwarf")) return false;
		if(lvl==0) return false;
		if(!item.getType().equals(Material.NETHERITE_PICKAXE)) return false;
		if(durability>1930)return false;
		if(meta.hasEnchant(Enchantment.MENDING)) return false;
		if(!item.getItemMeta().isUnbreakable()) {
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
	public int getRaceLvl(Player p) {
		File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		String race = getRace(p);
		int result = pls.getInt("Player."+ p.getName()+".RaceLvl."+race);
		return result;
	}
	public int getRandomArbitrary() {
		int min = 1; 
		int max = 51;//444
		int result = (int) (Math.random() * (max - min) + min);
		return result;
	}
}
