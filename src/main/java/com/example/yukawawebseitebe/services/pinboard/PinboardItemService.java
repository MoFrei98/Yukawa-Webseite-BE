package com.example.yukawawebseitebe.services.pinboard;

import com.example.yukawawebseitebe.models.pinboard.PinboardItem;
import com.example.yukawawebseitebe.models.pinboard.PinboardItemAttachment;
import com.example.yukawawebseitebe.repositories.pinboard.PinboardItemAttachmentRepository;
import com.example.yukawawebseitebe.repositories.pinboard.PinboardItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PinboardItemService {

    @Autowired
    private PinboardItemRepository pinboardItemRepository;

    @Autowired
    private PinboardItemAttachmentRepository pinboardItemAttachmentRepository;

    public List<PinboardItem> getAllPinboardItems() {
        return pinboardItemRepository.findAll();
    }

    public Optional<PinboardItem> getPinboardItemById(String uuid) {
        return pinboardItemRepository.findById(uuid);
    }

    public PinboardItem savePinboardItem(PinboardItem pinboardItem) {
        pinboardItem.setCreatedAt(LocalDateTime.now());
        pinboardItem.setCreatedBy(getCurrentUser());
        for (PinboardItemAttachment attachment : pinboardItem.getAttachments()) {
            attachment.setPinboardItem(pinboardItem);
            pinboardItemAttachmentRepository.saveAndFlush(attachment);
        }
        return pinboardItemRepository.saveAndFlush(pinboardItem);
    }

    public PinboardItem updatePinboardItem(PinboardItem pinboardItem) {
        Optional<PinboardItem> optionalPinboardItem = pinboardItemRepository.findById(pinboardItem.getUuid());
        if (optionalPinboardItem.isPresent()) {
            PinboardItem existingItem = optionalPinboardItem.get();
            existingItem.setTitle(pinboardItem.getTitle());
            existingItem.setText(pinboardItem.getText());
            existingItem.setEditedAt(LocalDateTime.now());
            existingItem.setEditedBy(getCurrentUser());

            // Aktualisiere Anhänge
            existingItem.getAttachments().clear();
            pinboardItem.getAttachments().forEach(attachment -> {
                attachment.setPinboardItem(existingItem);
                existingItem.getAttachments().add(attachment);
                pinboardItemAttachmentRepository.saveAndFlush(attachment);
            });

            return pinboardItemRepository.saveAndFlush(existingItem);
        } else
            throw new EntityNotFoundException("Pinboard item not found with uuid: " + pinboardItem.getUuid());
    }

    public void deletePinboardItem(String uuid) {
        pinboardItemRepository.deleteById(uuid);
    }

    private String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : "unknown";
    }
}

