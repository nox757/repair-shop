package ru.chibisov.audit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.chibisov.audit.annotation.Audit;
import ru.chibisov.audit.model.AuditEntity;
import ru.chibisov.audit.model.EventAudit;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.UUID;

@Aspect
@Component
public class AuditLoggingAspectService {

    private static final Logger log = LoggerFactory.getLogger(AuditLoggingAspectService.class.getName());
    private AuditService auditService;
    private ObjectMapper mapper;

    public AuditLoggingAspectService(AuditService auditService, ObjectMapper mapper) {
        this.auditService = auditService;
        this.mapper = mapper;
    }

    @Pointcut("@annotation(ru.chibisov.audit.annotation.Audit) && execution(public * *(..))")
    public void publicAspectMethod() {
    }

    @Around("publicAspectMethod()")
    public Object logAudit(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Audit annotation = method.getAnnotation(Audit.class);
        Object[] args = joinPoint.getArgs();
        AuditEntity auditEntity = new AuditEntity()
                .setEventId(UUID.randomUUID())
                .setAuditCode(annotation.value().name())
                .setEvent(EventAudit.START)
                .setTimeStart(LocalDateTime.now())
                .setParams(mapToJsonString(args));
        auditService.addAudit(auditEntity);
        Object proceed;
        try {
            proceed = joinPoint.proceed();
            auditEntity.setTimeEnd(LocalDateTime.now())
                    .setEvent(EventAudit.SUCCESS)
                    .setReturnValue(mapToJsonString(proceed));

        } catch (Throwable ex) {
            auditEntity.setTimeEnd(LocalDateTime.now())
                    .setEvent(EventAudit.FAILURE)
                    .setReturnValue(mapToJsonString(ex.getMessage()));
            throw ex;
        } finally {
            auditService.addAudit(auditEntity);
        }
        return proceed;
    }

    private String mapToJsonString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.debug(e.getMessage());
        }
        return String.format("Error parse to json object - %s", obj.toString());
    }

}
