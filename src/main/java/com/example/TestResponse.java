package com.example;

import io.micronaut.core.annotation.Introspected;

import java.util.Map;

@Introspected
public class TestResponse {
    private Map<String, Object> headers;

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }
}
