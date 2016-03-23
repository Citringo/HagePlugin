/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.citringo.hageplugin;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;
import net.milkbowl.vault.chat.Chat;
import net.minecraft.server.v1_9_R1.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.map.MinecraftFont;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 *
 * @author pokem
 */
public class HagePlugin extends JavaPlugin implements Listener {

	// Object
	public FileConfiguration config;
	private static HagePlugin instance;

	// Configs
	private boolean enableChat;
	private boolean replaceChat;
	private List<String> users;
	private String message;
	private List<String> patterns;
	private Pattern pattern;

	public HagePlugin() {

	}

	@Override
	public void onEnable() {
		readConfig();
		getServer().getPluginManager().registerEvents(this, this);
		RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		chat = rsp.getProvider();
		getLogger().info("onEnable");
	}

	public static HagePlugin getInstance() {
		if (instance == null) {
			instance = (HagePlugin) Bukkit.getPluginManager().getPlugin("HagePlugin");
		}
		return instance;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e)
	{
		e.setMessage(e.getMessage().replaceAll("&([0-9a-fk-r])", "§$1"));
		if (users.contains(e.getPlayer().getUniqueId().toString()))
		{
			try
			{
				String[] split = e.getMessage().split("\\$");
				String AAF = "";
				boolean henkanflag = true;
				for (String s : split)
				{
					if (henkanflag)
					{
						try
						{
							AAF += hageime(s);
						}
						catch (Exception ex)
						{
							AAF += s;
						}
					}
					else
					{
						AAF += s;
					}
					henkanflag = !henkanflag;
				}
				e.setFormat(getPrefix(e.getPlayer()) + "%s" + getSuffix(e.getPlayer()) + ChatColor.GREEN + ": " + ChatColor.RESET + "%s");
				e.setMessage(AAF + ChatColor.GRAY + "(" + e.getMessage() + ")");
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		if (!enableChat) return;
		if (pattern.matcher(e.getMessage()).find())
		{
			if (replaceChat)
			{
				e.setMessage(pattern.matcher(e.getMessage()).replaceAll(message));
				return;
			}
			getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				@Override
				public void run() {
					getServer().broadcastMessage(ChatColor.BOLD + message);
				}
			}, 2);
		}
		
		
	}

	public static Chat chat = null;
	public String getPrefix(Player p) {
		if (getServer().getPluginManager().getPlugin("Vault") != null && chat != null) {
			return chat.getPlayerPrefix(p).replaceAll("&([0-9a-fk-r])", "§$1");
		}
		
		return "";
	}
	
	public String getSuffix(Player p) {
		if (getServer().getPluginManager().getPlugin("Vault") != null && chat != null) {
			return chat.getPlayerSuffix(p).replaceAll("&([0-9a-fk-r])", "§$1");
		}
		return "";
	}
	
	String hageime(String romaji) {
		Gson gson = new Gson();
		List AAC = null;
		String AAD = "";
		try {
			romaji = AAA.AAB(romaji);
			for (int i = 0; i < romaji.length(); i += 42) {
				String romaji2;
				if (i + 42 >= romaji.length()) {
					romaji2 = romaji.substring(i, romaji.length());
				} else {
					romaji2 = romaji.substring(i, i + 42);
				}
				AAC = gson.fromJson(get("http://www.google.com/transliterate?langpair=ja-Hira|ja&text=" + URLEncoder.encode(romaji2, "utf-8")), List.class);
				for (Object a : AAC) {
					AAD += ((List) (((List) a).get(1))).get(0).toString();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return AAD;
	}

	public void readConfig() {
		config = getConfig();
		// もしconfig.ymlが存在しないなら、既定のconfig.ymlをコピーします。
        this.saveDefaultConfig();
		
		reloadConfig();

		// chat
		enableChat = config.getBoolean("chat.enable");
		message = config.getString("chat.message");
		patterns = config.getStringList("chat.patternlist");
		replaceChat = config.getBoolean("chat.replace");

		// ime
		users = config.getStringList("ime.user");
		
		getLogger().info("readConfig");
		updatePattern();
	}

	public void writeConfig() {
		config = getConfig();
		
		// chat
		config.set("chat.enable", enableChat);
		config.set("chat.message", message);
		config.set("chat.patternlist", patterns);
		config.set("chat.replace", replaceChat);

		// ime
		config.set("ime.user", users);
		saveConfig();
		
		getLogger().info("writeConfig" + String.format(
				"chat.enable = %s"
				+ "chat.message = %s"
				+ "chat.patternlist = %s"
				+ "chat.replace = %s"
				+ "ime.user = %s", 
				config.getBoolean("chat.enable"),
				config.getString("chat.message"),
				config.getStringList("chat.patternlist"),
				config.getBoolean("chat.replace"),
				config.getStringList("ime.user")));
	}

	String get(String u) {
		StringBuilder output = new StringBuilder();
		try {
			URL url = new URL(u);

			HttpURLConnection connection = null;

			try {
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");

				if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					try (InputStreamReader isr = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)) {
						BufferedReader reader = new BufferedReader(isr);
						{
							String line;
							while ((line = reader.readLine()) != null) {
								output.append(line);
							}
						}
					}
				}
			} finally {
				if (connection != null) {
					connection.disconnect();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output.toString();
	}

	public String getMessage() {
		return message;
	}

	public List<String> getPatterns() {
		return patterns;
	}

	public void setMessage(String message) {
		this.message = message;
		writeConfig();
	}

	public void enableChat(boolean enable) {
		this.enableChat = enable;
		writeConfig();
	}

	public void setReplace(boolean enable) {
		this.replaceChat = enable;
		writeConfig();
	}

	protected void enableIME(String uuid) {
		users.add(uuid);
		writeConfig();
	}

	protected void disableIME(String uuid) {
		users.remove(uuid);
		writeConfig();
	}

	public void addPattern(String pattern) {
		patterns.add(pattern);
		updatePattern();
		writeConfig();
	}

	public void removePattern(String pattern) {
		patterns.remove(pattern);
		updatePattern();
		writeConfig();
	}

	public void clearPattern() {
		patterns.clear();
		updatePattern();
		writeConfig();
	}

	protected void updatePattern() {
		StringBuilder stringBuilder = new StringBuilder();
		int i = 0;
		for (String p : patterns) {
			stringBuilder.append('(');
			stringBuilder.append(p);
			if (i == patterns.size() - 1) {
				stringBuilder.append(")");
			} else {
				stringBuilder.append(")|");
			}
			i++;
		}
		pattern = Pattern.compile(stringBuilder.toString());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (command.getName().equalsIgnoreCase("hage")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("enable")) {
					enableChat(true);
				} else if (args[0].equalsIgnoreCase("disable")) {
					enableChat(false);
				} else if (args[0].equalsIgnoreCase("message")) {
					sender.sendMessage(getMessage());
				} else if (args[0].equalsIgnoreCase("clear")) {
					clearPattern();
				} else if (args[0].equalsIgnoreCase("list")) {
					List<String> list = getPatterns();
					for (String p : list) {
						sender.sendMessage(p);
					}
				} else {
					return false;
				}
				return true;
			}
			else if (args.length != 2) {
				return false;
			}

			if (args[0].equalsIgnoreCase("message")) {
				setMessage(args[1].replace('　', ' '));
			}
			else if (args[0].equalsIgnoreCase("replace"))
			{
				if (args[1].equalsIgnoreCase("true"))
					setReplace(true);
				else if (args[1].equalsIgnoreCase("false"))
					setReplace(false);
				else
					return false;
			}
			else if (args[0].equalsIgnoreCase("add"))
				addPattern(args[1]);
			else if (args[0].equalsIgnoreCase("remove"))
				removePattern(args[1]);
			else
				return false;
		}
		else if (command.getName().equalsIgnoreCase("hageime"))
		{
			if (args.length != 1)
				return false;
			if ((args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("e")) && sender instanceof Player)
			{
				enableIME(((Player)sender).getUniqueId().toString());
			}
			else if ((args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("d")) && sender instanceof Player)
			{
				disableIME(((Player)sender).getUniqueId().toString());
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		return true;
	}

}
