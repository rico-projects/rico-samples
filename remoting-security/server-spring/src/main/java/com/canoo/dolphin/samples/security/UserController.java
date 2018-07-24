/*
 * Copyright 2015-2018 Canoo Engineering AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.canoo.dolphin.samples.security;

import com.canoo.platform.remoting.server.RemotingController;
import com.canoo.platform.remoting.server.RemotingModel;
import com.canoo.platform.server.security.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static com.canoo.dolphin.samples.security.Constants.USER_CONTROLLER;

@RemotingController(USER_CONTROLLER)
public class UserController {

    @RemotingModel
    private UserBean model;

    @Autowired
    private SecurityContext securityContext;

    @PostConstruct
    public void init() {
        model.setUserName(securityContext.getUser().getUserName());
        model.setMailAddress(securityContext.getUser().getEmail());
    }
}
