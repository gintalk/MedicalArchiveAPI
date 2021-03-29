package com.vgu.cs.ma.api.request;

/*
 * Copyright (c) 2012-2016 by Zalo Group.
 * All Rights Reserved.
 *
 * @author namnh16 on 28/03/2021
 */

import com.vgu.cs.common.web.IRequest;
import com.vgu.cs.common.web.VPath;
import com.vgu.cs.common.web.VRequest;
import com.vgu.cs.ma.api.model.WelcomeModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeRequest extends VRequest implements IRequest {

    public WelcomeRequest(HttpServletRequest req, HttpServletResponse res, VPath path, WelcomeModel model) {
        super(req, res, path, model);
    }
}
