package com.douzone.lis_back.controller.Collecting.patient;

import com.douzone.lis_back.service.barcode.BarcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data-service")
@RequiredArgsConstructor
public class GetBarcodeController {
    private final BarcodeService barcodeService;

    @GetMapping("/barcode")
    public List<Map<String,Object>> getBarcode(@RequestParam List<String> prescribeCodeList){
        barcodeService.getBarcodeByPrescribeCodeList(prescribeCodeList);
        return barcodeService.getBarcodeByPrescribeCodeList(prescribeCodeList);
    }
}
