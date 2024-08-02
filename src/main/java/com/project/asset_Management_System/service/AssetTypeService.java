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
    ResponseEntity<String> createAssetTypes(List<AssetType> assetTypes);

    ResponseEntity<String> updateAssetType(List<AssetType>assetTypes, int id);

    ResponseEntity<String> deleteAssetType(int id);
}
