package com.yukawawebseite.models.pinboard;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "pinboard_item")
public class PinboardItem {
    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "edited_at")
    private LocalDateTime editedAt;

    @Column(name = "edited_by")
    private String editedBy;

    @OneToMany(mappedBy = "pinboardItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PinboardItemAttachment> attachments = new ArrayList<>();
}
