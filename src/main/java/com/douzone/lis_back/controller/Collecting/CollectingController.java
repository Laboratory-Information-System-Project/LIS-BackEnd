package com.douzone.lis_back.controller.Collecting;

import com.douzone.lis_back.service.collecting.CollectingService;
import com.douzone.lis_back.service.prescribe.PrescribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/collecting-service")
public class CollectingController {
    private final CollectingService collectingService;

    private final PrescribeService prescribeService;
    @PutMapping("/collecting")
    public List<Object> collecting(@RequestBody Map<String, List<Object>> prescribeCodeList) {

        log.info("{}", prescribeCodeList);

        List<Object> prescribeCodes = new ArrayList<>();
        String result = collectingService.collect(prescribeCodeList);

        prescribeCodes.add(result);
        if("update success".equals(result)){
            prescribeCodes.add("채혈");
            prescribeCodes.addAll(prescribeCodeList.get("prescribeCodeList"));
            prescribeService.updateStatus("C", prescribeCodeList.get("prescribeCodeList"));

        }

        return prescribeCodes;
    }

    @PutMapping("/collecting/canceldate")
    public List<Object> cancelCollecting(@RequestBody Map<String, List<Object>> barcodeListMap ) {
        List<Object> prescribeCodeList = new ArrayList<>();
        barcodeListMap.get("prescribeCodeList").stream().forEach(item-> prescribeCodeList.add(item));
        if(prescribeService.getStatus(prescribeCodeList, "C").size() != prescribeCodeList.size()){
            return Collections.singletonList("채혈 상태가 아닌 바코드가 선택되었습니다");
        }

        String result = collectingService.removeCollectingInfo(barcodeListMap);

        if("채혈이 취소되었습니다.".equals(result)) {
            prescribeService.updateStatus("B", prescribeCodeList);
            prescribeCodeList.add(0, result);
            prescribeCodeList.add(1, "바코드출력");
        }

        return prescribeCodeList;

    }
}
