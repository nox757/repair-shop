package ru.chibisov.shedule;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.chibisov.service.RequestService;

import java.time.LocalDateTime;

@Component
@ConditionalOnProperty(prefix = "scheduling.cron", name = {"enabled"}, matchIfMissing = false)
public class ArchiveRequestSchedule {

    private RequestService requestService;

    public ArchiveRequestSchedule(RequestService requestService) {
        this.requestService = requestService;
    }

    @Scheduled(cron = "${scheduling.cron.expression}")
    public void archiveRequestSchedule() {
        requestService.archiveRequestByUpdatedTime(LocalDateTime.now().minusMonths(12));
    }

}
