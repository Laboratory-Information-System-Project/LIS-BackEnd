package com.douzone.lis_back.service.unsuitable;

import com.douzone.lis_back.domain.unsuitable.UnsuitableReasonDTO;
import com.douzone.lis_back.mapper.unsuitable.UnsuitableSampleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UnsuitableSampleServiceImpl implements UnsuitableSampleService {
    private final UnsuitableSampleMapper mapper;
    @Override
    public List<Map<String, Object>> getSampleInfo(Long barcode, String authority) {
        // error 메세지
        List<Map<String, Object>> listMap = new ArrayList<>();
        Map<String, Object> map = new HashMap<String,Object>();

        // 검체 정보 수집
        List<Map<String, Object>> sample = mapper.findSampleByBarcode(barcode, authority);
        if(sample.isEmpty()) {
            map.put("message", "존재하지 않는 바코드입니다.");
            listMap.add(map);
            return listMap;
        }
        return sample;
    }

    @Override
    public List<Map<String, Object>>  getPrescribeInfo(Long barcode, String authority) {
        List<Map<String, Object>>  listMap = new ArrayList<>();
        Map<String, Object> map = new HashMap<String,Object>();

        // 처방 정보 수집
        List<Map<String, Object>> prescribe = mapper.findPrescribeByBarcode(barcode, authority);
        if(prescribe.isEmpty()) {
            map.put("message", "존재하지 않는 바코드입니다.");
            listMap.add(map);
            return listMap;
        }
        return prescribe;

    }

    @Override
    public List<Map<String, Object>> getUsersInfo(String userName) {
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String,Object>();

        List<Map<String, Object>> users = mapper.findUsersByUsername(userName);
        if(users.isEmpty()) {
            map.put("message", "존재하지 않는 사용자입니다.");
            listMap.add(map);
            return listMap;
        }
        return users;
    }

    @Override
    public List<Map<String, Object>> getUnsuitInfo(Long barcode) {
        List<Map<String, Object>> unsuitSample = mapper.findUnsuitByBarcode(barcode);
        return unsuitSample;
    }

    @Override
    public List<UnsuitableReasonDTO> getUnsuitableReason() {
        return mapper.findAllUnsuitableReason();
    }

    @Override
    public void insertUnsuitableSample(List<Map<String, String>> unsuitInfo) {
        for(Map<String, String> item : unsuitInfo
        ) {
            mapper.insertUnsuitableSampleInfo(item);
        }
    }
}
