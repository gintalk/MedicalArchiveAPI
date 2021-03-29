package com.vgu.cs.ma.api.handler;

/*
 * Copyright (c) 2012-2016 by Zalo Group.
 * All Rights Reserved.
 *
 * @author namnh16 on 26/03/2021
 */

import com.vgu.cs.common.logger.VLogger;
import com.vgu.cs.common.util.LoggingUtils;
import com.vgu.cs.common.web.IRequest;
import com.vgu.cs.common.web.VApiHandler;
import com.vgu.cs.common.web.VModel;
import com.vgu.cs.common.web.VPath;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ApiHandler extends VApiHandler {

    private static final Logger LOGGER = VLogger.getLogger(ApiHandler.class);
    private static final Map<String, VModel<IRequest>> MODEL_MAP = new HashMap<>();

    public static synchronized void registerModel(String container, String group, String version, VModel<IRequest> model) {
        MODEL_MAP.put(_getModelName(container, group, version), model);
    }

    private static String _getModelName(String container, String group, String version) {
        return container + "." + group + "." + version;
    }

    @Override
    protected void doHandle(HttpServletRequest req, HttpServletResponse res) {
        String path = req.getPathInfo();
        VPath apiPath = new VPath(path);
        if (!apiPath.isValid()) {
            LOGGER.error(LoggingUtils.buildLog("Invalid API path", apiPath));
            return;
        }

        try {
            String modelName = _getModelName(apiPath.detail.getContainer(), apiPath.detail.getGroup(), apiPath.detail.getVersion());
            VModel<IRequest> model = MODEL_MAP.get(modelName);
            if (model == null) {
                LOGGER.error(LoggingUtils.buildLog("Model not found", modelName));
                return;
            }

            IRequest request = model.buildRequest(req, res, apiPath);
            request.doRequest();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
