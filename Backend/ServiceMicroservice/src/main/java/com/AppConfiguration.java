package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public ServiceDataService createDataService(){
        ServiceDataService sbds = new ServiceDataService();
        return sbds;
    }
}
