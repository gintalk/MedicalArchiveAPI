package com.vgu.cs.ma.api.app;

/*
 * Copyright (c) 2012-2016 by Zalo Group.
 * All Rights Reserved.
 *
 * @author namnh16 on 25/03/2021
 */

import com.vgu.cs.common.logger.VLogger;
import com.vgu.cs.ma.api.server.ApiServer;
import org.apache.logging.log4j.Logger;

public class MainApp {

    private static final Logger LOGGER = VLogger.getLogger(MainApp.class);

    public static void main(String[] args) {
        ApiServer server = new ApiServer("main");
        if (!server.start()) {
            LOGGER.error("Could not start ApiServer");
            System.exit(1);
        }
    }
}
