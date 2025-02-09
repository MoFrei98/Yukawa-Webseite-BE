package com.example.yukawawebseitebe.models.pinboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "pinboard_item_attatchments")
public class PinboardItemAttachment {
    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "pinboard_item_uuid", referencedColumnName = "uuid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private PinboardItem pinboardItem;

    @Column(name = "file_type")
    @Enumerated(EnumType.STRING)
    private PinboardItemAttachmentType fileType;

    @Column(name = "path_type")
    @Enumerated(EnumType.STRING)
    private PinboardItemAttachmentPathType pathType;

    @Column(name = "path")
    private String path;
}
