package com.example.yukawawebseitebe.repositories.pinboard;

import com.example.yukawawebseitebe.models.pinboard.PinboardItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinboardItemRepository extends JpaRepository<PinboardItem, String> {
}
