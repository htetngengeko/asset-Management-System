package com.project.asset_Management_System.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.asset_Management_System.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class HoverRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    @JsonProperty("asset_id")
    @NotNull (message = "Asset can't be null")
    private Asset asset;

    @NotNull(message = "Date can't be null")
    private LocalDate hoverDate;

    @NotNull(message = "Previous owner name can't be null")
    private String previousOwner;

    @NotNull(message = "New owner name can't be null")
    private String newOwner;

    @NotNull
    private boolean isDeleted = Boolean.FALSE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public LocalDate getHoverDate() {
        return hoverDate;
    }

    public void setHoverDate(LocalDate hoverDate) {
        this.hoverDate = hoverDate;
    }

    public String getPreviousOwner() {
        return previousOwner;
    }

    public void setPreviousOwner(String previousOwner) {
        this.previousOwner = previousOwner;
    }

    public String getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(String newOwner) {
        this.newOwner = newOwner;
    }

    @NotNull
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(@NotNull boolean deleted) {
        isDeleted = deleted;
    }
}
