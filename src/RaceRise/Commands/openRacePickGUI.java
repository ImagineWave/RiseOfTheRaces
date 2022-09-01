package RaceRise.Commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import RaceRise.Main;

public class openRacePickGUI implements CommandExecutor{
	private Main plugin;
	public openRacePickGUI(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		 if (!(sender instanceof Player)) {
	            sender.sendMessage("Only for player usage!");
	            return true;
	        }
		Player p = (Player) sender;
		
		File messages = new File(plugin.getDataFolder() + File.separator + "MessagesRu.yml");
		FileConfiguration msgs = YamlConfiguration.loadConfiguration(messages);
		String guiName = msgs.getString("GUIname");
		String RacePickCooldown = msgs.getString("RacePickCooldown");
		String humanName = msgs.getString("humanName");
		String elfName = msgs.getString("elfName");
		String dwarfName = msgs.getString("dwarfName");
		String orcName = msgs.getString("orcName");
		String nagaName = msgs.getString("nagaName");
		String skyName = msgs.getString("skyName");
		String noPermission = msgs.getString("NoPermission");
		 if(!p.hasPermission("race.pick")) {
			 p.sendMessage(noPermission);
			 return true;
		}
		 
		 File players = new File(plugin.getDataFolder() + File.separator + "Players.yml");
		 FileConfiguration pls = YamlConfiguration.loadConfiguration(players);
		 Long cooldownRacePick = pls.getLong("cooldownRacePick");
		 Long cooldown = pls.getLong("Player."+ p.getName()+".Cooldown");
		 cooldown = cooldown + cooldownRacePick;
		 if(!p.hasPermission("race.cooldown.bypass")) {
			 if (cooldown-System.currentTimeMillis()>0) {
				String messageCooldown = formatDuration2((cooldown-System.currentTimeMillis())/1000);
				 p.sendMessage(RacePickCooldown+" "+messageCooldown);
				 return true;
			 }
		 }
		 
		 Inventory gui = Bukkit.createInventory(p, 9, guiName);
		 
		 ItemStack human = new ItemStack(Material.YELLOW_BANNER);
		 ItemMeta human_meta = human.getItemMeta();
		 human_meta.setDisplayName(humanName);
		 List<String> human_lore = new ArrayList<String>();
		 human_lore.add("§a20 §fHP §c♥");
		 human_lore.add("§a§lУровень §eI");
		 human_lore.add("§aПерманентный баф §a§lГерой деревни II §e♦");
		 human_lore.add("§a§lУровень §eII");
		 human_lore.add("§aПерманентный баф §a§lГерой деревни IV §e♦");
		 human_lore.add("§aПерманентный баф §a§lСопротивление I §e♦");
		 human_lore.add("---------");
		 human_lore.add("§4Смена расы мгновенно убивает игрока");
		 human_meta.setLore(human_lore);
		 human.setItemMeta(human_meta);
		 
		 ItemStack elf = new ItemStack(Material.LIME_BANNER);
		 ItemMeta elf_meta = elf.getItemMeta();
		 elf_meta.setDisplayName(elfName);
		 List<String> elf_lore = new ArrayList<String>();
		 elf_lore.add("§e12 §fHP §c♥");
		 elf_lore.add("Если находится §e§nвыше§r 25 §rвысоты");
		 elf_lore.add("получает §a+10 §fHP §c♥");
		 elf_lore.add("§a§lУровень §eI");
		 elf_lore.add("§aПерманентный баф §a§lСкорость II §e♦");
		 elf_lore.add("§aПерманентный баф §a§lПрыгучесть II §e♦");
		 elf_lore.add("§a§lУровень §eII");
		 elf_lore.add("§aПерманентный баф §a§lSaturation I §e♦");
		 elf_lore.add("---------");
		 elf_lore.add("§4Смена расы мгновенно убивает игрока");
		 elf_meta.setLore(elf_lore);
		 elf.setItemMeta(elf_meta);
		 
		 ItemStack dwarf = new ItemStack(Material.BLUE_BANNER);
		 ItemMeta dwarf_meta = dwarf.getItemMeta();
		 dwarf_meta.setDisplayName(dwarfName);
		 List<String> dwarf_lore = new ArrayList<String>();
		 dwarf_lore.add("§e8 §fHP §c♥");
		 dwarf_lore.add("Если находится §e§nниже§r 45 высоты");
		 dwarf_lore.add("получает §a+12 §fHP §c♥");
		 dwarf_lore.add("§a§lУровень §eI");
		 dwarf_lore.add("§aПерманентный баф §a§lСпе§aш§a§lка II §e♦");
		 dwarf_lore.add("§aПерманентный баф §a§lНочное зрение I §e♦");
		 dwarf_lore.add("§a§lУровень §eII");
		 dwarf_lore.add("§aПерманентный баф §a§lСпе§aш§a§lка IV §e♦");
		 dwarf_lore.add("Возможность сломать §nбедрок");
		 dwarf_lore.add("---------");
		 dwarf_lore.add("§4Смена расы мгновенно убивает игрока");
		 dwarf_meta.setLore(dwarf_lore);
		 dwarf.setItemMeta(dwarf_meta);
		 
		 ItemStack orc = new ItemStack(Material.RED_BANNER);
		 ItemMeta orc_meta = orc.getItemMeta();
		 orc_meta.setDisplayName(orcName);
		 List<String> orc_lore = new ArrayList<String>();
		 orc_lore.add("§a16 §fHP §c♥");
		 orc_lore.add("Если находится §e§nв нижнем§r мире");
		 orc_lore.add("получает §a+10 §fHP §c♥");
		 orc_lore.add("§a§lУровень §eI");
		 orc_lore.add("При убийстве моба получает");
		 orc_lore.add("§a§lСила II §610 сек. §e♦");
		 orc_lore.add("§a§lРегенерация II §610 сек. §e♦");
		 orc_lore.add("Торговля с жителями §4недоступна");
		 orc_lore.add("§a§lУровень §eII");
		 orc_lore.add("Торговля с жителями §aдоступна");
		 orc_lore.add("---------");
		 orc_lore.add("§4Смена расы мгновенно убивает игрока");
		 orc_meta.setLore(orc_lore);
		 orc.setItemMeta(orc_meta);
		 
		 ItemStack naga = new ItemStack(Material.CYAN_BANNER);
		 ItemMeta naga_meta = naga.getItemMeta();
		 naga_meta.setDisplayName(nagaName);
		 List<String> naga_lore = new ArrayList<String>();
		 naga_lore.add("§a20 §fHP §c♥");
		 naga_lore.add("§a§lУровень §eI");
		 naga_lore.add("§aПерманентный баф §a§lГрация дель§aф§a§lина I §e♦");
		 naga_lore.add("§aПерманентный баф §a§lВодное дыхание I §e♦");
		 naga_lore.add("§aПерманентный баф §a§lСила источника I §e♦");
		 naga_lore.add("§a§lУровень §eII");
		 naga_lore.add("Мгновенное убийство трезубцем в §9воде");
		 naga_lore.add("---------");
		 naga_lore.add("§4Смена расы мгновенно убивает игрока");
		 naga_meta.setLore(naga_lore);
		 naga.setItemMeta(naga_meta);
		 
		 ItemStack sky = new ItemStack(Material.WHITE_BANNER);
		 ItemMeta sky_meta = sky.getItemMeta();
		 sky_meta.setDisplayName(skyName);
		 List<String> sky_lore = new ArrayList<String>();
		 sky_lore.add("§c4 §fHP §c♥");
		 sky_lore.add("§a§lУровень §eI");
		 sky_lore.add("Если находится §e§nвыше§r 100 §rвысоты");
		 sky_lore.add("получает §a+16 §fHP §c♥");
		 sky_lore.add("§a§lПостоянный полет §6♦");
		 sky_lore.add("§4(полет недоступен в энде)");
		 sky_lore.add("§a§lУровень §eII");
		 sky_lore.add("§eГенератор руд: §rпри генерации");
		 sky_lore.add("булыжника есть шанс появления руды");
		 sky_lore.add("(Работает только выше 100 высоты)");
		 sky_lore.add("---------");
		 sky_lore.add("§4Смена расы мгновенно убивает игрока");
		 sky_meta.setLore(sky_lore);
		 sky.setItemMeta(sky_meta);
		 
		 ItemStack random = new ItemStack(Material.STRUCTURE_VOID);
		 ItemMeta random_meta = random.getItemMeta();
		 random_meta.setDisplayName("§eР§aа§9н§cд§bо§fм");
		 List<String> random_lore = new ArrayList<String>();
		 random_lore.add("§cПерезарядка выбора расы §45 дней");
		 random_lore.add("§cИспользуйте только");
		 random_lore.add("§cна свой §4страх и риск");
		 random_lore.add("§4Смена расы мгновенно убивает игрока");
		 random_meta.setLore(random_lore);
		 random.setItemMeta(random_meta);
		 
		 ItemStack blank = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		 
		 ItemStack close = new ItemStack(Material.BARRIER);
		 ItemMeta close_meta = close.getItemMeta();
		 close_meta.setDisplayName("§4Закрыть");
		 close.setItemMeta(close_meta);
		 
		 ItemStack[] menu_items = {human, elf, dwarf, orc, naga, sky, random, blank, close};
		 gui.setContents(menu_items);
		 p.openInventory(gui);
		return true;
	}
	public String formatDuration(Long time) {
		long hours = time/3600;
		long minutes = time%3600/60;
		long seconds = time%3600%60;
		String sthours = Long.toString(hours);
		String stminutes = Long.toString(minutes);
		String stseconds = Long.toString(seconds);
		String msgduration = "§6";
		if(hours != 0) msgduration += sthours + " §4Часов§6 ";
		if(minutes != 0) msgduration += stminutes + " §4Минут§6 ";
		if(seconds != 0) msgduration += stseconds + " §4Секунд";
		return msgduration;
	}
	public String formatDuration2(Long time) {
		long days = time/86400;
		long hours = time%86400/3600;
		long minutes = time%86400%3600/60;
		long seconds = time%86400%3600%60;
		String stdays = Long.toString(days);
		String sthours = Long.toString(hours);
		String stminutes = Long.toString(minutes);
		String stseconds = Long.toString(seconds);
		String msgduration = "§6";
		if(days != 0) msgduration += stdays + " §4Day(s)§6 ";
		if(hours != 0) msgduration += sthours + " §4Hour(s)§6 ";
		if(minutes != 0) msgduration += stminutes + " §4Minute(s)§6 ";
		if(seconds != 0) msgduration += stseconds + " §4Second(s) ";
		return msgduration;
	}

}
