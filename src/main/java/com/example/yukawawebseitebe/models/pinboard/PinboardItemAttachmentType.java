package com.example.yukawawebseitebe.models.pinboard;

public enum PinboardItemAttachmentType {
    IMAGE("Image"),
    VIDEO("Video"),
    LINK("Link");

    private final String typeName;

    PinboardItemAttachmentType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
