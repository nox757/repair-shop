package ru.chibisov.audit.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditConfig {

    @Bean
    public ObjectMapper auditJsonMapper() {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.WRAP_ROOT_VALUE)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        return mapper;
    }
}
