package com.junior.modules;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplatGet {
    private static RestTemplate restTemplate;

    private RestTemplatGet() {
    }

    public static RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpComponentsClientHttpRequestFactory httpRequestFactory =
                    new HttpComponentsClientHttpRequestFactory(httpClient);
            httpRequestFactory.setConnectTimeout(100);
            return restTemplate = new RestTemplate(httpRequestFactory);
        }
        return restTemplate;
    }
}
