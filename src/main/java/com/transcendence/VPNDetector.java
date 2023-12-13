package com.transcendence;

import com.transcendence.command.ReloadCommand;
import com.transcendence.configuration.PluginConfiguration;
import com.transcendence.constants.logger.Log;
import com.transcendence.event.PlayerListener;
import com.transcendence.model.VPNResponse;
import com.transcendence.service.api.SurveyService;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VPNDetector extends JavaPlugin {

    private PluginConfiguration config;

    @Override
    public void onEnable() {
        loadConfigFiles();
        registerEvents();
        registerCommands();
        Log.info("[VPNDetector] Plugin enabled.");
    }

    @Override
    public void onDisable() {
        Log.info("Plugin disabled");
    }

    private void loadConfigFiles() {
        config = new PluginConfiguration(this);
        config.setupFiles();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(
                new PlayerListener(this, new SurveyService(), new VPNResponse()),
                this);
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("vpndetector")).setExecutor(new ReloadCommand(this));
    }

    public PluginConfiguration getPluginConfig() {
        return config;
    }
}
