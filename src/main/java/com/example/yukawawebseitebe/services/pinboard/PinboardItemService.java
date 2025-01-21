package com.example.yukawawebseitebe.services.pinboard;

import com.example.yukawawebseitebe.models.pinboard.PinboardItem;
import com.example.yukawawebseitebe.repositories.pinboard.PinboardItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PinboardItemService {

    @Autowired
    private PinboardItemRepository pinboardItemRepository;

    public List<PinboardItem> getAllPinboardItems() {
        return pinboardItemRepository.findAll();
    }

    public Optional<PinboardItem> getPinboardItemById(String uuid) {
        return pinboardItemRepository.findById(uuid);
    }

    public PinboardItem savePinboardItem(PinboardItem item) {
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("test");
        return pinboardItemRepository.saveAndFlush(item);
    }

    public PinboardItem updatePinboardItem(PinboardItem item) {
        Optional<PinboardItem> optionalItem = pinboardItemRepository.findById(item.getUuid());
        if (optionalItem.isPresent()) {
            PinboardItem pinboardItem = optionalItem.get();
            pinboardItem.setTitle(item.getTitle());
            pinboardItem.setText(item.getText());
            pinboardItem.setEditedAt(LocalDateTime.now());
            pinboardItem.setEditedBy("test");
            return pinboardItemRepository.saveAndFlush(pinboardItem);
        } else
            return null;
    }

    public void deletePinboardItem(String uuid) {
        pinboardItemRepository.deleteById(uuid);
    }
}
