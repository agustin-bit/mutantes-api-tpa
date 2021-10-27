package com.example.mutantesapi_v1.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;

// Clase de ayuda, contiene métodos que nos permiten manipular la matriz

@Service
public class HelperService {

    // Devuelve una matriz rotada 90 grados con respecto a la original
    static public String[] rotateMatrix (String[] dna){

        String[] columns = new String[dna.length];
        Arrays.fill(columns, "");

        for (int i = 0; i<dna.length; i++) {
            for (int j = 0; j<dna.length; j++) {
                columns[dna.length - 1 - j] += dna[i].charAt(j);
            }
        }
        System.out.println("-----------------------");
        for (String d: columns) System.out.println(d);
        return columns;
    }

    //Devuelve las diagonales descendientes de una matriz
    //Primero se llama con la matriz normal y luego rotada, para encontrar todas las diagonales
    static public String[] findDiagonals (String[] dna){

        // Se declara un array lleno de cadenas vacías
        String[] diagonals = new String[dna.length*2];
        Arrays.fill(diagonals, "");

        int diagonal = 0; // Diagonal que se está leyendo

        // Fila y columna que se está leyendo, valor auxiliar que ayuda a recorrer la matriz
        int r = 0, c, aux;

        // Primera mitad de las diagonales, de la menor a la mayor
        while(r<dna.length){
            c = 0;
            aux = r;
            while(aux>=0){
                diagonals[diagonal] += (dna[aux].charAt(c));
                aux--;
                c++;
            }
            diagonal++;
            r++;
        }

        c = 1;

        // Segunda mitad de diagonales
        while(c<dna.length){
            r = dna.length-1;
            aux = c;
            while(aux<=dna.length-1){
                diagonals[diagonal] += (dna[r].charAt(aux));
                r--;
                aux++;
            }
            diagonal++;
            c++;
        }

        // Filtramos aquellas cuyo tamaño sea menor a tres
        diagonals = Arrays.stream(diagonals).filter(x -> (x.length() > 3)).toArray(String[]::new);

        return diagonals;
    }
}
