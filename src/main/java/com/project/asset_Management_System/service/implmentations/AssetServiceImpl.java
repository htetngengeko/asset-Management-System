package com.project.asset_Management_System.service.implmentations;

import com.project.asset_Management_System.enums.Status;
import com.project.asset_Management_System.model.Asset;
import com.project.asset_Management_System.model.AssetType;
import com.project.asset_Management_System.repository.AssetRepository;
import com.project.asset_Management_System.repository.AssetTypeRepository;
import com.project.asset_Management_System.service.interfaces.AssetService;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    AssetRepository assetRepository;
    @Autowired
    private AssetTypeRepository assetTypeRepository;


    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public ResponseEntity<Asset> getAssetById(int id) {
        return assetRepository.findById(id).map(asset -> new ResponseEntity<>(asset, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<String> createAssets(List<Asset>assets){
        List<Asset> newAssets = new ArrayList<>();
        for (Asset asset : assets) {
            Optional<AssetType> assetType = assetTypeRepository.findById(asset.getAssetType().getId());
            try{
                if (assetType.isPresent()) {
                    asset.setAssetType(assetType.get());
                    newAssets.add(asset);
                }
                assetRepository.saveAll(newAssets);
                return ResponseEntity.status(HttpStatus.CREATED).body("Assets created successfully.");

            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error while creating assets.");
            }

        }
        return null;
    }



    @Transactional
    @Override
    public ResponseEntity<String> importAssets(MultipartFile file) throws IOException {
        List<Asset> assets = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowIndex = 0;
            for (Row row : sheet) {
                Asset asset = new Asset();
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.cellIterator();
                int cellIndex = 0;

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cellIndex) {
                        case 0 -> asset.setName(cell.getStringCellValue());
                        case 1 -> asset.setAssetType(assetTypeRepository.findById((int) cell.getNumericCellValue()).orElseThrow());
                        case 2 -> asset.setSerial_number(cell.getStringCellValue());
                        case 3 -> asset.setStatus(Status.valueOf(cell.getStringCellValue().toUpperCase()));
                        default -> {}
                    }
                    cellIndex++;
                }
                assets.add(asset);
            }
            assetRepository.saveAll(assets);
            return ResponseEntity.status(HttpStatus.CREATED).body("Assets imported successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to import assets.");
        }
    }

    @Override
    public ResponseEntity<String> updateAsset(Asset asset, int id) {
        try{
            Optional<Asset> existAsset = assetRepository.findById(id);
            if(existAsset.isPresent()) {
                Asset originalAsset = existAsset.get();
                originalAsset.setName(asset.getName());
                originalAsset.setStatus(asset.getStatus());
                originalAsset.setSerial_number(asset.getSerial_number());
                assetRepository.save(originalAsset);
            } return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update asset.");
        }

    }

    public ResponseEntity<String> deleteAsset(int id) {
        try {
            if(assetRepository.findById(id).isPresent()) {
                Asset originalAsset = assetRepository.findById(id).get();
                originalAsset.setDeleted(Boolean.TRUE);
            }return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete asset.");
        }

    }
}
