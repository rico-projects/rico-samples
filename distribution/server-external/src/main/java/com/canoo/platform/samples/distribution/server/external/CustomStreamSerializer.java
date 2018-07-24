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

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class CustomStreamSerializer implements StreamSerializer<CustomEventFormat> {

    @Override
    public void write(ObjectDataOutput out, CustomEventFormat object) throws IOException {
        final JsonObject root = new JsonObject();
        root.addProperty("shared-event-spec-version", "1.0");
        root.addProperty("data", toBase64(object.getMyMessage()));
        final JsonObject context = new JsonObject();
        context.addProperty("timestamp", System.currentTimeMillis());
        context.addProperty("topic", object.getTopic());
        context.add("metadata", new JsonArray());
        root.add("context", context);
        final String json = new GsonBuilder().serializeNulls().create().toJson(root);
        out.writeUTF(json);
    }

    @Override
    public CustomEventFormat read(ObjectDataInput in) throws IOException {
        final JsonElement element = new JsonParser().parse(in.readUTF());
        try {
            return new CustomEventFormat(fromBase64(element.getAsJsonObject().get("data").getAsString()), element.getAsJsonObject().get("context").getAsJsonObject().get("topic").getAsString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error", e);
        }
    }

    @Override
    public int getTypeId() {
        return 4711;
    }

    @Override
    public void destroy() {
    }

    private String toBase64(final String data) throws IOException {
        final ByteArrayOutputStream rawOutputStream = new ByteArrayOutputStream();
        final ObjectOutputStream dataOutputStream = new ObjectOutputStream(rawOutputStream);
        dataOutputStream.writeObject(data);
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(rawOutputStream.toByteArray());
    }

    private String fromBase64(final String data) throws IOException, ClassNotFoundException {
        final Base64.Decoder decoder = Base64.getDecoder();
        final byte[] raw = decoder.decode(data);
        final ByteArrayInputStream rawInputStream = new ByteArrayInputStream(raw);
        final ObjectInputStream dataInputStream = new ObjectInputStream(rawInputStream);
        return (String) dataInputStream.readObject();
    }
}
