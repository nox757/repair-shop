package ru.chibisov.audit.service;

import ru.chibisov.audit.model.AuditEntity;

/**
 * Интрефейс взаимодействия с логированием аудита
 */
interface AuditService {

    /**
     * Сохраняет событие аудита
     * @param auditEntity сохраненное собатие аудита
     */
    void addAudit(AuditEntity auditEntity);
}
