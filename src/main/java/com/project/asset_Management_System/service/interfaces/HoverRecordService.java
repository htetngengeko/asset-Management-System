package com.project.asset_Management_System.service.interfaces;

import com.project.asset_Management_System.model.HoverRecord;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HoverRecordService {
    List<HoverRecord> getAllHoverRecords();

    ResponseEntity<HoverRecord> getHoverRecordById(int id);

    @Transactional
    ResponseEntity<String> createHoverRecord(List<HoverRecord> hoverRecords);

    ResponseEntity<String> updateHoverRecord(HoverRecord HoverRecord, int id);

    ResponseEntity<String> deleteHoverRecord(int id);
}
