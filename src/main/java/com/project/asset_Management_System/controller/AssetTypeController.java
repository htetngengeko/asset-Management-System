package com.project.asset_Management_System.controller;

import com.project.asset_Management_System.model.AssetType;
import com.project.asset_Management_System.service.AssetTypeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssetTypeController {
    @Autowired
    AssetTypeServiceImpl assetTypeServiceImpl;

    @GetMapping("/asset-types")
    public List<AssetType> getAllAssetTypes() {
        return assetTypeServiceImpl.getAllAssetTypes();
    }

    @GetMapping("/asset-types/{id}")
    public ResponseEntity<AssetType> getAssetTypeById(@PathVariable int id) {
        return assetTypeServiceImpl.getAssetTypeById(id);
    }

    @PostMapping("/asset-types")
    public ResponseEntity<AssetType> createAssetType(@Valid @RequestBody List<AssetType> assetType) {
        return assetTypeServiceImpl.createAssetTypes(assetType);
    }

    @PutMapping("/asset-types/{id}")
    public ResponseEntity<AssetType> updateAssetType(@Valid @RequestBody List<AssetType> assetTypes, @PathVariable int id) {
        return assetTypeServiceImpl.updateAssetType(assetTypes, id);
    }

    @DeleteMapping("/asset-types/{id}")
    public void deleteAssetType(@PathVariable int id) {
         assetTypeServiceImpl.deleteAssetType(id);
    }

}
