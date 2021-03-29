package com.vgu.cs.ma.api.model;

/*
 * Copyright (c) 2012-2016 by Zalo Group.
 * All Rights Reserved.
 *
 * @author namnh16 on 28/03/2021
 */

import com.vgu.cs.common.util.ServletUtils;
import com.vgu.cs.common.util.StringUtils;
import com.vgu.cs.common.web.VModel;
import com.vgu.cs.common.web.VPath;
import com.vgu.cs.common.web.VResponse;
import com.vgu.cs.common.web.thrift.TStatusCode;
import com.vgu.cs.common.wrapper.JsonObject;
import com.vgu.cs.ma.api.request.WelcomeRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeModel extends VModel<WelcomeRequest> {

    public static final WelcomeModel INSTANCE = new WelcomeModel();

    private WelcomeModel() {
        super(WelcomeRequest.class, "welcome", "core", "0");
    }

    @Override
    public WelcomeRequest buildRequest(HttpServletRequest req, HttpServletResponse res, VPath path) {
        return new WelcomeRequest(req, res, path, this);
    }

    public VResponse getWelcomeMessage(WelcomeRequest req) {
        String message, name;
        if (StringUtils.isNullOrEmpty(name = ServletUtils.getString(req.getHRequest(), "name"))) {
            message = "Meep-morp, zeep. Robot Captain engaged!";
        } else {
            message = "Willkommen, " + name;
        }
        JsonObject ret = new JsonObject().put("message", message);

        return new VResponse(TStatusCode.SUCCESS, ret);
    }
}
