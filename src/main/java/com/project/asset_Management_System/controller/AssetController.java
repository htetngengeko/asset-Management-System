package com.project.asset_Management_System.controller;

import com.project.asset_Management_System.model.Asset;
import com.project.asset_Management_System.repository.AssetRepository;
import com.project.asset_Management_System.service.AssetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Asset> setAsset(@RequestBody List<Asset> assets) {
        return assetServiceImpl.createAssets(assets);
    }

    @RequestMapping(value = "/assets/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Asset> updateAsset(@RequestBody Asset asset, @PathVariable int id) {
        return assetServiceImpl.updateAsset(asset, id);
    }

    @RequestMapping(value = "/assets/{id}", method = RequestMethod.DELETE)
    public void deleteAsset(@PathVariable int id) {
            assetServiceImpl.deleteAsset(id);
    }
}
