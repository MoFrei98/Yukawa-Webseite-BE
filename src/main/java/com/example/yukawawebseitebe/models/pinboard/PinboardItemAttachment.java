package com.example.yukawawebseitebe.models.pinboard;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pinboard_item_attatchments")
public class PinboardItemAttachment {
    @Id
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "pinboard_item_uuid", referencedColumnName = "uuid")
    private PinboardItem pinboardItem;

    @Column(name = "type")
    private PinboardItemAttachmentType type;

    @Column(name = "path")
    private String path;
}
