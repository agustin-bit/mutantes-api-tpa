package com.example.mutantesapi_v1.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;

// Clase de ayuda, que filtra las peticiones incorrectas y ahorra trabajo en el server

@Service
public class ValidatorService {

    public boolean isValid(String[] dna) {

        // Filtra las matrices que no son cuadradas
        if (Arrays.stream(dna).filter(x -> (x.length() != dna.length)).count() != 0) return false;

        // Filtra las cadenas que tiene carácteres no válidos
        if (Arrays.stream(dna)
                .filter(x -> (x.replaceAll("[ATCG]", "").length() != 0))
                .count() != 0)
                return false;

        return true;

    }
}
