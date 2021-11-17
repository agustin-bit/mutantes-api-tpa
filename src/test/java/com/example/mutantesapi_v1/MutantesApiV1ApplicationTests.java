package com.example.mutantesapi_v1;

import com.example.mutantesapi_v1.DTO.StatsDTO;
import com.example.mutantesapi_v1.controllers.MutantController;
import com.example.mutantesapi_v1.entities.DNA;
import com.example.mutantesapi_v1.services.DetectorService;
import com.example.mutantesapi_v1.services.HelperService;
import com.example.mutantesapi_v1.services.MutantService;
import com.example.mutantesapi_v1.services.StatsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class MutantesApiV1ApplicationTests {

    @Test
    void TestDetector() {
        MutantService mutantService = new MutantService();
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","AAAAAA","TCACTG"};
        boolean result = mutantService.isMutant(dna);
        Assertions.assertTrue(result);
    }

    @Test
    void TestDetectorFalse() {
        MutantService mutantService = new MutantService();
        String[] dna = {"ATGCGA1","CAGTGC","TTATGT","AGAAGG","AAAAAA","TCACTG"};
        boolean result = mutantService.isMutant(dna);
        Assertions.assertFalse(result);
    }

    @Test
    void TestDetectorController() {
        MutantController mutantService = new MutantController();
        DNA dna = new DNA();
        dna.dna = new String[]{"AGGCGA", "CAGTGC", "TTTTGT", "AGAAGG", "TAAAAA", "TCACTG"};
        ResponseEntity result = mutantService.isMutant(dna);
        Assertions.assertTrue(result.getStatusCode() == HttpStatus.OK);
    }

    @Test
    void TestDetectorControllerFalse() {
        MutantController mutantService = new MutantController();
        DNA dna = new DNA();
        dna.dna = new String[]{"ATGCGA1", "CAGTGC", "TTATGT", "AGAAGG", "AAAAAA", "TCACTG"};
        ResponseEntity result = mutantService.isMutant(dna);
        Assertions.assertTrue(result.getStatusCode() == HttpStatus.FORBIDDEN);
    }

    @Test
    void TestStats() throws Exception {
        StatsService statsService = new StatsService();
        StatsDTO dto = statsService.getStats();
        Assertions.assertTrue(dto.count_mutant_dna > 0);
    }

    @Test
    void TestRotare() throws Exception {
        HelperService helperService = new HelperService();
        String[] rot = helperService.rotateMatrix(new String[]{"AB", "CD"});
        Assertions.assertTrue(rot[0].equals("BD") && rot[1].equals("AC"));
    }

    @Test
    void TestGetDiagonals() {
        HelperService helperService = new HelperService();
        String[] rot = helperService.findDiagonals(new String[]{"ABCD","EFGH","IJKL","MNÑO"});
        Assertions.assertTrue(rot[0].equals("MJGD"));
    }

}
