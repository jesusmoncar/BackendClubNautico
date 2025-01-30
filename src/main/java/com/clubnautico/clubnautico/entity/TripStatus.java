package com.clubnautico.clubnautico.entity;

public enum TripStatus {

    PENDING, //estado inicial para cuando se crea a la espera de añadir un patron
    ACCEPTED, //ya tiene un patron añadido a falta de realizar el viaje
    FINISHED
}
