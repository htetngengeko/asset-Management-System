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

    @ManyToOne
    @JoinColumn(name = "previous_owner", nullable = false)
    @JsonProperty("previous_owner")
    @NotNull(message = "Previous owner can't be null")
    private Employee previousOwner;

    @ManyToOne
    @JoinColumn(name = "new_owner", nullable = false)
    @JsonProperty("new_owner")
    @NotNull(message = "New owner can't be null")
    private Employee newOwner;

    @NotNull
    private boolean isDeleted = Boolean.FALSE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = "Asset can't be null") Asset getAsset() {
        return asset;
    }

    public void setAsset(@NotNull(message = "Asset can't be null") Asset asset) {
        this.asset = asset;
    }

    public @NotNull(message = "Date can't be null") LocalDate getHoverDate() {
        return hoverDate;
    }

    public void setHoverDate(@NotNull(message = "Date can't be null") LocalDate hoverDate) {
        this.hoverDate = hoverDate;
    }

    public @NotNull(message = "Previous owner can't be null") Employee getPreviousOwner() {
        return previousOwner;
    }

    public void setPreviousOwner(@NotNull(message = "Previous owner can't be null") Employee previousOwner) {
        this.previousOwner = previousOwner;
    }

    public @NotNull(message = "New owner can't be null") Employee getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(@NotNull(message = "New owner can't be null") Employee newOwner) {
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
