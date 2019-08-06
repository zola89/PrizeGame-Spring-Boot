package com.lazar.prizegame.utils.headers;

import org.springframework.http.HttpHeaders;

public final class HeadersHelper {

    public static HttpHeaders add(String headerName, String headerValue) {
        return add(headerName, headerValue, null);
    }

    public static HttpHeaders add(String headerName, String headerValue, HttpHeaders headers) {

        headers = headers != null ? headers : new HttpHeaders();

        headers.set(headerName, headerValue);

        return headers;
    }

}