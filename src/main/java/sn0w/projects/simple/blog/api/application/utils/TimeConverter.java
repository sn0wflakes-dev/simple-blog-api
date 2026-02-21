package sn0w.projects.simple.blog.api.application.utils;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class TimeConverter {
    public static OffsetDateTime offsiteConverter(Instant instant) {
        return instant.atOffset(ZoneOffset.UTC);
    }
}
