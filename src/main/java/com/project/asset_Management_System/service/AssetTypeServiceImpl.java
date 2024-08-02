package com.project.asset_Management_System.service;

import com.project.asset_Management_System.enums.Status;
import com.project.asset_Management_System.model.AssetType;
import com.project.asset_Management_System.repository.AssetTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetTypeServiceImpl implements AssetTypeService{
    @Autowired
    AssetTypeRepository assetTypeRepository;

    @Override
    public List<AssetType> getAllAssetTypes() {
        return assetTypeRepository.findAll();
    }

    @Override
    public ResponseEntity<AssetType> getAssetTypeById(int id) {
         assetTypeRepository.findById(id).get();
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @Transactional
//    public AssetType createAssetTypeByObject(AssetType assetType) {
//        if(assetType.getStatus() == null){
//            assetType.setStatus(Status.AVAILABLE);
//        }
//        return assetTypeRepository.save(assetType);
//    }

    @Transactional
    @Override
    public ResponseEntity<String> createAssetTypes(List<AssetType> assetTypes) {
        assetTypeRepository.saveAll(assetTypes);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created.");
    }


    @Override
    public ResponseEntity<String> updateAssetType(List<AssetType> assetTypes, int id) {
        for(AssetType assetType : assetTypes) {
            Optional<AssetType> existingAssetType = assetTypeRepository.findById(id);
            if (existingAssetType.isPresent()) {
                AssetType originalAssetType = existingAssetType.get();
                originalAssetType.setName(assetType.getName());
                assetTypeRepository.save(originalAssetType);
            }
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated successfully.");
    }

    @Override
    public ResponseEntity<String> deleteAssetType(int id) {
        Optional<AssetType> existingAssetType = assetTypeRepository.findById(id);
        if (existingAssetType.isPresent()) {
            AssetType originalAssetType = existingAssetType.get();
            originalAssetType.setDeleted(Boolean.TRUE);
            assetTypeRepository.save(originalAssetType);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully.");

    }
}
