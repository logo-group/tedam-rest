/*
 * Copyright 2014-2019 Logo Business Solutions
 * (a.k.a. LOGO YAZILIM SAN. VE TIC. A.S)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.lbs.tedam.webservice.rest.client;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

public class RestClient {

    private Client buildClient() {
        Client client = ClientBuilder.newClient();
        client.register(com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider.class);
        return client;
    }

    public String getValue(String url, String... parameters) {
        Logger.getLogger(getClass().getName()).info("Url : " + url);
        Client client = buildClient();
        WebTarget target = client.target(url);
        for (String parameter : parameters) {
            target = target.path(parameter);
        }
        Invocation.Builder builderLogin = target.request(MediaType.APPLICATION_JSON);
        String json = builderLogin.get(String.class);
        return json;

    }

    public void postValue(String url, String jsonString) {
        Logger.getLogger(getClass().getName()).info("Url : " + url);
        Client client = buildClient();
        WebTarget target = client.target(url);
        Invocation.Builder builderLogin = target.request(MediaType.APPLICATION_JSON);
        builderLogin.post(Entity.json(jsonString));
    }

}
