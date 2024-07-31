package com.project.asset_Management_System.service;

import com.project.asset_Management_System.enums.Status;
import com.project.asset_Management_System.model.AssetType;
import com.project.asset_Management_System.repository.AssetTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetTypeServiceImpl {
    @Autowired
    AssetTypeRepository assetTypeRepository;

    public List<AssetType> getAllAssetTypes() {
        return assetTypeRepository.findAll();
    }

    public AssetType getAssetTypeById(int id) {
        return assetTypeRepository.findById(id).get();
    }

//    @Transactional
//    public AssetType createAssetTypeByObject(AssetType assetType) {
//        if(assetType.getStatus() == null){
//            assetType.setStatus(Status.AVAILABLE);
//        }
//        return assetTypeRepository.save(assetType);
//    }

    @Transactional
    public AssetType createAssetType(List<AssetType> assetTypes) {
        for(AssetType assetType : assetTypes) {
            if(assetType.getStatus() == null){
                assetType.setStatus(Status.AVAILABLE);
            }
            return assetTypeRepository.save(assetType);
        }
        return null;
    }


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
