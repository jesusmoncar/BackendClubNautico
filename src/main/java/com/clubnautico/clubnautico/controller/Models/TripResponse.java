package com.clubnautico.clubnautico.controller.Models;

import com.clubnautico.clubnautico.entity.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripResponse {

    private Long idTrip;
    private Date fechayHora;
    private String description;
    private String organizadorName;
    private Long shipId;
    private TripStatus tripStatus;


}
