package com.yukawawebseite.controller.pinboard;

import com.yukawawebseite.models.pinboard.PinboardItem;
import com.yukawawebseite.models.pinboard.PinboardItemAttachment;
import com.yukawawebseite.services.pinboard.PinboardItemAttatchmentService;
import com.yukawawebseite.services.pinboard.PinboardItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/pinboard-items")
public class PinboardItemController {

    @Autowired
    private PinboardItemService pinboardItemService;

    @Autowired
    private PinboardItemAttatchmentService pinboardItemAttatchmentService;

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
    public PinboardItem createPinboardItem(@RequestBody PinboardItem item){
        return pinboardItemService.savePinboardItem(item);
    }

    @PutMapping("update")
    public PinboardItem updatePinboardItem(@RequestBody PinboardItem item){
        return pinboardItemService.updatePinboardItem(item);
    }

    @DeleteMapping("delete/{uuid}")
    public ResponseEntity<String> deletePinboardItem(@PathVariable String uuid) {
        boolean deleted = false;
        List<PinboardItemAttachment> attachments = pinboardItemAttatchmentService.getAllPinboardItemAttatchments();
        deleted = pinboardItemService.deletePinboardItem(uuid);
        if (deleted) {
            return ResponseEntity.ok("Pinboard-Item " + uuid + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Fehler: Pinboard-Item " + uuid + " not found.");
        }
    }


}
