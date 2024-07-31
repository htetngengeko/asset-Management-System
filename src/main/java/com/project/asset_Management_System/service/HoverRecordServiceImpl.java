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
public class HoverRecordServiceImpl {
    @Autowired
    HoverRecordRepository hoverRecordRepository;
    @Autowired
    private AssetRepository assetRepository;

    public List<HoverRecord> getAllHoverRecords(){
        return hoverRecordRepository.findAll();
    }

    public HoverRecord getHoverRecordById(int id){
        return hoverRecordRepository.findById(id).get();
    }

    public ResponseEntity<HoverRecord> createHoverRecord(List<HoverRecord> hoverRecords){
        for(HoverRecord hoverRecord : hoverRecords) {
            if(hoverRecord.getStatus() == null) {
                hoverRecord.setStatus(Status.AVAILABLE);
            }
            Optional<Asset> asset = assetRepository.findById(hoverRecord.getAsset().getId());
            if(asset.isPresent()) {
                hoverRecord.setAsset(asset.get());
                hoverRecordRepository.save(hoverRecord);
            }
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public HoverRecord updateHoverRecord(HoverRecord hoverRecord, int id){
        Optional<HoverRecord> existingHoverRecord = hoverRecordRepository.findById(id);
        if(existingHoverRecord.isPresent()){
            HoverRecord originalHoverRecord = existingHoverRecord.get();
            originalHoverRecord.setAsset(hoverRecord.getAsset());
            originalHoverRecord.setNewOwner(hoverRecord.getNewOwner());
            originalHoverRecord.setPreviousOwner(hoverRecord.getPreviousOwner());
            originalHoverRecord.setHoverDate(hoverRecord.getHoverDate());
            hoverRecordRepository.save(originalHoverRecord);
        }
        return hoverRecord;
    }

    public HoverRecord deleteHoverRecord(int id){
        Optional<HoverRecord> existingHoverRecord = hoverRecordRepository.findById(id);
        if (existingHoverRecord.isPresent()) {
            HoverRecord originalHoverRecord = existingHoverRecord.get();
            originalHoverRecord.setStatus(Status.UNAVAILABLE);
            return hoverRecordRepository.save(originalHoverRecord);
        }else {
            return null;
        }
    }

}
