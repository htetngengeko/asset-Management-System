package com.project.asset_Management_System.controller;

import com.project.asset_Management_System.model.AssetType;
import com.project.asset_Management_System.service.implmentations.AssetTypeServiceImpl;
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
    public ResponseEntity<String> createAssetType(@Valid @RequestBody List<AssetType> assetType) {
        return assetTypeServiceImpl.createAssetTypes(assetType);
    }

    @PutMapping("/asset-types/{id}")
    public ResponseEntity<String> updateAssetType(@Valid @RequestBody AssetType assetType, @PathVariable int id) {
        return assetTypeServiceImpl.updateAssetType(assetType, id);
    }

    @DeleteMapping("/asset-types/{id}")
    public ResponseEntity<String> deleteAssetType(@PathVariable int id) {
         return assetTypeServiceImpl.deleteAssetType(id);
    }

}
