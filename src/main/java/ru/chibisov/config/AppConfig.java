package ru.chibisov.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ru.chibisov")
@PropertySource("classpath:application.properties")
public class AppConfig {

}
