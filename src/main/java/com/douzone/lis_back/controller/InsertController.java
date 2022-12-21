package com.douzone.lis_back.controller;

import com.douzone.lis_back.service.InsertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/data-service")
@RequiredArgsConstructor
public class InsertController {
    private final InsertService service;
//    private final KafkaProducer kafkaProducer;
    @PostMapping("/inspection-service/insert")
    public void patient(@RequestBody HashMap<String, List<Object>> barcodeList){
        System.out.println(barcodeList.get("barcodeList"));
        service.InspectionAdd(barcodeList.get("barcodeList"));
    }
    @PostMapping("/inspection-service/kafka")
    public void kafka(@RequestBody HashMap<String ,List<HashMap<String, Object>>>prescribeList){
        System.out.println(prescribeList.get("prescribeList"));
        service.updatePrescribeCode(prescribeList.get("prescribeList"));
    }

    @PostMapping("/inspection-service/cancellation")
    public void cancellation(@RequestBody HashMap<String, String> updateData){
        System.out.println(updateData);
        service.updateMapper(updateData);
    }

    @PostMapping("/inspection-service/cancellationKafka")
    public  void cancellationKafka(@RequestBody HashMap<String ,List<HashMap<String, Object>>>prescribeList){
        System.out.println(prescribeList.get("prescribeList"));
        service.updatePrescribeCodeData(prescribeList.get("prescribeList"));
    }
}
