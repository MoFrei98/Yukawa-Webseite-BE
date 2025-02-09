package com.example.yukawawebseitebe.controller.pinboard;

import com.example.yukawawebseitebe.models.pinboard.PinboardItem;
import com.example.yukawawebseitebe.services.pinboard.PinboardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pinboard-items")
public class PinboardItemController {

    @Autowired
    private PinboardItemService pinboardItemService;

    @GetMapping("/get-all")
    public List<PinboardItem> getAllPinboardItems() {
        return pinboardItemService.getAllPinboardItems();
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<PinboardItem> getPinboardItemById(@PathVariable String uuid) {
        Optional<PinboardItem> pinboardItem = pinboardItemService.getPinboardItemById(uuid);
        return pinboardItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public PinboardItem createPinboardItem(@RequestBody PinboardItem pinboardItem) {
        PinboardItem createdPinboardItem = null;
        try {
            createdPinboardItem = pinboardItemService.savePinboardItem(pinboardItem);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return createdPinboardItem;
    }

    @PutMapping("/update")
    public PinboardItem updatePinboardItem(@RequestBody PinboardItem pinboardItem) {
        return pinboardItemService.updatePinboardItem(pinboardItem);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Void> deletePinboardItem(@PathVariable String uuid) {
        pinboardItemService.deletePinboardItem(uuid);
        return ResponseEntity.noContent().build();
    }

}
