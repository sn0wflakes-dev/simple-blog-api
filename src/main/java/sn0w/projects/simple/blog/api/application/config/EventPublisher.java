package sn0w.projects.simple.blog.api.application.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import sn0w.projects.simple.blog.api.core.event.DomainEvent;

@Component
public class EventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
