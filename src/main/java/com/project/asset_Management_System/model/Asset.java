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
    @Column(unique = true)
    private String serial_number;

    @NotNull(message = "Name can't be null")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    @NotNull
    private boolean isDeleted = Boolean.FALSE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(@NotNull AssetType assetType) {
        this.assetType = assetType;
    }

    public String getName() {
        return name;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(@NotNull String serialNumber) {
        this.serial_number = serialNumber;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull Status status) {
        this.status = status;
    }

    @NotNull
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(@NotNull boolean deleted) {
        isDeleted = deleted;
    }
}
