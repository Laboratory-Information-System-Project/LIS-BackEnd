package com.douzone.lis_back.controller.Collecting;

import com.douzone.lis_back.service.barcode.BarcodeService;
import com.douzone.lis_back.service.prescribe.PrescribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collecting-service")
public class BarcodeController {
    private final BarcodeService barcodeService;
    private final PrescribeService prescribeService;

    @PostMapping("/barcode")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Map<String, Object>> newBarcode (@RequestBody Map<String, List<Object>> prescribeInfoList) {
        String result = barcodeService.createBarcode(prescribeInfoList);
        List<String> prescribeCodes = prescribeInfoList.get("prescribeCodeList").stream().map(item-> item.toString()).collect(Collectors.toList());
        List<Map<String,Object>> barcode = new ArrayList<>();
        barcode.add(Collections.singletonMap("message", result));

        barcode.addAll(barcodeService.getBarcodeList(prescribeCodes));
        if("create barcode successfully!".equals(result)) {
            prescribeService.updateStatus("B", prescribeCodes);
        }

        return barcode;
    }

    @PutMapping("/barcode")
    @Transactional
    public String cancelBarcode(@RequestBody Map<String, List<String>> barcodeListMap){
        List<String> prescribeCodeList = barcodeListMap.get("prescribeCodeList");

        if(prescribeService.getStatus(prescribeCodeList, "B").size() != prescribeCodeList.size()){
            return "바코드출력 상태가 아닌 바코드가 선택되었습니다";
        }

        String result = barcodeService.removeBarcode(barcodeListMap);

            prescribeService.updateStatus("X", barcodeListMap.get("prescribeCodeList"));
        prescribeCodeList.add(result);

        return result;
    }
}