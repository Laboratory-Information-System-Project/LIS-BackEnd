package com.douzone.lis_back.controller.Collecting;

import com.douzone.lis_back.service.collecting.CollectingService;
import com.douzone.lis_back.service.prescribe.PrescribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/collecting-service")
public class CollectingController {
    private final CollectingService collectingService;

    private final PrescribeService prescribeService;
    @PutMapping("/collecting")
    public List<String> collecting(@RequestBody Map<String, List<String>> prescribeCodeList) {

        log.info("{}", prescribeCodeList);


        List<String> prescribeCodes = new ArrayList<>();
        String result = collectingService.collect(prescribeCodeList);

        if("update success".equals(result)){
            prescribeCodes.add(result);
            prescribeCodes.addAll(prescribeCodeList.get("prescribeCodeList"));
            prescribeService.updateStatus("C", prescribeCodeList.get("prescribeCodeList"));

        }

        return prescribeCodes;
    }

    @PutMapping("/collecting/canceldate")
    public String cancelCollecting(@RequestBody Map<String, List<String>> barcodeListMap ) {
        List<String> prescribeCodeList = barcodeListMap.get("prescribeCodeList");
        if(prescribeService.getStatus(prescribeCodeList, "C").size() != prescribeCodeList.size()){
            return "채혈 상태가 아닌 바코드가 선택되었습니다";
        }

        String result = collectingService.removeCollectingInfo(barcodeListMap);


        prescribeService.updateStatus("B", prescribeCodeList);

        return result;

    }
}
