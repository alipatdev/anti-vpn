package com.transcendence.constants.logger;

import java.util.logging.Logger;

public class Log {

    private static final Logger _log = Logger.getLogger("[VPNDetector]");

    public static void info(String info) {
        _log.info(info);
    }

    public static void warning(String warnig) { _log.warning(warnig); }


}
