package com.project.asset_Management_System.service;

import com.project.asset_Management_System.enums.Status;
import com.project.asset_Management_System.model.Asset;
import com.project.asset_Management_System.model.HoverRecord;
import com.project.asset_Management_System.repository.AssetRepository;
import com.project.asset_Management_System.repository.HoverRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoverRecordServiceImpl implements HoverRecordService {
    @Autowired
    HoverRecordRepository hoverRecordRepository;
    @Autowired
    private AssetRepository assetRepository;

    @Override
    public List<HoverRecord> getAllHoverRecords(){
        return hoverRecordRepository.findAll();
    }

    @Override
    public ResponseEntity<HoverRecord> getHoverRecordById(int id){
        hoverRecordRepository.findById(id).get();
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createHoverRecord(List<HoverRecord> hoverRecords){
        for(HoverRecord hoverRecord : hoverRecords) {
            Optional<Asset> asset = assetRepository.findById(hoverRecord.getAsset().getId());
            if(asset.isPresent()) {
                hoverRecord.setAsset(asset.get());
                hoverRecordRepository.save(hoverRecord);
            }
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully created");
    }

    @Override
    public ResponseEntity<String > updateHoverRecord(List<HoverRecord> hoverRecords, int id){
        for(HoverRecord hoverRecord : hoverRecords) {
            Optional<HoverRecord> existingHoverRecord = hoverRecordRepository.findById(id);
            if(existingHoverRecord.isPresent()){
                HoverRecord originalHoverRecord = existingHoverRecord.get();
                originalHoverRecord.setAsset(hoverRecord.getAsset());
                originalHoverRecord.setNewOwner(hoverRecord.getNewOwner());
                originalHoverRecord.setPreviousOwner(hoverRecord.getPreviousOwner());
                originalHoverRecord.setHoverDate(hoverRecord.getHoverDate());
                hoverRecordRepository.save(originalHoverRecord);
            }

        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated successfully.");
    }

    @Override
    public ResponseEntity<String> deleteHoverRecord(int id){
        Optional<HoverRecord> existingHoverRecord = hoverRecordRepository.findById(id);
        if (existingHoverRecord.isPresent()) {
            HoverRecord originalHoverRecord = existingHoverRecord.get();
            originalHoverRecord.setDeleted(Boolean.TRUE);
             hoverRecordRepository.save(originalHoverRecord);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully.");
    }

}
