/*
 * Copyright 2014-2017 the original author or authors.
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
package de.codecentric.boot.admin.server.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a certain status a certain time.
 *
 * @author Johannes Stelzer
 */
@lombok.Data
public class StatusInfo implements Serializable {
    private static final long serialVersionUID = 3L;
    private final String status;
    private final Map<String, Serializable> details;

    private StatusInfo(String status, Map<String, ? extends Serializable> details) {
        this.status = status.toUpperCase();
        this.details = details != null ? Collections.unmodifiableMap(new HashMap<>(details)) : Collections.emptyMap();
    }

    public static StatusInfo valueOf(String statusCode, Map<String, ? extends Serializable> details) {
        return new StatusInfo(statusCode, details);
    }

    public static StatusInfo valueOf(String statusCode) {
        return valueOf(statusCode, null);
    }

    public static StatusInfo ofUnknown() {
        return valueOf("UNKNOWN", null);
    }

    public static StatusInfo ofUp() {
        return ofUp(null);
    }

    public static StatusInfo ofDown() {
        return ofDown(null);
    }

    public static StatusInfo ofOffline() {
        return ofOffline(null);
    }

    public static StatusInfo ofUp(Map<String, ? extends Serializable> details) {
        return valueOf("UP", details);
    }

    public static StatusInfo ofDown(Map<String, ? extends Serializable> details) {
        return valueOf("DOWN", details);
    }

    public static StatusInfo ofOffline(Map<String, ? extends Serializable> details) {
        return valueOf("OFFLINE", details);
    }

    @JsonIgnore
    public boolean isUp() {
        return "UP".equals(status);
    }

    @JsonIgnore
    public boolean isOffline() {
        return "OFFLINE".equals(status);
    }

    @JsonIgnore
    public boolean isDown() {
        return "DOWN".equals(status);
    }

    @JsonIgnore
    public boolean isUnknown() {
        return "UNKNOWN".equals(status);
    }

}