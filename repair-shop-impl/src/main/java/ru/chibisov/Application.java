package ru.chibisov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.chibisov.service.impl.RequestServiceImpl;

@PropertySource("classpath:server.properties")
@EnableScheduling
@EnableTransactionManagement
@EnableAspectJAutoProxy
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ru.chibisov.repository", "ru.chibisov.audit.repository"})
public class Application {

    private static final Logger log = LoggerFactory.getLogger(RequestServiceImpl.class.getName());

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(Application.class, args);
        BuildProperties buildProperties = application.getBean(BuildProperties.class);
        ConfigurableEnvironment env = application.getEnvironment();
        StringBuilder sb = new StringBuilder("\n----------------------------------------------------------\n\t");
        sb.append("Application: ").append(env.getProperty("spring.application.name")).append("\n\t");
        sb.append("Build version is ").append(buildProperties.getVersion()).append("\n\t");
        sb.append("Profile(s): ");
        sb.append(String.join(", ", env.getActiveProfiles()));
        sb.append(" \n").append("----------------------------------------------------------");
        log.info(sb.toString());
    }
}


