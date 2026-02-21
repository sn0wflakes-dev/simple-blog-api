package sn0w.projects.simple.blog.api.core.domain.tag;

public class Tag {
    private final TagId tagId;
    private final TagName tagName;

    public Tag(TagId id, TagName name) {
        this.tagId = id;
        this.tagName = name;
    }

    public static Tag create(String name) {
        return new Tag(
                TagId.generate(),
                TagName.of(name)
        );
    }

    public static Tag of(String id, String name) {
        return new Tag(
                TagId.of(id),
                TagName.of(name)
        );
    }

    public TagId getTagId() {
        return tagId;
    }

    public TagName getTagName() {
        return tagName;
    }
}
