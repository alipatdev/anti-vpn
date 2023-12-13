package com.transcendence.configuration;

import com.transcendence.constants.logger.Log;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class PluginConfiguration {
    private File configFile;
    private FileConfiguration config;
    private final JavaPlugin plugin;

    public PluginConfiguration(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void setupFiles() {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            plugin.saveResource("config.yml", false);
        }
        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            Log.warning(e.getLocalizedMessage());
        }
    }

    public FileConfiguration getConfig() { return config; }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            Log.warning(e.getLocalizedMessage());
        }
    }

    public void reloadConfig() {
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            Log.warning(e.getLocalizedMessage());
        }
    }

    public String getString(String key) {
        Optional<String> optionalString = Optional.ofNullable(config.getString(key));
        if (optionalString.isPresent() && !optionalString.get().isEmpty()) {
            String message = setPrefix(optionalString.get());
            return ChatColor.translateAlternateColorCodes('&', message);
        }
        return String.format("String value of key: %s not found in config.yml. If you want to reset config, run: /vpndetector reset", key);
    }

    private String setPrefix(String message) {
        Optional<String> optionalPrefix = Optional.ofNullable(config.getString("plugin_prefix"));
        if (optionalPrefix.isPresent() && !optionalPrefix.get().isEmpty()) {
            String prefix = optionalPrefix.get();
            return prefix + " " + message;
        }
        return message;
    }
}
