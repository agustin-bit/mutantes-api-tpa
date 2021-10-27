package com.example.mutantesapi_v1.services;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Clase que detecta las secuencias de mutantes

@Service
public class DetectorService {

    // Comprueba si un una secuencia es mutante
    public boolean detector(String[] dna) {

        // Patrón regex compilado que reconoce cadenas de 4 bases repetidas
        // Se compila solo una vez para mejorar la performance
        Pattern pattern = Pattern.compile("([A,T,C,G])\\1{3}");

        //Matcher que interpreta el patrón
        Matcher matcher;

        // Cantidad de secuencias mutantes
        int mutantSequences = 0;

        // Recorremos todas las cadenas posibles
        for (String row : dna)
        {
            matcher = pattern.matcher(row); // Se trata de matchear el patrón

            if (matcher.find()) { //Si hay match
                mutantSequences++; //Aumentamos la cantidad de secuencias
                if (matcher.find() || mutantSequences >= 2) {
                    // Comprobamos que no exista otra secuencia en la misma cadena o una anterior
                    // Apenas se encunetran dos iguales, se deja de analizar el adn y se devuelve true
                    return true;
                }
            }
        }

        //Si se analizaron todas las cadenas y coincidencias, se devuelve false
        return false;
    }

}
