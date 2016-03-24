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
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author pokem
 */
public class HagePlugin extends JavaPlugin implements Listener {

	// Object
	private FileConfiguration config;

	// Configs
	private boolean enableChat;
	private boolean replaceChat;
	private List<String> users;
	private String message;
	private List<String> patterns;
	private Pattern pattern;

	@Override
	public void onEnable() {
		readConfig();
		getServer().getPluginManager().registerEvents(this, this);
		RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		chat = rsp.getProvider();
	}
	
	@SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.LOWEST)
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e)
	{
        String basemessage = e.getMessage();
        e.setFormat(getPrefix(e.getPlayer()) + "%s" + getSuffix(e.getPlayer()) + ChatColor.GREEN + ": " + ChatColor.RESET + "%s");
		e.setMessage(e.getMessage().replaceAll("&([0-9a-fk-r])", "§$1"));
		if (users.contains(e.getPlayer().getUniqueId().toString()) && !(basemessage.getBytes().length > basemessage.length() || basemessage.matches("[ \\uFF61-\\uFF9F]+")))
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
			if (replaceChat) {
                e.setMessage(pattern.matcher(e.getMessage()).replaceAll(message));
                return;
            }
            new BukkitRunnable() {

                @Override
                public void run() {
                    getServer().broadcastMessage(ChatColor.BOLD + message);
                }
            }.runTaskLaterAsynchronously(this, 20);

		}
		
		
	}

	private static Chat chat = null;
	private  String getPrefix(Player p) {
		if (getServer().getPluginManager().getPlugin("Vault") != null && chat != null) {
			return chat.getPlayerPrefix(p).replaceAll("&([0-9a-fk-r])", "§$1");
		}
		
		return "";
	}
	
	private String getSuffix(Player p) {
		if (getServer().getPluginManager().getPlugin("Vault") != null && chat != null) {
			return chat.getPlayerSuffix(p).replaceAll("&([0-9a-fk-r])", "§$1");
		}
		return "";
	}
	
	private String hageime(String romaji) {
		Gson gson = new Gson();
		List AAC;
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

	private void readConfig() {
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
		
		updatePattern();
	}

	private void writeConfig() {
		config = getConfig();
		
		// chat
		config.set("chat.enable", enableChat);
		config.set("chat.message", message);
		config.set("chat.patternlist", patterns);
		config.set("chat.replace", replaceChat);

		// ime
		config.set("ime.user", users);
		saveConfig();
		
	}

	private String get(String u) {
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

	private String getMessage() {
		return message;
	}

	private List<String> getPatterns() {
		return patterns;
	}

	private void setMessage(String message) {
		this.message = message;
		writeConfig();
	}

	private void enableChat(boolean enable) {
		this.enableChat = enable;
		writeConfig();
	}

	private void setReplace(boolean enable) {
		this.replaceChat = enable;
		writeConfig();
	}

	private void enableIME(String uuid) {
		users.add(uuid);
		writeConfig();
	}

	private void disableIME(String uuid) {
		users.remove(uuid);
		writeConfig();
	}

	private void addPattern(String pattern) {
		patterns.add(pattern);
		updatePattern();
		writeConfig();
	}

	private void removePattern(String pattern) {
		patterns.remove(pattern);
		updatePattern();
		writeConfig();
	}

	private void clearPattern() {
		patterns.clear();
		updatePattern();
		writeConfig();
	}

	private void updatePattern() {
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
