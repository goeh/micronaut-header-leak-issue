package com.example;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.multipart.MultipartBody;
import reactor.core.publisher.Mono;

@Client("https://httpbin.org")
public interface TestClient {
    @Get("/headers")
    Mono<TestResponse> getData(@Header("X-Tenant") String tenant);

    @Post(value = "/anything", produces = MediaType.MULTIPART_FORM_DATA)
    Mono<TestResponse> uploadResult(@Header("X-Tenant") String tenant, @Body MultipartBody body);
}
