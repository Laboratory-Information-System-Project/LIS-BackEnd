package com.douzone.lis_back.controller;

import com.douzone.lis_back.domain.collectdomain.CollectDomainDTO;
import com.douzone.lis_back.domain.patientdomain.PatientDomainDTO;
import com.douzone.lis_back.service.collectservice.CollectService;
import com.douzone.lis_back.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data-service")
public class DataController {
    private final PatientService patientService;
    private final CollectService collectService;

    @GetMapping("/patient/{barcode}")
    public List<PatientDomainDTO>patientData(@PathVariable String barcode){
        return patientService.patientData(barcode);
    }
    @GetMapping("/collect/{barcode}")
    public List<CollectDomainDTO>colletData(@PathVariable String barcode){
        return collectService.colletData(barcode);
    }

    @GetMapping("/pri/data/{barcode}")
    public List<CollectDomainDTO>getCodeData(@PathVariable String barcode){
        return collectService.getCodeData(barcode);
    }
}
