package com.clubnautico.clubnautico.controller.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipRequest {

    private String nameShip;
    private String type;
    private String description;
    private String registrationNumber;//matricula
    private double fee;//tarifa
    private int morring;//amarre
}
