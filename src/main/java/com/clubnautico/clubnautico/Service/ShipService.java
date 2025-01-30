package com.clubnautico.clubnautico.Service;

import com.clubnautico.clubnautico.controller.Models.ShipRequest;
import com.clubnautico.clubnautico.controller.Models.ShipResponse;
import com.clubnautico.clubnautico.entity.Ship;
import com.clubnautico.clubnautico.entity.User;
import com.clubnautico.clubnautico.exception.NotFound;
import com.clubnautico.clubnautico.repository.ShipRepository;
import com.clubnautico.clubnautico.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShipService{

    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private UserRepository userRepository;

    private User getAuthenticateUser(){
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return userRepository.findByUsername(username)
                .orElseThrow(()->new NotFound("usuario no encontrado: " + username));
    }

    public List<ShipResponse> getUserShips(){
        User propietario = getAuthenticateUser();

        List<Ship> ships = shipRepository.findByOwnerId(propietario.getId());

        return ships.stream()
                .map(ship->new ShipResponse(
                       ship.getIdShip(),
                        ship.getNameShip(),
                        ship.getType(),
                        ship.getDescription(),
                        ship.getRegistrationNumber(),
                        ship.getFee(),
                        ship.getMorring()
                ))
                .collect(Collectors.toList());
    }

    // Obtener un barco por su ID
    public Ship getShipById(Long id) {
        return shipRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Ship not found with ID: " + id));
    }

    public Ship createShip(ShipRequest shipRequest){
        User propietario = getAuthenticateUser();

        Ship ship = Ship.builder()
                .nameShip(shipRequest.getNameShip())
                .type(shipRequest.getType())
                .description(shipRequest.getDescription())
                .registrationNumber(shipRequest.getRegistrationNumber())
                .fee(shipRequest.getFee())
                .morring(shipRequest.getMorring())
                .owner(propietario)
                .build();

        return shipRepository.save(ship);
    }

    public void deleteShip(Long idShip){
        User owner = getAuthenticateUser();
        Ship ship = shipRepository.findById(idShip)
                .orElseThrow(()->new NotFound("ship no encontrado: " + idShip));

        if(!ship.getOwner().equals(owner)){
            throw new RuntimeException("Este usuario no tiene permiso para eliminar Barcos");
        }

        shipRepository.delete(ship);
    }


}
