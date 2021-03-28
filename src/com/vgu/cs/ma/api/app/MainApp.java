package com.vgu.cs.ma.api.app;

/*
 * Copyright (c) 2012-2016 by Zalo Group.
 * All Rights Reserved.
 *
 * @author namnh16 on 25/03/2021
 */

import com.vgu.cs.ma.api.server.ApiServer;

public class MainApp {

    public static void main(String[] args) {
        ApiServer server = new ApiServer("main");
        if (!server.start()) {
            System.exit(1);
        }
    }
}
