package com.douzone.lis_back.service.unsuitable;

import com.douzone.lis_back.domain.unsuitable.UnsuitableReasonDTO;

import java.util.List;
import java.util.Map;

public interface UnsuitableSampleService {
    List<Map<String, Object>> getSampleInfo(Long barcode, String authority);
    List<Map<String, Object>>  getPrescribeInfo(Long barcode, String authority);
    List<Map<String, Object>> getUsersInfo(String userName);
    List<Map<String, Object>> getUnsuitInfo(Long barcode);
    List<UnsuitableReasonDTO> getUnsuitableReason();
    void insertUnsuitableSample(List<Map<String, String>> unsuitInfo);
}
