package com.example.mutantesapi_v1.controllers;

import com.example.mutantesapi_v1.entities.DNA;
import com.example.mutantesapi_v1.services.MutantService;
import com.example.mutantesapi_v1.services.StatsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "stats")

public class StatsController {

    protected StatsService _servicio = new StatsService();

    @PostMapping("")
    public ResponseEntity<?> getStats() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body( _servicio.getStats());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

}
