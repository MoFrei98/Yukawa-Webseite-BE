package com.example.yukawawebseitebe.services.pinboard;

import com.example.yukawawebseitebe.models.pinboard.PinboardItemAttachment;
import com.example.yukawawebseitebe.repositories.pinboard.PinboardItemAttatchmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PinboardItemAttatchmentService {

    @Autowired
    private PinboardItemAttatchmentRepository pinboardItemAttatchmentRepository;

    public List<PinboardItemAttachment> getAllPinboardItemAttatchments() {
        return pinboardItemAttatchmentRepository.findAll();
    }

    public Optional<PinboardItemAttachment> getPinboardItemAttatchmentById(String uuid) {
        return pinboardItemAttatchmentRepository.findById(uuid);
    }

    public PinboardItemAttachment savePinboardItemAttatchment(PinboardItemAttachment attachment) {
        return pinboardItemAttatchmentRepository.saveAndFlush(attachment);
    }

    public PinboardItemAttachment updatePinboardItemAttatchment(PinboardItemAttachment attachment) {
        Optional<PinboardItemAttachment> optionalAttachment = pinboardItemAttatchmentRepository.findById(attachment.getUuid());
        if (optionalAttachment.isPresent()) {
            PinboardItemAttachment pinboardItemAttachment = optionalAttachment.get();
            pinboardItemAttachment.setPath(attachment.getPath());
            pinboardItemAttachment.setPathType(attachment.getPathType());
            pinboardItemAttachment.setFileType(attachment.getFileType());
            pinboardItemAttachment.setPinboardItem(attachment.getPinboardItem());
            return pinboardItemAttatchmentRepository.saveAndFlush(pinboardItemAttachment);
        } else
            return null;
    }

    public boolean deletePinboardItemAttatchment(String uuid) {
        pinboardItemAttatchmentRepository.findById(uuid);
        return pinboardItemAttatchmentRepository.findById(uuid).isEmpty();
    }

    public List<PinboardItemAttachment> getPinboardItemAttatchmentsByItemId(String uuid) {
        return pinboardItemAttatchmentRepository.findByPinboardItemUuid(uuid);
    }
}
