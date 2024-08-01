package com.project.asset_Management_System.controller;

import com.project.asset_Management_System.model.Asset;
import com.project.asset_Management_System.repository.AssetRepository;
import com.project.asset_Management_System.service.AssetServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class AssetController {

    @Autowired
    AssetServiceImpl assetServiceImpl;
    @Autowired
    private AssetRepository assetRepository;

    @RequestMapping("/assets")
    public List<Asset> showAsset() {
        return assetServiceImpl.getAllAssets();
    }

    @RequestMapping("/assets/{id}")
    public ResponseEntity<Asset> showAssetDetails(@PathVariable int id) {
        return assetServiceImpl.getAssetById(id);
    }

    @RequestMapping(value = "/assets", method = RequestMethod.POST)
    public ResponseEntity<String> createAssets(@Valid @RequestParam("file") MultipartFile file) {
        try {
            assetServiceImpl.createAssets(file);
            return ResponseEntity.ok("Assets imported successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to import assets.");
        }
    }

    @RequestMapping(value = "/assets/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Asset> updateAsset(@Valid @RequestBody Asset asset, @PathVariable int id) {
        return assetServiceImpl.updateAsset(asset, id);
    }

    @RequestMapping(value = "/assets/{id}", method = RequestMethod.DELETE)
    public void deleteAsset(@PathVariable int id) {
        assetServiceImpl.deleteAsset(id);
    }
}
