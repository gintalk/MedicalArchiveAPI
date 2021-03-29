package com.vgu.cs.ma.api.handler;

/*
 * Copyright (c) 2012-2016 by Zalo Group.
 * All Rights Reserved.
 *
 * @author namnh16 on 26/03/2021
 */

import com.vgu.cs.common.logger.VLogger;
import com.vgu.cs.common.util.LoggingUtils;
import com.vgu.cs.common.web.*;
import com.vgu.cs.ma.api.model.WelcomeModel;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class ApiHandler extends VApiHandler {

    private static final Logger LOGGER = VLogger.getLogger(ApiHandler.class);
    private static final List<VModel<? extends VRequest>> INIT = Arrays.asList(
            WelcomeModel.INSTANCE
    );

    @Override
    protected void doHandle(HttpServletRequest req, HttpServletResponse res) {
        String path = req.getPathInfo();

        VPath apiPath = new VPath(path);
        if (!apiPath.isValid()) {
            LOGGER.error(LoggingUtils.buildLog("Invalid API path", apiPath));
            return;
        }

        try {
            VModel<VRequest> model = VModelController.INSTANCE.getModel(
                    apiPath.detail.getContainer(),
                    apiPath.detail.getGroup(),
                    apiPath.detail.getVersion()
            );
            if (model == null) {
                LOGGER.error(LoggingUtils.buildLog("Model not found", apiPath.detail));
                return;
            }

            VRequest request = model.buildRequest(req, res, apiPath);
            request.doRequest();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
