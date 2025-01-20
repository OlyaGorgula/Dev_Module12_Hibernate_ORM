package com.feature.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "client")
@Entity
@Data
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column
    private String name;
}
