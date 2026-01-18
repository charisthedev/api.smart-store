package com.charis.api.e_commerce.config;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.IOException;
import java.util.Map;

@Configuration
@Slf4j
public class GrpcServerConfig implements ApplicationListener<ContextRefreshedEvent>, DisposableBean {

    @Value("${grpc.server.port:9090}")
    private int port;

    private Server server;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();

        // Since BindableService is an interface, let's find beans by type
        Map<String, BindableService> grpcServices = applicationContext.getBeansOfType(BindableService.class);

        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);
        for (BindableService service : grpcServices.values()) {
            serverBuilder.addService(service);
            log.info("Registered gRPC service: {}", service.getClass().getName());
        }

        server = serverBuilder.build();
        try {
            server.start();
            log.info("gRPC Server started on port {}", port);
        } catch (IOException e) {
            log.error("Failed to start gRPC server", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        if (server != null) {
            log.info("Shutting down gRPC server");
            server.shutdown();
        }
    }
}
