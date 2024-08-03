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
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    AssetServiceImpl assetServiceImpl;
    @Autowired
    private AssetRepository assetRepository;

    @GetMapping
    public List<Asset> showAsset() {
        return assetServiceImpl.getAllAssets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> showAssetDetails(@PathVariable int id) {
        return assetServiceImpl.getAssetById(id);
    }

    @PostMapping
    public ResponseEntity<String> createAssets(@Valid @RequestBody List<Asset> assets) {
        return assetServiceImpl.createAssets(assets);
    }

    @PostMapping("/batch")
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

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAsset(@Valid @RequestBody Asset asset, @PathVariable int id) {
        return assetServiceImpl.updateAsset(asset, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable int id) {
        return assetServiceImpl.deleteAsset(id);
    }
}
