package com.transcendence.service.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.transcendence.model.VPNResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SurveyService {

    public VPNResponse isUsingVpn(String ipAddress) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest req = HttpRequest
                    .newBuilder()
                    .uri(new URI("https://proxycheck.io/v2/" + ipAddress + "?vpn=1&asn=1"))
                    .GET()
                    .build();

            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

            return bindData(res, ipAddress);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private VPNResponse bindData(HttpResponse<String> response, String ipAddress) {
        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

        VPNResponse vpnResponse = new VPNResponse();
        String proxy = jsonObject.get(ipAddress).getAsJsonObject().get("proxy").getAsString();
        vpnResponse.setVpn(proxy.equals("yes"));

        return vpnResponse;
    }
}
