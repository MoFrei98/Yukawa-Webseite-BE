package com.yukawawebseite.repositories.pinboard;

import com.yukawawebseite.models.pinboard.PinboardItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinboardItemRepository extends JpaRepository<PinboardItem, String> {
}
