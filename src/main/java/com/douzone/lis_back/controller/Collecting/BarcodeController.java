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
        List<Object> prescribeCodes = prescribeInfoList.get("prescribeCodeList");
        List<Map<String,Object>> barcode = new ArrayList<>();
        barcode.add(Collections.singletonMap("message", result));

        if("create barcode successfully!".equals(result)) {
            prescribeService.updateStatus("B", prescribeCodes);
            barcode.add(Collections.singletonMap("status", "바코드출력"));
            barcode.addAll(barcodeService.getBarcodeList(prescribeCodes));
        }

        return barcode;
    }

    @PutMapping("/barcode")
    @Transactional
    public List<Map<String,Object>> cancelBarcode(@RequestBody Map<String, List<Object>> barcodeListMap){
        List<Object> prescribeCodeList = new ArrayList<>();
        barcodeListMap.get("prescribeCodeList").stream().forEach(item-> prescribeCodeList.add(Integer.parseInt(item.toString())));
        List<Map<String,Object>> forReturn = new ArrayList<>();

        if(prescribeService.getStatus(prescribeCodeList, "B").size() != prescribeCodeList.size()){
            forReturn.add(Collections.singletonMap("message","바코드출력 상태가 아닌 바코드가 선택되었습니다"));
            return forReturn;
        }

        List<Map<String,Object>> tmp = barcodeService.getBarcodeList(prescribeCodeList);

        String message = barcodeService.removeBarcode(barcodeListMap);
        forReturn.add(Collections.singletonMap("message", message));
        if("선택하신 바코드 발급이 취소되었습니다".equals(message)) {
            prescribeService.updateStatus("X", barcodeListMap.get("prescribeCodeList"));
            forReturn.add(Collections.singletonMap("status", "처방"));
            forReturn.addAll(tmp);
        }
        return forReturn;
    }
}