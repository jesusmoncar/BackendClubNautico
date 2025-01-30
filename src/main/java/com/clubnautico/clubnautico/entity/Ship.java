package com.clubnautico.clubnautico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShip;

    private String nameShip;
    private String type;
    private String description;
    private String registrationNumber;//matricula
    private double fee;//tarifa
    private int morring;//amarre

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId", nullable = false)
    private User owner; // Propietario

}
