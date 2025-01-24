package com.feature.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = "client")
@Entity
@Data
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy="client")
    private List<Ticket> tickets;

}
