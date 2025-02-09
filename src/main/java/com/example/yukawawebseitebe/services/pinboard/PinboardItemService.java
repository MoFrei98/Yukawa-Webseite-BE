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

import java.time.LocalDate;
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

        }
        return pinboardItemRepository.saveAndFlush(pinboardItem);
        } else
            return null;
    }

    public boolean deletePinboardItem(String uuid) {
        pinboardItemRepository.deleteById(uuid);
        return pinboardItemRepository.findById(uuid).isEmpty();
    }
}
