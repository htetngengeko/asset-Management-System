package com.project.asset_Management_System.service.interfaces;

import com.project.asset_Management_System.model.Asset;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AssetService {

    List<Asset> getAllAssets();

    ResponseEntity<Asset> getAssetById(int id);

    ResponseEntity<String> createAssets(List<Asset> assets) ;

    @Transactional
    ResponseEntity<String> importAssets(MultipartFile file) throws IOException;

    ResponseEntity<String> updateAsset(Asset asset, int id);

    ResponseEntity<String> deleteAsset(int id);
}
