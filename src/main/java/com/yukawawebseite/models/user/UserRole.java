package com.yukawawebseite.models.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();;

    @Column(name = "name")
    private String name;

    @Column(name = "can_post")
    private boolean canPost;

    @Column(name = "can_edit")
    private boolean canEdit;

    @Column(name = "can_delete")
    private boolean canDelete;

    @Column(name = "can_view")
    private boolean canView;
}
