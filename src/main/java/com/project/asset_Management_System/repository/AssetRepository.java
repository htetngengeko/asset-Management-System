package com.project.asset_Management_System.repository;

import com.project.asset_Management_System.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Integer> {

    @Query(value = "SELECT * from asset where name=?1 and type=?2", nativeQuery = true)
    Optional<Asset> findByNameAndType(String name1, String type2);

    @Query(value = "UPDATE asset SET asset.status = from asset where id=?1", nativeQuery = true)
    void updateStatus(String name1, String type2);




}
