package com.project.asset_Management_System.service;

import com.project.asset_Management_System.model.Asset;
import com.project.asset_Management_System.model.HoverRecord;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HoverRecordService {
    List<HoverRecord> getAllHoverRecords();

    ResponseEntity<HoverRecord> getHoverRecordById(int id);

    @Transactional
    ResponseEntity<HoverRecord> createHoverRecord(List<HoverRecord> hoverRecords);

    ResponseEntity<HoverRecord> updateHoverRecord(List<HoverRecord> HoverRecord, int id);

    void deleteHoverRecord(int id);
}
