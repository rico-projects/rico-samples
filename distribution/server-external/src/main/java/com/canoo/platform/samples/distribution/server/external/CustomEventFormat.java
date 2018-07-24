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
package com.canoo.platform.samples.distribution.server.external;

public class CustomEventFormat {

    private final String myMessage;

    private final String topic;

    public CustomEventFormat(final String myMessage, final String topic) {
        this.myMessage = myMessage;
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public String getMyMessage() {
        return myMessage;
    }

}
