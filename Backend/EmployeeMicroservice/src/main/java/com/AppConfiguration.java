package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public EmployeeDataService createDataService(){
        EmployeeDataService eda = new EmployeeDataService();
        return eda;
    }
}
