package ru.chibisov.audit.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.chibisov.audit.model.AuditEntity;
import ru.chibisov.audit.repository.AuditRepository;

@Service
@ConditionalOnProperty(prefix = "audit.db", name = {"enabled"}, havingValue = "true", matchIfMissing = false)
public class DbAuditServiceImpl implements AuditService {

    private AuditRepository auditRepository;

    public DbAuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public void addAudit(AuditEntity auditEntity) {
        auditRepository.save(auditEntity);
    }
}
