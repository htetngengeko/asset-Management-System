package com.project.asset_Management_System.service;

import com.project.asset_Management_System.model.Asset;
import com.project.asset_Management_System.model.AssetType;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssetTypeService {
    List<AssetType> getAllAssetTypes();
    ResponseEntity<AssetType> getAssetTypeById(int id);
    @Transactional
    ResponseEntity<AssetType> createAssetTypes(List<AssetType> assetTypes);

    @Transactional
    AssetType createAssetType(List<AssetType> assetTypes);

    ResponseEntity<Asset> updateAssetType(AssetType assetType, int id);
    void deleteAssetType(int id);
}
