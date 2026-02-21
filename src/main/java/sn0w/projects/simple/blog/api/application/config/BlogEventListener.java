package sn0w.projects.simple.blog.api.application.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sn0w.projects.simple.blog.api.application.event.BlogEventSyncService;
import sn0w.projects.simple.blog.api.core.event.BlogCreateEvent;

@Component
@Slf4j
public class BlogEventListener {

    private final BlogEventSyncService eventSyncService;

    public BlogEventListener(BlogEventSyncService eventSyncService) {
        this.eventSyncService = eventSyncService;
    }

    @Async
    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void blogCreationHandler(BlogCreateEvent event) {
        log.info("Sync blog to Mongodb");
        try {
            eventSyncService.syncBlogCreation(event);
        } catch (Exception e) {
            log.error("Error : {}", e.toString());
        }
    }


}
