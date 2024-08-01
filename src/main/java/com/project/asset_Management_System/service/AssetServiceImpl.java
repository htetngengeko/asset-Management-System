package com.project.asset_Management_System.service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.project.asset_Management_System.enums.Status;
import com.project.asset_Management_System.model.Asset;
import com.project.asset_Management_System.model.AssetType;
import com.project.asset_Management_System.repository.AssetRepository;
import com.project.asset_Management_System.repository.AssetTypeRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    @Override
    public ResponseEntity<Asset> createAssets(MultipartFile file) throws IOException {
        Asset asset = new Asset();
        try{
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            int rowIndex = 0;

            for(Row row : sheet) {
                if(rowIndex == 0){
                    rowIndex++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();
                int cellIndex = 0;
                while(cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cellIndex) {
                        case 0 -> asset.setName(cell.getStringCellValue());
                        case 1 -> asset.setAssetType(assetTypeRepository.findById((int) cell.getNumericCellValue()).orElseThrow());
                        case 2 -> asset.setSerial_number(cell.getStringCellValue());
                        case 3 -> asset.setStatus(Status.valueOf(cell.getStringCellValue()));

                        default -> {

                        }
                    }
                    cellIndex++;
                }

                assetRepository.save(asset);

            }
        } catch (IOException e){
            e.printStackTrace();
        }



        return new ResponseEntity<>(HttpStatus.CREATED);


    }

    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public ResponseEntity<Asset> updateAsset(Asset asset, int id) {
        Optional<Asset> existAsset = assetRepository.findById(id);
        if(existAsset.isPresent()) {
            Asset originalAsset = existAsset.get();
            originalAsset.setName(asset.getName());
            originalAsset.setStatus(asset.getStatus());
            originalAsset.setSerial_number(asset.getSerial_number());
            assetRepository.save(originalAsset);
        } return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public void deleteAsset(int id) {
        if(assetRepository.findById(id).isPresent()) {
            Asset originalAsset = assetRepository.findById(id).get();
            originalAsset.setStatus(Status.UNAVAILABLE);
        }
    }
}
