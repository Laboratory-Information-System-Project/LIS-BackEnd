package com.douzone.lis_back.service.prescribe;

import java.util.List;
import java.util.Map;

public interface PrescribeService {

    List<Map<String, Object>> getPrescribeInfoByVisitNo(Long visitNo);

    Integer updateStatus(String status, List<String> prescribeCodeList);

    List<String> getStatus(List<String> prescribeCodeList, String c);
}
