package RaceRise.listeners;

import java.io.File;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import RaceRise.Main;

public class NagaOneShot implements Listener{
	private Main plugin;
	public NagaOneShot(Main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void nagaHit(EntityDamageByEntityEvent e) {
		Entity target = e.getEntity();
		if (target == null) return;
		if(!(e.getDamager() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getDamager();
		if(checkAllow(p)) {
			killTargetByPlayer(p,target);
		}
		return;
	}
	public boolean checkAllow(Player p) {
		ItemStack weapon = p.getInventory().getItemInMainHand();
		String race = getRace(p);
		int lvl = getRaceLvl(p);
		if(!race.equalsIgnoreCase("Naga")) return false;
		if(lvl<1) return false;
		if(!weapon.getType().equals(Material.TRIDENT)) return false;
		if(!p.getLocation().getBlock().getType().equals(Material.WATER)) return false;
		return true;
	}
	public void killTargetByPlayer(Player p, Entity target) {
		if (!(target instanceof Damageable)) {
			return;
		}
		((Damageable) target).setHealth(0);
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
