package com.project.asset_Management_System.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.asset_Management_System.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class HoverRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    @JsonProperty("asset_id")
    private Asset asset;

    private LocalDate hoverDate;
    private String previousOwner;
    private String newOwner;

    @Enumerated(EnumType.STRING)
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
