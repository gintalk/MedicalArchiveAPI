package com.vgu.cs.ma.api.server;

/*
 * Copyright (c) 2012-2016 by Zalo Group.
 * All Rights Reserved.
 *
 * @author namnh16 on 25/03/2021
 */

import com.vgu.cs.common.web.WebServer;
import com.vgu.cs.ma.api.handler.ApiHandler;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.servlet.ServletHandler;

import java.util.function.Consumer;

public class ApiServer extends WebServer {
    public ApiServer(String name) {
        super(name);

        Handler handler = createHandler(this::setupHandler);
        setup(handler);
    }

    private Handler createHandler(Consumer<ServletHandler> setupHandler) {
        ServletHandler handler = new ServletHandler();
        setupHandler.accept(handler);

        return handler;
    }

    private void setupHandler(ServletHandler handler) {
        handler.addServletWithMapping(ApiHandler.class, "/*");
    }
}
