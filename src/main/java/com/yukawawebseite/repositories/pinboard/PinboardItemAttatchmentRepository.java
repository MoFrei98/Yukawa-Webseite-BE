package com.yukawawebseite.repositories.pinboard;

import com.yukawawebseite.models.pinboard.PinboardItemAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PinboardItemAttatchmentRepository extends JpaRepository<PinboardItemAttachment, String> {
    @Query("SELECT p FROM PinboardItemAttachment p WHERE p.pinboardItem.uuid = ?1")
    List<PinboardItemAttachment> findByPinboardItemUuid(String uuid);
}
