package me.messageofdeath.commandnpc.Utilities.BungeeCord;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.messageofdeath.commandnpc.CommandNPC;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BungeeCordUtil {
	
	public static void setupUtil() {
		JavaPlugin plugin = CommandNPC.getInstance();
		plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
	}
	
	public static void disableUtil() {
		JavaPlugin plugin = CommandNPC.getInstance();
		plugin.getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, "BungeeCord");
	}
	
	public static void sendPlayerToServer(Player player, String serverName) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(serverName);
		player.sendPluginMessage(CommandNPC.getInstance(), "BungeeCord", out.toByteArray());
	}

	public static void redirectPlus(Player player, String command) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(stream);
		try {
			out.writeUTF("execute-alias");
			out.writeUTF(player.getName());
			out.writeUTF(command.toLowerCase());
			player.sendPluginMessage(CommandNPC.getInstance(), "martenm:redirectplus", stream.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
