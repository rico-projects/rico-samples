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
package com.canoo.platform.samples.security;

import com.canoo.platform.client.PlatformClient;
import com.canoo.platform.client.security.Security;
import com.canoo.platform.core.http.HttpClient;

public class Client {

    public static void main(String[] args) throws Exception{
        final HttpClient client = PlatformClient.getService(HttpClient.class);

        final String message = client.get("http://localhost:8080/api/message").
                withoutContent().
                readString().execute().get().getContent();
        System.out.println(message);

        final Security security = PlatformClient.getService(Security.class);
        security.login("admin", "admin").get();

        final String message2 = client.get("http://localhost:8080/api/secure/message").
                withoutContent().
                readString().execute().get().getContent();
        System.out.println(message2);
    }
}
