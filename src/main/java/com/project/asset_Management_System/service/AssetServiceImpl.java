package com.project.asset_Management_System.service;

import com.project.asset_Management_System.enums.Status;
import com.project.asset_Management_System.model.Asset;
import com.project.asset_Management_System.model.AssetType;
import com.project.asset_Management_System.repository.AssetRepository;
import com.project.asset_Management_System.repository.AssetTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    AssetRepository assetRepository;
    @Autowired
    private AssetTypeRepository assetTypeRepository;

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public ResponseEntity<Asset> getAssetById(int id) {
        return assetRepository.findById(id).map(asset -> new ResponseEntity<>(asset, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Override
    public ResponseEntity<Asset> createAssets(List<Asset> assets) {
        for (Asset asset : assets) {
            if(asset.getStatus() == null){
                asset.setStatus(Status.AVAILABLE);
            }
            Optional<AssetType> assetType = assetTypeRepository.findById(asset.getAssetType().getId());
            if (assetType.isPresent()) {
                asset.setAssetType(assetType.get());
                assetRepository.save(asset);
            }
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public ResponseEntity<Asset> updateAsset(Asset asset, int id) {
        Optional<Asset> existAsset = assetRepository.findById(id);
        if(existAsset.isPresent()) {
            Asset originalAsset = existAsset.get();
            originalAsset.setName(asset.getName());
            originalAsset.setStatus(asset.getStatus());
            originalAsset.setSerial_number(asset.getSerial_number());
            assetRepository.save(originalAsset);
        } return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public void deleteAsset(int id) {
        if(assetRepository.findById(id).isPresent()) {
            Asset originalAsset = assetRepository.findById(id).get();
            originalAsset.setStatus(Status.UNAVAILABLE);
        }
    }
}
