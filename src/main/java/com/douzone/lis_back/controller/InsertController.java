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
//    @PostMapping("/kafka")
//    public void kafka(@RequestBody HashMap<String ,String>barcodeList){
//        System.out.println(barcodeList.get("barcodeList"));
//        kafkaProducer.send("sendBarcodeUpdate",barcodeList.get("barcodeList"));
//    }

    @PostMapping("/inspection-service/cancellation")
    public void cancellation(@RequestBody HashMap<String, String> updateData){
        System.out.println(updateData);
        service.updateMapper(updateData);
    }

//    @PostMapping("/cancellationKafka")
//    public  void cancellationKafka(@RequestBody HashMap<String,String> updateData){
//        System.out.println(updateData);
//        kafkaProducer.send("sendBarcodeReUpdate",updateData);
//    }
}
