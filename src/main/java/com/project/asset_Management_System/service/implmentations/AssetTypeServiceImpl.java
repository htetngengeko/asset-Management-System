package com.project.asset_Management_System.service.implmentations;

import com.project.asset_Management_System.model.AssetType;
import com.project.asset_Management_System.repository.AssetTypeRepository;
import com.project.asset_Management_System.service.interfaces.AssetTypeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetTypeServiceImpl implements AssetTypeService {
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

    @Transactional
    @Override
    public ResponseEntity<String> createAssetTypes(List<AssetType> assetTypes) {
        try {
            assetTypeRepository.saveAll(assetTypes);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created.");

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating asset type");
        }

    }

    @Override
    public ResponseEntity<String> updateAssetType(AssetType assetType, int id) {
        try {
            Optional<AssetType> existingAssetType = assetTypeRepository.findById(id);
            if (existingAssetType.isPresent()) {
                AssetType originalAssetType = existingAssetType.get();
                originalAssetType.setName(assetType.getName());
                assetTypeRepository.save(originalAssetType);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated successfully.");

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating asset type");
        }
    }

    @Override
    public ResponseEntity<String> deleteAssetType(int id) {
        try{
            Optional<AssetType> existingAssetType = assetTypeRepository.findById(id);
            if (existingAssetType.isPresent()) {
                AssetType originalAssetType = existingAssetType.get();
                originalAssetType.setDeleted(Boolean.TRUE);
                assetTypeRepository.save(originalAssetType);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting asset type");
        }
    }
}
