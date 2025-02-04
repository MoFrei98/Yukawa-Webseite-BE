package com.example.yukawawebseitebe.repositories.pinboard;

import com.example.yukawawebseitebe.models.pinboard.PinboardItemAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PinboardItemAttatchmentRepository extends JpaRepository<PinboardItemAttachment, String> {
    @Query("SELECT p FROM PinboardItemAttachment p WHERE p.pinboardItem.uuid = ?1")
    List<PinboardItemAttachment> findByPinboardItemUuid(String uuid);
}
