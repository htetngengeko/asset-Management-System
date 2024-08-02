package com.project.asset_Management_System.model;

import com.project.asset_Management_System.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class AssetType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "Name can't be null")
    private String name;

    @NotNull
    private boolean isDeleted = Boolean.FALSE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(@NotNull boolean deleted) {
        isDeleted = deleted;
    }
}
