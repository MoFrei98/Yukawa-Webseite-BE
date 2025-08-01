package com.yukawawebseite.controller.pinboard;

import com.yukawawebseite.models.pinboard.PinboardItemAttachment;
import com.yukawawebseite.services.pinboard.PinboardItemAttatchmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<PinboardItemAttachment> getPinboardItemAttatchmentById(@PathVariable(name = "uuid")String uuid){
        Optional<PinboardItemAttachment> item = pinboardItemAttatchmentService.getPinboardItemAttatchmentById(uuid);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("get/item/{uuid}")
    public List<PinboardItemAttachment> getPinboardItemAttatchmentsByItemId(@PathVariable(name = "uuid") String uuid){
        return pinboardItemAttatchmentService.getPinboardItemAttatchmentsByItemId(uuid);
    }

    @PostMapping("create")
    public PinboardItemAttachment createPinboardItemAttatchment(@RequestBody PinboardItemAttachment attachment){
        return pinboardItemAttatchmentService.savePinboardItemAttatchment(attachment);
    }

    @PutMapping("update")
    public PinboardItemAttachment updatePinboardItemAttatchment(@RequestBody PinboardItemAttachment attachment){
        return pinboardItemAttatchmentService.updatePinboardItemAttatchment(attachment);
    }

    @DeleteMapping("delete/{uuid}")
    public ResponseEntity<String> deletePinBoardItemAttatchment(@PathVariable String uuid){
        boolean deleted = pinboardItemAttatchmentService.deletePinboardItemAttatchment(uuid);
        if (deleted) {
            return ResponseEntity.ok("Pinboard-Item-Attatchment " + uuid + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Fehler: Pinboard-Item-Attatchment " + uuid + " not found.");
        }
    }
}
