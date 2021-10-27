package com.example.mutantesapi_v1.services;

import com.example.mutantesapi_v1.repository.MutantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MutantService{

    private HelperService helperService = new HelperService();
    private DetectorService detectorService = new DetectorService();
    private ValidatorService validatorService = new ValidatorService();
    private MutantRepository mutantRepository= new MutantRepository();


    public boolean isMutant(String[] dna )  {

        if (!validatorService.isValid(dna)) return false;

        String dnaHash = Arrays.toString(dna).replaceAll("[\", ]", "");

        String pastResult = mutantRepository.searchHistory(dnaHash);
        if (pastResult != null) {
            return pastResult.equals("1");
        }

        //Array con todas las cadenas
        List<String> allStrings = new ArrayList<>(List.of(dna)); //Se agregan las filas

        String[] rotatedMatrix = helperService.rotateMatrix(dna); //Rotación 90 grados de la matriz

        allStrings.addAll(List.of(rotatedMatrix)); //Se agregan las columnas

        allStrings.addAll(List.of(helperService.findDiagonals(dna))); //Se agregan las diagonales ascendentes

        allStrings.addAll(List.of(helperService.findDiagonals(rotatedMatrix))); //Se agregan las diagonales descendentes

        boolean result = detectorService.detector(allStrings.toArray(String[]::new)); //Se buscan los mutantes

        mutantRepository.saveResult(dnaHash, result == true ? "1" : "0");

        return result;

    }
}
