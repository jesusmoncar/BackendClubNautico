package com.clubnautico.clubnautico.repository;

import com.clubnautico.clubnautico.entity.Trip;
import com.clubnautico.clubnautico.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAllByOrganizadorId(User organizadorId); //obtiene viaje segun organizador

}
