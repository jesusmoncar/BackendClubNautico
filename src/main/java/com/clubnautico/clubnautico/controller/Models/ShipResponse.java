package com.clubnautico.clubnautico.controller.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipResponse {

    private Long idShip;
    private String nameShip;
    private String type;
    private String description;
    private String registrationNumber;//matricula
    private double fee;//tarifa
    private int morring;//amarre

}
