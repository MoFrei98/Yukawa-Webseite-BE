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

    @GetMapping("get-all")
    public List<PinboardItem> getAllPinboardItems() {
        return pinboardItemService.getAllPinboardItems();
    }

    @GetMapping("get/{uuid}")
    public ResponseEntity<PinboardItem> getPinboardItemById(String uuid){
        Optional<PinboardItem> item = pinboardItemService.getPinboardItemById(uuid);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("create")
    public PinboardItem createPinboardItem(@RequestBody PinboardItem item){
        return pinboardItemService.savePinboardItem(item);
    }

    @PutMapping("update")
    public PinboardItem updatePinboardItem(@RequestBody PinboardItem item){
        return pinboardItemService.updatePinboardItem(item);
    }

    @DeleteMapping("delete/{uuid}")
    public ResponseEntity<Void> deletePinboardItem(@PathVariable String uuid){
        pinboardItemService.deletePinboardItem(uuid);
        return ResponseEntity.noContent().build();
    }

}
