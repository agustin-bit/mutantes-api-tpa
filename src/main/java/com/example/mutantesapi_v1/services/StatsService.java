package com.example.mutantesapi_v1.services;

import com.example.mutantesapi_v1.DTO.StatsDTO;
import com.example.mutantesapi_v1.repository.MutantRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class StatsService {

    private MutantRepository mutantRepository = new MutantRepository();

    public StatsDTO getStats () throws Exception {
        try {
            StatsDTO result = new StatsDTO();
            result.count_human_dna = Integer.valueOf(mutantRepository.getValue("count_human_dna"));
            result.count_mutant_dna = Integer.valueOf(mutantRepository.getValue("count_mutant_dna"));
            result.ratio = ((double) result.count_mutant_dna) / (result.count_mutant_dna + result.count_human_dna);
            return result;
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
