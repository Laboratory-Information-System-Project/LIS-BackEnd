package com.douzone.lis_back.service.barcode;

import java.util.List;
import java.util.Map;

public interface BarcodeService {
    String createBarcode(Map<String, List<Object>> prescribeCodeList);

    List<Map<String, Object>> getBarcodeList(List<Object> prescribeCodeList);

    String removeBarcode(Map<String, List<Object>> barcodeList);

    List<Map<Object, Object>> getAll(List<String> prescribeCodeList);

    List<Map<String, Object>> getBarcodeByPrescribeCodeList(List<String> prescribeCodeList);
}
