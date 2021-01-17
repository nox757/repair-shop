package ru.chibisov.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chibisov.model.CreateAtIdentified;
import ru.chibisov.service.impl.RequestServiceImpl;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuditingListener {

    private static final Logger log = LoggerFactory.getLogger(RequestServiceImpl.class.getName());

    @PrePersist
    public void prePersist(CreateAtIdentified createAtIdentified) {
        log.debug("prePersist - created");
        createAtIdentified.setCreatedAt(LocalDateTime.now());
        createAtIdentified.setUpdatedAt(createAtIdentified.getCreatedAt());
    }

    @PreUpdate
    public void preUpdate(CreateAtIdentified createAtIdentified) {
        log.debug("preUpdate - updated");
        createAtIdentified.setUpdatedAt(LocalDateTime.now());
    }

}

