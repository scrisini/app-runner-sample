package com.example.restservice;

import java.io.File;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {

    public static void main(String[] args) {
    	
        Map<String, String> settings = System.getenv();
        String env = settings.getOrDefault("APP_ENV", "local"); // "prod" or "local"
        boolean isLocal = "local".equals(env);
        File dataDir = new File(settings.getOrDefault("APP_DATA", "target/data"));
        File tempDir = new File(settings.getOrDefault("TEMP", "target/temp"));

        // When run from app-runner, you must use the port set in the environment variable APP_PORT
        int port = Integer.parseInt(settings.getOrDefault("APP_PORT", "8081"));
        System.setProperty("server.port", Integer.toString(port));
//        //server:
  //      port: 8327

        // All URLs must be prefixed with the app name, which is got via the APP_NAME env var.
        String appName = settings.getOrDefault("APP_NAME", "mds-api");
//        server:
  //      port: 8700

    //    servlet:
      //  context-path: /config
        System.setProperty("server.servlet.context-path", "/"+appName);

        SpringApplication.run(RestServiceApplication.class, args);
    }

}
