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

import com.canoo.platform.server.security.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/api/secure/message")
public class SecureRestEndpoint {

    private final static Logger LOG = LoggerFactory.getLogger(SecureRestEndpoint.class);

    @Autowired
    private SecurityContext securityContext;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.POST)
    public String convert(String message) {
        final String userName = Optional.ofNullable(securityContext.getUser()).
                map(u -> u.getName()).
                orElse("UNKNOWN");
        LOG.info("Secure endpoint called by {}", userName);
        return userName.toUpperCase();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getSecureMessage() {
        final String userName = Optional.ofNullable(securityContext.getUser()).
                map(u -> u.getName()).
                orElse("UNKNOWN");
        final String convertedUser = restTemplate.postForEntity("http://localhost:8080/api/secure/message", userName, String.class).getBody();
        LOG.info("Secure endpoint called by {}", userName);
        return "A secure message that was requested by " + convertedUser;
    }
}
