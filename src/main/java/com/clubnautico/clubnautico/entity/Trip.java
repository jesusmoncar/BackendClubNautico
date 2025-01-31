package com.clubnautico.clubnautico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrip;

    private Date fechayHora;
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizadorId", nullable = false)
    private User organizadorId; // Id usuario organizador

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipId", nullable = false)
    private Ship shipId;

    @Enumerated(EnumType.ORDINAL)
    private TripStatus status;
}
