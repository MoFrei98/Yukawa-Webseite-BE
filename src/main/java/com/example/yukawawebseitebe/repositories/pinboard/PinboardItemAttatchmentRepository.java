package com.example.yukawawebseitebe.repositories.pinboard;

import com.example.yukawawebseitebe.models.pinboard.PinboardItemAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinboardItemAttatchmentRepository extends JpaRepository<PinboardItemAttachment, String> {
}
