package com.example.yukawawebseitebe.controller.pinboard;

import com.example.yukawawebseitebe.models.pinboard.PinboardItemAttachment;
import com.example.yukawawebseitebe.services.pinboard.PinboardItemAttatchmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pinboard-item-attatchments")
public class PinboardItemAttatchmentController {

    @Autowired
    private PinboardItemAttatchmentService pinboardItemAttatchmentService;

    @GetMapping("get-all")
    public List<PinboardItemAttachment> getAllPinboardItemAttatchments() {
        return pinboardItemAttatchmentService.getAllPinboardItemAttatchments();
    }

    @GetMapping("get/{uuid}")
    public ResponseEntity<PinboardItemAttachment> getPinboardItemAttatchmentById(String uuid){
        Optional<PinboardItemAttachment> item = pinboardItemAttatchmentService.getPinboardItemAttatchmentById(uuid);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("create")
    public PinboardItemAttachment createPinboardItemAttatchment(@RequestBody PinboardItemAttachment attachment){
        return pinboardItemAttatchmentService.savePinboardItemAttatchment(attachment);
    }

    @PutMapping
    public PinboardItemAttachment updatePinboardItemAttatchment(@RequestBody PinboardItemAttachment attachment){
        return pinboardItemAttatchmentService.updatePinboardItemAttatchment(attachment);
    }
}
