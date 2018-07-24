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

import com.canoo.platform.remoting.RemotingBean;
import com.canoo.platform.remoting.Property;

@RemotingBean
public class UserBean {

    private Property<String> name;

    private Property<String> firstName;

    private Property<String> lastName;

    private Property<String> mail;

    public String getName() {
        return name.get();
    }

    public Property<String> nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public Property<String> firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public Property<String> lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getMail() {
        return mail.get();
    }

    public Property<String> mailProperty() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }
}
