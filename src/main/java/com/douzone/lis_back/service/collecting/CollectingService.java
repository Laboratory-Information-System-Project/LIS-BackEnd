package com.douzone.lis_back.service.collecting;

import java.util.List;
import java.util.Map;

public interface CollectingService {
    String collect(Map<String, List<Object>> barcodeList);
    // List<String> getPrescribeCodeByBarcode(List<String> barcodeList);

    String removeCollectingInfo(Map<String, List<Object>> barcodeList);
}
