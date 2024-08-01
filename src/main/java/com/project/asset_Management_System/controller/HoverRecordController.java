package com.project.asset_Management_System.controller;

import com.project.asset_Management_System.model.HoverRecord;
import com.project.asset_Management_System.service.HoverRecordServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HoverRecordController {

    @Autowired
    HoverRecordServiceImpl hoverRecordServiceImpl;

    @GetMapping("/hover-records")
    public List<HoverRecord> getAllHoverRecords() {
        return hoverRecordServiceImpl.getAllHoverRecords();
    }

    @GetMapping("/hover-records/{id}")
    public ResponseEntity<HoverRecord> getHoverRecordById(@PathVariable int id) {
        return hoverRecordServiceImpl.getHoverRecordById(id);
    }

    @PostMapping("/hover-records")
    public ResponseEntity<HoverRecord> createHoverRecord(@Valid @RequestBody List<HoverRecord> hoverRecords) {
        return hoverRecordServiceImpl.createHoverRecord(hoverRecords);
    }

    @PutMapping("/hover-records/{id}")
    public ResponseEntity<HoverRecord> updateHoverRecord(@Valid @RequestBody List<HoverRecord> HoverRecords, @PathVariable int id) {
        return hoverRecordServiceImpl.updateHoverRecord(HoverRecords, id);
    }

    @DeleteMapping("/hover-records/{id}")
    public void deleteHoverRecord(@PathVariable int id) {
        hoverRecordServiceImpl.deleteHoverRecord(id);
    }
}
