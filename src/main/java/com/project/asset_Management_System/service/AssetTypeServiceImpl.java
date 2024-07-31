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
    public AssetType createAssetType(List<AssetType> assetTypes) {
        for(AssetType assetType : assetTypes) {
            if(assetType.getStatus() == null){
                assetType.setStatus(Status.AVAILABLE);
            }
            return assetTypeRepository.save(assetType);
        }
        return null;
    }


    @Override
    public AssetType updateAssetType(AssetType assetType, int id) {
        Optional<AssetType> existingAssetType = assetTypeRepository.findById(id);
        if (existingAssetType.isPresent()) {
            AssetType originalAssetType = existingAssetType.get();
            originalAssetType.setName(assetType.getName());
            return assetTypeRepository.save(originalAssetType);
        }else {
            return null;
        }
    }

    @Override
    public AssetType deleteAssetType(int id) {
        Optional<AssetType> existingAssetType = assetTypeRepository.findById(id);
        if (existingAssetType.isPresent()) {
            AssetType originalAssetType = existingAssetType.get();
            originalAssetType.setStatus(Status.UNAVAILABLE);
            return assetTypeRepository.save(originalAssetType);
        }else {
            return null;
        }
    }
}
