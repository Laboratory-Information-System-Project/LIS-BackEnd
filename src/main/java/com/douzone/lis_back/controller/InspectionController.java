package com.douzone.lis_back.controller;

import com.douzone.lis_back.domain.*;
import com.douzone.lis_back.service.InspectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

@RestController
@RequestMapping("/inspection-service")
@RequiredArgsConstructor
public class InspectionController {

    private final InspectionService service;


    @GetMapping("/unregistered/search")
    public List<RegisterDTO> getUnregistered(){

        return service.getUnregistered();
    }

    @GetMapping("/register/search")
    public List<RegisterDTO> getSearchRegister(SearchDTO search){
        return service.getSearchRegister(search);
    }

    @GetMapping("/inspection-type/search")
    public List<InspectionTypeDTO> getSearchInspectionType(String orderCode){
        return service.getSearchInspectionType(orderCode);
    }

    @GetMapping("/conclusion/search")
    public List<ConclusionDTO> getSelectConclusion(SearchDTO search){
        return service.getSelectConclusion(search);
    }

    @GetMapping("/UnsuitableStatus")
    public List<UnsuitableStatusDTO> getUnsuitableStatus(){
        return service.getUnsuitableStatus();
    }



    @PostMapping("/conclusion")
    public String insertConclusion(@RequestBody List<ConclusionDTO> conclusion){

//        String barcode =conclusion.get(0).getBarcode();
//        String orderCode =conclusion.get(0).getOrderCode();
//        System.out.println(conclusion.get(0));
//
//        String text1 = barcode;
//        String text2 = orderCode;
//
//        List data = new ArrayList<>();
//
//        data.add(text1);
//        data.add(text2);

        service.insertConclusionBatch(conclusion);
        service.updatePrescribeStatus(conclusion.get(0).getRegisterCode(), "D");


        return "성공하였습니다.";
    }

    @PutMapping("/conclusion")
    public String updateConclusion(@RequestBody List<ConclusionDTO> conclusion) {
        service.updateConclusionBatch(conclusion);

        return "성공하였습니다.";
    }

    @PutMapping("/status")
    public String updatePrescribeStatus(Long registerCode) {
            service.updatePrescribeStatus(registerCode, "M");
        return "성공하였습니다.";
    }
}
