package com.vgu.cs.ma.api.model;

/*
 * Copyright (c) 2012-2016 by Zalo Group.
 * All Rights Reserved.
 *
 * @author namnh16 on 28/03/2021
 */

import com.vgu.cs.common.web.VModel;
import com.vgu.cs.common.web.VPath;
import com.vgu.cs.common.web.VResponse;
import com.vgu.cs.common.web.thrift.TStatusCode;
import com.vgu.cs.ma.api.request.WelcomeRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeModel extends VModel<WelcomeRequest> {
    public WelcomeModel(Class<WelcomeRequest> clazz, String container, String group) {
        super(clazz, container, group);
    }

    @Override
    public WelcomeRequest buildRequest(HttpServletRequest req, HttpServletResponse res, VPath path) {
        return new WelcomeRequest(req, res, path, this);
    }

    public VResponse welcome(WelcomeRequest req){
        return new VResponse(TStatusCode.SUCCESS, "Wilkommen");
    }
}
