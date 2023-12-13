package com.transcendence.command;

import com.transcendence.VPNDetector;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final VPNDetector plugin;

    public ReloadCommand(VPNDetector plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("vpndetector")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("vpndetector.reload")) {
                    plugin.getPluginConfig().reloadConfig();
                    sender.sendMessage(plugin.getPluginConfig().getString("config_reload_message"));
                    return true;
                }
                sender.sendMessage(plugin.getPluginConfig().getString("no_permissions"));
                return true;
            }
            sender.sendMessage(plugin.getPluginConfig().getString("default_command_message"));
            return true;
        }
        return false;
    }
}
