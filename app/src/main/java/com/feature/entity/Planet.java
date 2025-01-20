package com.feature.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "planet")
@Entity
@Data
public class Planet {
    @Id
    private String id;

    @Column
    private String name;

    public void setId(String id) {
        this.id = id.toUpperCase();
    }
}
