package com.example.mutantesapi_v1.controllers;

import com.example.mutantesapi_v1.entities.DNA;
import com.example.mutantesapi_v1.services.MutantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "mutant")

public class MutantController {

    protected MutantService _servicio = new MutantService();

    @PostMapping("")
    public ResponseEntity<?> isMutant(@RequestBody DNA dna) {
        try {
            if (_servicio.isMutant(dna.dna)) {
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }
            else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

}
