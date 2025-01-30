package com.clubnautico.clubnautico.controller;

import com.clubnautico.clubnautico.Service.ShipService;
import com.clubnautico.clubnautico.controller.Models.ShipRequest;
import com.clubnautico.clubnautico.controller.Models.ShipResponse;
import com.clubnautico.clubnautico.entity.Ship;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ship")
@RequiredArgsConstructor
public class ShipController {

    private final ShipService shipService;

    // Obtener un barco por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Ship> getShipById(@PathVariable Long id) {
        Ship ship = shipService.getShipById(id);
        return ResponseEntity.ok(ship);
    }

    @GetMapping("/getShip")
    public ResponseEntity<List<ShipResponse>>getUserShips(){
        return ResponseEntity.ok(shipService.getUserShips());
    }

    @PostMapping("/create")
    public ResponseEntity<Ship> createShip(@RequestBody ShipRequest shipRequest){
        return ResponseEntity.ok(shipService.createShip(shipRequest));
    }

    @DeleteMapping("/deleteShip/{id}")
    public ResponseEntity<Void>deleteShip(@PathVariable Long id){
        shipService.deleteShip(id);
        return ResponseEntity.noContent().build();
    }

}
