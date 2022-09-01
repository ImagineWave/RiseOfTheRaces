package RaceRise.Meta;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class MetaManager {
	
	private MetaManager() {}
	
	private static MetaManager manager = new MetaManager();
	
	public static MetaManager getManager() {
		return manager;
	}
	
	public enum MessageType {
		INFO(ChatColor.AQUA),
		GOOD(ChatColor.GREEN),
		BAD(ChatColor.RED);
		
		private ChatColor color;
		
		MessageType (ChatColor color) {
			this.color = color;
		}
		
		public ChatColor getColor() {
			return color;
		}
	}
	private String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "STR" + ChatColor.GREEN + "race" + ChatColor.GRAY + "]: " + ChatColor.RESET;
	public void msg (CommandSender sender, MessageType type, String... msgs) {
		for (String msg : msgs) {
			sender.sendMessage(prefix + type.getColor() + msg);
		}
	}
}
