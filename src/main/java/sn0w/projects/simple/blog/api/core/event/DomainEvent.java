package sn0w.projects.simple.blog.api.core.event;

import java.time.OffsetDateTime;

public interface DomainEvent {
    String getEventType();
    OffsetDateTime occurredAt();
}
