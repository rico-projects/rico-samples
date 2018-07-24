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
package com.canoo.platform.samples.microservices.user;

import com.canoo.platform.remoting.server.RemotingAction;
import com.canoo.platform.remoting.server.RemotingController;
import com.canoo.platform.remoting.server.RemotingModel;

import static com.canoo.platform.samples.microservices.user.UserConstants.USER_CONTROLLER_NAME;
import static com.canoo.platform.samples.microservices.user.UserConstants.USER_REFRESH_ACTION;

@RemotingController(USER_CONTROLLER_NAME)
public class UserController {
    private static int counter;

    @RemotingModel
    private UserBean model;

    @RemotingAction(USER_REFRESH_ACTION)
    public void refresh() {
        model.setName("john0815 " + ++counter);
    }
}
