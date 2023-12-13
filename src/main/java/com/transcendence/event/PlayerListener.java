package com.transcendence.event;

import com.transcendence.VPNDetector;
import com.transcendence.constants.logger.Log;
import com.transcendence.model.VPNResponse;
import com.transcendence.service.api.SurveyService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final VPNDetector plugin;
    private final SurveyService surveyService;
    private VPNResponse vpnResponse;

    public PlayerListener(VPNDetector plugin, SurveyService surveyService, VPNResponse vpnResponse) {
        this.plugin = plugin;
        this.surveyService = surveyService;
        this.vpnResponse = vpnResponse;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Log.warning(event.getPlayer().getDisplayName() + " is using a VPN. Will be kicked");
        if (vpnResponse.isVpn()) {
            event.getPlayer().kickPlayer(plugin.getPluginConfig().getString("kick_message"));
        }
    }

    @EventHandler
    public void asyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event) {
        String ipAddress = event.getAddress().getHostAddress();
        vpnResponse = surveyService.isUsingVpn(ipAddress);
        Log.info(String.valueOf(vpnResponse.isVpn()));
    }

}

