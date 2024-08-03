package com.project.asset_Management_System.service.implmentations;

import com.project.asset_Management_System.model.Asset;
import com.project.asset_Management_System.model.Employee;
import com.project.asset_Management_System.model.HoverRecord;
import com.project.asset_Management_System.repository.AssetRepository;
import com.project.asset_Management_System.repository.EmployeeRepository;
import com.project.asset_Management_System.repository.HoverRecordRepository;
import com.project.asset_Management_System.service.interfaces.HoverRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoverRecordServiceImpl implements HoverRecordService {
    @Autowired
    HoverRecordRepository hoverRecordRepository;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<HoverRecord> getAllHoverRecords(){
        return hoverRecordRepository.findAll();
    }

    @Override
    public ResponseEntity<HoverRecord> getHoverRecordById(int id){
        hoverRecordRepository.findById(id).get();
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createHoverRecord(List<HoverRecord> hoverRecords) {
        try {
            for (HoverRecord hoverRecord : hoverRecords) {
                Optional<Asset> asset = assetRepository.findById(hoverRecord.getAsset().getId());
                Optional<Employee> previousEmployee = employeeRepository.findByEmployeeCode(hoverRecord.getPreviousOwner().getEmployeeCode());
                Optional<Employee> newEmployee = employeeRepository.findByEmployeeCode(hoverRecord.getNewOwner().getEmployeeCode());

                if (asset.isPresent() && previousEmployee.isPresent() && newEmployee.isPresent()) {
                    hoverRecord.setAsset(asset.get());
                    hoverRecord.setPreviousOwner(previousEmployee.get());
                    hoverRecord.setNewOwner(newEmployee.get());
                    hoverRecordRepository.save(hoverRecord);
                }
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating hover record");
        }
    }

    @Override
    public ResponseEntity<String > updateHoverRecord(HoverRecord hoverRecord, int id){
        try{
            Optional<HoverRecord> existingHoverRecordOpt = hoverRecordRepository.findById(id);
            Optional<Employee> existingPreviousEmployeeOpt = employeeRepository.findByEmployeeCode(hoverRecord.getPreviousOwner().getEmployeeCode());
            Optional<Employee> existingNewEmployeeOpt = employeeRepository.findByEmployeeCode(hoverRecord.getNewOwner().getEmployeeCode());

            if(existingHoverRecordOpt.isPresent() && existingPreviousEmployeeOpt.isPresent() &&  existingNewEmployeeOpt.isPresent()){

                HoverRecord originalHoverRecord = existingHoverRecordOpt.get();
                Asset existingAsset = originalHoverRecord.getAsset();
                Employee existingPreviousEmployee = existingPreviousEmployeeOpt.get();
                Employee existingNewEmployee = existingNewEmployeeOpt.get();

                originalHoverRecord.setAsset(existingAsset);
                originalHoverRecord.setNewOwner(existingNewEmployee);
                originalHoverRecord.setPreviousOwner(existingPreviousEmployee);
                originalHoverRecord.setHoverDate(hoverRecord.getHoverDate());
                hoverRecordRepository.save(originalHoverRecord);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated successfully.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating hover record");
        }
    }

    @Override
    public ResponseEntity<String> deleteHoverRecord(int id){
        try {
            Optional<HoverRecord> existingHoverRecord = hoverRecordRepository.findById(id);
            if (existingHoverRecord.isPresent()) {
                HoverRecord originalHoverRecord = existingHoverRecord.get();
                originalHoverRecord.setDeleted(Boolean.TRUE);
                hoverRecordRepository.save(originalHoverRecord);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully.");

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting hover record");
        }
    }

}
