package com.transcendence.model;

public class VPNResponse {

    private boolean isVpn;
    private boolean adminBypass;

    public boolean hasAdminBypass() {
        return adminBypass;
    }

    public void setAdminBypass(boolean bypassControl) {
        this.adminBypass = bypassControl;
    }

    public VPNResponse() {
    }

    public VPNResponse(boolean isVpn) {
        this.isVpn = isVpn;
    }

    public boolean isVpn() {
        return isVpn;
    }

    public void setVpn(boolean vpn) {
        this.isVpn = vpn;
    }

    @Override
    public String toString() {
        return "VPNResponse{" +
                ", hostIp=" + isVpn +
                '}';
    }
}
