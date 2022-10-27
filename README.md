## Micronaut 3.7.2 Issue reproducer

Run the application

    ./gradlew run

I another terminal, access /prometheus endpoint concurrently

    while true; do sleep 1; curl -H "X-Tenant: foo" http://localhost:8080/prometheus; done

     __  __ _                                  _   
    |  \/  (_) ___ _ __ ___  _ __   __ _ _   _| |_
    | |\/| | |/ __| '__/ _ \| '_ \ / _` | | | | __|
    | |  | | | (__| | | (_) | | | | (_| | |_| | |_
    |_|  |_|_|\___|_|  \___/|_| |_|\__,_|\__,_|\__|
    Micronaut (v3.7.2)
    
    17:42:33.884 [main] INFO  i.m.l.PropertiesLoggingLevelsConfigurer - Setting log level 'INFO' for logger: 'io.micronaut.http.client'
    17:42:34.116 [main] INFO  io.micronaut.runtime.Micronaut - Startup completed in 429ms. Server Running: http://localhost:8080
    17:42:50.240 [default-nioEventLoopGroup-1-3] INFO  com.example.TestService - Success
    17:43:20.157 [default-nioEventLoopGroup-1-9] ERROR com.example.TestService - Unexpected header value: foo
