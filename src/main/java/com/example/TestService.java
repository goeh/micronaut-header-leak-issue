package com.example;

import io.micronaut.http.MediaType;
import io.micronaut.http.client.multipart.MultipartBody;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.scheduler.Schedulers;

import java.nio.charset.StandardCharsets;

@Singleton
public class TestService {

    private static final Logger log = LoggerFactory.getLogger(TestService.class);

    private static final String TENANT = "test";

    private final TestClient client;

    public TestService(TestClient client) {
        this.client = client;
    }

    @Scheduled(initialDelay = "15s", fixedDelay = "15s")
    public void clientRequest() {
        // Get data from remote service
        client.getData(TENANT)
                // Process data and upload to remote service
                .flatMap(data -> client.uploadResult(TENANT, createMultiPartBody(data.toString())))
                .doOnSuccess(response -> {
                    Object value = response.getHeaders().get("X-Tenant");
                    if (TENANT.equals(value)) {
                        log.info("Success");
                    } else {
                        log.error("Unexpected header value: {}", value);
                    }
                })
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
    }

    private MultipartBody createMultiPartBody(String text) {
        return MultipartBody.builder()
                .addPart("result", "data.txt", MediaType.TEXT_PLAIN_TYPE, text.getBytes(StandardCharsets.UTF_8))
                .build();
    }
}
