package com.douzone.lis_back.service.prescribe;

import com.douzone.lis_back.mapper.PrescribeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrescribeServiceImpl implements PrescribeService{

    private final PrescribeMapper prescribeMapper;

    @Override
    public List<Map<String, Object>> getPrescribeInfoByVisitNo(Long visitNo) {

        List<Map<String, Object>> prescribeInfo = prescribeMapper.findPrescribeByVisitNo(visitNo);
        prescribeInfo.forEach((data)-> {
            data.put("Bool", false);
            String time = data.get("prescribe_dt").toString();
            data.put("prescribe_dt",time.replace('T',' '));

        });

        return prescribeInfo;
    }

    @Override
    public Integer updateStatus(String status, List<Object> prescribeCodeList) {

        return prescribeMapper.update(prescribeCodeList, status);
    }

    @Override
    public List<String> getStatus(List<Object> prescribeCodeList, String status) {

        return prescribeMapper.findStatus(prescribeCodeList, status);
    }
}
