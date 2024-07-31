package com.project.asset_Management_System.service;

import com.project.asset_Management_System.model.Asset;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssetService {

    List<Asset> getAllAssets();
    ResponseEntity<Asset> getAssetById(int id);
    @Transactional
    ResponseEntity<Asset> createAssets(List<Asset> assets);
    ResponseEntity<Asset> updateAsset(Asset asset, int id);
    void deleteAsset(int id);
}
