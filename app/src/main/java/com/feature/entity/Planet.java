package com.feature.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "planet")
@Entity
@Data
public class Planet {
    @Id
    @Column(name = "planet_id")
    private String id;

    @Column
    private String name;

    @OneToMany(mappedBy="fromPlanet", orphanRemoval = true)
    private List<Ticket> ticketsFromPlanet;

    @OneToMany(mappedBy="toPlanet", orphanRemoval = true)
    private List<Ticket> ticketsToPlanet;

    public void setId(String id) {
        this.id = id.toUpperCase();
    }
}
