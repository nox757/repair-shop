package ru.chibisov.audit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import ru.chibisov.audit.model.AuditEntity;

@Service
@ConditionalOnExpression("not ${audit.db.enabled:false}")
public class LogAuditServiceImpl implements AuditService {

    private static final Logger log = LoggerFactory.getLogger(AuditLoggingAspectService.class);

    private ObjectMapper mapper;

    public LogAuditServiceImpl(@Qualifier("auditJsonMapper") ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void addAudit(AuditEntity auditEntity) {
        try {
            log.info(mapper.writeValueAsString(auditEntity));
        } catch (JsonProcessingException e) {
            log.debug(e.getMessage());
        }
    }
}
