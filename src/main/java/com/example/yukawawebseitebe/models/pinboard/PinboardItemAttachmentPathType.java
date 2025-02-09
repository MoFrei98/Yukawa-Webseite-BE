package com.example.yukawawebseitebe.models.pinboard;

public enum PinboardItemAttachmentPathType {
    URL("Image"),
    FILE_PATH("File Path");;

    private final String typeName;

    PinboardItemAttachmentPathType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
