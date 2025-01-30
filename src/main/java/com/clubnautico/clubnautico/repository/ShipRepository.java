package com.clubnautico.clubnautico.repository;

import com.clubnautico.clubnautico.entity.Ship;
import com.clubnautico.clubnautico.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    List<Ship> findByOwnerId(Long userId); //obtener barco segun id de propietario
}
