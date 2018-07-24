package com.guigarage.platform.sample;


import com.canoo.platform.remoting.server.spring.EnableRemoting;
import com.canoo.platform.server.spring.EnableDolphinPlatform;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDolphinPlatform
@EnableRemoting
public class ServerApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Class[]{ServerApplication.class}, args);
    }
}

