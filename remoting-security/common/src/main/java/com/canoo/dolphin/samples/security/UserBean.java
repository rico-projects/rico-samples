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

import com.canoo.platform.remoting.Property;
import com.canoo.platform.remoting.RemotingBean;

@RemotingBean
public class UserBean {

    private Property<String> userName;

    private Property<String> mailAddress;

    public String getUserName() {
        return userName.get();
    }

    public Property<String> userNameProperty() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName.set(userName);
    }

    public String getMailAddress() {
        return mailAddress.get();
    }

    public Property<String> mailAddressProperty() {
        return mailAddress;
    }

    public void setMailAddress(final String mailAddress) {
        this.mailAddress.set(mailAddress);
    }
}
