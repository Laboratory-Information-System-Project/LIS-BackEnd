package com.douzone.lis_back.controller.unsuitable;

import com.douzone.lis_back.domain.unsuitable.UnsuitableReasonDTO;
import com.douzone.lis_back.service.unsuitable.UnsuitableSampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/unsuitable-service")
public class UnsuitableSampleController {
    private final UnsuitableSampleService service;

    @GetMapping("/sample/search")
    public List<Map<String, Object>> getSampleInfo(@RequestParam Long barcode, @RequestParam String authority) {

        return service.getSampleInfo(barcode, authority);
    }

    @GetMapping("/prescribe/search")
    public List<Map<String, Object>>  getPrescribeInfo(Long barcode, String authority)  {return service.getPrescribeInfo(barcode, authority); }

    @GetMapping("/user/search/{userName}")
    public List<Map<String, Object>> getUsersInfo(@PathVariable String userName) { return service.getUsersInfo(userName); }

    @GetMapping("/unsuitable-reason")
    public List<UnsuitableReasonDTO> getUnsuitableReason() { return service.getUnsuitableReason(); }

    @GetMapping("/unsuitable/{barcode}")
    public List<Map<String, Object>> getUnsuitInfo(@PathVariable Long barcode) { return service.getUnsuitInfo(barcode); }

    @PostMapping("/unsuitable-reason-management/")
    public void insertUnsuitableSample(@RequestBody List<Map<String, String>> unsuitInfo) {
        System.out.println(unsuitInfo);
        service.insertUnsuitableSample(unsuitInfo);
    }

}
