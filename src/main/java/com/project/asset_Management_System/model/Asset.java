package com.project.asset_Management_System.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.asset_Management_System.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;

@Entity
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "asset_type_id", nullable = false)
    @JsonProperty("asset_type_id")
    @NotNull(message = "Asset type can't be null")
    private AssetType assetType;

    @NotNull(message = "Serial Number can't be null")
    private String serial_number;

    @NotNull(message = "Name can't be null")
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public String getName() {
        return name;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serialNumber) {
        this.serial_number = serialNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
