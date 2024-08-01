package com.project.asset_Management_System.repository;

import com.project.asset_Management_System.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Integer> {
}
