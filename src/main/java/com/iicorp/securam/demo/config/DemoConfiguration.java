package com.iicorp.securam.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfiguration
{
    @Bean
    DefaultSecrets getControllerConfiguration() { return new DefaultSecrets(); }
}
