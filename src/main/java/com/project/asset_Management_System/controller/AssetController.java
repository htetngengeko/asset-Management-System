package com.project.asset_Management_System.controller;

import com.project.asset_Management_System.model.Asset;
import com.project.asset_Management_System.repository.AssetRepository;
import com.project.asset_Management_System.service.implmentations.AssetServiceImpl;
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
    public ResponseEntity<String> createAssets(@Valid @RequestBody List<Asset> assets) {
        return assetServiceImpl.createAssets(assets);
    }

    @RequestMapping(value = "/assets/batch", method = RequestMethod.POST)
    public ResponseEntity<String> importAssets(@Valid @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();

        if (fileName == null || (!fileName.endsWith(".xlsx") || !fileName.contains(".xls"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type. Only .xlsx files are supported.");
        }
        try {
            assetServiceImpl.importAssets(file);
            return ResponseEntity.ok("Assets imported successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to import assets.");
        }
    }

    @RequestMapping(value = "/assets/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateAsset(@Valid @RequestBody Asset asset, @PathVariable int id) {
        return assetServiceImpl.updateAsset(asset, id);
    }

    @RequestMapping(value = "/assets/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAsset(@PathVariable int id) {
        return assetServiceImpl.deleteAsset(id);
    }
}
