package com.mai.pilot_assistent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.yml")
public class CloudFileConfig {
    @Autowired
    Environment mEnv;

    @Bean(name = "cloudinary.url")
    public String getCloudinaryUrl() {
        return mEnv.getProperty("cloudinary.url");
    }
}
